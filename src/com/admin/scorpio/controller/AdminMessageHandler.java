package com.admin.scorpio.controller;

import com.admin.EmptyHandler;
import com.admin.IMessageHandler;
import com.admin.xml.Message;
import com.admin.scorpio.view.Application;

/**
 * Handles the chain of responsibility for response handling
 * 
 * @author Apoorva
 *
 */
public class AdminMessageHandler implements IMessageHandler {

	Application app;
	ControllerChain chain = new EmptyHandler();

	/**
	 * Register new controller chain as occuring before existing chain.
	 */
	public void registerHandler(ControllerChain handler) {
		handler.next = chain;
		chain = handler;
	}

	public AdminMessageHandler(Application app) {
		this.app = app;
	}

	@Override
	public void process(Message response) {
		chain.process(response);

	}

}
