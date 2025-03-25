package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable, Comparable<Searchable> {

    private final String nameArticle;
    private final String textArticle;
    private final UUID id;

    public Article(UUID id, String nameArticle, String textArticle) {
        this.nameArticle = nameArticle;
        this.textArticle = textArticle;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @Override
    public String toString() {
        return getNameArticle() + System.lineSeparator() + getTextArticle();
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return getNameArticle() + System.lineSeparator() + getTextArticle();
    }

    @JsonIgnore
    @Override
    public String typeContent() {
        return "ARTICLE";
    }

    @Override
    public String searchableName() {
        return getNameArticle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(nameArticle, article.nameArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameArticle);
    }

    @Override
    public int compareTo(Searchable o) {
        return this.searchableName().compareTo(o.searchableName());
    }
}
