package com.mamay.inspection.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.exception.CommandException;
import com.mamay.inspection.factory.ActionFactory;

/**
 * <p>
 * This class represents the main and only controller of the application.
 * <p>
 * For creating and determining command type we are using <b>Factory Method</b>
 * design pattern
 * 
 * @author Mamay Viktor
 * @since 12 november 2014
 * @version 1.0
 * 
 * @see javax.servlet.http.HttpServlet
 */

@WebServlet("/controller")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final ActionFactory factory = new ActionFactory();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * <p>
	 * method which processes both get and post requests
	 * 
	 * @throws CommandException
	 *             - when corresponding action returns null page
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		ActionCommand command = factory.defineCommand(request);
		String page = command.execute(request);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			String errorMsg = ActionHelper.findProperty(request, "message.nullPage");
			throw new CommandException(errorMsg);
		}
	}
}
