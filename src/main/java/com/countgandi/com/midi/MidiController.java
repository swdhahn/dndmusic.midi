package com.countgandi.com.midi;

import java.util.ArrayList;

import com.countgandi.com.songs.SongManager;

public class MidiController {
	
	// For controlling music volume and ambient volume
	protected ArrayList<Integer> sliders = new ArrayList<Integer>();
	// Select the currently playing song
	protected ArrayList<Integer> buttons = new ArrayList<Integer>();
	protected int masterVolume, previousButton, nextButton, pauseButton;
	
	protected String name;
	protected SongManager manager;
	
	public MidiController(SongManager manager, String name, int masterVolume, int previousButton, int nextButton, int pauseButton) {
		this.manager = manager;
		this.name = name;
		this.masterVolume = masterVolume;
		this.previousButton = previousButton;
		this.nextButton = nextButton;
		this.pauseButton = pauseButton;
	}
	
	public void noteOn(int controller, int value) {
		
	}
	
	public void noteOff(int controller, int value) {
		System.out.println("Note off: " + controller + "  value: " + value);
	}
	
	public void controlChange(int controller, int value) {
		System.out.println("Control Change: " + controller + "  value: " + value);
		
		if(masterVolume == controller) {
			manager.masterVolume = remapValue(value, 127, 100);
		}
	}
	
	private static float remapValue(float val, float max1, float max2) {
		return (val / max1) * max2;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}

}

