package com.tags;

import com.domain.Role;
import com.domain.customer.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static com.util.StreamCloser.close;

/**
 * @author Stanislav Kurilin
 */
public class RolesEdit extends TagSupport {
    private String path;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int doStartTag() throws JspException {
        final JspWriter out = pageContext.getOut();
        try {
            final Collection<Role> all = Role.assignable();
            final Collection<Role> matched = matchedRoles();
            for (Role role : all) {
                out.print(String.format("<input type='checkbox' name='%s' value='%s' checked='%b'/><br/>",
                        path,
                        role.authority().getAuthority(),
                        matched.contains(role)));
                out.print(String.format("<input type='checkbox' name='%s' value='%s' checked='%b'/><br/>",
                        path,
                        role.authority().getAuthority(),
                        matched.contains(role)));
            }
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

//    @Override
//    protected int writeTagContent(TagWriter tagWriter) throws JspException {
//        final Collection<Role> all = Role.assignable();
//        final Collection<Role> matched = matchedRoles();
//        for (Role role : all) {
//            tagWriter.writeAttribute(role.authority().getAuthority(), role.authority().getAuthority());
//        }
//        return Tag.EVAL_BODY_INCLUDE;
//    }

    private Collection<Role> matchedRoles() {
        return user.isNew() ? Collections.<Role>emptySet() : user.getRoles();
    }
}
