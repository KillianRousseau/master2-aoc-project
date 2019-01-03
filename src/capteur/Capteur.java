package capteur;

import memento.CapteurCreateur;
import strategy.AlgoDiffusion;

/**
 * L'interface Capteur occupe le rôle de générateur dans l'application.
 * Étend l'interface Runnable pour que le Capteur soit un Thread.
 * Étend égalemnt l'interface CapteurCreateur pour pouvoir gérer des Mementos du Capteur
 * @author Killian Rousseau, Emilien Petit
 *
 */
public interface Capteur extends Runnable, CapteurCreateur{

	/**
	 * La fonction setAlgoDiffusion permet de définir l'algorithme de diffusion du capteur
	 * @param diffusion : Algorithme de diffusion que l'on souhaite utiliser
	 */
	public void setAlgoDiffusion(AlgoDiffusion diffusion);
	
	/**
	 * Permet de récupérer la valeur courante générée par le capteur
	 * @return Un int correspondant à la valeur générée par le capteur 
	 */
	public int getCurrentValue();
}
