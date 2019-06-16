package InterfaceGrafica;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import DominioDoProblema.Pecas.PecaIdentificacao;


public class PecaView extends JLabel {
	
	protected PecaIdentificacao id;
	
	public PecaView(PecaIdentificacao id) {
		this.id = id;
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		
		URL url;
		switch (id) {
		case BISPO_BRANCO:
			url = getClass().getResource("/bispoBranco.png");
			break;
		case BISPO_PRETO:
			url = getClass().getResource("/bispoPreto.png");
			break;
		case REI_BRANCO:
			url = getClass().getResource("/reiBranco.png");
			break;
		case REI_PRETO:
			url = getClass().getResource("/reiPreto.png");
			break;
		case TORRE_BRANCA:
			url = getClass().getResource("/torreBranca.png");
			break;
		case TORRE_PRETA:
			url = getClass().getResource("/torrePreta.png");
			break;
		default:
			url = null;
			break;
		}		
		
		ImageIcon  img = new ImageIcon(url);
		img = new ImageIcon(img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		this.setIcon(img);
	}
}
