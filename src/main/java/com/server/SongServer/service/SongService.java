package com.server.SongServer.service;

import com.server.SongServer.dao.SongDAO;
import com.server.SongServer.models.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class SongService {

    private final SongDAO songDAO;

    public SongService(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public List<Song> getSongs() {
        Optional<List<Song>> optionalSongs = Optional.ofNullable(songDAO.getSongs());
        return optionalSongs.orElseThrow();
    }

    public Song getSongById(Integer id) {
        Optional<Song> optionalSong = Optional.ofNullable(songDAO.getSongById(id));
        return optionalSong.orElseThrow(NoSuchElementException::new);
    }

    public List<Song> getSongByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return getSongs();
        }
        return songDAO.getSongByGenre(genre);
    }


    public Song addSong(Song song) {
        Optional<Song> optionalSong = Optional.ofNullable(getSongById(song.getId()));//check whether id is unique
        optionalSong.ifPresentOrElse(
                (value) -> {
                    throw new IllegalArgumentException();
                },
                () -> songDAO.create(song)
        );
        return song;
    }

    public Song deleteSong(Integer id) {
        Song songToBeDeleted = songDAO.getSongById(id);
        songDAO.delete(id);
        return songToBeDeleted;
    }

    public Song updateSong(Song song, Integer id) {
        if (songDAO.getSongById(id) == null || song.getId() != id) {
            throw new NoSuchElementException();
        }
        songDAO.update(song);
        return song;
    }
}
