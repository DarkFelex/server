package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import base.GameService;
import game.Village;
import game.units.Units;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

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
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String count = request.getParameter("count");
        String areaNumber = request.getParameter("area_number");
        String barraksLocation = request.getParameter("barraks_location");

        if (x == null || x.isEmpty() ||
                y == null || y.isEmpty() ||
                areaNumber == null || areaNumber.isEmpty()) return;

        response.setContentType("text/html;charset=utf-8");

        switch (path) {
            case "/api/v1/village/get":
                // TODO: get village by user name
                break;
            case "/api/v1/village/build/palace":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.buildPalace(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/v1/village/build/warehouse":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.buildWarehouse(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/v1/village/build/farm":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.buildFarm(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/v1/village/build/barracks":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.buildBarracks(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/v1/village/build/woodfactory":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.buildWoodFactory(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/v1/village/build/upgrade":
                if (!checkAvailableToBuild(parseInt(x), parseInt(y), profile, parseInt(areaNumber)))
                    return;
                gameService.upgradeBuilding(parseInt(x), parseInt(y), parseInt(areaNumber));
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/api/v1/village/army/create/spearman":
                if (!checkAvailableToCreateUnits(parseInt(x), parseInt(y), parseInt(count), profile))
                    return;
                createUnit(parseInt(x), parseInt(y), profile, parseInt(count), parseInt(barraksLocation));
                response.setStatus(HttpServletResponse.SC_OK);
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Something go wrong");
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

                Village village = createVillage(parseInt(x), parseInt(y), profile, villageName);
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
        //проверяем: 1) достаточно ресурсов
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

    private void createUnit(int x, int y, UserProfile ownerUser, int count, int barraksLocation){
        gameService.createSpearman(x, y, count, barraksLocation, ownerUser.getLogin());
    }

    private boolean checkAvailableToBuild(int x, int y, UserProfile userProfile, int areaNumber){
        /*
        1. проверка что на этой клетке есть деревня вообще
        2. проверка что юзер строит в своей деревне
        3. проверка что клетка для строительства не занята другим зданием
        TODO: 3. проверка, что достаточно ресурсов
        * */
        Village village = gameService.getRegionOnTheMap(x, y).getVillage();
        if (village == null) {
            System.out.println("Ошибка при попытке построит здание: на указанной клетке нет деревни");
            return false;
        }
        if (village.getOwnerUser() != userProfile.getLogin()) {
            System.out.println("Ошибка при попытке построит здание: попытка построить здание в чужой деревне");
            return false;
        }
        if (village.getAreaForBuildings().get(areaNumber) != null) {
            System.out.println("Ошибка при попытке построит здание: попытка постотить здание на занятой клетке");
            return false;
        }

        return true;
    }

    private boolean checkAvailableToCreateUnits(int x, int y, int count, UserProfile userProfile){
        Village village = gameService.getRegionOnTheMap(x, y).getVillage();
        if (village == null) {
            System.out.println("Ошибка при попытке построит здание: на указанной клетке нет деревни");
            return false;
        }
        if (village.getOwnerUser() != userProfile.getLogin()) {
            System.out.println("Ошибка при попытке построит здание: попытка построить здание в чужой деревне");
            return false;
        }
        if (count < 0) {
            return false;
        }
        return true;
    }
}