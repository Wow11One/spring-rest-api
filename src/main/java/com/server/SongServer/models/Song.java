package com.server.SongServer.models;

public class Song {

    private Integer id;
    private String singer;
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