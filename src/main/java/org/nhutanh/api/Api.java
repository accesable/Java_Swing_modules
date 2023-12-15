/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.nhutanh.api;


import org.nhutanh.api.dao.UserDao;
import org.nhutanh.api.models.User;
import org.nhutanh.api.services.UserDaoImpl;
import org.nhutanh.api.services.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;

/**
 *
 * @author trann
 */
public class Api {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
            UserDao userDAO = new UserDaoImpl();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = new User("trannhutanh@admin.com",bCryptPasswordEncoder.encode("trannhutanh"));
//            userDAO.addUser(user);

//            System.out.println(userDAO.getUserById(2).getUsername());
//            System.out.println(bCryptPasswordEncoder.matches("trannhutanh",userDAO.getUserById(3).getPassword()));
//            System.out.println(userDAO.getUserById(2).getFullName());
            UserService userService = new UserService();
//            userService.registerUser("admin@gmail.com","admin");
            System.out.println("Update user");
            User user1 = new User();
            user1.setId(4);
//            user1.setFullName("Quan Tri Vien 23");
//            user1.setAge(20);
            user1.setImageUrl("My-Profile.img");
            user1.setStatus(true);
            userService.updateUser(user1);
            System.out.println("User Updated");
            if (userService.login("trannhutanh@admin.com","trannhutanh")){
                System.out.println("Login Success");
            }else {
                System.out.println("Login Failed");
            }
        }catch(Exception e){
            System.out.println(e);
        }
           
    }
}
