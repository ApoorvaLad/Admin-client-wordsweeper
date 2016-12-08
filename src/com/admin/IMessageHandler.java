package com.admin;

import com.admin.xml.Message;

/**
 * 
 * @author Apoorva
 *
 */
public interface IMessageHandler {

	/** Process's the protocol response */
	void process(Message response);
}
