package InterfaceGrafica;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.IdentifierHelper;

import DominioDoProblema.ControladorDeCartas;
import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;

public class InformacoesDeCartas extends PanelArredondado {
	// TODO na porra toda	
	protected List<CartaView> deck = new ArrayList<CartaView>();
	protected List<CartaView> mao = new ArrayList<CartaView>();
	protected List<CartaView> descarte = new ArrayList<CartaView>();

	public InformacoesDeCartas(MouseAdapter cardHandler) {
		super();
		GridLayout layout = new GridLayout(1, 5);
		layout.setVgap(2);
		layout.setHgap(5);
		this.setLayout(layout);
		
		this.setSize(680, 180);
		
		this.deck.clear();
		this.mao.clear();
		this.descarte.clear();
		
		int i = 0;
		while(this.mao.size() != 5) {
			this.mao.add(new CartaView());
			this.mao.get(i).addMouseListener(cardHandler);
			this.add(this.mao.get(i), 0, i);
			i++;
		}
	}

	public void atualizarMao(CartaIdentificacao[] cartas) {
		int tamanhoMao = cartas.length;
		// Atualiza icon das cartas na mão
		for(int i = 0; i < tamanhoMao; i++) {
			this.mao.get(i).setId(cartas[i]);
			this.mao.get(i).atualizarIcon();
		}
		
		// Atualiza icon das cartas na mão restantes até a quinta
		for(int i = tamanhoMao; i < 5; i++) {
			this.mao.get(i).setId(CartaIdentificacao.NENHUMA);
			this.mao.get(i).atualizarIcon();
		}
	}

	public void atualizarTextosDasCartas(String[] descricaoCartasDaMao) {
		int tamanhoMao = descricaoCartasDaMao.length;
		// Atualiza icon das cartas na mão
		for(int i = 0; i < tamanhoMao; i++) {
			this.mao.get(i).setDescricao(descricaoCartasDaMao[i]);
		}
		
		// Atualiza icon das cartas na mão restantes até a quinta
		for(int i = tamanhoMao; i < 5; i++) {
			this.mao.get(i).setDescricao("");
		}
	}
}
