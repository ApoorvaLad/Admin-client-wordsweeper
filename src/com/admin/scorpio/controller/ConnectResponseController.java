package com.admin.scorpio.controller;

import com.admin.xml.Message;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;

/**
 * Connect Response Controller - This class deals with connect response received
 * from the server.
 * 
 * @author Apoorva
 *
 */
public class ConnectResponseController extends ControllerChain {
	public Application app;
	public Model model;

	public ConnectResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("connectResponse")) {
			return next.process(response);
		}

		return true;
	}

}
