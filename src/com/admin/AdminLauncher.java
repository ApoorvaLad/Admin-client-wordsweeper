package com.admin;


import com.admin.scorpio.controller.AdminMessageHandler;
import com.admin.scorpio.controller.ConnectResponseController;
import com.admin.scorpio.controller.GameListResponseController;
import com.admin.scorpio.controller.ShowGameStateResponseController;
import com.admin.xml.Message;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;

/** Code to launch the Admin */
public class AdminLauncher {

	public static final String serverHost = "localhost";

	public static void main(String[] args) {
		// Register the protocol
		if (!Message.configure("wordsweeper.xsd")) {
			System.exit(0);
		}

		// select dedicated server with '-server' options
		String host = "localhost";
		if (args.length > 0 && args[0].equals("-server")) {
			host = serverHost;
		}

		// Initialize the client application and its corresponding model

		Model model = new Model();

		Application app = Application.getInstance();

		// set up the chain of responsibility

		AdminMessageHandler handler = new AdminMessageHandler(app);
		handler.registerHandler(new ConnectResponseController(app, model));
		handler.registerHandler(new GameListResponseController(app, model));
		handler.registerHandler(new ShowGameStateResponseController(app,model));

		// try to connect to the server. Once connected, messages are going to
		// be processed by
		// SampleClientMessageHandler. For now we just continue on with the
		// initialization because
		// no message is actually sent by the connect method.
		ServerAccess sa = new ServerAccess(host, 11425);
		if (!sa.connect(handler)) {
			System.out.println("Unable to connect to server (" + host + "). Exiting.");
			System.exit(0);
		}
		System.out.println("Connected to " + host);

		// Should we on the client ever need to communicate with the server, we
		// need this ServerAccess
		// object.
		app.setServerAccess(sa);

		// send an introductory connect request now that we have created (but
		// not made visible!)
		// the GUI
		String xmlString = Message.requestHeader() + "<connectRequest/></request>";
		Message m = new Message(xmlString);
		sa.sendRequest(m);
		// /app.getRequestArea().append(m.toString() + "\n");

		// at this point, we need to make app visible, otherwise we would
		// terminate application
		app.setVisible(true);

	}
}
