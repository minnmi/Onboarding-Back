package br.com.totemti.onboardingback.exceptions;

public class EntityNotFoundException extends DomainException {

    private static final long serialVersionUID = -9114413233303957486L;

    public EntityNotFoundException(final String message, final Object... args) {
        super(message, args);
    }

}