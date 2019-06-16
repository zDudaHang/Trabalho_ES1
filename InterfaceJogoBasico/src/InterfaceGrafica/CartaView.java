package InterfaceGrafica;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;

public class CartaView extends JLabel {
	
	protected CartaIdentificacao id; // Corresponde ao ID da carta que representa
	
	protected Carta carta;
	
	public Carta getCarta() {
		return carta;
	}

	public CartaIdentificacao getId() {
		return this.id;
	}
	
	public CartaView() {
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);

		// TODO
		URL url = getClass().getResource("/backcard.png");
		this.setToolTipText("This is a default card, for testing purposes. If you use this card, not will actually happen.");
		ImageIcon  img = new ImageIcon(url);
		this.setIcon(img);
		
	}
	protected void usarCarta(CartaIdentificacao id) {
	}
	
	public void setId(CartaIdentificacao id) {
		this.id = id;
	}
	
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	public void atualizarIcon() {
		switch (this.getId()) {
		case CORREDORES_EXPERIENTES: {
			URL url = getClass().getResource("/CorrExp.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case ESCUDOS: {
			URL url = getClass().getResource("/Escudos.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case ESPIAR: {
			URL url = getClass().getResource("/Espiar.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			
			break;
		}
		case MAOS_AO_ALTO: {
			URL url = getClass().getResource("/Maos.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case MOVIMENTO_BRUSCO: {
			URL url = getClass().getResource("/MovBrusco.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case RECICLAGEM: {
			URL url = getClass().getResource("/Reciclagem.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case SACRIFICIO: {
			URL url = getClass().getResource("/Sacrificio.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case SAIR_PELA_TANGENTE: {
			URL url = getClass().getResource("/Tangente.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case SAVE_THE_KING: {
			URL url = getClass().getResource("/SaveTheKing.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		case SILENCIO_POR_FAVOR: {
			URL url = getClass().getResource("/Silencio.png");
			ImageIcon  img = new ImageIcon(url);
			this.setIcon(img);
			break;
		}
		}
	}
}
