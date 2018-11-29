package strategy;

import java.util.ArrayList;
import java.util.List;

import capteur.Capteur;
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
	 * Capteur générant les valeurs à récupérer par l'algorithme de diffusion
	 */
	private Capteur capteur;
	
	/**
	 * Valeur actuelle de l'algorithme de diffusion
	 */
	private Integer currentValue;
	
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
	 * @param capteur : Capteur à partir duquel récupérer les valeurs générées
	 */
	public DiffusionSequentielle(Capteur capteur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteur = capteur;
		this.obsCount = 0;
		this.currentValue = null;
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
			this.currentValue = this.capteur.getCurrentValue();
			
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
	 * Accesseur permettant de récupérer la valeur actuelle de l'algorithme de diffusion
	 * Incrémente également le compteur obsCount utilisé dans execute
	 * @return Integer de la valeur actuelle de l'algorithme
	 */
	@Override
	public Integer getValue() {
		this.obsCount++;
		return this.currentValue;
	}
	
	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs 
	 */
	@Override
	public void notifyObservers() {
		for(ObservateurCapteurAsync obs : this.observateurs) {
			obs.update(capteur);
		}		
	}

}
