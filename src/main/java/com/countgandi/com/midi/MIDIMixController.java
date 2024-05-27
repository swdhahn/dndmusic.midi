package com.countgandi.com.midi;

import java.util.Arrays;

import com.countgandi.com.songs.SongManager;

public class MIDIMixController extends MidiController {
	
	public MIDIMixController(SongManager manager) {
		super(manager, "MIDI Mix", 62, 25, 26, 27);
		
		sliders.addAll(Arrays.asList(19, 23, 27, 31, 49, 53, 57, 61));
		buttons.addAll(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22));
		
	}

}
