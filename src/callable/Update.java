package callable;

import java.util.concurrent.Callable;

import proxy.CapteurAsync;

/**
 * Update implémente l'interface Callable pour réaliser l'action d'update des afficheurs
 * S'inscrit dans le rôle de Concrete MI dans le pattern ActiveObject
 * @author Killian Rousseau, Emilien Petit
 * @param <V> type de valeur retournée par le Callable update, celui-ci retourne null donc non utilisé
 */
public class Update<V> implements Callable<Void>{

	/**
	 * CapteurAsync (Canal) servant de Subject pour notifier ses observateurs qu'il faut se mettre à jour
	 */
	private CapteurAsync capteurAsync;
	
	/**
	 * Constructeur du Callable GetValue
	 * @param capteurAsync : CapteurAsync(Canal) lié à l'update 
	 */
	public Update(CapteurAsync capteurAsync) {
		this.capteurAsync = capteurAsync;
	}
	
	/**
	 * Fonction de l'interface Callable permettant de réaliser l'action update via 
	 * le notifyObservers du capteurAsync attribut de la classe. Ce notify va update tous les afficheurs lié à un canal
	 */
	@Override
	public Void call() {
		this.capteurAsync.notifyObservers();
		return null;
	}

}
