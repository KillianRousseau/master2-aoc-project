package proxy;

import java.util.concurrent.Future;

import capteur.Capteur;

/**
 * L'interface ObservateurCapteurAsync occupe le rôle de Service dans le pattern Active Object de la fonction Update
 * @author Killian Rousseau, Emilien Petit 
 */
public interface ObservateurCapteurAsync {

	/**
	 * Fonction permettant de réaliser l'action Update du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler
	 * @param capteur : Capteur ayant appelé le Update (Client du Active Object)
	 * @return un Future créé par un scheduler
	 */
	public Future<Object> update(Capteur capteur);

}
