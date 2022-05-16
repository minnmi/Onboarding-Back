package br.com.totemti.onboardingback.commons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PageableBase implements Serializable {

    private static final long serialVersionUID = -2628613421055717528L;

    private int pagina;

    private int tamanhoPagina;

    private int totalPaginas;

    private long totalRegistros;

    public <T> PageableBase(final Page<T> page) {
        this.setPagina(page.getNumber());
        this.setTamanhoPagina(page.getSize());
        this.setTotalPaginas(page.getTotalPages());
        this.setTotalRegistros(page.getTotalElements());
    }

}

