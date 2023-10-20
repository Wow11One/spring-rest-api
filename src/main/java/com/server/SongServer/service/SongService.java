package com.server.SongServer.service;

import com.server.SongServer.dao.SongDAO;
import com.server.SongServer.models.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class SongService {

    private SongDAO songDAO;

    public SongService(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public List<Song> getSongs() {
        List<Song> result = songDAO.getSongs();
        if (result == null) {
            throw new NoSuchElementException("exception: the list is null");
        } else {
            return result;
        }
    }

    public Song getSongById(Integer id) {
        Song result = songDAO.getSongById(id);
        if (result == null) {
            throw new NoSuchElementException("exception: wrong id");
        } else {
            return result;
        }
    }

    public List<Song> getSongByGenre(String genre) {
        List<Song> result = songDAO.getSongByGenre(genre);
        if (result == null) {
            throw new NoSuchElementException("exception: genre does not exist");
        } else {
            return result;
        }
    }


    public Song addSong(Song song) {
        if (songDAO.getSongById(song.getId()) != null) {
            throw new IllegalArgumentException("exception: object with this id already exists");
        }
        boolean isCreated = songDAO.create(song);
        if (!isCreated) {
            throw new IllegalArgumentException("exception: couldn't create a song");
        }
        return song;
    }

    public Song deleteSong(Integer id) {
        Song songToBeDeleted = songDAO.getSongById(id);
        if (songToBeDeleted == null) {
            throw new NoSuchElementException("exception: incorrect id");
        }
        songDAO.delete(id);
        return songToBeDeleted;
    }

    public Song updateSong(Song song, Integer id) {
        if (songDAO.getSongById(id) == null || song.getId() != id) {
            throw new NoSuchElementException("exception: incorrect id");
        }
        songDAO.update(song);
        return song;
    }
}
