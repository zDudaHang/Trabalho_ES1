package InterfaceGrafica;

import java.awt.event.MouseAdapter;

import DominioDoProblema.Cartas.CartaIdentificacao;

public class JogadorView {	
	protected InformacoesDeCartas info;
	protected String nome;
//	protected pecas Peca;

	public JogadorView(MouseAdapter cardHandler) {
		// Inicializa controlador de cartas do jogador
		info = new InformacoesDeCartas(cardHandler);
		info.setLocation(10, 520);
	}

	public InformacoesDeCartas getInfo() {
		return info;
	}

	public void habilitarCartas(boolean habilitar) {
		for (int i = 0; i < this.info.mao.size(); i++) {
			this.info.mao.get(i).setEnabled(habilitar);
		}
	}

	public void atualizarMao(CartaIdentificacao[] cartas) {
		this.info.atualizarMao(cartas);	
	}

	public void atualizarTextosDasCartas(String[] descricaoCartasDaMao) {
		this.info.atualizarTextosDasCartas(descricaoCartasDaMao);		
	}
}
