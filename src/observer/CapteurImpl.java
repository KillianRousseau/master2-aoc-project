package observer;

import java.util.List;

public class CapteurImpl implements Capteur, Runnable{

	private int value;
	private long time;
	
	private List<ObservateurCapteur> observateurs;
	
	@Override
	public void attach(ObservateurCapteur o) {
		observateurs.add(o);
		
	}

	@Override
	public void detach(ObservateurCapteur o) {
		observateurs.remove(o);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setValue(getValue()+1);
			for(ObservateurCapteur o : observateurs) {
				o.update(this);
			}
		}
	}
}
