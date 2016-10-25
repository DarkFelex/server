package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nmikutskiy on 18.09.16.
 */
public class Frontend extends HttpServlet {
    private final AccountService accountService;
    public Frontend(AccountService accountService){
        this.accountService = accountService;
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
