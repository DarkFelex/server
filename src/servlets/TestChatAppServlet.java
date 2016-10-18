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
 * Created by nmikutskiy on 04.10.16.
 */
public class TestChatAppServlet extends HttpServlet {
    private final AccountService accountService;


    public TestChatAppServlet(AccountService accountService){
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

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("name", profile.getLogin());

        String path = request.getRequestURI().toString();
        switch (path) {
            case "/chat.js":
                response.getWriter().println(PageGenerator.instance().getPage("chat.js", pageVariables));
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/chat_app_agedan":
                response.getWriter().println(PageGenerator.instance().getPage("chat_app_agedan.html", pageVariables));
                response.setContentType("text/html; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                break;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        pageVariables.put("host", main.config.getHost());
        pageVariables.put("port", main.config.getChatPort());
        return pageVariables;
    }
}

