package com.admin.test.server;

import java.util.ArrayList;
import java.util.Hashtable;

import com.admin.IMessageHandler;
import com.admin.ServerAccess;
import com.admin.controller.IAdminController;
import com.admin.xml.Message;

public class MockServerAccess extends ServerAccess {
	/** Sent messages. */
	ArrayList<Message> sentMessages = new ArrayList<Message>();

	/** Special message requests to be process. */
	Hashtable<String, Message> waiting = new Hashtable<String, Message>();

	public MockServerAccess(String host) {
		super(host);
	}

	public MockServerAccess(String host, int port) {
		super(host, port);
	}

	/** To simulate connect, just grab onto callback handler object. */
	@Override
	public boolean connect(final IMessageHandler handler) {
		return true;
	}

	/** To simulate disconnect, clear out. */
	@Override
	public void disconnect() {

	}

	/** Mock server is true to its name. */
	public String getHost() {
		return "mockServer";
	}

	/** Requests are held onto so they can be inspected later. */
	public synchronized boolean sendRequest(Message r) {
		sentMessages.add(r);
		return true;
	}

	/** Requests to be processed specially are held separately. */
	public synchronized boolean sendRequest(IAdminController c, Message m) {
		waiting.put(m.id(), m);
		return true;
	}

	/** Get and clear sent Messages. */
	public ArrayList<Message> getAndClearMessages() {
		ArrayList<Message> al = sentMessages;
		sentMessages = new ArrayList<Message>();
		return al;
	}

	/** Get and clear waiting Messages. */
	public Hashtable<String, Message> getAndClearWaitingMessages() {
		Hashtable<String, Message> ht = new Hashtable<String, Message>();
		waiting.clear();
		return ht;
	}
}
