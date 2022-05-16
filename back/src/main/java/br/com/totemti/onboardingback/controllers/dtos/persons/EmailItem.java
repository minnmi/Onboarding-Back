package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.EmailDefault;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class EmailItem {
    Long id;
    String email;
    EmailDefault emailDefault;
}
