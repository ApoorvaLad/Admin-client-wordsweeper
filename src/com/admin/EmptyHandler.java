package com.admin;

import com.admin.controller.ControllerChain;
import com.admin.xml.Message;

/**
 * Empty handler to trigger error for any non handled response
 * @author Apoorva
 *
 */
public final class EmptyHandler extends ControllerChain {

	@Override
	public boolean process(Message response) {
		System.err.println("Not Handled:" + response);
		return true;
	}

}
