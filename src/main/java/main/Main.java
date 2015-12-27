package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.FrontEnd;

public class Main {
    public static final int PORT = 8080;
    public static void main(String[] args) {
        FrontEnd frontEnd = new FrontEnd();

        Server server = new Server(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(frontEnd), "/authform");

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}