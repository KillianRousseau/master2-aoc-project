package capteur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import strategy.AlgoDiffusion;

/**
 * La classe CapteurImpl implémente l'interface Capteur pour occuper un rôle de générateur dans l'application.
 * Elle étend également la classe JFrame pour créer une interface en Swing montrant la valeur du générateur.
 * @author Killian Rousseau, Emilien Petit
 *
 */
public class CapteurImpl extends JFrame implements Capteur{

	private static final long serialVersionUID = -2095705696902692910L;
	private int currentValue;
	private final long TIME = 1000;
	private JPanel panel;
	private JLabel label;
	
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
	 * La fonction getValue permet d'obtenir la valeur actuelle par l'algorithme de diffusion
	 * @return Un Integer correspondant à la valeur actuelle de l'algorithme
	 */
	@Override
	public Integer getValue() {
		return this.algoDiffusion.getValue();
	}
	
	
	/**
	 * La fonction setAlgoDiffusion permet de définir l'algorithme de diffusion du capteur
	 * @param Algorithme de diffusion que l'on souhaite utiliser
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
}
