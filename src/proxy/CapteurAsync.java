package proxy;

import java.util.concurrent.Future;

import afficheur.ObservateurCapteur;

/**
 * L'interface CapteurAsync occupe le rôle de Service dans le pattern Active Object de la fonction getValue
 * Elle occupe également un rôle de Subject du pattern Observer, en ayant des ObservateurCapteurs(Afficheur) comme observateurs
 * @author Killian Rousseau, Emilien Petit 
 */
public interface CapteurAsync {

	/**
	 * Fonction permettant de réaliser l'action getValue du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler
	 * @return un Future créé par un scheduler
	 */
	public Future<Integer> getValue();
	
	/**
	 * Fonction du pattern Observer permettant d'attacher un observateur de type ObservateurCapteur au Subject CapteurAsync
	 * @param observateur : ObservateurCapteur à ajouter à la liste d'observateurs
	 */
	public void attach(ObservateurCapteur observateur);
	
	/**
	 * Fonction du pattern Observer permettant de retirer un observateur de type ObservateurCapteur au Subject CapteurAsync
	 * @param observateur : ObservateurCapteur à retirer à la liste d'observateurs
	 */
	public void detach(ObservateurCapteur observateur);
	
	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs 
	 */
	public void notifyObservers();
}
