package cn.sixlab.sixlab.web.app.bean;

import java.util.List;

public class DoubanSingleMovie {

    private DoubanRate rating;
    private String year;
    private String alt;
    private String id;
    private String title;
    private String originalTitle;
    private String[] countries;
    private String[] genres;
    private List<DoubanMan> directors;
    private String[] aka;

    public DoubanRate getRating() {
        return rating;
    }

    public void setRating(DoubanRate rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public List<DoubanMan> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DoubanMan> directors) {
        this.directors = directors;
    }

    public String[] getAka() {
        return aka;
    }

    public void setAka(String[] aka) {
        this.aka = aka;
    }
}
