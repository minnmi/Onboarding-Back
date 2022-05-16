package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.PhoneDefault;
import br.com.totemti.onboardingback.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneRequest {
    private String phone;

    private PhoneDefault phoneDefault;

    private PhoneType phoneType;


}
