package com.example.ashutoshshrivastava.librarymanagementsystem;

public class BookList {
    String booknameText;
    String authorText;
    String ratingText;
    String genreText;
    String coverPicTextURL;
    String issuerText;
    String statusText;

    public BookList(String booknameText, String authorText, String ratingText, String genreText, String coverPicTextURL, String issuerText, String statusText) {
        this.booknameText = booknameText;
        this.authorText = authorText;
        this.ratingText = ratingText;
        this.genreText = genreText;
        this.coverPicTextURL = coverPicTextURL;
        this.issuerText = issuerText;
        this.statusText = statusText;
    }

    public String getBooknameText() {
        return booknameText;
    }

    public String getAuthorText() {
        return authorText;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getGenreText() {
        return genreText;
    }

    public String getCoverPicTextURL() {
        return coverPicTextURL;
    }

    public String getIssuerText() {
        return issuerText;
    }

    public String getStatusText() {
        return statusText;
    }
}
