package execution;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class AudioPlayer{
	
	

	private long currentFrame;
	private Clip clip;
	private AudioInputStream audioStream;
	private File audioFile;
	private String status, audioFilePath;
	private FloatControl fc;
	private float range, gain, volume;
	
	public AudioPlayer (String audioFilePath){
		audioFile = new File (audioFilePath);
		this.audioFilePath = audioFilePath;
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip = AudioSystem.getClip(); 
			clip.open(audioStream); 
	        status = "play";
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			range = fc.getMaximum() - fc.getMinimum();
			volume = (float) 0.7;
			gain = (range * volume) + fc.getMinimum();
			fc.setValue(gain);
			
   	        
        } catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("Music created");         
	}
	
	
	public void start() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		System.out.println("Music started");
	}
	
	public void startNoLoop() {
		clip.start();
		System.out.println("Sound started");
		
	}
	
	
	public void pause() {
		if (status == "play") {
			this.currentFrame = this.clip.getMicrosecondPosition();   
		    clip.setMicrosecondPosition(currentFrame); 
		    clip.stop();
		    status = "pause";
		}
	}
	
	public void resumeAudio() {
		if (status == "pause") {
			clip.close();
			try {
				this.resetAudioStream();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			clip.setMicrosecondPosition(currentFrame);
			this.start();
		}
	}
	
	public void setAudio() {
		clip.close();
		try {
			this.resetAudioStream();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		clip.setMicrosecondPosition(0);
		this.startNoLoop();
		
	}
	
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		audioStream= AudioSystem.getAudioInputStream(new File(audioFilePath)); 	
		clip.open(audioStream); 
	}
	

	public void setVolume(float volume) {
		this.volume = volume;
		gain = (range * volume) + fc.getMinimum();
		fc.setValue(gain);
	}

}