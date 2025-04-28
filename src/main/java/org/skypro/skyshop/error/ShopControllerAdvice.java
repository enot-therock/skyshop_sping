package org.skypro.skyshop.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

//    @ExceptionHandler(NoSuchProductException.class)
//    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException e) {
//        ShopError shopError = new ShopError("404", e.getMessage());
//        if (e.getMessage().contains("Такого продукта нет")) {
//            return ResponseEntity.badRequest().body(shopError);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
