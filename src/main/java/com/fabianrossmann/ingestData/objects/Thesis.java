package com.fabianrossmann.ingestData.objects;

import java.util.Objects;

public class Thesis {
    private String TKey;
    private String author;
    private String title;
    private int year;
    private String type;
    private String school;
    private String country;
    private String pages;
    private String isbn;

    public Thesis(String TKey, String author, String title, int year, String type, String school, String country, String pages, String isbn) {
        this.TKey = TKey;
        this.author = author;
        this.title = title;
        this.year = year;
        this.type = type;
        this.school = school;
        this.country = country;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getTKey() {
        return TKey;
    }

    public void setTKey(String TKey) {
        this.TKey = TKey;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNPages() {
        return Integer.parseInt(this.pages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thesis)) {
            return false;
        }
        Thesis thesis = (Thesis) o;
        return getYear() == thesis.getYear() &&
            Objects.equals(getTKey(), thesis.getTKey()) &&
            Objects.equals(getAuthor(), thesis.getAuthor()) &&
            Objects.equals(getTitle(), thesis.getTitle()) &&
            Objects.equals(getType(), thesis.getType()) &&
            Objects.equals(getSchool(), thesis.getSchool()) &&
            Objects.equals(getCountry(), thesis.getCountry()) &&
            Objects.equals(getPages(), thesis.getPages()) &&
            Objects.equals(getIsbn(), thesis.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTKey(), getAuthor(), getTitle(), getYear(), getType(), getSchool(), getCountry(), getPages(), getIsbn());
    }

    @Override
    public String toString() {
        return "Thesis{" +
            "TKey='" + TKey + '\'' +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            ", year=" + year +
            ", type='" + type + '\'' +
            ", school='" + school + '\'' +
            ", country='" + country + '\'' +
            ", pages='" + pages + '\'' +
            ", isbn='" + isbn + '\'' +
            '}';
    }
}
