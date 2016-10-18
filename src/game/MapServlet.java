package game;

import accounts.AccountService;
import accounts.UserProfile;

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

    public MapServlet (AccountService accountService){
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getSession().getId());
        if (profile == null) {
            response.setContentType("text/html; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }

}
