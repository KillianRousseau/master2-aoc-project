package afficheur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import proxy.Canal;
import proxy.ObservateurCapteur;

public class Afficheur extends JFrame implements ObservateurCapteur{

	private Canal canal;
	private String val;
	private JPanel panel;
	private JLabel label;
	
	public Afficheur(int locationX, int locationY) {
		this(null, locationX, locationY);
	}
	
	public Afficheur(Canal canal, int locationX,int locationY) {
		this.canal = canal;
		this.val = "";
		
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
	public Future<Integer> update(CapteurAsync capteur) {
		if(this.canal != null) {
			Future<Integer> f = this.canal.getValue();
			try {
				this.val = f.get().toString();
				this.label.setText(this.val);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return f;
		}
		return null;
		
	}
	
	public void setCanal(Canal c) {
		this.canal = c;
	}
	
	public Canal getCanal() {
		return this.canal;
	}

}
