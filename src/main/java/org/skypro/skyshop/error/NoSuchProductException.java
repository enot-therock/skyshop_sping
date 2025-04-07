package org.skypro.skyshop.error;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super("Такого продукта в корзине нет");
    }
}
