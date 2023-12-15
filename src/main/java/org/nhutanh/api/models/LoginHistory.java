package org.nhutanh.api.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistory {

    private Long id;

    private Date loginTime;

    public User user;

}
