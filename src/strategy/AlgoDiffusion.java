package strategy;

import proxy.ObservateurCapteurAsync;

/**
 * Interface AlgoDiffusion permettant de mettre en place un pattern Strategy pour l'algorithme de diffusion à utiliser dans l'application
 * Prend également un rôle de Subject dans un pattern Observer avec les ObservateurCapteurAsync comme observateurs
 * @author Killian Rousseau, Emilien Petit 
 */
public interface AlgoDiffusion {
	
	/**
	 * Fonction du pattern Strategy permettant d'exécuter la stratégie de diffusion
	 */
	public void execute();
	
	/**
	 * Fonction du pattern Observer permettant d'attacher un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à ajouter à la liste d'observateurs
	 */
	public void attach(ObservateurCapteurAsync observateur);
	
	/**
	 * Fonction du pattern Observer permettant de retirer un observateur de type ObservateurCapteurAsync au Subject
	 * @param observateur : ObservateurCapteurAsync à retirer à la liste d'observateurs
	 */
	public void detach(ObservateurCapteurAsync observateur);
	
	/**
	 * Fonction du pattern Observer permettant de notifier tous les observateurs 
	 */
	public void notifyObservers();
	
	/**
	 * Accesseur permettant de récupérer la valeur actuelle de l'algorithme de diffusion
	 * @return Integer de la valeur actuelle de l'algorithme
	 */
	public Integer getValue();
}
