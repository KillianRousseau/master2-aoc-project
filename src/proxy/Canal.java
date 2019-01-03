package proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import afficheur.ObservateurCapteur;
import callable.GetValue;
import callable.Update;
import memento.CapteurMemento;

/**
 * La classe Canal occupe le rôle de Proxy dans le pattern Active Object de l'application.
 * Il implémente donc les interfaces CapteurAsync et ObservateurCapteurAsync pour servir de proxy pour chaque méthode (update et getValue)
 * @author Killian Rousseau, Emilien Petit 
 */
public class Canal implements CapteurAsync, ObservateurCapteurAsync{

	/**
	 * La liste des observateurs du Subject Canal
	 */
	private List<ObservateurCapteur> observateurs;
	
	/**
	 * Le CapteurMemento qui contient l'état du capteur lors de l'update et à récupérer lors du getValue
	 */
	private CapteurMemento capteurMemento;
	
	/**
	 * Le Scheduler permettant de gérer les différentes méthodes invoquées par des Callable
	 */
	private ScheduledExecutorService scheduler;
	
	 /** Constructeur de Canal affectant le capteur et le scheduler à utiliser
	 * @param scheduler : Scheduler permettant de gérer les différentes méthodes invoquées par des Callable
	 */
	public Canal(ScheduledExecutorService scheduler) {
		this.observateurs = new ArrayList<ObservateurCapteur>();
		this.scheduler = scheduler;
	}
	
	/**
	 * Fonction permettant de réaliser l'action Update du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler.
	 * Un random est utilisé dans le schedule pour simuler des durées différentes à l'exécution des actions
	 * @param capteurMemento : CapteurMemento contenant l'état du capteur lors de l'update
	 * @return un Future créé par un scheduler
	 */
	@Override
	public Future<Void> update(CapteurMemento capteurMemento) {
		this.capteurMemento = capteurMemento;
		Callable<Void> update = new Update<Void>(this);
		return scheduler.schedule(update, (long)(Math.random()*600L+400L), TimeUnit.MILLISECONDS);
	}

	/**
	 * Fonction permettant de réaliser l'action getValue du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler.
	 * Un random est utilisé dans le schedule pour simuler des durées différentes à l'exécution des actions
	 * @return un Future créé par un scheduler
	 */
	@Override
	public Future<Integer> getValue() {
		Callable<Integer> getValue = new GetValue<Integer>(this.capteurMemento);
		return scheduler.schedule(getValue, (long)(Math.random()*600L+400L), TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Fonction du pattern Observer permettant d'attacher un observateur de type ObservateurCapteur du Subject Canal
	 * @param observateur : ObservateurCapteur à ajouter à la liste d'observateurs
	 */
	@Override
	public void attach(ObservateurCapteur observateur) {
		this.observateurs.add(observateur);
	}
	
	/**
	 * Fonction du pattern Observer permettant de retirer un observateur de type ObservateurCapteur du Subject Canal
	 * @param observateur : ObservateurCapteur à retirer à la liste d'observateurs
	 */
	@Override
	public void detach(ObservateurCapteur observateur) {
		this.observateurs.remove(observateur);
	}
	
	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs 
	 */
	@Override
	public void notifyObservers() {
		for(ObservateurCapteur obsCapteur : this.observateurs) {
			obsCapteur.update(this);
		}
	}
}
