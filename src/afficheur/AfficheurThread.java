package afficheur;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * La classe AfficheurThread implémente Runnable pour être un Thread lié à un Afficheur
 * Celui-ci a pour but de mettre à jour la valeur de l'afficheur en récupérant le Future de l'Afficheur
 * @author Killian Rousseau, Emilien Petit
 */
public class AfficheurThread implements Runnable {

	/**
	 * Future retournant un integer, récupéré dans l'Afficheur, permettant de modifier la valeur de l'Afficheur
	 */
	private Future<Integer> future;
	
	/**
	 * Afficheur lié au Thread
	 */
	private ObservateurCapteur afficheur;
	
	/**
	 * Constructeur de l'AfficheurThread 
	 * @param o : ObservateurCapteur ou Afficheur avec lequel le Thread va communiquer
	 */
	public AfficheurThread(ObservateurCapteur o) {
		this.afficheur = o;
	}

	/**
	 * Fonction run du Thread AfficheurThread 
	 * Permet d'essayer de récupérer en boucle le Future de l'Afficheur 
	 * et de mettre à jour la valeur de l'Afficheur si le Future existe
	 */
	@Override
	public void run() {
		while(true) {
			if(this.afficheur.getFuture()!=null) {
				try {
					this.future = this.afficheur.getFuture();
					Integer i = this.future.get();
					this.afficheur.setVal(Integer.toString(i));
					this.afficheur.setFuture(null);
					this.future = null;
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
