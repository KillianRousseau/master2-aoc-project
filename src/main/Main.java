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
import strategy.DiffusionSequentielle;

/**
 * Classe possédant un main permettant de créer tous les objets utiles au projet et de les lier entre eux
 * Va notamment créer toutes les interfaces Swing(Capteur et Afficheurs) ainsi que les canaux entre ces classes.
 * @author Killian Rousseau, Emilien Petit
 */
public class Main {

	/**
	 * Récupère en paramètre un String indiquant l'algorithme de diffusion à utiliser
	 * @param args
	 */
	public static void main(String[] args) {

		//Créer le scheduler qui va gérer les instances de Callable
		final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		
		Capteur capteur = new CapteurImpl(1000, 200);
		AlgoDiffusion diffusion;
		if(args.length>0) {
			if(args[0].equals("atomique")) {
				diffusion = new DiffusionAtomique(capteur);
			}
			else {
				diffusion = new DiffusionSequentielle(capteur);
			}
		}
		
		else {		
			diffusion = new DiffusionAtomique(capteur);
		}
		
		//On donne le scheduler a tous les canaux
		Canal c1 = new Canal(scheduledExecutorService);
		Canal c2 = new Canal(scheduledExecutorService);
		Canal c3 = new Canal(scheduledExecutorService);
		Canal c4 = new Canal(scheduledExecutorService);
		
		ObservateurCapteur o1 = new Afficheur(c1,0,0);
		ObservateurCapteur o2 = new Afficheur(c2,500,0);
		ObservateurCapteur o3 = new Afficheur(c3,0,400);
		ObservateurCapteur o4 = new Afficheur(c4,500,400);
		
		//On affecte un Afficheur à chaque AfficheurThread
		AfficheurThread affThread1 = new AfficheurThread(o1);
		AfficheurThread affThread2 = new AfficheurThread(o2);
		AfficheurThread affThread3 = new AfficheurThread(o3);
		AfficheurThread affThread4 = new AfficheurThread(o4);

		// On donne notre capteur (générateur) à chaque canal
		c1.setCapteur(capteur);
		c2.setCapteur(capteur);
		c3.setCapteur(capteur);
		c4.setCapteur(capteur);
		
		// On attache chaque afficheur à son canal en tant qu'observateur
		c1.attach(o1);
		c2.attach(o2);
		c3.attach(o3);
		c4.attach(o4);
		
		// On attache chaque canal à l'algorithme de diffusion en tant qu'observateur
		diffusion.attach(c1);
		diffusion.attach(c2);
		diffusion.attach(c3);
		diffusion.attach(c4);
		
		capteur.setAlgoDiffusion(diffusion);
		
		// On crée et on démarre tous les threads en tant que démons pour qu'ils s'arrêtent à l'arrêt du Main
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
