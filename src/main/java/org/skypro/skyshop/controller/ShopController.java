package org.skypro.skyshop.controller;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class ShopController {

    private final SearchService searchService;
    private final StorageService storageService;
    private final BasketService basketService;

    public ShopController(BasketService basketService) {
        this.basketService = basketService;
        this.searchService = new SearchService();
        this.storageService = new StorageService();
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductMap();
    }

    @GetMapping("/article")
    public Collection<Article> getAllArticles() {
        return storageService.getArticleMap();
    }

    @GetMapping("/search")
    public Collection<SearchResult> getSearchResult(@RequestParam("pattern") String pattern) {
        Stream.concat(getAllArticles().stream().filter(v -> v.searchableName().contains(pattern.toLowerCase())),
                getAllProducts().stream().filter(v -> v.searchableName().contains(pattern.toLowerCase())))
                .collect(Collectors.toCollection(ArrayList::new));
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addBasket(id);
        return "Продукт " + id + " успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @GetMapping("/id")
    public void idd() {
        System.out.println(storageService.allPrintProduct());  // метод отображения id
    }
}
