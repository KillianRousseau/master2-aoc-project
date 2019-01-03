package callable;

import java.util.concurrent.Callable;

import memento.CapteurMemento;

/**
 * GetValue implémente l'interface Callable pour réaliser l'action getValue et retourner une valeur entière.
 * S'inscrit dans le rôle de Concrete MI dans le pattern ActiveObject
 * @author Killian Rousseau, Emilien Petit
 * @param <V> type de valeur retournée par le Callable GetValue, Integer suggéré.
 */
public class GetValue<V> implements Callable<Integer>{

	/**
	 * CapteurMemento à partir duquel récupérer la valeur
	 */
	private CapteurMemento capteurMemento;
	
	/**
	 * Constructeur du Callable GetValue
	 * @param capteurMemento : CapteurMemento contenant l'état du capteur à récupérer
	 */
	public GetValue(CapteurMemento capteurMemento) {
		this.capteurMemento = capteurMemento;
	}

	/**
	 * Fonction de l'interface Callable permettant de réaliser l'action getValue depuis le capteurMemento attribut de la classe
	 */
	@Override
	public Integer call() {
		return this.capteurMemento.getState();
	}

}
