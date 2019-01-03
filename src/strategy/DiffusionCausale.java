package strategy;

import java.util.ArrayList;
import java.util.List;

import memento.CapteurCreateur;
import memento.CapteurMemento;
import proxy.ObservateurCapteurAsync;

/**
 * La classe DiffusionCausale implémente une stratégie d'algorithme de diffusion à utiliser dans l'application
 * Prend également un rôle de Subject dans un pattern Observer avec les ObservateurCapteurAsync comme observateurs
 * @author Killian Rousseau, Emilien Petit 
 */

public class DiffusionCausale implements AlgoDiffusion{

	/**
	 * Liste des observateurs du Subject DiffusionAtomique
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
	 * Constructeur de DiffusionCausale initialisant tous ses attributs
	 * @param capteurCreateur : CapteurCreateur à partir duquel on va créer les Memento
	 */
	public DiffusionCausale(CapteurCreateur capteurCreateur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteurMemento = null;
		this.capteurCreateur = capteurCreateur;
	}

	/**
	 * Fonction de l'interface AlgoDiffusion permettant d'exécuter la stratégie de diffusion
	 * Vérifie que tous les observateurs ont bien récupéré la dernière valeur avant de notifier ses observateurs
	 */
	@Override
	public void execute() {
		this.capteurMemento = this.capteurCreateur.createMemento();
		notifyObservers();
	}
	
	/**
	 * Fonction du pattern Observer permettant d'attacher un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à ajouter à la liste d'observateurs
	 */
	@Override
	public void attach(ObservateurCapteurAsync observateur) {
		this.observateurs.add(observateur);
	}

	/**
	 * Fonction du pattern Observer permettant de retirer un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à retirer à la liste d'observateurs
	 */
	@Override
	public void detach(ObservateurCapteurAsync observateur) {
		this.observateurs.remove(observateur);
	}
	

	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs
	 */
	@Override
	public void notifyObservers() {
		for(ObservateurCapteurAsync obs : this.observateurs) {
			obs.update(this.capteurMemento);
		}		
	}
}
