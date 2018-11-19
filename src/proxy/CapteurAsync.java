package proxy;

import java.util.concurrent.Future;

import afficheur.ObservateurCapteur;

public interface CapteurAsync {

	public Future<Integer> getValue();
	
	public void attach(ObservateurCapteur observateur);
	
	public void detach(ObservateurCapteur observateur);
	
	public void notifyObservers();
}
