package callable;

import java.util.concurrent.Callable;

import proxy.CapteurAsync;

public class Update<V> implements Callable<Object>{

	private CapteurAsync capteurAsync;
	
	public Update(CapteurAsync capteurAsync) {
		this.capteurAsync = capteurAsync;
	}
	
	@Override
	public Object call() {
		this.capteurAsync.notifyObservers();
		return null;
	}

}
