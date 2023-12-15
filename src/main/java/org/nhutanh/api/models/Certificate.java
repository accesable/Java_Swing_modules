package org.nhutanh.api.models;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Certificate {


    private Long id;

    private String title;
    private String issuingOrganization;
    private Date issueDate;
    private String description;

    private Student student;

}
