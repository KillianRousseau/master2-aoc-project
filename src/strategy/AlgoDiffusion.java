package strategy;

import proxy.ObservateurCapteurAsync;

public interface AlgoDiffusion {
	
	public void execute();
	
	public void attach(ObservateurCapteurAsync observateur);
	
	public void detach(ObservateurCapteurAsync observateur);
	
	public Integer getValue();
}
