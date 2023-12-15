package org.nhutanh.api.services;

import org.nhutanh.api.dao.UserDao;
import org.nhutanh.api.models.LoginHistory;
import org.nhutanh.api.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserService {

    private final UserDao userDao; // Use interface rather than direct implementation
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final LoginHistoryService loginHistoryService;

    // Constructor with dependency injection (can be achieved using Spring or manual wiring)
    public UserService() {
        this.userDao = new UserDaoImpl();
        this.loginHistoryService = new LoginHistoryService();
    }

    public boolean login(String username, String password) {
        User user = userDao.getUserByUsername(username); // Assuming this method exists in UserDao

        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setUser(user);
            loginHistory.setLoginTime(new Date());
            loginHistoryService.addHistory(loginHistory);
            return true;
        }
        return false;
    }

    public User getUserById(int id){
        return this.userDao.getUserById(id);
    }
    public List<User> getAllUsers(){
        return this.userDao.getAllUsers();
    }
    public void updateUser(User user){
        this.userDao.updateUser(user);
    }
    public void deleteUser(int id){
        this.userDao.deleteUser(id);
    }
    public User getUserByUsername(String username){
        return this.userDao.getUserByUsername(username);
    }




    public boolean registerUser(String username, String password) {
        User user = new User(username, bCryptPasswordEncoder.encode(password));
        try {
            userDao.addUser(user);
        } catch (RuntimeException e) { // Replace with a specific data access exception
            // Log exception details here
            return false;
        }
        return true;
    }

    // Additional methods...
}

