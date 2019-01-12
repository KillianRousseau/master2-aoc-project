package memento;

/**
 * CapteurCreateur est l'interface Createur (Originator) dans le patron de conception Memento
 * @author Killian Rousseau, Emilien Petit
 *
 */
public interface CapteurCreateur {

	public CapteurMemento createMemento();
}
