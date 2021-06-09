package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class BuildUser {
    static final List<Snakeuser> userList;


    static {
        userList = new ArrayList<>();
        Snakeuser user1 = new Snakeuser();
        user1.setUsername("snake_1");
        user1.setPassword("123456");
        userList.add(user1);
    }

    public static Snakeuser login(Snakeuser user) {
        for (Snakeuser Luser : userList) {
            if (user.getUsername().equals(Luser.getUsername()) && user.getPassword().equals(Luser.getPassword())) {
                return Luser;
            }
        }
        return null;
    }

    public static Snakeuser getInfo() {
        for (Snakeuser Luser : userList) {
            return Luser;
        }
        return null;
    }
}