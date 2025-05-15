package com.elyes.ecommerce.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class BuisnessException extends RuntimeException {
    private final String msg ;
}
