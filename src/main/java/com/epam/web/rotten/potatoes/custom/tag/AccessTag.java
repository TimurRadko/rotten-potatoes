package com.epam.web.rotten.potatoes.custom.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class AccessTag extends TagSupport {
    private static final String RIGHTS_ATTRIBUTE = "rights";
    private static final String USER = "user";
    private String accessName;

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String rights = (String) session.getAttribute(RIGHTS_ATTRIBUTE);
        if (rights == null) {
            return SKIP_BODY;
        }
        if (rights.equalsIgnoreCase(accessName)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
