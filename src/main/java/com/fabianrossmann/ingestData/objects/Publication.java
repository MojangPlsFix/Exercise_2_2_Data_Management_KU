package com.fabianrossmann.ingestData.objects;

import java.util.Collection;
import java.util.Objects;

public class Publication {
    private String PKey;
    private Collection<String> authors;
    private String title;
    private String pages;
    private String CJKey;

    public Publication(String PKey, Collection<String> authors, String title, String pages, String CJKey) {
        this.PKey = PKey;
        this.authors = authors;
        this.title = title;
        this.pages = pages;
        this.CJKey = CJKey;
    }

    public String getPKey() {
        return PKey;
    }

    public void setPKey(String PKey) {
        this.PKey = PKey;
    }

    public Collection<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCJKey() {
        return CJKey;
    }

    public void setCJKey(String CJKey) {
        this.CJKey = CJKey;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publication)) {
            return false;
        }
        Publication that = (Publication) o;
        return Objects.equals(getPKey(), that.getPKey()) &&
            Objects.equals(getAuthors(), that.getAuthors()) &&
            Objects.equals(getTitle(), that.getTitle()) &&
            Objects.equals(getPages(), that.getPages()) &&
            Objects.equals(getCJKey(), that.getCJKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPKey(), getAuthors(), getTitle(), getPages(), getCJKey());
    }

    @Override
    public String toString() {
        return "Publication{" +
            "PKey='" + PKey + '\'' +
            ", authors=" + authors +
            ", title='" + title + '\'' +
            ", pages='" + pages + '\'' +
            ", CJKey='" + CJKey + '\'' +
            '}';
    }
}

