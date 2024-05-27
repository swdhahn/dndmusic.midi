package com.countgandi.com.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;

public class MidiManager {
	
	private MidiController controller;
	
	private MidiDevice deviceReceiver;
	private MidiDevice deviceTransmitter;

	public MidiManager(MidiController controller) {
		this.controller = controller;
		
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				if (!infos[i].toString().trim().equals(controller.getName())) {
					continue;
				}
				if (MidiSystem.getMidiDevice(infos[i]).getMaxTransmitters() == 0) {
					deviceReceiver = MidiSystem.getMidiDevice(infos[i]);
				} else {
					deviceTransmitter = MidiSystem.getMidiDevice(infos[i]);
				}
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}

		MidiReceiver rec = new MidiReceiver(controller);

		try {
			if (!deviceTransmitter.isOpen()) {
				deviceTransmitter.open();
			}
			if (!deviceReceiver.isOpen()) {
				deviceReceiver.open();
			}

			deviceTransmitter.getTransmitter().setReceiver(rec);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	class MidiReceiver implements Receiver {
		
		private MidiController midi;
		
		public MidiReceiver(MidiController controller) {
			this.midi = controller;
		}

		@Override
		public void close() {

		}

		@Override
		public void send(MidiMessage message, long timeStamp) {
			int status = message.getStatus();
			byte controller = message.getMessage()[1];
			byte value = message.getMessage()[2];
			
			switch (status) {
			case 0x80:
				// Note off
				midi.noteOff((int)controller, (int)value);
				break;
			case 0x90:
				// Note on
				midi.noteOn((int)controller, (int)value);
				break;
			case 0xA0:
				// Polyphonic Key Pressure
				break;
			case 0xB0:
				// Control Change
				midi.controlChange((int)controller, (int)value);
				break;
			case 0xC0:
				// Program Change
				break;
			case 0xD0:
				// Channel Pressure
				break;
			case 0xE0:
				// Pitch Bend
				break;
			}
		}
	}
	
}
