package seminars.third.coverage.hw;

import seminars.third.coverage.User;

import java.util.ArrayList;
import java.util.List;

//Task 4
public class UserRepository {
    private List<User> users = new ArrayList<>();
    public List<User> getUsers() {
        return users;
    }
    public boolean addUser(User user){
        if (user.isAuthenticate()) {
            users.add(user);
            return true;
        }
        System.out.println("User is not Authenticate");
        return false;
    }

    public boolean logoutAllIfNotAdmin() {
        boolean flag = false;
        for (User user: users) {
            if(!user.isAdmin()){
                user.setAuthenticate(false);
                flag = true;
            }
        }
        return flag;
    }
}
