package com.api.rent.memovie.enums;

/**
 * Enumerador para clasificar el tipo de movimiento de pelicula
 *
 * @author MARIO MENCIA
 */
public enum EventTypeEnum {

    SALES("SALES", "For sell movies"),
    RENT("RENT", "For rent movies");

    String code;
    String description;

    private EventTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EventTypeEnum getEventTypeEnum(final String code) {
        EventTypeEnum ret = null;
        for (EventTypeEnum activeEnum : values()) {
            if (activeEnum.getCode().equals(code)) {
                ret = activeEnum;
                break;
            }
        }
        return ret;
    }

}
