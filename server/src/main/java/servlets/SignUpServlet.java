package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nmikutskiy on 19.09.16.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (login == "" || pass == "") {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (accountService.getUserByLogin(login) != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Can not register with login: " + login);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        accountService.addNewUser(new UserProfile(login, pass, ""));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Registration complited. Please login!");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
