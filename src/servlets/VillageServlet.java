package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import base.GameService;
import game.Village;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nmikutskiy on 04.10.16.
 */
public class VillageServlet extends HttpServlet {
    private final AccountService accountService;
    private final GameService gameService;

    public VillageServlet(AccountService accountService, GameService gameService){
        this.accountService = accountService;
        this.gameService = gameService;
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getSession().getId());
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
            return;
        }

        String path = request.getRequestURI().toString();
        switch (path) {
            case "/api/v1/village/get":
                // TODO: get village by user name
                break;
            default:
                // TODO: send error response
                break;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getSession().getId());
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
            return;
        }

        String path = request.getRequestURI().toString();
        switch (path) {
            case "/api/v1/village/create":
                String x = request.getParameter("x");
                String y = request.getParameter("y");
                String villageName = request.getParameter("village_name");

                if (x == null || x.isEmpty() ||
                        y == null || y.isEmpty() ||
                        villageName == null || villageName.isEmpty()) break;

                Village village = createVillage(Integer.parseInt(x), Integer.parseInt(y), profile, villageName);
                if (village == null) break;

                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            default:
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Something go wrong");
                break;
        }

    }

    private Village createVillage(int x, int y, UserProfile ownerUser, String villageName){
        //проверяем: 1) достаточно ресурсов 2) на клетке можно строить
        if (ownerUser.getScore() >= 10000 && ownerUser.getGold() >= 300){
            ownerUser.setGold(ownerUser.getGold() - 300);
            ownerUser.setScore(ownerUser.getScore() - 10000);
            return gameService.createVillage(x, y, ownerUser.getLogin(), villageName);
        }
        else {
            System.out.println("Can not create village: score or gold is missing");
            return null;
        }
    }
}