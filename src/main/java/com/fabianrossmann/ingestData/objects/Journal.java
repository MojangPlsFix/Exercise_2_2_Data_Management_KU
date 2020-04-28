package com.fabianrossmann.ingestData.objects;

import java.io.Serializable;
import java.util.Objects;

public class Journal implements Serializable {
    private String JKey;
    private String shortName;
    private String title;
    private int volume;
    private int number;
    private int year;

    public Journal(String JKey, String shortName, String title, int volume, int number, int year) {
        this.JKey = JKey;
        this.shortName = shortName;
        this.title = title;
        this.volume = volume;
        this.number = number;
        this.year = year;
    }

    public String getJKey() {
        return JKey;
    }

    public void setJKey(String JKey) {
        this.JKey = JKey;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Journal)) {
            return false;
        }
        Journal journal = (Journal) o;
        return getVolume() == journal.getVolume() &&
            getNumber() == journal.getNumber() &&
            getYear() == journal.getYear() &&
            Objects.equals(getJKey(), journal.getJKey()) &&
            Objects.equals(getShortName(), journal.getShortName()) &&
            Objects.equals(getTitle(), journal.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJKey(), getShortName(), getTitle(), getVolume(), getNumber(), getYear());
    }

    @Override
    public String toString() {
        return "Journal{" +
            "JKey='" + JKey + '\'' +
            ", shortName='" + shortName + '\'' +
            ", title='" + title + '\'' +
            ", volume=" + volume +
            ", number=" + number +
            ", year=" + year +
            '}';
    }
}
