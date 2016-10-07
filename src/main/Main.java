package main;

import accounts.AccountService;
import accounts.UserProfile;
import chat.WebSocketChatServlet;
import dbService.DBException;
import base.DBService;
import dbService.DBServiceImpl;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ChatServlet;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.TestChatAppServlet;
import servlets.UsersServlet;
import servlets.VillageServlet;

/**
 * Created by nmikutskiy on 18.09.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        config.loadProperties();

        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();
        try {
            long userId = dbService.addUser("user");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

//            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }// TODO: Нужно закрывать работу с базой!

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
        context.addServlet(new ServletHolder(new VillageServlet(accountService)), "/api/v1/create_village");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new ChatServlet(accountService)), "/chat_app");
        context.addServlet(new ServletHolder(new TestChatAppServlet(accountService)), "/chat_app_agedan");
        context.addServlet(new ServletHolder(new TestChatAppServlet(accountService)), "/chat.js");

        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
//        todo: добавить вебсокет для системных сообщений

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

        Server server = new Server(8081);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
