package proxy;

import observer.Capteur;
import observer.ObservateurCapteur;

public class Canal implements Capteur, ObservateurCapteur{

	@Override
	public void update(Capteur generateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attach(ObservateurCapteur o) {}

	@Override
	public void detach(ObservateurCapteur o) {}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}


}
