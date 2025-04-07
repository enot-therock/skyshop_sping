package org.skypro.skyshop.error;

public class ShopError {

    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String shopErrorException(String code, String message) {
        return "Ошибка " + code + " " + message;
    }
}
