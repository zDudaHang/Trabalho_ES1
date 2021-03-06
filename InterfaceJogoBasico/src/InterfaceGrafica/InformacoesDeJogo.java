package InterfaceGrafica;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import DominioDoProblema.Etapa;

import java.net.URL;

import javax.swing.Box;


public class InformacoesDeJogo extends PanelArredondado {
	private static final long serialVersionUID = 4563781218869376936L;
	private InformacoesDeJogador jogador, oponente;
	
	public InformacoesDeJogador getJogador() {
		return jogador;
	}

	private AutoBreakLineLabel faseDoTurno;
	private JLabel etapaDoTurno;
	private Box verticalBox;

	public InformacoesDeJogo(JButton botaoPassarEtapa) {
		this.setSize(190, 480);
		BorderLayout layout = new BorderLayout(25, 25);
		setLayout(layout);
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.jogador = new InformacoesDeJogador();
		this.oponente = new InformacoesDeJogador();
		this.faseDoTurno = new AutoBreakLineLabel();
		this.etapaDoTurno = new JLabel();
		
		// Testing Purposes
		jogador.setNome("Jogador Local");
		oponente.setNome("Jogador em Rede");
		
		jogador.setCartasDeck(5);
		oponente.setCartasDeck(5);
		
		jogador.setCartasMao(5);
		oponente.setCartasMao(5);
		
		jogador.setCartasDescarte(0);
		oponente.setCartasDescarte(0);
		
		faseDoTurno.setText("Inicie um jogo.");
		etapaDoTurno.setText("Nenhum jogo está em andamento =(");
		// End testing Purposes
		URL url = getClass().getResource("/kingCrown.png");
		JLabel logo = new JLabel(new ImageIcon(url));
		verticalBox = Box.createVerticalBox();		
		verticalBox.add(logo);
		verticalBox.add(this.etapaDoTurno);
		verticalBox.add(this.faseDoTurno);
		verticalBox.add(botaoPassarEtapa);
		
		
		this.add(this.oponente, BorderLayout.NORTH);
		this.add(verticalBox, BorderLayout.WEST);
		this.add(this.jogador, BorderLayout.SOUTH);
		
	}
	
	void setNomeJogador(String nome) {
		this.jogador.setNome(nome);
	}

	void setNomeOponente(String nome) {
		this.oponente.setNome(nome);
	}

	public void atualizarEtapaDoTurno(Etapa etapa) {
		switch (etapa) {
		case AGUARDANDO_ADVERSARIO:
			this.etapaDoTurno.setText("Aguardando adversário");
			break;
		case MOVIMENTO:
			this.etapaDoTurno.setText("Etapa de movimento");
			break;
		case USO_CARTA_COMECO:
		case USO_CARTA_FIM:
			this.etapaDoTurno.setText("Uso de carta");
		default:
			break;
		
		}
	}

	public String getFaseDoTurno() {
		return faseDoTurno.getText();
	}

	public void setFaseDoTurno(String faseDoTurno) {
		this.faseDoTurno.setText(faseDoTurno);
	}

	public void atualizarNumeroDeCartas(int cartasMaoJogadorLocal, int cartasDeckJogadorLocal,
			int cartasDescarteJogadorLocal, int cartasMaoAdversario, int cartasDeckAdversario,
			int cartasDescarteAdversario) {
		
		this.jogador.setCartasDeck(cartasDeckJogadorLocal);
		this.oponente.setCartasDeck(cartasDeckAdversario);
		
		this.jogador.setCartasMao(cartasMaoJogadorLocal);
		this.oponente.setCartasMao(cartasMaoAdversario);
		
		this.jogador.setCartasDescarte(cartasDescarteJogadorLocal);
		this.oponente.setCartasDescarte(cartasDescarteAdversario);
	}
}
