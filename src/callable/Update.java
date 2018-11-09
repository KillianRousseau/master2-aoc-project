package callable;

import java.util.concurrent.Callable;

import observer.Capteur;

public class Update<V> implements Callable<V>{

	private Capteur generateur;
	
	public Update(Capteur generateur) {
		this.generateur = generateur;
	}
	@Override
	public V call() {
		return null;
	}

}
