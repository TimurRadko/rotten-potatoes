package com.epam.web.rotten.potatoes.controller.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContextHelper {
    private static final String INVALIDATE_ATTRIBUTE = "invalidate";

    public RequestContext create(HttpServletRequest req) {
        HttpSession session = req.getSession();

        Enumeration<String> attributesName = req.getAttributeNames();
        Map<String, Object> requestAttributes = putAttributes(attributesName, req);

        Enumeration<String> parametersName = req.getParameterNames();
        Map<String, String> requestParameters = putParameters(parametersName, req);

        Enumeration<String> sessionAttributesName = session.getAttributeNames();
        Map<String, Object> sessionAttributes = putSessionAttributes(sessionAttributesName, session);

        return new RequestContext(requestAttributes, requestParameters, sessionAttributes);
    }

    private Map<String, Object> putAttributes(Enumeration<String> attributesName, HttpServletRequest req) {
        Map<String, Object> requestAttributes = new HashMap<>();
        while (attributesName.hasMoreElements()) {
            String name = attributesName.nextElement();
            Object attribute = req.getAttribute(name);
            requestAttributes.put(name, attribute);
        }
        requestAttributes.put("req", req);
        return requestAttributes;
    }

    private Map<String, String> putParameters(Enumeration<String> parametersName, HttpServletRequest req) {
        Map<String, String> requestParameters = new HashMap<>();
        while (parametersName.hasMoreElements()) {
            String name = parametersName.nextElement();
            String parameter = req.getParameter(name);
            requestParameters.put(name, parameter);
        }
        return requestParameters;
    }

    private Map<String, Object> putSessionAttributes(Enumeration<String> sessionAttributesName, HttpSession session) {
        Map<String, Object> sessionAttributes = new HashMap<>();
        while (sessionAttributesName.hasMoreElements()) {
            String name = sessionAttributesName.nextElement();
            Object parameter = session.getAttribute(name);
            sessionAttributes.put(name, parameter);
        }
        return sessionAttributes;
    }

    public void updateRequest(HttpServletRequest req, RequestContext requestContext) {
        Map<String, Object> requestAttributes = requestContext.getRequestAttributes();
        Map<String, Object> sessionAttributes = requestContext.getSessionAttributes();
        updateRequestAttributes(requestAttributes, req);
        updateSessionAttributes(sessionAttributes, req);
    }

    private void updateRequestAttributes(Map<String, Object> requestAttributes, HttpServletRequest req) {
        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            String key = attribute.getKey();
            Object value = requestAttributes.get(key);
            req.setAttribute(key, value);
        }
    }

    private void updateSessionAttributes(Map<String, Object> sessionAttributes, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (isInvalidateSession(sessionAttributes)) {
            session.invalidate();
        } else {
            for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
                String key = attribute.getKey();
                Object value = sessionAttributes.get(key);
                session.setAttribute(key, value);
            }
        }
    }

    private boolean isInvalidateSession(Map<String, Object> sessionAttributes) {
        return sessionAttributes.get(INVALIDATE_ATTRIBUTE) != null;
    }
}