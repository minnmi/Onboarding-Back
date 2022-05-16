package br.com.totemti.onboardingback.enums;


public enum ProductType {

    UNIDADE, CAIXA, PESO;

    public static ProductType getEnum(final Integer value) {
        if (value == 1) {
            return UNIDADE;
        }
        if (value == 2) {
            return CAIXA;
        }
        if (value == 3) {
            return PESO;
        }
        return null;
    }

    public Integer getValue() {
        switch (this) {
            case UNIDADE:
                return 1;
            case CAIXA:
                return 2;
            case PESO:
                return 3;
        }
        return null;
    }

}
