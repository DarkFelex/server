package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nmikutskiy on 19.10.16.
 */
public class SignOutServlet extends HttpServlet {
    private final AccountService accountService;

    private String sessionId;

    public SignOutServlet(AccountService accountService){
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        sessionId = request.getSession().getId();

        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.deleteSession(sessionId);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
