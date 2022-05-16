package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.PersonStatus;
import br.com.totemti.onboardingback.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class PersonQueryParameter {

    private PersonType type;
    private String name;
    private PersonStatus status;
    private Pageable pageable;
}
