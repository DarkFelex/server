package accounts;

/**
 * Created by nmikutskiy on 19.09.16.
 */
public class UserProfile {
    private final String login;
    private final String pass;
    private final String email;
    private int gold = 300;
    private int score = 10000;

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public int getGold() {
        return gold;
    }

    public int getScore() {
        return score;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
