package org.nhutanh.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private int id;

    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean status ;
    private String imageUrl;
    private int age;
    private String role;
    private List<LoginHistory> loginHistories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
