package snakeGame;


public class Snakeuser {
    String userName;
    String passWord;

    public Snakeuser() {
    }

    public Snakeuser(int uid, String username, String password) {
        this.userName = username;
        this.passWord = password;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }
}
