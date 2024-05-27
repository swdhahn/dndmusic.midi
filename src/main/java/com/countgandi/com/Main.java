package com.countgandi.com;

import com.countgandi.com.apis.YoutubeManager;
import com.countgandi.com.midi.MIDIMixController;
import com.countgandi.com.midi.MidiManager;
import com.countgandi.com.songs.SongManager;

public class Main {

	public Main() {
		
		SongManager songs = new SongManager();
		
		new YoutubeManager().downloadSong("https://www.youtube.com/watch?v=Wlw6rscU4gI");

		MidiManager midi = new MidiManager(new MIDIMixController(songs));

	}

	public static void main(String[] args) {
		new Main();
	}

	

	

}
