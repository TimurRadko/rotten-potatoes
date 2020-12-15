package com.epam.web.rotten.potatoes.controller;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandFactory;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.connection.ConnectionPool;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.controller.context.RequestContextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilmController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FilmController.class);
    private static final String COMMAND_PARAMETER = "command";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_PAGE = "WEB-INF/views/error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestContextHelper requestContextHelper = new RequestContextHelper();
        RequestContext requestContext = requestContextHelper.create(req);
        try {
            String commandParam = req.getParameter(COMMAND_PARAMETER);
            Command command = CommandFactory.create(commandParam);
            CommandResult commandResult = command.execute(requestContext);
            requestContextHelper.updateRequest(req, requestContext);
            dispatch(commandResult, req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            req.setAttribute(ERROR_MESSAGE, e.getMessage());
            dispatch(CommandResult.redirect(ERROR_PAGE), req, resp);
        }
    }

    private void dispatch(final CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean redirect = commandResult.isRedirect();
        String page = commandResult.getCommand();
        if (redirect) {
            resp.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool instance = ConnectionPool.getInstance();
        instance.closeConnections();
        super.destroy();
    }
}
