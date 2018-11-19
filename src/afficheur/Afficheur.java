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

public class Afficheur extends JFrame implements ObservateurCapteur{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8822264027392620676L;
	private Canal canal;
	private String val;
	private JPanel panel;
	private JLabel label;
	private Future<Integer> future;
	
	public Afficheur(int locationX, int locationY) {
		this(null, locationX, locationY);
	}
	
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
	
	@Override
	public synchronized void update(CapteurAsync capteur) {
		if(this.canal != null) {
			this.future = this.canal.getValue();
		}
	}
	
	public void setCanal(Canal c) {
		this.canal = c;
	}
	
	public Canal getCanal() {
		return this.canal;
	}

	public synchronized void setVal(String val) {
		this.val = val;
		this.label.setText(this.val);
	}

	public synchronized Future<Integer> getFuture() {
		return future;
	}

	public synchronized void setFuture(Future<Integer> future) {
		this.future = future;
	}

}
