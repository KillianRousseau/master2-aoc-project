package main;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChooseDiffusion {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Choisir sa diffusion");
		JPanel panel;
		JButton atomique = new JButton("Diffusion atomique");
		JButton sequentielle = new JButton("Diffusion séquentielle");
		String[] diffusion = new String[1];
		atomique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diffusion[0] = "atomique";
				Main.main(diffusion);
				frame.setVisible(false);
			}
		});
		sequentielle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diffusion[0] = "sequentielle";
				Main.main(diffusion);
				frame.setVisible(false);
			}
		});
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(500, 400));
	    frame.setLocation(200, 200);
	    
	    frame.setLayout(new GridBagLayout());
	    panel = new JPanel();
	    panel.add(atomique);
	    panel.add(sequentielle);
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);  
	}

}
