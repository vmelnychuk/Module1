package main;

import bean.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.AccountService;
import service.AccountSessionImpl;
import servlet.FrontEnd;
import servlet.SignInServlet;
import servlet.SignUpServlet;

public class Main {
    public static final int PORT = 8080;
    public static final String SIGN_UP = "/signup";
    public static final String SIGN_IN = "/signin";
    public static void main(String[] args) {
        AccountService accountService = new AccountSessionImpl();

        accountService.addUser(new UserProfile("admin"));
        accountService.addUser(new UserProfile("test"));


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), SIGN_UP);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), SIGN_IN);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(PORT);
        server.setHandler(handlers);

        try {
            server.start();
            System.out.println("Server started");
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
