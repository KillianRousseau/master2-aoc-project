package callable;

import java.util.concurrent.Callable;

import capteur.Capteur;
import proxy.ObservateurCapteur;

public class GetValue<V> implements Callable<V>{

	public GetValue(Capteur capteur, ObservateurCapteur observateur) {
		
	}

	@Override
	public V call() {

		return null;
	}

}
