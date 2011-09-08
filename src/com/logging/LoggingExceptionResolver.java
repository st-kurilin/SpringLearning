package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Andrey Loboda
 * @date : 05.09.11
 */
public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(LoggingExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        log.error(ex.getMessage());
        log.error(getStackTrace(ex));
        return super.resolveException(request, response, handler, ex);
    }

    /**
     * @param throwable, Throwable
     * @return String of the exception
     */
    public String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

