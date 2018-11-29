package strategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import capteur.Capteur;
import proxy.ObservateurCapteurAsync;

/**
 * La classe DiffusionAtomique implémente une stratégie d'algorithme de diffusion à utiliser dans l'application
 * Prend également un rôle de Subject dans un pattern Observer avec les ObservateurCapteurAsync comme observateurs
 * @author Killian Rousseau, Emilien Petit 
 */
public class DiffusionAtomique implements AlgoDiffusion{

	/**
	 * Liste des observateurs du Subject DiffusionAtomique
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
	 * Queue contenant toutes les valeurs dernières valeurs récupérées depuis le générateur(Capteur) et non consommées par les observateurs
	 */
	private Queue<Integer> queue;
	
	/**
	 * Constructeur de DiffusionAtomique initialisant tous ses attributs
	 * @param capteur : Capteur à partir duquel récupérer les valeurs générées
	 */
	public DiffusionAtomique(Capteur capteur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteur = capteur;
		this.obsCount = 0;
		this.currentValue = null;
		this.queue = new ArrayDeque<>();
	}

	/**
	 * Fonction de l'interface AlgoDiffusion permettant d'exécuter la stratégie de diffusion
	 * Vérifie que tous les observateurs ont bien récupéré la dernière valeur avant de changer sa valeur actuelle et de notifier ses observateurs
	 * Stocke toutes les valeurs du générateur dans une pile
	 */
	@Override
	public void execute() {
		queue.add(this.capteur.getCurrentValue());

		if(this.obsCount == this.observateurs.size()) {
			
			this.currentValue = queue.poll();
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
