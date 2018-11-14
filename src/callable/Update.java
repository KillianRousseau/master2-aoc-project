package callable;

import java.util.concurrent.Callable;

import afficheur.CapteurAsync;
import capteur.Capteur;

public class Update<V> implements Callable<V>{

	private CapteurAsync capteurAsync;
	
	public Update(CapteurAsync capteurAsync) {
		this.capteurAsync = capteurAsync;
	}
	@Override
	public V call() {
		return null;
	}

}
