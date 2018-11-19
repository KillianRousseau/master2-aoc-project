package strategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import capteur.Capteur;
import proxy.ObservateurCapteurAsync;

public class DiffusionAtomique implements AlgoDiffusion{

	
	private List<ObservateurCapteurAsync> observateurs;
	private Capteur capteur;
	private Integer currentValue;
	private int obsCount;
	private Queue<Integer> queue;
	
	public DiffusionAtomique(Capteur capteur) {
		this.observateurs = new ArrayList<ObservateurCapteurAsync>();
		this.capteur = capteur;
		this.obsCount = 0;
		this.currentValue = null;
		this.queue = new ArrayDeque<>();
	}

	@Override
	public void execute() {
		queue.add(this.capteur.getCurrentValue());

		if(this.obsCount == this.observateurs.size()) {
			
			this.currentValue = queue.poll();
			
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
	
	public Integer getValue() {
		this.obsCount++;
		return this.currentValue;
	}
	
}
