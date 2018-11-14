package main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import afficheur.Afficheur;
import capteur.Capteur;
import capteur.CapteurImpl;
import proxy.Canal;
import proxy.ObservateurCapteur;

public class Main {

	public static void main(String[] args) {

		final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Capteur capteur = new CapteurImpl(1000, 200);

		Canal c1 = new Canal(scheduledExecutorService);
		Canal c2 = new Canal(scheduledExecutorService);
		Canal c3 = new Canal(scheduledExecutorService);
		Canal c4 = new Canal(scheduledExecutorService);
		
		c1.setCapteur(capteur);
		c2.setCapteur(capteur);
		c3.setCapteur(capteur);
		c4.setCapteur(capteur);
		
		ObservateurCapteur o1 = new Afficheur(c1,0,0);
		ObservateurCapteur o2 = new Afficheur(c2,500,0);
		ObservateurCapteur o3 = new Afficheur(c3,0,400);
		ObservateurCapteur o4 = new Afficheur(c4,500,400);
		
		c1.setObservateur(o1);
		c2.setObservateur(o2);
		c3.setObservateur(o3);
		c4.setObservateur(o4);
		
		Thread threadCapteur = new Thread(capteur,"capteur");
		threadCapteur.setDaemon(true);
		threadCapteur.start();
	
	}
}
