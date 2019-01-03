package main;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe à exécuter possédant un main permettant de créer une interface Swing simple
 * pour sélectionner l'algorithme de diffusion à utiliser dans l'application
 * @author Killian Rousseau, Emilien Petit 
 */
public class ChooseDiffusion {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Choisir sa diffusion");
		JPanel panel;
		JButton atomique = new JButton("Diffusion atomique");
		JButton sequentielle = new JButton("Diffusion séquentielle");
		JButton causale = new JButton("Diffusion causale");
		String[] diffusion = new String[1];
		
		//Ajouter un listener au clic sur le bouton atomique pour lancer la classe Main avec le bon paramètre
		atomique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diffusion[0] = "atomique";
				Main.main(diffusion);
				frame.setVisible(false);
			}
		});
		
		//Ajouter un listener au clic sur le bouton sequentielle pour lancer la classe Main avec le bon paramètre
		sequentielle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diffusion[0] = "sequentielle";
				Main.main(diffusion);
				frame.setVisible(false);
			}
		});
		
		causale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				diffusion[0] = "causale";
				Main.main(diffusion);
				frame.setVisible(false);
			}
			
		});
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(800, 400));
	    frame.setLocation(200, 200);
	    
	    frame.setLayout(new GridBagLayout());
	    panel = new JPanel();
	    panel.add(atomique);
	    panel.add(sequentielle);
	    panel.add(causale);
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);  
	}

}
