package servlets;

import accounts.AccountService;
import accounts.UserProfile;
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

    public VillageServlet(AccountService accountService){
        this.accountService = accountService;
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

        String path = request.getPathInfo();
        switch (path) {
            case "/api/v1/create_village":
                // TODO: create new user village on the map
                break;
            case "/api/v1/get_village":
                // TODO: get village by user name
                break;
            default:
                // TODO: send error response
                break;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }

    private Village createVillage(int x, int y, String ownerUser, String villageName){
        // TODO: проверить: 1) достаточно ресурсов 2) на клетке можно строить
        // TODO: записать в базу данных на карте
        return new Village(x, y, ownerUser, villageName);
    }
}