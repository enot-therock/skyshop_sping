package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ShopController extends StorageService {

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return getProductMap();
    }

    @GetMapping("/article")
    public Collection<Article> getAllArticles() {
        return getArticleMap();
    }

    @GetMapping("/search")
    public Collection<SearchResult> getSearchResult(@RequestParam("pattern") String pattern) {
        return Stream.concat(getAllArticles().stream().filter(v -> v.searchableName().contains(pattern.toLowerCase())),
                getAllProducts().stream().filter(v -> v.searchableName().contains(pattern.toLowerCase())))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

}
