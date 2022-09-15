package com.example.api.purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CreditCardDTO {
    private @NotEmpty String cardNumber;
}
