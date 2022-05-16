package br.com.totemti.onboardingback.controllers.dtos.persons;


import br.com.totemti.onboardingback.enums.PhoneDefault;
import br.com.totemti.onboardingback.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneItem {
    Long id;
    String phone;
    PhoneType phoneType;
    PhoneDefault phoneDefault;

}
