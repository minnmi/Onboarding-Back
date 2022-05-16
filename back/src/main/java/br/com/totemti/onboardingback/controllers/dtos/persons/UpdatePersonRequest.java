package br.com.totemti.onboardingback.controllers.dtos.persons;


import br.com.totemti.onboardingback.enums.Gender;
import br.com.totemti.onboardingback.enums.PersonStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UpdatePersonRequest {
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;

    private String document;

    private Gender gender;

    private String inscricaoEstadual;

    private PersonStatus status;

    private List<AddressRequest> addresses;

    private List<EmailRequest> emails;

    private List<PhoneRequest> phones;

}
