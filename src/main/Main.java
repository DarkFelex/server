package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.UsersServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import chat.WebSocketChatServlet;

/**
 * Created by nmikutskiy on 18.09.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        /**
         * Запрос, который пришёл на указанный ресурс будет собран в объеки
         * и передал в указанный сервлет.
         *
         * Получает объект request, в которомо будут параметры с которыми пользователь сделал запрос.
         * Отдаст объект response, который пользователь получит и отобразит как страницу.
         *
         */
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");


        //Для работы со статическими ресурсами
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        /**
         * Важен порядок передачи в handlers.setHandlers
         * 1) Сперва будет обрабатываться запрос к статике - resource_handler
         * 2) Потом к сервлетам - context
         */
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
