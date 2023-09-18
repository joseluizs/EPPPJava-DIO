package dio.gof.singleton;

/**
 * 
 * Sigleton "Lazy Holder"
 * @author joseluizs
 *
 */

public class SingletonLazyHolder {
	
	private static class InstanciaHolder {
		public static SingletonLazyHolder instancia = new SingletonLazyHolder();
	}
	
	private SingletonLazyHolder() {
		super();
	}
	
	public static SingletonLazyHolder getInstacia() {
		return InstanciaHolder.instancia;
	}

}
