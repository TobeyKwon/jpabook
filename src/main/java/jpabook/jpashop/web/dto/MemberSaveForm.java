package jpabook.jpashop.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveForm {

    @NotBlank
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
