package mechamorph.game.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static Sound test = loadSound("res/ladder.wav", false);
	public static Sound bgm = loadSound("res/bgm.wav", true);
	
	public static Sound loadSound(String path, boolean looping) {
		Sound sound = new Sound();
		try {
			FileInputStream audsrc = new FileInputStream(path);
			InputStream buffered = new BufferedInputStream(audsrc);
			AudioInputStream aud = AudioSystem.getAudioInputStream(buffered);
			Clip clip = AudioSystem.getClip();
			clip.open(aud);
			sound.clip = clip;
			sound.looping = looping;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sound;
	}
	
	private Clip clip;
	private boolean looping;
	
	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized(clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
							if (looping)
								clip.loop(Clip.LOOP_CONTINUOUSLY);
							else
								return;
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
