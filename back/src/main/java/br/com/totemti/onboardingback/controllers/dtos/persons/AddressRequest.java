package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.AddressDefault;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressRequest {
    private String cep;

    private String place;

    private String houseNumber;

    private String neighborhood;

    private String city;

    private String uf;

    private AddressDefault addressDefault;
}
