package org.nhutanh.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;

    private String fullName;
    private String phoneNumber;
    private int age;
    private List<Certificate> certificates;
}
