package sg.edu.rp.c346.id21011122.mymovies;

import androidx.annotation.NonNull;

public class Movie {

    private int id;
    private String title;
    private String genre;
    private int year;
    private String rating;

    public Movie(int id, String title, String genre, int year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString(){
        String ratingString = "";
        for (int i = 0; i < rating.length(); i++){
            ratingString += "*";
        }
        return title + "\n" + genre + " - " + year + "\n" + ratingString;
    }
}
