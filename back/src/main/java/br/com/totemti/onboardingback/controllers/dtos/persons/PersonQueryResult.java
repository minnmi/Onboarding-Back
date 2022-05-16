package br.com.totemti.onboardingback.controllers.dtos.persons;

import br.com.totemti.onboardingback.enums.Gender;
import br.com.totemti.onboardingback.enums.PersonStatus;
import br.com.totemti.onboardingback.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PersonQueryResult {

    private Long id;
    private String name;
    private String document;
    private PersonStatus status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;
    private Gender gender;
    private String inscricaoEstadual;
    private PersonType type;
}
