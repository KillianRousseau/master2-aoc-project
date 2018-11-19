package capteur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import strategy.AlgoDiffusion;

public class CapteurImpl extends JFrame implements Capteur{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2095705696902692910L;
	private int currentValue;
	private long time;
	private JPanel panel;
	private JLabel label;
	
	private AlgoDiffusion algoDiffusion;
		
	public CapteurImpl(int locationX, int locationY) {
		this.time = 1000;
		
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

	@Override
	public Integer getValue() {
		return this.algoDiffusion.getValue();
	}
	
	
	@Override
	public void setAlgoDiffusion(AlgoDiffusion algoDiffusion) {
		this.algoDiffusion = algoDiffusion;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(time);
				setCurrentValue(++this.currentValue);
				if(this.algoDiffusion!= null) {
					this.algoDiffusion.execute();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// ACCESSEURS 
	
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
		this.label.setText(currentValue+"");
	}
}
