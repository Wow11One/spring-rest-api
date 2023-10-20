package com.server.SongServer.controllers;

import com.server.SongServer.models.Song;
import com.server.SongServer.service.SongService;
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
    @ResponseBody
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/songs/{songId}")
    @ResponseBody
    public Song getSong(@PathVariable Integer songId) {
        return songService.getSongById(songId);
    }

    @GetMapping("/songs/search")
    @ResponseBody
    public List<Song> getSongByGenre(@RequestParam(name = "genre", required = false) String songGenre) {
        if (songGenre == null) {
            return songService.getSongs();
        }
        return songService.getSongByGenre(songGenre);
    }

    @PostMapping(value = "/songs", consumes = {"application/json"})
    @ResponseBody
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @DeleteMapping(value = "/songs/{songId}")
    @ResponseBody
    public Song deleteSong(@PathVariable Integer songId) {
        return songService.deleteSong(songId);
    }

    @PutMapping(value = "/songs/{songId}")
    @ResponseBody
    public Song updateSong(@RequestBody Song song, @PathVariable Integer songId) {
        return songService.updateSong(song, songId);
    }


}
