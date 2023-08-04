package com.example.musicplayer.Class;

public class Song {
    private String nameSong;
    private String nameSinger;
    private int picture;
    private int file;

    public Song(String nameSong, String nameSinger, int picture, int file) {
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.picture = picture;
        this.file = file;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
