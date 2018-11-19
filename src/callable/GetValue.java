package callable;

import java.util.concurrent.Callable;

import capteur.Capteur;

public class GetValue<V> implements Callable<Integer>{

	private Capteur capteur;
	
	public GetValue(Capteur capteur) {
		this.capteur = capteur;
	}

	@Override
	public Integer call() {
		Integer val = this.capteur.getValue();
		return val;
	}

}
