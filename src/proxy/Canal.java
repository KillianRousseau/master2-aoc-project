package proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import afficheur.ObservateurCapteur;
import callable.GetValue;
import callable.Update;
import capteur.Capteur;

public class Canal implements CapteurAsync, ObservateurCapteurAsync{

	private List<ObservateurCapteur> observateurs;
	private Capteur capteur;
	private ScheduledExecutorService scheduler;
	
	public Canal(ScheduledExecutorService scheduler) {
		this(null,scheduler);
	}

	public Canal(Capteur capteur, ScheduledExecutorService scheduler) {
		this.capteur = capteur;
		this.observateurs = new ArrayList<ObservateurCapteur>();
		this.scheduler = scheduler;
	}
	
	@Override
	public Future<Object> update(Capteur capteur) {
		Update<Integer> update = new Update<Integer>(this);
		return scheduler.schedule(update, (long)(Math.random()*600L+100L), TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Integer> getValue() {
		GetValue<Integer> getValue = new GetValue<Integer>(this.capteur);
		
		return scheduler.schedule(getValue, (long)(Math.random()*600L+100L), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void attach(ObservateurCapteur observateur) {
		this.observateurs.add(observateur);
	}
	
	@Override
	public void detach(ObservateurCapteur observateur) {
		this.observateurs.remove(observateur);
	}
	
	@Override
	public void notifyObservers() {
		for(ObservateurCapteur obsCapteur : this.observateurs) {
			obsCapteur.update(this);
		}
	}


	// SETTERS 

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

}
