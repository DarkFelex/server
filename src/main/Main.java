package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;

/**
 * Created by nmikutskiy on 18.09.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        /**
         * Запрос, который пришёл на указанный ресурс будет собран в объеки
         * и передал в указанный сервлет.
         *
         * Получает объект request, в которомо будут параметры с которыми пользователь сделал запрос.
         * Отдаст объект response, который пользователь получит и отобразит как страницу.
         *
         */
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
