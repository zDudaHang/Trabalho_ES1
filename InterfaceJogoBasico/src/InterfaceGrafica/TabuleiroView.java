package InterfaceGrafica;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;

public class TabuleiroView extends JPanel {
	protected PosicaoView posicoes[][];
	
	public TabuleiroView(ActionListener tabuleiroHandler) {
		this.setSize(480, 480);
		posicoes =  new PosicaoView[8][8];
		GridLayout layout = new GridLayout(8, 8);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(layout);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// Configura o click do botão para chamar um listener atrelado ao tabuleiro
				this.posicoes[x][y] = new PosicaoView(x, y, null, tabuleiroHandler);
				this.posicoes[x][y].setEnabled(false);
			}
		}
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				this.add(this.posicoes[x][y]);
			}
		}
	}
	
	public void atualizarTabuleiro(Tabuleiro tabuleiro) {
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Posicao pos = tabuleiro.pegarPosicao(x, y);
				// TODO desenhar segunça peça por cima
				Peca[] pecas = tabuleiro.pegarPecasDaPosicao(x, y);
				
				this.posicoes[x][y].setEnabled(pos.isHabilitada());

				if (pos.isHabilitada()) {
					this.posicoes[x][y].setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2));
					this.posicoes[x][y].setBorderPainted(true);
				} else {
					this.posicoes[x][y].setBorder(null);
					this.posicoes[x][y].setBorderPainted(false);
				}
				
				// Duas peças
				if (pecas[0] != null && pecas[1] != null) {
					Icon icon1 = new PecaView(pecas[0].getId()).getIcon();
					Icon icon2 = new PecaView(pecas[1].getId()).getIcon();
					
					BufferedImage bImg1 = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
					Graphics bi1 = bImg1.getGraphics();
					icon1.paintIcon(null, bi1, 0, 0);
					bi1.dispose();

					BufferedImage bImg2 = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
					Graphics bi2 = bImg2.getGraphics();
					icon2.paintIcon(null, bi2, 0, 0);
					bi2.dispose();
					
					BufferedImage combined = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = combined.createGraphics();
					g.drawImage(bImg1, 0, 0, null);
					g.drawImage(bImg2, 10, 10, null);
					g.dispose();
					
					this.posicoes[x][y].setIcon(new ImageIcon(combined));
					this.posicoes[x][y].setDisabledIcon(new ImageIcon(combined));
					this.posicoes[x][y].idPeca = pecas[0].getId();
					
				// Uma peça apenas
				} else if (pecas[0] != null && pecas[1] == null) {
					this.posicoes[x][y].setIcon(new PecaView(pecas[0].getId()).getIcon());
					this.posicoes[x][y].setDisabledIcon(new PecaView(pecas[0].getId()).getIcon());
					this.posicoes[x][y].idPeca = pecas[0].getId();
				
				// Nenhuma peça
				} else {
					this.posicoes[x][y].setIcon(null);
					this.posicoes[x][y].setDisabledIcon(null);
					this.posicoes[x][y].idPeca = null;
				}
			}
		}
	}
}
