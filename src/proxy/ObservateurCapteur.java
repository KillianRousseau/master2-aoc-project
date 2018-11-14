package proxy;

import java.util.concurrent.Future;

import afficheur.CapteurAsync;

public interface ObservateurCapteur{
	
	public Future<Integer> update(CapteurAsync capteurAsync);
}
