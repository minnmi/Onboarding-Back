package br.com.totemti.onboardingback.commons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;


@Getter
@NoArgsConstructor
public class PageableResponseBase<T> {

    @Setter(AccessLevel.PRIVATE)
    private boolean success;

    @Setter(AccessLevel.PRIVATE)
    private Collection<T> data;

    @Setter(AccessLevel.PRIVATE)
    private PageableBase pagination;

    public static <T> PageableResponseBase<T> of(final Page<T> data) {
        final var response = new PageableResponseBase<T>();

        response.setData(data.getContent());
        response.setPagination(new PageableBase(data));
        response.setSuccess(true);

        return response;
    }

    public static <T> PageableResponseBase<T> of(final Page<T> data, final boolean success) {
        final var response = new PageableResponseBase<T>();

        response.setData(data.getContent());
        response.setPagination(new PageableBase(data));
        response.setSuccess(success);

        return response;
    }

}

