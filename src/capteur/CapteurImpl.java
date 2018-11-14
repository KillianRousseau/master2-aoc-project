package capteur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import strategy.AlgoDiffusion;

public class CapteurImpl extends JFrame implements Capteur{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2095705696902692910L;
	private int value;
	private long time;
	private JPanel panel;
	private JLabel label;
	
	private AlgoDiffusion algoDiffusion;
		
	public CapteurImpl(int locationX, int locationY) {
		
		setTitle("Générateur");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(300, 500));
	    setLocation(locationX, locationY);
	    
	    this.setLayout(new GridBagLayout());
	    this.panel = new JPanel();
	    this.label = new JLabel(""+value);
	    this.label.setFont(new Font("Courier New", Font.BOLD, 60));
	    this.panel.add(this.label);
	    this.add(this.panel);
	    pack();
	    setVisible(true);
	}

	@Override
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setAlgoDiffusion(AlgoDiffusion algoDiffusion) {
		this.algoDiffusion = algoDiffusion;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setValue(getValue()+1);
			if(this.algoDiffusion!= null) {
				this.algoDiffusion.execute();
			}
		}
	}
}
