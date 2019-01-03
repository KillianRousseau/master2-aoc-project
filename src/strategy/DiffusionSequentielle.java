package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import memento.CapteurCreateur;
import memento.CapteurMemento;
import proxy.ObservateurCapteurAsync;

/**
 * La classe DiffusionSequentielle implémente une stratégie d'algorithme de diffusion à utiliser dans l'application
 * Prend également un rôle de Subject dans un pattern Observer avec les ObservateurCapteurAsync comme observateurs
 * @author Killian Rousseau, Emilien Petit 
 */
public class DiffusionSequentielle implements AlgoDiffusion{

	/**
	 * Liste des observateurs du Subject DiffusionSequentielle
	 */
	private List<ObservateurCapteurAsync> observateurs;
	
	/**
	 * CapteurMemento utilisé pour sauvegarder un état du Capteur pendant la diffusion
	 */
	private CapteurMemento capteurMemento;
	
	/**
	 * CapteurCreateur qui va nous permettre de créer et de restaurer des Memento du capteur pendant la diffusion
	 */
	private CapteurCreateur capteurCreateur;
	
	/**
	 * Compteur servant à savoir le nombre d'observateurs ayant récupéré la dernière valeur de l'algorithme de diffusion
	 */
	private int obsCount;
	
	/**
	 * Booléen utilisé pour savoir si un des observateurs à récupérer la valeur actuelle de l'algorithme de diffusion
	 * afin de bloquer la récupération des valeurs du générateurs tant que tous les observateurs n'ont pas récupéré cette valeur
	 */
	public boolean diffusionStarted;
	
	/**
	 * Constructeur de DiffusionSequentielle initialisant tous ses attributs
	 * @param capteurCreateur : CapteurCreateur à partir duquel on va créer les Memento
	 */
	public DiffusionSequentielle(CapteurCreateur capteurCreateur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteurCreateur = capteurCreateur;
		this.obsCount = 0;
		this.capteurMemento = null;
	}
	
	/**
	 * Fonction de l'interface AlgoDiffusion permettant d'exécuter la stratégie de diffusion
	 * Vérifie que tous les observateurs ont bien récupéré la dernière valeur avant de récupérer une nouvelle valeur et de notifier ses observateurs
	 */
	@Override
	public void execute() {
		if(this.obsCount == this.observateurs.size()) {
			this.diffusionStarted = false;
		}
		
		if(!this.diffusionStarted) {
			this.diffusionStarted = true;	
			this.capteurMemento = capteurCreateur.createMemento();
			notifyObservers();
			
			this.obsCount = 0;
		}
		
	}
	
	/**
	 * Fonction du pattern Observer permettant d'attacher un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à ajouter à la liste d'observateurs
	 */
	@Override
	public void attach(ObservateurCapteurAsync observateur) {
		this.observateurs.add(observateur);
		this.obsCount = this.observateurs.size();
	}

	/**
	 * Fonction du pattern Observer permettant de retirer un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à retirer à la liste d'observateurs
	 */
	@Override
	public void detach(ObservateurCapteurAsync observateur) {
		this.observateurs.remove(observateur);
		this.obsCount = this.observateurs.size();
	}

	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs 
	 */
	@Override
	public void notifyObservers() {
		for(ObservateurCapteurAsync obs : this.observateurs) {
			Future<Void> f = obs.update(capteurMemento);
			// On crée un thread qui va appeler la fonction bloquante get du Future
			// On va incrémenter notre compteur une fois que le get est terminé
			Thread t = new Thread() {
			    public void run() {
			    	try {
						f.get();
				    	obsCount++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
			    }
			};
			t.start();
			
		}		
	}

}
