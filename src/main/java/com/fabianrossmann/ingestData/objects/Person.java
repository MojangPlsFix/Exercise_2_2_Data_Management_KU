package com.fabianrossmann.ingestData.objects;

import java.util.Objects;

public class Person {
    private String AKey;
    private String name;
    private String aliases;
    private String affiliation;
    private String country;
    private String website;

    public Person(String AKey, String name, String aliases, String affiliation, String country, String website) {
        this.AKey = AKey;
        this.name = name;
        this.aliases = aliases;
        this.affiliation = affiliation;
        this.country = country;
        this.website = website;
    }

    public String getAKey() {
        return AKey;
    }

    public void setAKey(String AKey) {
        this.AKey = AKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(getAKey(), person.getAKey()) &&
            Objects.equals(getName(), person.getName()) &&
            Objects.equals(getAliases(), person.getAliases()) &&
            Objects.equals(getAffiliation(), person.getAffiliation()) &&
            Objects.equals(getCountry(), person.getCountry()) &&
            Objects.equals(getWebsite(), person.getWebsite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAKey(), getName(), getAliases(), getAffiliation(), getCountry(), getWebsite());
    }

    @Override
    public String toString() {
        return "Person{" +
            "aKey='" + AKey + '\'' +
            ", name='" + name + '\'' +
            ", aliases='" + aliases + '\'' +
            ", affiliation='" + affiliation + '\'' +
            ", country='" + country + '\'' +
            ", website='" + website + '\'' +
            '}';
    }
}
