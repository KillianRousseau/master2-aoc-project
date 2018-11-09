package callable;

import java.util.concurrent.Callable;

import observer.Capteur;

public class GetValue<V> implements Callable<V>{

	public GetValue(Capteur capteur) {

	}

	@Override
	public V call() {

		return null;
	}

}
