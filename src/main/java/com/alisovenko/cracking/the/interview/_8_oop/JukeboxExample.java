package com.alisovenko.cracking.the.interview._8_oop;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author alisovenko 04.10.14
 */
public class JukeboxExample {
    class Jukebox {
        Library library;
        Set<PlayList> playLists;
        Properties properties;
        Player player;
        SongSelector songSelector;

        public void play() {
            player.play(songSelector.nextSong());
        }

    }
    class Player {
        public void play(Song song) {

        }

        public void play(PlayList list) {

        }

        public void stop() {

        }
        // etc. pause, forward, backword
    }
    class Artist {
        List<Album> albums;
        String fio;
        // etc.

        public void addAlbum(Album album) {

        }
    }
    class Song {
        final Album album;

        Song(Album album) {
            this.album = album;
        }
    }
    class Album {
        List<Artist> artists;
        List<Song> songs;

        private Album() {
        }

        class AlbumBuilder {
            AlbumBuilder addArtist(Artist artist) {
                return this;
            }
            AlbumBuilder addSong(Song artist) {
                return this;
            }

            Album build() {
                return null;
            }
            // etc (year, genre etc.)
        }
    }
    class PlayList {
        List<Song> songs;

        public void addSong(Song song) {

        }

        public void removeSong(Song song) {

        }
    }

    private static class SongSelector {
        private static byte NORMAL = 1;
        private static byte RANDOM = 1<<1;
        private static byte SHUFFLE = 1<<2;
        private byte currentMode;
        private PlayList currentPlaylist;

        Song nextSong() {
            return null;
        }

    }

    private class Library {
        Set<Album> albums;
        public Song findSong(String namePattern, int year, int length) {
            return null;
        }

        public void addAlbum(Album album) {

        }

        public void addSong(Song song) {

        }
    }
}
