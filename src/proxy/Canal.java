package proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import afficheur.CapteurAsync;
import callable.GetValue;
import callable.Update;
import capteur.Capteur;

public class Canal implements CapteurAsync, ObservateurCapteurAsync{

	private ObservateurCapteur observateur;
	private Capteur capteur;
	private ScheduledExecutorService scheduler;
	
	public Canal(ScheduledExecutorService scheduler) {
		this(null,null,scheduler);
	}

	public Canal(Capteur capteur, ObservateurCapteur observateur, ScheduledExecutorService scheduler) {
		this.capteur = capteur;
		this.observateur = observateur;
		this.scheduler = scheduler;
	}
	
	@Override
	public Future<Integer> update(Capteur generateur) {
		Update<Integer> update = new Update<Integer>(this);
		return scheduler.schedule(update, 200, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Integer> getValue() {
		GetValue<Integer> getValue = new GetValue<Integer>(this.capteur,this.observateur);
		return scheduler.schedule(getValue, 100, TimeUnit.MILLISECONDS);
	}

	
	// SETTERS 
	
	public void setObservateur(ObservateurCapteur observateur) {
		this.observateur = observateur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

}
