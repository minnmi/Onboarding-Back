package br.com.totemti.onboardingback.exceptions;

public class DomainException extends RuntimeException {
    private static final long serialVersionUID = 6748596483342030634L;
    private final Object[] arguments;

    public DomainException(final String message, final Object... args) {
        super(message);
        this.arguments = args.clone();
    }

    public Object[] arguments() {
        return this.arguments.clone();
    }

}