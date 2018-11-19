package main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import afficheur.Afficheur;
import afficheur.AfficheurThread;
import afficheur.ObservateurCapteur;
import capteur.Capteur;
import capteur.CapteurImpl;
import proxy.Canal;
import strategy.AlgoDiffusion;
import strategy.DiffusionAtomique;

public class Main {

	public static void main(String[] args) {

		final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		Capteur capteur = new CapteurImpl(1000, 200);
		AlgoDiffusion diffusion = new DiffusionAtomique(capteur);

		Canal c1 = new Canal(scheduledExecutorService);
		Canal c2 = new Canal(scheduledExecutorService);
		Canal c3 = new Canal(scheduledExecutorService);
		Canal c4 = new Canal(scheduledExecutorService);
		
		ObservateurCapteur o1 = new Afficheur(c1,0,0);
		ObservateurCapteur o2 = new Afficheur(c2,500,0);
		ObservateurCapteur o3 = new Afficheur(c3,0,400);
		ObservateurCapteur o4 = new Afficheur(c4,500,400);
		
		AfficheurThread affThread1 = new AfficheurThread(o1);
		AfficheurThread affThread2 = new AfficheurThread(o2);
		AfficheurThread affThread3 = new AfficheurThread(o3);
		AfficheurThread affThread4 = new AfficheurThread(o4);

		c1.setCapteur(capteur);
		c2.setCapteur(capteur);
		c3.setCapteur(capteur);
		c4.setCapteur(capteur);
		
		c1.attach(o1);
		c2.attach(o2);
		c3.attach(o3);
		c4.attach(o4);
		
		diffusion.attach(c1);
		diffusion.attach(c2);
		diffusion.attach(c3);
		diffusion.attach(c4);
		
		capteur.setAlgoDiffusion(diffusion);
		
		
		Thread threadCapteur = new Thread(capteur,"capteur");
		threadCapteur.setDaemon(true);
		threadCapteur.start();
		
		Thread threadAfficheur1 = new Thread(affThread1,"afficheur1");
		threadAfficheur1.setDaemon(true);
		threadAfficheur1.start();
		
		Thread threadAfficheur2 = new Thread(affThread2,"afficheur2");
		threadAfficheur2.setDaemon(true);
		threadAfficheur2.start();
		
		Thread threadAfficheur3 = new Thread(affThread3,"afficheur3");
		threadAfficheur3.setDaemon(true);
		threadAfficheur3.start();
		
		Thread threadAfficheur4 = new Thread(affThread4,"afficheur4");
		threadAfficheur4.setDaemon(true);
		threadAfficheur4.start();
	
	}
}
