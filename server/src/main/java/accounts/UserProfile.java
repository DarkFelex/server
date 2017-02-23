package accounts;

import java.util.concurrent.atomic.AtomicInteger;

import static accounts.UserProfile.AccountType.USER;

/**
 * Created by nmikutskiy on 19.09.16.
 */
public class UserProfile {
    private final String login;
    private final String pass;
    private final String email;
    private AccountType userGroup = USER;
    private AtomicInteger gold = new AtomicInteger(300);
    private AtomicInteger score = new AtomicInteger(10000);
    //todo: добавить список деревень пользователя

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

    public enum AccountType {
        USER,
        ADMIN,
        TESTER,
        DEVELOPER;
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
        return gold.get();
    }

    public int getScore() {
        return score.get();
    }

    public void setGold(int gold) {
        this.gold.getAndSet(gold);
    }

    public void setScore(int score) {
        this.score.getAndSet(score);
    }

    public AccountType getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(AccountType userGroup) {
        this.userGroup = userGroup;
    }
}
