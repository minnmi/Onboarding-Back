package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.AddressDefault;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressItem {
    Long id;
    String place;
    String neighborhood;
    String cep;
    String city;
    String uf;
    AddressDefault addressDefault;
}
