package br.com.totemti.onboardingback.commons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ResponseBase<T> {

    @Setter(AccessLevel.PRIVATE)
    private boolean success;

    @Setter(AccessLevel.PRIVATE)
    private T data;

    public static ResponseBase<Void> of() {
        final var response = new ResponseBase<Void>();

        response.setData(null);
        response.setSuccess(true);

        return response;
    }

    public static <T> ResponseBase<T> of(final T data) {
        final var response = new ResponseBase<T>();

        response.setData(data);
        response.setSuccess(true);

        return response;
    }

    public static ResponseBase<Void> of(final boolean success) {
        final var response = new ResponseBase<Void>();

        response.setData(null);
        response.setSuccess(success);

        return response;
    }

    public static <T> ResponseBase<T> of(final T data, final boolean success) {
        final var response = new ResponseBase<T>();

        response.setData(data);
        response.setSuccess(success);

        return response;
    }

}
