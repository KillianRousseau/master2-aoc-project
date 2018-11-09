package proxy;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import callable.GetValue;
import callable.Update;
import observer.Capteur;
import observer.CapteurAsync;
import observer.ObservateurCapteur;

public class Canal implements CapteurAsync, ObservateurCapteur{

	private List<ObservateurCapteur> observateurs;
	private Capteur capteur;
	private ScheduledExecutorService scheduler;
	
	public Canal(ScheduledExecutorService scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	public Future<Integer> update(Capteur generateur) {
		Update<Integer> update = new Update<Integer>(generateur);
		return scheduler.schedule(update, 200, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Integer> getValue() {
		GetValue<Integer> getValue = new GetValue<Integer>(capteur);
		return scheduler.schedule(getValue, 100, TimeUnit.MILLISECONDS);
	}


}
