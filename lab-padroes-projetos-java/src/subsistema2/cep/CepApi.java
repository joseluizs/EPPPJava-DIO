package subsistema2.cep;

public class CepApi {
	
	private static CepApi instancia = new CepApi();
	
	private CepApi() {
		super();
	}
	
	public static CepApi getInstacia() {
		return instancia;
	}

	public String recuperarCidade(String cep) {
		return "Brasília";
	}
	public String recuperarEstado(String cep) {
		return "DF";
	}
}
