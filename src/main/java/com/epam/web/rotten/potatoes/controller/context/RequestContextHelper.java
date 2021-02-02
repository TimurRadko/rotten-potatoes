package com.epam.web.rotten.potatoes.controller.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestContextHelper {
    private static final Logger LOGGER = LogManager.getLogger(RequestContextHelper.class);
    private static final String COMMAND_PARAMETER = "command";
    private static final String ADMIN_ADD_FILM_COMMAND = "admin-add-film";
    private static final String ADMIN_EDIT_FILM_COMMAND = "admin-edit-film";
    private static final String POSTER_PARAMETER = "poster-path";
    private static final String PART_ATTRIBUTE = "part";
    private static final String SERVLET_CONTEXT_ATTRIBUTE = "servletContext";

    public RequestContext create(HttpServletRequest req) throws ServletException {
        HttpSession session = req.getSession();

        Enumeration<String> attributesName = req.getAttributeNames();
        Map<String, Object> requestAttributes = putAttributes(attributesName, req);

        Enumeration<String> parametersName = req.getParameterNames();
        Map<String, String> requestParameters = putParameters(parametersName, req);

        Enumeration<String> sessionAttributesName = session.getAttributeNames();
        Map<String, Object> sessionAttributes = putSessionAttributes(sessionAttributesName, session);

        return new RequestContext(requestAttributes, requestParameters, sessionAttributes);
    }

    private Map<String, Object> putAttributes(Enumeration<String> attributesName, HttpServletRequest req) throws ServletException {
        Map<String, Object> requestAttributes = new HashMap<>();
        while (attributesName.hasMoreElements()) {
            String name = attributesName.nextElement();
            Object attribute = req.getAttribute(name);
            requestAttributes.put(name, attribute);
        }
        putPartAndServletContextIntoRequestAttribute(req, requestAttributes);
        return requestAttributes;
    }

    private void putPartAndServletContextIntoRequestAttribute(HttpServletRequest req, Map<String, Object> requestAttributes) throws ServletException {
        String command = req.getParameter(COMMAND_PARAMETER);
        try {
            if (command.equalsIgnoreCase(ADMIN_ADD_FILM_COMMAND) || command.equalsIgnoreCase(ADMIN_EDIT_FILM_COMMAND)) {
                Part part = req.getPart(POSTER_PARAMETER);
                requestAttributes.put(PART_ATTRIBUTE, part);
                ServletContext servletContext = req.getServletContext();
                requestAttributes.put(SERVLET_CONTEXT_ATTRIBUTE, servletContext);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
        return sessionAttributes.get("invalidate") != null;
    }
}