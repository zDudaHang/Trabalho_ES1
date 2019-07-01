package InterfaceGrafica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import DominioDoProblema.Etapa;
import DominioDoProblema.Respostas;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Cartas.CartaIdentificacao;

public class InterfaceJogo {

	private JFrame frame;
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
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
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
				if(atorJogador.jogo.getJogadorLocal().ehJogadorDaVez()) {
					PosicaoView pView = (PosicaoView)e.getSource();
					
					Respostas r = atorJogador.posicaoClicada(pView.x, pView.y);
					
					atualizarTextos(r);
				}
			}
		};
		
		
		// Cria tabuleiro
		this.tabuleiroView = new TabuleiroView(tabuleiroHandler);
		tabuleiroView.setLocation(210, 30);
		frame.getContentPane().add(tabuleiroView);
		
		// Criar botão de passar etapa
		ActionListener botaoPassarEtapaListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (atorJogador.jogo.getJogadorLocal().ehJogadorDaVez()) {
					Respostas r = atorJogador.botaoPassarEtapa();
					atualizarTextos(r);
				}
			}
		};
		JButton botaoPassarEtapa = new JButton("Passar etapa");
		botaoPassarEtapa.addActionListener(botaoPassarEtapaListener);
		botaoPassarEtapa.setLocation(30, 30);

		// Cria painel de mostra
		informacoes = new InformacoesDeJogo(botaoPassarEtapa);
		informacoes.setLocation(10, 30);
		frame.getContentPane().add(informacoes);
		
		MouseAdapter cardHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartaView c = (CartaView) e.getSource();
				Respostas r = atorJogador.cartaClicada(c.getId());
				
				atualizarTextos(r);
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
	
	public void setEtapaDoTurno(Etapa etapa) {
		this.informacoes.atualizarEtapaDoTurno(etapa);
	}

	public void conectarUsuario() {
		JTextField name_field = new JTextField();
		JTextField server_field = new JTextField("localhost");
		
		Object[] field_to_fill = {
			    "Nome:", name_field,
			    "Servidor:", server_field
			};
		
		int option = JOptionPane.showConfirmDialog(frame, field_to_fill, "Dados da conexão", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String server = server_field.getText();
			String name = name_field.getText();
			
			if (!name.isEmpty() && !server.isEmpty()) {
				String mensagem = atorJogador.conectar(server_field.getText(), name_field.getText());
				JOptionPane.showMessageDialog(frame, mensagem);
	
			} else {
				JOptionPane.showMessageDialog(frame, "Entrada inválida!");
			}
	
		} else {
			JOptionPane.showMessageDialog(frame, "Conexão cancelada.");
		}
	}
	
	public void desconectarUsuario() {
		String mensagem = atorJogador.desconectar();
		JOptionPane.showMessageDialog(frame, mensagem);
	}
	
	public void iniciarPartida() {
		String mensagem = atorJogador.iniciarPartida();
		JOptionPane.showMessageDialog(frame, mensagem);
	}

	public void atualizarCartas(CartaIdentificacao[] cartas) {
		this.jogador.atualizarMao(cartas);
		this.informacoes.getJogador().setCartasMao(cartas.length);
	}

	public void cartaClicada(CartaIdentificacao id) {
		Respostas r = this.atorJogador.cartaClicada(id);
		
		if (r == Respostas.NAO_EH_O_MOMENTO) {
			JOptionPane.showMessageDialog(frame, "Não é o momento.");
		}
	}

	public void habilitarCartas(boolean habilitar) {
		this.jogador.habilitarCartas(habilitar);
	}

	public void atualizarTextos(Respostas resposta) {
		switch (resposta) {
		case NAO_EH_O_MOMENTO:
			this.informacoes.setFaseDoTurno("Você deveria fazer isso?");
			break;
		case SELECIONAR_POSICAO:
			this.informacoes.setFaseDoTurno("Selecione uma posição válida");
			break;
		case SELECIONAR_UMA_PECA_ADVERSARIA:
			this.informacoes.setFaseDoTurno("Selecione uma peça adversária");
			break;
		case SELECIONAR_UMA_PECA_ADVERSARIA_NAO_REI:
			this.informacoes.setFaseDoTurno("Selecione uma peça adversário que não seja o rei");
			break;
		case SELECIONAR_UMA_PECA_LOCAL:
			this.informacoes.setFaseDoTurno("Selecione uma peça sua");
			break;
		case SELECIONAR_UMA_PECA_LOCAL_NAO_REI:
			this.informacoes.setFaseDoTurno("Selecione uma peça sua que não seja o rei");
			break;
		case USAR_CARTA:
			this.informacoes.setFaseDoTurno("Você pode usar uma carta");
			break;
		case VITORIA_DO_OPONENTE:
			this.informacoes.setFaseDoTurno("Você perdeu =(");
			break;
		case AGUARDAR_ADVERSARIO:
			this.informacoes.setFaseDoTurno("Aguarde seu oponente jogar");
			break;
		default:
			this.informacoes.setFaseDoTurno("" + resposta);
			break;

		}
	}

	public void finalizarJogoComDerrota() {
		JOptionPane.showMessageDialog(frame, "Jogo finalizado! Você perdeu!");
		this.initialize();
	}

	public void finalizarJogoComVitoria() {
		JOptionPane.showMessageDialog(frame, "Jogo finalizado! Você venceu!");
		this.initialize();
	}

	public void atualizarNumeroDeCartas(int cartasMaoJogadorLocal, int cartasDeckJogadorLocal,
			int cartasDescarteJogadorLocal, int cartasMaoAdversario, int cartasDeckAdversario, int cartasDescarteAdversario) {
		this.informacoes.atualizarNumeroDeCartas(cartasMaoJogadorLocal,
												 cartasDeckJogadorLocal,
												 cartasDescarteJogadorLocal,
												 cartasMaoAdversario, 
												 cartasDeckAdversario, 
												 cartasDescarteAdversario);	
	}

	public void atualizarTextosDasCartas(String[] descricaoCartasDaMao) {
		this.jogador.atualizarTextosDasCartas(descricaoCartasDaMao);
	}

	public void notificarCartaUsada(CartaIdentificacao idCartaUsada) {
		String mensagem = "";
		switch (idCartaUsada) {
		case CORREDORES_EXPERIENTES:
			mensagem = "Oponente utilizou Corredores Experientes";
			break;
		case ESCUDOS:
			mensagem = "Oponente utilizou Escudos";
			break;
		case MAOS_AO_ALTO:
			mensagem = "Oponente utilizou Mãos ao alto";
			break;
		case MOVIMENTO_BRUSCO:
			mensagem = "Oponente utilizou Movimento Brusco";
			break;
		case SACRIFICIO:
			mensagem = "Oponente utilizou Sacrificio";
			break;
		case SAIR_PELA_TANGENTE:
			mensagem = "Oponente utilizou Sair Pela Tangente";
			break;
		case SAVE_THE_KING:
			mensagem = "Oponente utilizou Save the King";
			break;
		case SILENCIO_POR_FAVOR:
			mensagem = "Oponente utilizou Silencio, Por Favor";
			break;
		case NENHUMA:
			mensagem = "Oponente não utilizou cartas nesta etapa";
			break;

		}
		
		JOptionPane pane = new JOptionPane(mensagem, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(this.frame, "Jogada recebida");
		new Thread(new Runnable()
        {
          public void run()
          {
              try {
				Thread.sleep(1000);;
				dialog.dispose();
				dialog.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
          }
        }).start();
		dialog.setVisible(true);
	}
}
