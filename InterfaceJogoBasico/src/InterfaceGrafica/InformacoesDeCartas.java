package InterfaceGrafica;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

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

	public int atualizarMao(ControladorDeCartas controlador) {
		for(int i = 0; i < controlador.getMao().size(); i++) {
			Carta carta = controlador.getMao().get(i);
			this.mao.get(i).setId(carta.getId());
			this.mao.get(i).atualizarIcon();
		}
		return controlador.getMao().size();
	}
}
