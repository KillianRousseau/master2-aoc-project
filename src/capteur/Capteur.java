package capteur;

import strategy.AlgoDiffusion;

public interface Capteur extends Runnable{

	public Integer getValue();
	
	public void setAlgoDiffusion(AlgoDiffusion diffusion);
	
	public int getCurrentValue();
}
