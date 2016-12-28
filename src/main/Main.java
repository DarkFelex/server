package main;

import accounts.AccountService;
import accounts.UserProfile;
import base.GameService;
import chat.WebSocketChatServlet;
import dbService.DBException;
import base.DBService;
import dbService.DBServiceImpl;
import dbService.dataSets.UsersDataSet;
import game.GameServiceImpl;
import game.Map;
import game.MapServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ChatServlet;
import servlets.Frontend;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignOutServlet;
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
            long userId = dbService.addUser("user"); // юзер в базе
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

//            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }// TODO: Нужно закрывать работу с базой!

        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("test")); //будет затераться при рестарте

        GameService gameService = new GameServiceImpl();
        gameService.createCleanMap("FirstMap", 30, 15).fillRandomPlaces();
        gameService.getRegionOnTheMap(1,2).enableVillageBuildingOnPlace();

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
        context.addServlet(new ServletHolder(new VillageServlet(accountService, gameService)), "/api/v1/village/*");
        context.addServlet(new ServletHolder(new MapServlet(accountService, gameService)), "/api/v1/get_region");

        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignOutServlet(accountService)), "/signout");

        context.addServlet(new ServletHolder(new Frontend(accountService)), "/views/*");
        context.addServlet(new ServletHolder(new Frontend(accountService)), "/images/map/region/*");

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

        Server server = new Server(config.getPort());
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
