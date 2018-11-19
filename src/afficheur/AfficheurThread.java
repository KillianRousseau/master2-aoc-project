package afficheur;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AfficheurThread implements Runnable {

	private Future<Integer> future;
	private ObservateurCapteur afficheur;
	
	public AfficheurThread(ObservateurCapteur o1) {
		this.afficheur = o1;
	}

	@Override
	public void run() {
		while(true) {
			if(this.afficheur.getFuture()!=null) {
				try {
					this.future = this.afficheur.getFuture();
					Integer i = this.future.get();
					this.afficheur.setVal(Integer.toString(i));
					this.afficheur.setFuture(null);
					this.future = null;
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
