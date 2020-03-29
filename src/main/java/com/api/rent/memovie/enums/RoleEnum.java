package com.api.rent.memovie.enums;

/**
 * Enumerador de Roles
 *
 * @author MARIO MENCIA
 */
public enum RoleEnum {

    ADMIN("ROLE_ADMIN", "Administrador"),
    USER("ROLE_USER", "usuario");

    String code;
    String description;

    private RoleEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RoleEnum getRoleEnum(final String code) {
        RoleEnum ret = null;
        for (RoleEnum activeEnum : values()) {
            if (activeEnum.getCode().equals(code)) {
                ret = activeEnum;
                break;
            }
        }
        return ret;
    }

}
