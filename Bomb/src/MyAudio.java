import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MyAudio {
	bmb game;
	public MyAudio(bmb game) {
		// TODO Auto-generated constructor stub
		this.game=game;
	}
	void playSound() {
		try {
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;
			File BG = new File(System.getProperty("user.dir")+File.separator+"music.wav");
			stream=AudioSystem.getAudioInputStream(BG);
			format=stream.getFormat();
			info=new DataLine.Info(Clip.class, format);
			clip=(Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			Thread.sleep(82000);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	 void playdie() {
		// TODO Auto-generated method stub
		 try {
				AudioInputStream stream;
				AudioFormat format;
				DataLine.Info info;
				Clip clip;
				File BG = new File(System.getProperty("user.dir")+File.separator+"die.wav");
				stream=AudioSystem.getAudioInputStream(BG);
				format=stream.getFormat();
				info=new DataLine.Info(Clip.class, format);
				clip=(Clip) AudioSystem.getLine(info);
				clip.open(stream);
				clip.start();
				Thread.sleep(82000);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
}
