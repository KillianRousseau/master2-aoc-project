package strategy;

import java.util.ArrayList;
import java.util.List;

import capteur.Capteur;
import proxy.ObservateurCapteurAsync;

public class DiffusionSequentielle implements AlgoDiffusion{

	
	private List<ObservateurCapteurAsync> observateurs;
	private Capteur capteur;
	private Integer currentValue;
	private int obsCount;
	public boolean diffusionStarted;
	
	public DiffusionSequentielle(Capteur capteur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteur = capteur;
		this.obsCount = 0;
		this.currentValue = null;
	}
	
	@Override
	public void execute() {
		if(this.obsCount == this.observateurs.size()) {
			this.diffusionStarted = false;
		}
		
		if(!this.diffusionStarted) {
			this.diffusionStarted = true;			
			this.currentValue = this.capteur.getCurrentValue();
			
			for(ObservateurCapteurAsync obs : this.observateurs) {
				obs.update(capteur);
			}
			this.obsCount = 0;
		}
		
	}

	@Override
	public void attach(ObservateurCapteurAsync observateur) {
		this.observateurs.add(observateur);
		this.obsCount = this.observateurs.size();
	}

	@Override
	public void detach(ObservateurCapteurAsync observateur) {
		this.observateurs.remove(observateur);
		this.obsCount = this.observateurs.size();
	}

	@Override
	public Integer getValue() {
		this.obsCount++;
		return this.currentValue;
	}

}
