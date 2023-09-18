package dio.gof.singleton;

/**
 * 
 * Sigleton "apressado"
 * @author joseluizs
 *
 */

public class SingletonEager {
	
	private static SingletonEager instancia = new SingletonEager();
	
	private SingletonEager() {
		super();
	}
	
	public static SingletonEager getInstacia() {
		return instancia;
	}

}
