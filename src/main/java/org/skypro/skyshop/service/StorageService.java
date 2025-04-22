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

    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        this.creatTest();
    }

    public void creatTest() {
        Product cake = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
        Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);
        Product meat = new SimpleProduct(UUID.randomUUID(), "Мясо", 1296);
        Product orange = new SimpleProduct(UUID.randomUUID(), "Апельсин", 44);

        Product sausage = new DiscountedProduct(UUID.randomUUID(), "Колбаса", 536, 2);
        Product flakes = new DiscountedProduct(UUID.randomUUID(), "Хлопья", 298, 20);

        Product snickers = new FixPriceProduct(UUID.randomUUID(), "Сникерс");

        Article cake1 = new Article(UUID.randomUUID(), "Торт", "Жиры 20%, Белки 25%, Углеводы 55%");
        Article meatBeard = new Article(UUID.randomUUID(), "Мясо-птицы", "Жиры 16%, Белки 13%, Углеводы 70%");

        productMap.put(cake.getId(), cake);
        productMap.put(kofi.getId(), kofi);
        productMap.put(meat.getId(), meat);
        productMap.put(orange.getId(), orange);

        productMap.put(sausage.getId(), sausage);
        productMap.put(flakes.getId(), flakes);

        productMap.put(snickers.getId(), snickers);

        articleMap.put(cake1.getId(), cake1);
        articleMap.put(meatBeard.getId(), meatBeard);
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

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void addArticle(Article article) {
        articleMap.put(article.getId(), article);
    }
}
