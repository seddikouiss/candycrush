package modele;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.EventListenerList;

import event.FactoryCandyEvent;
import listener.Observable;
import listener.Observerateur;
   
public class Zic extends JFrame implements Observable{
   
	private static final long serialVersionUID = 1L;
	
	private static volatile Zic instance = null;
	private EventListenerList listeners;
	Clip clip;
   
   boolean bStart = false;
   private Zic() {
   
      try {
         URL url = this.getClass().getClassLoader().getResource("../zic/candy.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioIn);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
      listeners = new EventListenerList() ;
   }

	public final static Zic getInstance() {
		
		if (Zic.instance == null) {
			synchronized(Zic.class) {
				if (Zic.instance == null) {
					Zic.instance = new Zic();
				}
			}
		}
		return Zic.instance;
	}
	public void start(){
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		bStart = true ;
		fireStart () ;
	}
	public void stop(){
		clip.stop();
		bStart = false ;
		fireStop () ;
	}
	public void startStop(){
		if (bStart) stop();
		else start() ;
	}
	@Override
	public void addListener(Observerateur l) {
		listeners.add(Observerateur.class, l);
		
	}

	@Override
	public void removeListener(Observerateur l) {
		listeners.remove(Observerateur.class, l);
		
	}
	private void fireStart(){
		if(bStart){
			Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
			for(Observerateur listener : listenerList){
				listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventPlayStopZic", this, true));
			}
		}
	}
	private void fireStop(){
		if(!bStart){
			Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
			for(Observerateur listener : listenerList){
				listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventPlayStopZic", this, false));
			}
		}
	}


   
}
