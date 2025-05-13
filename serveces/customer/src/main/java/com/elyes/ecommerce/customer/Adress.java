package com.elyes.ecommerce.customer;


import lombok.*;

import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Adress {
    private String street;
    private String houseNumber;
    private String zipCode;

}
