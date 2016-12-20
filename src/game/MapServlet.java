package game;

import accounts.AccountService;
import accounts.UserProfile;
import base.GameService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nmikutskiy on 18.10.16.
 */
public class MapServlet extends HttpServlet {
    private final AccountService accountService;
    private final GameService gameService;

    public MapServlet (AccountService accountService, GameService gameService){
        this.accountService = accountService;
        this.gameService = gameService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getSession().getId());
        if (profile == null) {
            response.setContentType("text/html; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String path = request.getRequestURI().toString();
        switch (path) {
            case "/api/v1/get_region":
                //get single square from map
                String x = request.getParameter("x");
                String y = request.getParameter("y");
                if (x == null || x.isEmpty() ||
                        y == null || y.isEmpty()) break;
                Place place = gameService.getRegionOnTheMap(Integer.parseInt(x), Integer.parseInt(y));
                if (place == null) {
                    System.out.println("Error: place = null");
                    break;
                }
                Gson gson = new Gson();
                String json = gson.toJson(place);

                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(json);
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/api/v1/get_region/all":
                // TODO: get reqion
                break;
            default:
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Error: path " + path);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

}
