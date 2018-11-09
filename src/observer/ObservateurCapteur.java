package observer;

import java.util.concurrent.Future;

public interface ObservateurCapteur{
	
	public Future update(Capteur generateur);
}
