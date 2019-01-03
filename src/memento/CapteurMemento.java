package memento;

/**
 * CapteurMemento est la classe Memento du patron de conception Memento pour enregistrer un Capteur
 * @author Killian Rousseau, Emilien Petit
 *
 */
public class CapteurMemento {

	/**
	 * Etat du Capteur créé
	 */
	private Integer state;
	
	/**
	 * Constructeur de CapteurMemento permettant d'initialiser la valeur de l'état
	 * @param state : entier correspondant à l'état du capteur lors de la création du Memento.
	 */
	public CapteurMemento(Integer state) {
		this.state = state;
	}
	
	/**
	 * La fonction getState permet de récupérer l'état du CapteurMemento
	 * @return Integer : état du CapteurMemento 
	 */
	public synchronized Integer getState() {
		return this.state;
	}
}
