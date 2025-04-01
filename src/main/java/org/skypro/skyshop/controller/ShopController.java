package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
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

    public ShopController() {
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
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        return storageService.getProductMap().stream()
                .filter(p -> p.getId(id))
                .toList() + " - продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return getUserBasket();
    }
}
