package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import base.GameService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nmikutskiy on 18.09.16.
 */
public class Frontend extends HttpServlet {
    private final AccountService accountService;
    private final GameService gameService;

    public Frontend(AccountService accountService, GameService gameService){
        this.accountService = accountService;
        this.gameService = gameService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getSession().getId());
        if (profile == null){
            response.setContentType("text/html; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("sessionId", request.getSession().getId());

        String path = request.getRequestURI().toString();
        if (path.contains("/images/map/region/")){
            List<String> imgName = Stream.of(path.split("/")).filter(s -> s.contains("-")).collect(Collectors.toList());
            System.out.println(imgName);
            pageVariables.put("regionId", "1");
            pageVariables.put("imgName", imgName.get(0));

            response.setContentType("text/html; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PageGenerator.instance().getPage("map_place_img.html", pageVariables));
            return;
        }

        if (path.contains("/api/v1/game/current_time")){
            response.setContentType("text/html; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().printf("{\"current game time\": \"%d\"}", gameService.getCurrentServerTimeInSeconds());
        }

        switch (path){
            case "/views/chat.html":
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(PageGenerator.instance().getPage("views/chat.html", pageVariables));
                break;
            case "/views/city.html":
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(PageGenerator.instance().getPage("views/city.html", pageVariables));
                break;
            case "/views/main.html":
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(PageGenerator.instance().getPage("views/main.html", pageVariables));
                break;
            case "/views/map.html":
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(PageGenerator.instance().getPage("views/map.html", pageVariables));
                break;
            case "/views/old.html":
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(PageGenerator.instance().getPage("views/old.html", pageVariables));
                break;
            default:
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }
}
