package com.epam.web.rotten.potatoes.controller.context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestContext {
    private final Map<String, Object> requestAttributes;
    private final Map<String, String> requestParameters;
    private final Map<String, Object> sessionAttributes;
    private final HttpServletRequest req;

    /*package-private*/ RequestContext(Map<String, Object> requestAttributes, Map<String, String> requestParameters,
                                       Map<String, Object> sessionAttributes, HttpServletRequest req) {
        this.requestAttributes = requestAttributes;
        this.requestParameters = requestParameters;
        this.sessionAttributes = sessionAttributes;
        this.req = req;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public String getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void setRequestParameter(String key, String value) {
        requestParameters.put(key, value);
    }

    public void setRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    /*package-private*/ Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    /*package-private*/ Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    /*package-private*/ Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}
