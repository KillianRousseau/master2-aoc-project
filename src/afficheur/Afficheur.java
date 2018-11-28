package afficheur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import proxy.Canal;
import proxy.CapteurAsync;

/**
 * La classe Afficheur implémente l'interface ObservateurCapteur pour occuper le rôle de moniteur dans l'application.
 * Elle étend également la classe JFrame pour créer une interface en Swing montrant la valeur actuelle du moniteur.
 * @author Killian Rousseau, Emilien Petit
 */
public class Afficheur extends JFrame implements ObservateurCapteur{

	private static final long serialVersionUID = -8822264027392620676L;
	private Canal canal;
	private String val;
	private JPanel panel;
	private JLabel label;
	private Future<Integer> future;
	
	/**
	 * Constructeur de l'afficheur permettant principalement de créer une fenêtre Swing
	 * @param locationX : entier X correspondant à la position X de la fenêtre à créer
	 * @param locationY : entier Y correspondant à la position Y de la fenêtre à créer
	 */
	public Afficheur(int locationX, int locationY) {
		this(null, locationX, locationY);
	}
	
	/**
	 * Constructeur de l'afficheur permettant principalement de créer une fenêtre Swing en prenant un canal en paramètre
	 * @param canal : Canal lié a l'afficheur
	 * @param locationX : entier X correspondant à la position X de la fenêtre à créer
	 * @param locationY : entier Y correspondant à la position Y de la fenêtre à créer
	 */
	public Afficheur(Canal canal, int locationX,int locationY) {
		this.canal = canal;
		this.val = "0";
		
		setTitle("Afficheur");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(500, 400));
	    setLocation(locationX, locationY);
	    
	    this.setLayout(new GridBagLayout());
	    this.panel = new JPanel();
	    this.label = new JLabel(val);
	    this.label.setFont(new Font("Courier New", Font.BOLD, 60));
	    this.panel.add(this.label);
	    this.add(this.panel);
	    pack();
	    setVisible(true);        
          
	}
	
	/**
	 * La méthode update permet d'indiquer à l'afficheur de se mettre à jour
	 * @param capteurAsync : non utilisé
	 */
	@Override
	public synchronized void update(CapteurAsync capteur) {
		if(this.canal != null) {
			this.future = this.canal.getValue();
		}
	}
	
	/**
	 * Accesseur permettant de modifier le Canal lié à l'afficheur
	 * @param c : Canal à lier à notre afficheur
	 */
	public void setCanal(Canal c) {
		this.canal = c;
	}
	
	/**
	 * Accesseur permettant de récupérer le canal actuel lié à l'afficheur
	 * @return Canal : le canal actuel de l'afficheur
	 */
	public Canal getCanal() {
		return this.canal;
	}

	/**
	 * Accesseur permettant de modifier la valeur de l'afficheur
	 * @param val : nouvelle valeur à affecter
	 */
	@Override
	public synchronized void setVal(String val) {
		this.val = val;
		this.label.setText(this.val);
	}

	/**
	 * Accesseur permettant de récupérer le Future possédé par l'afficheur
	 * @return le Future actuel de l'observateur
	 */
	@Override
	public synchronized Future<Integer> getFuture() {
		return future;
	}

	/**
	 * Accesseur permettant de modifier le Future actuel de l'afficheur,
	 * notamment si sa valeur a été consommée par le Thread de l'afficheur
	 * @param future : nouveau Future<Integer>
	 */
	@Override
	public synchronized void setFuture(Future<Integer> future) {
		this.future = future;
	}

}
