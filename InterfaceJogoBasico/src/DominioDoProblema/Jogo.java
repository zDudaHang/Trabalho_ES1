package DominioDoProblema;

public class Jogo {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	public Jogo() {
		// TODO Auto-generated constructor stub
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void definirPartidaAndamento(boolean valor) {
		partidaAndamento = valor;
	}
	
	public boolean informarPartidaAndamento() {
		return partidaAndamento;
	}
	
	public boolean permitidoConectar() {
		return !conectado;
		// defina a l�gica do seu jogo
	}
	
	public boolean permitidoDesconectar() {
		return conectado;
		// defina a l�gica do seu jogo
	}

	public boolean permitidoIniciarPartida() {
		return !partidaAndamento;
		// defina a l�gica do seu jogo
	}


}
