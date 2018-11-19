package proxy;

import java.util.concurrent.Future;

import capteur.Capteur;

public interface ObservateurCapteurAsync {

	public Future<Object> update(Capteur capteur);

}
