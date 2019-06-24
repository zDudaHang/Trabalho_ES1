package DominioDoProblema;

import java.util.ArrayList;
import java.util.List;

import DominioDoProblema.Cartas.*;

import java.util.Random;

public class ControladorDeCartas {
	
	protected List<Carta> deck = new ArrayList<Carta>();
	protected List<Carta> mao = new ArrayList<Carta>();
	protected List<Carta> descarte = new ArrayList<Carta>();
	
	public List<Carta> getDeck() {
		return deck;
	}
	public List<Carta> getMao() {
		return mao;
	}

	public List<Carta> getDescarte() {
		return descarte;
	}

	public void inicializar() {
		this.deck.clear();
		this.mao.clear();
		this.descarte.clear();
		
		CorredoresExperientes ce = new CorredoresExperientes();
		Escudos e = new Escudos();
		MaosAoAlto ma = new MaosAoAlto();
		SacrificioNecessario sn = new SacrificioNecessario();
		SaveTheKing stk = new SaveTheKing();
		SilencioPorFavor spf = new SilencioPorFavor();
		MovimentoBrusco mb1 = new MovimentoBrusco();
		MovimentoBrusco mb2 = new MovimentoBrusco();
		SairPelaTangente st1 = new SairPelaTangente();
		SairPelaTangente st2 = new SairPelaTangente();
		
		Carta[] cartas = {ce, e, ma, mb1, mb2, sn, st1, st2, stk, spf};
		
		for(int i = 0; i < cartas.length; i++) {
			this.deck.add(cartas[i]);
		}
		
		this.sortearCartas();
	}
	
	public void sortearCartas() {
		Random random = new Random();
		for(int i = 0; i < 5; i++) {
			int indice = random.nextInt(this.deck.size());
			Carta c = deck.get(indice);
			this.mao.add(c);
			this.deck.remove(c);
		}
	}
	public int getTamanhoMao() {
		return this.mao.size();
	}
	public int getTamanhoDeck() {
		return this.deck.size();
	}
	public int getTamanhoDescarte() {
		return this.descarte.size();
	}
	public Carta getCartaDaMao(CartaIdentificacao cartaUsada) {
		for (int i = 0; i < this.getTamanhoMao(); i++) {
			Carta carta = this.mao.get(i);
			if (carta.getId() == cartaUsada) {
				this.mao.remove(i);
				return carta;
			}
		}
		return null;
	}
	public boolean comprarCarta() {
		if (this.getTamanhoDescarte() > 0) {
			Random random = new Random();
			int indice = random.nextInt(this.deck.size());
			Carta c = deck.remove(indice);
			this.mao.add(c);
			return true;
		} else {
			return false;
		}
	}
	public CartaIdentificacao[] idCartasMao() {
		int tamanhoMao = this.getTamanhoMao();
		CartaIdentificacao[] cartas = new CartaIdentificacao[tamanhoMao];
		for (int i = 0; i < tamanhoMao; i++) {
			cartas[i] = this.mao.get(i).getId();
		}
		
		return cartas;
	}
}