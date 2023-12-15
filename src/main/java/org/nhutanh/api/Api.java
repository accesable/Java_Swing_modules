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
            UserService userService = new UserService();

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
