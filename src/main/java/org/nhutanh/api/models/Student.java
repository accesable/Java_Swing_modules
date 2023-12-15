package org.nhutanh.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;

    private String fullName;
    private String phoneNumber;
    private int age;
    private List<Certificate> certificates;
}
