package com.countgandi.com.songs;

import java.util.ArrayList;

public class SongManager {
	
	private ArrayList<Song> songs = new ArrayList<Song>();
	private MainSong song;
	public float masterVolume;
	
	public SongManager() {
		
	}
	
	public void playSong(int index) {
		
	}
	
	public void nextSong() {
		
	}
	
	public void previousSong() {
		
	}
	
	public void pauseSong() {
		
	}
	
	public void setMainVolume(float volume) {
		song.volume = volume;
	}
	
	public void setMasterVolume(float volume) {
		this.masterVolume = volume;
	}
	
	public void setVolume(int index, float volume) {
		songs.get(index).volume = volume;
	}

}
