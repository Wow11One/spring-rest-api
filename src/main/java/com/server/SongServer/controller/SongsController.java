package com.server.SongServer.controller;

import com.server.SongServer.model.Song;
import com.server.SongServer.service.SongService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class SongsController {

    private final SongService songService;

    public SongsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSong(@PathVariable Integer songId) {
        return songService.getSongById(songId);
    }

    @GetMapping("/songs/search")
    public List<Song> getSongByGenre(@RequestParam(name = "genre", required = false) String songGenre) {
        return songService.getSongByGenre(songGenre);
    }

    @PostMapping(value = "/songs", consumes = {"application/json"})
    public Song addSong(@RequestBody @Valid Song song) {
        return songService.addSong(song);
    }

    @DeleteMapping(value = "/songs/{songId}")
    public Song deleteSong(@PathVariable Integer songId) {
        return songService.deleteSong(songId);
    }

    @PutMapping(value = "/songs/{songId}")
    public Song updateSong(@RequestBody @Valid Song song, @PathVariable Integer songId) {
        return songService.updateSong(song, songId);
    }
}
