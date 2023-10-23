package com.server.SongServer.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Song {
    @NotNull
    @Min(value = 0, message = "id can't be less than zero")
    private Integer id;
    @NotBlank
    private String singer;
    @NotBlank
    private String name;

    private String genre;

    public Song(Integer id, String singer, String name, String genre) {
        this.id = id;
        this.singer = singer;
        this.name = name;
        this.genre = genre;
    }

    public Song() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
