package br.com.totemti.onboardingback.enums;


public enum SaleStatus {

    ABERTO, CONCLUIDO, CANCELADO;

    public static SaleStatus getEnum(final Integer value) {
        if (value == 1) {
            return ABERTO;
        }
        if (value == 2) {
            return CONCLUIDO;
        }
        if (value == 3) {
            return CANCELADO;
        }
        return null;
    }

    public Integer getValue() {
        switch (this) {
            case ABERTO:
                return 1;
            case CONCLUIDO:
                return 2;
            case CANCELADO:
                return 3;
        }
        return null;
    }

}
