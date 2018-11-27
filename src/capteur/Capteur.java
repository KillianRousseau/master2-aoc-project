package capteur;

import strategy.AlgoDiffusion;

/**
 * L'interface capteur occupe le rôle de générateur dans le pattern Observer.
 * @author Killian Rousseau, Emilien Petit
 *
 */
public interface Capteur extends Runnable{

	/**
	 * La fonction getValue permet d'obtenir la valeur actuelle par l'algorithme de diffusion
	 * @return Un Integer correspondant à la valeur actuelle de l'algorithme
	 */
	public Integer getValue();
	
	/**
	 * La fonction setAlgoDiffusion permet de définir l'algorithme de diffusion du capteur
	 * @param Algorithme de diffusion que l'on souhaite utiliser
	 */
	public void setAlgoDiffusion(AlgoDiffusion diffusion);
	
	/**
	 * Permet de récupérer la valeur courante générée par le capteur
	 * @return Un int correspondant à la valeur générée par le capteur 
	 */
	public int getCurrentValue();
}
