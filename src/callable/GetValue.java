package callable;

import java.util.concurrent.Callable;

import capteur.Capteur;

/**
 * GetValue implémente l'interface Callable pour réaliser l'action getValue et retourner une valeur entière.
 * @author Killian Rousseau, Emilien Petit
 * @param <V> type de valeur retournée par le Callable GetValue, Integer suggéré.
 */
public class GetValue<V> implements Callable<Integer>{

	private Capteur capteur;
	
	/**
	 * Constructeur du Callable GetValue
	 * @param capteur : Capteur(Générateur) lié au GetValue 
	 */
	public GetValue(Capteur capteur) {
		this.capteur = capteur;
	}

	/**
	 * Fonction de l'interface Callable permettant de réaliser l'action getValue depuis le capteur attribut de la classe
	 */
	@Override
	public Integer call() {
		Integer val = this.capteur.getValue();
		return val;
	}

}
