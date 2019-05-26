package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextField;

public class InterfaceJogo {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador;

	protected JogadorView jogador;
	protected InformacoesDeJogo informacoes;

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
		atorJogador = new AtorJogador();
		jogador = new JogadorView();
		
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
		mntmConectar.setAction(action);
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mntmDesconectar.setAction(action_1);
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
		mntmIniciarPartida.setAction(action_2);
		mnNewMenu.add(mntmIniciarPartida);
		
		// Cria tabuleiro
		TabuleiroView tabuleiro = new TabuleiroView();
		tabuleiro.setLocation(210, 30);
		frame.getContentPane().add(tabuleiro);
		
		// Cria painel de mostra
		informacoes = new InformacoesDeJogo();
		informacoes.setLocation(10, 30);
		frame.getContentPane().add(informacoes);
		
		
		// Insere o controlador de cartas do ator jogador
		frame.getContentPane().add(jogador.getControlador());
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}

		public void actionPerformed(ActionEvent e) {
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
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			String mensagem = atorJogador.desconectar();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}
		public void actionPerformed(ActionEvent e) {
			String mensagem = atorJogador.iniciarPartida();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
