package org.nhutanh.api.services;

import org.nhutanh.api.models.LoginHistory;
import org.nhutanh.api.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.Date;

public class UserService extends UserDaoImpl{

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private LoginHistoryService loginHistoryService;
    public UserService(){
        loginHistoryService = new LoginHistoryService();
    }
    public boolean login(String username,String password){
        User user = getUserByUsername(username);

        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // User authentication successful, add login history
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setUser(user);
            loginHistory.setLoginTime(new Date()); // Set the current time as login time
            loginHistoryService.addHistory(loginHistory);
            return true;
        }
        return false;
    }

    public boolean registerUser(String username,String password){
        User user = new User(username,bCryptPasswordEncoder.encode(password));
        try {
            this.addUser(user);
        }catch(RuntimeException e){
            return false;
        }
        return true;
    }
}
