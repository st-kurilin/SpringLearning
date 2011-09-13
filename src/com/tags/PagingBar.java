package com.tags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Stanislav Kurilin
 */
public class PagingBar extends TagSupport {
    private final static int range = 2;
    private Page page;
    private String root;

    public void setPage(Page page) {
        this.page = page;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public int doStartTag() throws JspException {
        try {
            final int current = page.getNumber();
            final int previous = current - 1;
            final int next = current + 1;
            final int total = page.getTotalPages();
            final int minAvailable = 0;
            final int maxAvailable = total - 1;
            final int minDisplayable = Math.max(current - range, minAvailable);
            final int maxDisplayable = Math.min(maxAvailable, current + range);
            final boolean fastDownLinkDisplay = current - minAvailable > 2;
            final boolean fastForwardLinkDisplay = maxAvailable - current > 2;

            final StringBuilder builder = new StringBuilder(500);
            final PageLinkFactory pageLinkFactory = new PageLinkFactory(root, retrievePrefix(page));

            fastDownLink(fastDownLinkDisplay, minAvailable, pageLinkFactory, builder);
            previousPageLink(!page.isFirstPage(), previous, pageLinkFactory, builder);
            previousPages(minDisplayable, current, pageLinkFactory, builder);
            currentPage(current, pageLinkFactory, builder);
            nextPages(maxDisplayable, current, pageLinkFactory, builder);
            nextPageLink(!page.isLastPage(), next, pageLinkFactory, builder);
            fastForwardPageLink(fastForwardLinkDisplay, maxAvailable, pageLinkFactory, builder);

            pageContext.getOut().print(builder.toString());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

    private static String retrievePrefix(Page page) {
        final StringBuilder builder = new StringBuilder(100);
        for (Sort.Order order : page.getSort()) {
            //should be one ore zero times in current impl
            builder
                    .append("sortBy=").append(order.getProperty())
                    .append("&")
                    .append("direction=").append(order.getDirection());
        }
        return builder.toString();
    }

    private static void fastDownLink(boolean display, int minAvailable, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        if (display) {
            pageLinkFactory.appendTo("<<", minAvailable, builder);
        }
    }

    private static void previousPageLink(boolean display, int previous, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        if (display) {
            pageLinkFactory.appendTo("<", previous, builder);
        }
    }

    private static void previousPages(int minDisplayable, int current, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        for (int i = minDisplayable; i < current; i++) {
            pageLinkFactory.appendTo(i, builder);
        }
    }

    private static void currentPage(int current, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        pageLinkFactory.appendTo(current, builder);
    }

    private static void nextPages(int maxDisplayable, int current, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        for (int i = current + 1; i <= maxDisplayable; i++) {
            pageLinkFactory.appendTo(i, builder);
        }
    }

    private static void nextPageLink(boolean display, int next, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        if (display) {
            pageLinkFactory.appendTo(">", next, builder);
        }
    }

    private void fastForwardPageLink(boolean display, int maxDisplayable, PageLinkFactory pageLinkFactory, StringBuilder builder) {
        if (display) {
            pageLinkFactory.appendTo(">>", maxDisplayable, builder);
        }
    }


    private final static class PageLinkFactory {
        private final String prefix;
        private final String postfix;

        public PageLinkFactory(String prefix, String postfix) {
            this.prefix = prefix;
            this.postfix = postfix;
        }

        public void appendTo(String label, int number, StringBuilder builder) {
            builder
                    .append("&nbsp;<a href=")
                    .append('"')
                    .append(prefix)
                    .append("?page=")
                    .append(number)
                    .append("&")
                    .append(postfix)
                    .append('"')
                    .append(">")
                    .append(label)
                    .append("</a>&nbsp;");
        }

        public void appendTo(int number, StringBuilder builder) {
            appendTo(Integer.toString(number), number, builder);
        }
    }
}
