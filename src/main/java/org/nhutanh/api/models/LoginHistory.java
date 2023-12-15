package org.nhutanh.api.models;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginHistory {

    private Long id;

    private Date loginTime;

    public User user;

}
