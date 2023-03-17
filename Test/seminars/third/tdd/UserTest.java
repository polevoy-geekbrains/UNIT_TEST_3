package seminars.third.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.third.coverage.User;
import seminars.third.coverage.hw.UserRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    /**
     * 3.6. Нужно написать класс User (пользователь) с методом аутентификации по логину и паролю,
     * (В метод передаются логин и пароль, метод возвращает true, если пароль и логин совпадают, иначе - false)
     */
    @Test
    void userCreation() {
        User user = new User("lgn", "qwerty");
        String login = "lgn";
        String pass = "qwerty";
        assertTrue(user.auth(login, pass));
    }
   //HomeWork 3
    private UserRepository repository;
    private User user,user1,user2;
    @BeforeEach
    void init(){
        repository = new UserRepository();
        user = new User("lgn", "qwerty");
        user1 = new User("user1", "qwerty");
        user2 = new User("user2", "qwerty");
    }
    // Test for Task 4
    @Test
    void addAuthenticatedUserToList(){
        user.setAuthenticate(true);

        assertTrue(repository.addUser(user));
    }
    @Test
    void addNotAuthenticatedUserToList(){
        UserRepository repository = new UserRepository();
        User user = new User("lgn", "qwerty");

        assertFalse(repository.addUser(user));
    }
    // Tests for Task 5
    @Test
    void logoutIfNotAdmin(){
        user1.setAuthenticate(true);
        user2.setAuthenticate(true);
        user1.setAdmin(true);
        repository.addUser(user1);
        repository.addUser(user2);

        assertTrue(repository.logoutAllIfNotAdmin());
    }
    @Test
    void tryLogoutIfAllAdmins(){
        user1.setAuthenticate(true);
        user2.setAuthenticate(true);
        user1.setAdmin(true);
        user2.setAdmin(true);
        repository.addUser(user1);
        repository.addUser(user2);

        assertFalse(repository.logoutAllIfNotAdmin());
    }

}