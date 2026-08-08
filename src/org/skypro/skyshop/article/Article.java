package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

public class Article implements Searchable {

    private final String title;
    private final String text;

    public Article(String title, String text) {
        Objects.requireNonNull(title, "Название статьи не может быть null");
        if (title.isBlank()) {throw new IllegalArgumentException("Название статьи не может быть пустой строкой");}
        this.title = title.trim().toUpperCase();

        Objects.requireNonNull(text, "Текст статьи не может быть null");
        if (text.isBlank()) {throw new IllegalArgumentException("Текст статьи не может быть пустой строкой");}
        this.text = text.trim();
    }

    @Override
    public String getSearchTerm() {
        return toString().toLowerCase();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Article article = (Article) object;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
