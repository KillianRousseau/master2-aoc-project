package observer;

public interface Capteur {

	public void attach(ObservateurCapteur o);
	
	public void detach(ObservateurCapteur o);
	
	public int getValue();
}
