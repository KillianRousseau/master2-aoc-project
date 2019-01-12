package capteur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memento.CapteurMemento;
import strategy.AlgoDiffusion;

/**
 * La classe CapteurImpl implémente l'interface Capteur pour occuper un rôle de générateur dans l'application.
 * Elle étend également la classe JFrame pour créer une interface en Swing montrant la valeur du générateur.
 * @author Killian Rousseau, Emilien Petit
 *
 */
public class CapteurImpl extends JFrame implements Capteur{

	private static final long serialVersionUID = -2095705696902692910L;
	
	/**
	 * Valeur actuelle du générateur de valeurs
	 */
	private int currentValue;
	
	/**
	 * Long défini permettant de laisser une seconde de repos entre chaque valeur générée par le Thread
	 */
	private final long TIME = 1000;
	
	/**
	 * Composant Swing pour créer une zone de contenu dans la fenêtre
	 */
	private JPanel panel;
	
	/**
	 * Composant Swing contenant la valeur actuelle affichée par l'Afficheur
	 */
	private JLabel label;
	
	/**
	 * Algorithme de diffusion utilisé par le générateur de valeurs
	 */
	private AlgoDiffusion algoDiffusion;
	
	/**
	 * Constructeur du CapteurImpl permettant principalement de créer une fenêtre Swing
	 * @param locationX : entier X correspondant à la position X de la fenêtre à créer
	 * @param locationY : entier Y correspondant à la position Y de la fenêtre à créer
	 */
	public CapteurImpl(int locationX, int locationY) {
		
		setTitle("Générateur");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(300, 500));
	    setLocation(locationX, locationY);
	    
	    this.setLayout(new GridBagLayout());
	    this.panel = new JPanel();
	    this.label = new JLabel(""+currentValue);
	    this.label.setFont(new Font("Courier New", Font.BOLD, 60));
	    this.panel.add(this.label);
	    this.add(this.panel);
	    pack();
	    setVisible(true);
	}
	
	/**
	 * La fonction setAlgoDiffusion permet de définir l'algorithme de diffusion du capteur
	 * @param algoDiffusion : Algorithme de diffusion que l'on souhaite utiliser
	 */
	@Override
	public void setAlgoDiffusion(AlgoDiffusion algoDiffusion) {
		this.algoDiffusion = algoDiffusion;
	}

	/**
	 * Fonction run du Thread du capteur 
	 * Permet d'incrémenter en boucle la valeur du générateur et d'exécuter l'algorithme de diffusion
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(TIME);
				setCurrentValue(++this.currentValue);
				if(this.algoDiffusion!= null) {
					this.algoDiffusion.execute();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
		
	/**
	 * Permet de récupérer la valeur courante générée par le capteur
	 * @return Un int correspondant à la valeur générée par le capteur 
	 */
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Permet de modifier la valeur courante du capteur et de mettre à jour le label de la fenêtre Swing
	 * @param currentValue : Nouvelle valeur du capteur
	 */
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
		this.label.setText(currentValue+"");
	}

	/**
	 * La fonction createMemento permet de créer et retourner un Memento enregistrant la valeur actuelle du Capteur
	 */
	@Override
	public CapteurMemento createMemento() {
		return new CapteurMemento(this.currentValue);
	}

}
