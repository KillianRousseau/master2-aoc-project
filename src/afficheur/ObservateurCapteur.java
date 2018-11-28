package afficheur;

import java.util.concurrent.Future;

import proxy.CapteurAsync;

/**
 * L'interface ObservateurCapteur occupe le rôle de ObservateurGenerateur dans l'application.
 * Occupe également le rôle de servant dans le pattern Active Object de l'update, et le rôle de client dans celui du getValue
 * @author Killian Rousseau, Emilien Petit
 *
 */
public interface ObservateurCapteur{
	
	/**
	 * La méthode update permet d'indiquer à l'observateur du capteur de se mettre à jour
	 * @param capteurAsync : non utilisé
	 */
	public void update(CapteurAsync capteurAsync);
	
	/**
	 * Accesseur permettant de modifier la valeur de l'observateur du capteur
	 * @param val : nouvelle valeur à affecter
	 */
	public void setVal(String val);
	
	/**
	 * Accesseur permettant de récupérer le Future possédé par l'observateur
	 * Celui-ci est créé et envoyé par le proxy de l'active object
	 * @return le Future actuel de l'observateur ou null si celui-ci a été utilisé
	 */
	public Future<Integer> getFuture();
	
	/**
	 * Accesseur permettant de modifier le Future actuel de l'observateur,
	 *  notamment si sa valeur a été consommée par le Thread de l'afficheur
	 * @param future : nouveau Future<Integer>
	 */
	public void setFuture(Future<Integer> future);
}
