package afficheur;

import java.util.concurrent.Future;

import proxy.CapteurAsync;

public interface ObservateurCapteur{
	
	public void update(CapteurAsync capteurAsync);
	
	public void setVal(String val);
	
	public Future<Integer> getFuture();
	
	public void setFuture(Future<Integer> future);
}
