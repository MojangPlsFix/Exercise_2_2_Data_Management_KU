package com.fabianrossmann.ingestData.objects;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Conference implements Serializable {
    private String CKey;
    private String shortName;
    private String title;
    private String city;
    private String country;
    private Collection<String> editors;
    private int Year;
    private String isbn;

    public Conference(String CKey, String shortName, String title, String city, String country, Collection<String> editors, int year, String isbn) {
        this.CKey = CKey;
        this.shortName = shortName;
        this.title = title;
        this.city = city;
        this.country = country;
        this.editors = editors;
        Year = year;
        this.isbn = isbn;
    }

    public String getCKey() {
        return CKey;
    }

    public void setCKey(String CKey) {
        this.CKey = CKey;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Collection<String> getEditors() {
        return editors;
    }

    public void setEditors(Collection<String> editors) {
        this.editors = editors;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Conference)) {
            return false;
        }
        Conference that = (Conference) o;
        return getYear() == that.getYear() &&
            Objects.equals(getCKey(), that.getCKey()) &&
            Objects.equals(getShortName(), that.getShortName()) &&
            Objects.equals(getTitle(), that.getTitle()) &&
            Objects.equals(getCity(), that.getCity()) &&
            Objects.equals(getCountry(), that.getCountry()) &&
            Objects.equals(getEditors(), that.getEditors()) &&
            Objects.equals(getIsbn(), that.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCKey(), getShortName(), getTitle(), getCity(), getCountry(), getEditors(), getYear(), getIsbn());
    }

    @Override
    public String toString() {
        return "Conference{" +
            "cKey='" + CKey + '\'' +
            ", shortName='" + shortName + '\'' +
            ", title='" + title + '\'' +
            ", city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", editors=" + editors +
            ", Year=" + Year +
            ", isbn='" + isbn + '\'' +
            '}';
    }
}
