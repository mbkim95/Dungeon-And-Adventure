package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundController {
	public void Sound(String file, boolean Loop, float volume) {
		Clip clip;

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-volume);
			clip.start();
			if (Loop)
				clip.loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
