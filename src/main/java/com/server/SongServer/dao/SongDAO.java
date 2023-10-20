package com.server.SongServer.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.server.SongServer.models.Song;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDAO {

    //database emulation with json file and array list collection
    private List<Song> songs;
    private Gson gson;

    @Value("${songs.json.filename}")
    private String songsFileName;

    public SongDAO(Gson gson) {
        this.gson = gson;
        songs = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        Type type = new TypeToken<List<Song>>() {
        }.getType();
        try {
            songs = gson.fromJson(new FileReader(songsFileName), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try (Writer writer = new FileWriter(songsFileName)) {
            gson.toJson(songs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Song song) {
        return songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Song getSongById(int id) {
        return songs.stream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    public List<Song> getSongByGenre(String genre) {
        return songs.stream().filter(song -> song.getGenre().equals(genre)).toList();
    }

    public void delete(int id) {
        songs = songs.stream().filter(e -> e.getId() != id).toList();
    }

    public void update(Song song) {
        Song songToBeUpdated = getSongById(song.getId());
        songToBeUpdated.setName(song.getName());
        songToBeUpdated.setSinger(song.getSinger());
        songToBeUpdated.setGenre(song.getGenre());
    }
}
