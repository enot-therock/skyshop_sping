package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class StorageService {

    private Map<UUID, Product> productMap = new HashMap<>();
    private Map<UUID, Article> articleMap = new HashMap<>();

    public StorageService() {
        productMap.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Торт", 598));
        productMap.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Мясо", 1296));
        productMap.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Кофе", 813));
        productMap.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Молоко", 81));
        productMap.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Вафли", 44));

        productMap.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Хлопья", 298, 20));
        productMap.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Колбаса", 536, 2));

        productMap.put(UUID.randomUUID(), new FixPriceProduct(UUID.randomUUID(), "Сникерс"));

        articleMap.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Торт", "Жиры 20%, Белки 25%, Углеводы 55%"));
        articleMap.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Кофе", "Кофеин 4%, Эфирные масла 20%, Углеводы 50%"));
        articleMap.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Мясо", "Жиры 12%, Белки 17%, Углеводы 69%"));
        articleMap.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Мясо-птицы", "Жиры 16%, Белки 13%, Углеводы 70%"));
        articleMap.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Колбаса", "Жиры 16%, Белки 13%, Углеводы 70%"));
    }

    public Collection<Product> getProductMap() {
        return productMap.values().stream().toList();
    }

    public Collection<Article> getArticleMap() {
        return articleMap.values().stream().toList();
    }

    public Collection<Searchable> SearchableStorage() {
        return Stream.concat(productMap.values().stream(), articleMap.values().stream()).toList();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public List<UUID> allPrintProduct() {
        return new ArrayList<>(productMap.keySet());  // метод для отображения id
    }

}
