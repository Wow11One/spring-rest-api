package com.server.SongServer.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.SongServer.model.Song;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDAO {

    //database emulation with json file and array list collection
    private List<Song> songs;
    private ObjectMapper objectMapper;

    @Value("${songs.json.filename}")
    private String songsFileName;

    public SongDAO(ObjectMapper objectMapper) {
        songs = new ArrayList<>();
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        try {
            songs = objectMapper.readValue(new File(songsFileName), new TypeReference<ArrayList<Song>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            objectMapper.writeValue(new File(songsFileName), songs);
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
        return songs.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Song> getSongByGenre(String genre) {
        return songs.stream().filter(song -> song.getGenre().equals(genre)).toList();
    }

    public void delete(int id) {
        songs = songs.stream()
                .filter(e -> e.getId() != id)
                .toList();
    }

    public void update(Song song) {
        Song songToBeUpdated = getSongById(song.getId());
        songToBeUpdated.setName(song.getName());
        songToBeUpdated.setSinger(song.getSinger());
        songToBeUpdated.setGenre(song.getGenre());
    }
}
