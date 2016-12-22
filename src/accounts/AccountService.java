package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nmikutskiy on 19.09.16.
 */
public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>(); //временно вместо BD
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        System.out.println(String.format("addNewUser: %s", userProfile.getLogin()));
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
        System.out.println(String.format("addSession: %s:%s", userProfile.getLogin(), sessionId));
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
        System.out.println(String.format("deleteSession: %s", sessionId));
    }

    public boolean checkActiveSession(String sessionId){
        System.out.println(sessionIdToProfile.get(sessionId));
        if (sessionIdToProfile.get(sessionId) == null){
            return false;
        }
        return true;
    }

    public void getAllActiveUsers(){
//TODO: ручка для просмотра всех активных пользователей
    }

    public void getAllActiveSessions(){
//TODO: админская ручка для просмотра всех активных сессий
    }
}