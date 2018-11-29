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
	 * Le Capteur (rôle de Générateur de valeur) lié au Canal
	 */
	private Capteur capteur;
	
	/**
	 * Le Scheduler permettant de gérer les différentes méthodes invoquées par des Callable
	 */
	private ScheduledExecutorService scheduler;
	
	/**
	 * Constructeur de Canal affectant à null le capteur et affectant le scheduler à utiliser
	 * @param scheduler : Scheduler permettant de gérer les différentes méthodes invoquées par des Callable
	 */
	public Canal(ScheduledExecutorService scheduler) {
		this(null,scheduler);
	}

	/**
	 * Constructeur de Canal affectant le capteur et le scheduler à utiliser
	 * @param capteur : Le Capteur (rôle de Générateur de valeur) lié au Canal
	 * @param scheduler : Scheduler permettant de gérer les différentes méthodes invoquées par des Callable
	 */
	public Canal(Capteur capteur, ScheduledExecutorService scheduler) {
		this.capteur = capteur;
		this.observateurs = new ArrayList<ObservateurCapteur>();
		this.scheduler = scheduler;
	}
	
	/**
	 * Fonction permettant de réaliser l'action Update du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler.
	 * Un random est utilisé dans le schedule pour simuler des durées différentes à l'exécution des actions
	 * @param capteur : Capteur ayant appelé le Update (Client du Active Object)
	 * @return un Future créé par un scheduler
	 */
	@Override
	public Future<Object> update(Capteur capteur) {
		Update<Integer> update = new Update<Integer>(this);
		return scheduler.schedule(update, (long)(Math.random()*600L+100L), TimeUnit.MILLISECONDS);
	}

	/**
	 * Fonction permettant de réaliser l'action getValue du pattern Active Object en créant un Callable 
	 * et en retournant le Future créé par un scheduler.
	 * Un random est utilisé dans le schedule pour simuler des durées différentes à l'exécution des actions
	 * @return un Future créé par un scheduler
	 */
	@Override
	public Future<Integer> getValue() {
		GetValue<Integer> getValue = new GetValue<Integer>(this.capteur);
		
		return scheduler.schedule(getValue, (long)(Math.random()*600L+100L), TimeUnit.MILLISECONDS);
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


	/**
	 * Accesseur permettant de modifier le Capteur(Générateur) lié au Canal
	 * @param capteur : Nouveau capteur à lier au Canal
	 */
	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

}
