package InterfaceGrafica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

import DominioDoProblema.ControladorDeCartas;
import DominioDoProblema.Respostas;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;
import DominioDoProblema.Pecas.Peca;
import DominioDoProblema.Pecas.PecaIdentificacao;
import Rede.Etapa;

public class InterfaceJogo {

	private JFrame frame;
//	private final Action action = new SwingAction();
//	private final Action action_1 = new SwingAction_1();
//	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador;

	protected JogadorView jogador;
	protected InformacoesDeJogo informacoes;
	protected TabuleiroView tabuleiroView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceJogo window = new InterfaceJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		atorJogador = new AtorJogador(this);
		
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 700, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 700, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jogo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmConectar = new JMenuItem("conectar");
//		mntmConectar.setAction(action);
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
//		mntmDesconectar.setAction(action_1);
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
//		mntmIniciarPartida.setAction(action_2);
		mnNewMenu.add(mntmIniciarPartida);
		

		class MenuHandler implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem item = (JMenuItem)e.getSource();
				switch(item.getText()) {
				case "conectar":
					conectarUsuario();
					break;
				case "desconectar":
					desconectarUsuario();
					break;
				case "iniciar partida":
					iniciarPartida();
					break;
				}
			
			}
		}
		
		MenuHandler handler = new MenuHandler();
		
		mntmConectar.addActionListener(handler);
		mntmDesconectar.addActionListener(handler);
		mntmIniciarPartida.addActionListener(handler);
		
		ActionListener tabuleiroHandler = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(atorJogador.jogo.getJogadorLocal().isJogadorDaVez()) {
					PosicaoView pView = (PosicaoView)e.getSource();
					System.out.println("(" + pView.x + "," + pView.y + ") = " + pView.idPeca);
				} else {
					System.out.println("Não é sua vez :/");
				}
			}
		};
		
		// Cria tabuleiro
		this.tabuleiroView = new TabuleiroView(tabuleiroHandler);
		tabuleiroView.setLocation(210, 30);
		frame.getContentPane().add(tabuleiroView);
		
		// Cria painel de mostra
		informacoes = new InformacoesDeJogo();
		informacoes.setLocation(10, 30);
		frame.getContentPane().add(informacoes);
		
		MouseAdapter cardHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartaView c = (CartaView) e.getSource();
				Respostas r = atorJogador.cartaClicada(c.getId());
				
				switch (r) {
				case NAO_EH_O_MOMENTO:
					JOptionPane.showMessageDialog(null, "Não é sua vez de fazer isso =(");
					break;
					
				case NADA:
					break;
					
				case SELECIONAR_TORRE:
					informacoes.setFaseDoTurno("Selecione uma de suas torres");
					break;
					
				case SELECIONAR_UMA_PECA_ADVERSARIA:
					informacoes.setFaseDoTurno("Selecione uma peça adversária");
					break;
					
				case SELECIONAR_UMA_PECA_LOCAL:
					informacoes.setFaseDoTurno("Selecione uma peça sua");
					break;
					
				case SELECIONAR_LOCAL_E_ADVERSARIA_NAO_REI:
					informacoes.setFaseDoTurno("Selecione uma peça sua e uma peça adversária");
					break;
					
				case SELECIONAR_UMA_PECA_LOCAL_NAO_REI:
					informacoes.setFaseDoTurno("Selecione uma peça sua que não seja o rei");
					break;
					
				default:
					break;

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				CartaView c = (CartaView)e.getSource();
				c.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				CartaView c = (CartaView)e.getSource();
				c.setBorder(null);
			}
		};
		
		jogador = new JogadorView(cardHandler);
		
		// Insere o controlador de cartas do ator jogador
		frame.getContentPane().add(jogador.getInfo());
		
	}

	public void atualizarTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiroView.atualizarTabuleiro(tabuleiro);
	}
	
	public void setJogadorAtivo(Boolean jogadorLocalAtivo) {
		this.informacoes.setJogadorAtivo(jogadorLocalAtivo);
	}
	
	public void setFaseDoTurno(Etapa etapa) {
		String text;
		switch(etapa) {
		case COMPRA:
			text = "Etapa de compra";
			break;
		case DANO:
			text = "Etapa de dano";
			break;
		case ENCERRAR_TURNO:
			text = "Encerramento de turno";
			break;
		case MOVIMENTO:
			text = "Etapa de movimentação";
			break;
		case USO_CARTA_COMECO:
			text = "Você pode usar uma carta";
			break;
		case USO_CARTA_FIM:
			text = "Você pode usar uma carta";
			break;
		case AGUARDANDO_ADVERSARIO:
			text = "Aguardando adversário";
			break;
		default:
			text = "Etapa incerta.(falha de programação)";
			break;
		
		}
		this.informacoes.setFaseDoTurno(text);
	}

	public void conectarUsuario() {
		JTextField name_field = new JTextField();
		JTextField server_field = new JTextField("localhost");
		
		Object[] field_to_fill = {
			    "Nome:", name_field,
			    "Servidor:", server_field
			};
		
		int option = JOptionPane.showConfirmDialog(null, field_to_fill, "Dados da conexão", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String server = server_field.getText();
			String name = name_field.getText();
			
			if (!name.isEmpty() && !server.isEmpty()) {
				String mensagem = atorJogador.conectar(server_field.getText(), name_field.getText());
				JOptionPane.showMessageDialog(null, mensagem);
	
			} else {
				JOptionPane.showMessageDialog(null, "Entrada inválida!");
			}
	
		} else {
			JOptionPane.showMessageDialog(null, "Conexão cancelada.");
		}
	}
	
	public void desconectarUsuario() {
		String mensagem = atorJogador.desconectar();
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	public void iniciarPartida() {
		String mensagem = atorJogador.iniciarPartida();
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void atualizarCartas(ControladorDeCartas controlador) {
		int tamanhoMao = this.jogador.info.atualizarMao(controlador);
		this.informacoes.getJogador().setCartasMao(tamanhoMao);
	}
	
//	public void atualizarCarta(Carta carta) {
//		this.cartaUsada = carta;
//		System.out.print("CartaUsada = " + this.cartaUsada.getId());
//		if (carta.isAfetaPecaLocal()) {
//			JOptionPane.showMessageDialog(null, "Escolha uma peça sua.");
//		}
//	}
//
//	
//	public void usarCarta() {
//		System.out.println(this.posicaoEscolhida.idPeca);
//		System.out.println(this.cartaUsada.getId());
//		posicaoEscolhida = null;
////		cartaUsada.aplicarEfeito(, pecaAdversaria, jogadorLocal, jogadorAdversario);
//	}

	// Maos ao Alto
	public PecaView pegarPecaAdversaria(boolean ehBranco, CartaIdentificacao id) {
		System.out.println("Pegar peça adversária");
		return null;
		
	}
	
	public boolean ehBranca(PecaView p) {
		return (p.id == PecaIdentificacao.REI_BRANCO || p.id == PecaIdentificacao.TORRE_BRANCA || p.id == PecaIdentificacao.BISPO_BRANCO);
	}

	public void cartaClicada(CartaIdentificacao id) {
		this.atorJogador.cartaClicada(id);
	}

	public void habilitarCartas(boolean habilitar) {
		this.jogador.habilitarCartas(habilitar);
	}
}
