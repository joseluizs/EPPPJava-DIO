package dio.gof;

import dio.gof.facade.Facade;
import dio.gof.singleton.SingletonEager;
import dio.gof.singleton.SingletonLazy;
import dio.gof.singleton.SingletonLazyHolder;
import dio.gof.strategy.Comportamento;
import dio.gof.strategy.ComportamentoAgressivo;
import dio.gof.strategy.ComportamentoDefensivo;
import dio.gof.strategy.ComportamentoNormal;
import dio.gof.strategy.Robo;

public class Test {

	public static void main(String[] args) {
		//Testes realizados ao Design Pattern Singleton;
		SingletonLazy lazy = SingletonLazy.getInstacia();
		System.out.println(lazy);
		lazy = SingletonLazy.getInstacia();
		System.out.println(lazy);
		
		SingletonEager eager = SingletonEager.getInstacia();
		System.out.println(eager);
		eager = SingletonEager.getInstacia();
		System.out.println(eager);

		SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstacia();
		System.out.println(lazyHolder);
		lazyHolder = SingletonLazyHolder.getInstacia();
		System.out.println(lazyHolder);
		
		//Testes realizados ao Design Pattern Strategy;
		Comportamento defensivo = new ComportamentoDefensivo();
		Comportamento normal = new ComportamentoNormal();
		Comportamento agressivo = new ComportamentoAgressivo();
		
		Robo robo = new Robo();
		robo.setComportamento(normal);
		robo.mover();
		robo.mover();
		robo.setComportamento(defensivo);
		robo.mover();
		robo.setComportamento(agressivo);
		robo.mover();
		robo.mover();
		robo.mover();
		
		//Testes realizados ao Design Pattern Facede
		
		Facade facade = new Facade();
		facade.migraCliente("Luiz", "71570-613");
		
	}

}
