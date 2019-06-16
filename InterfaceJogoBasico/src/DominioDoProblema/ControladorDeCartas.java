package DominioDoProblema;

import java.util.ArrayList;
import java.util.List;

import DominioDoProblema.Cartas.Carta;

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
		Espiar esp = new Espiar();
		MaosAoAlto ma = new MaosAoAlto();
		MovimentoBrusco mb = new MovimentoBrusco();
		Reciclagem rc = new Reciclagem();
		SacrificioNecessario sn = new SacrificioNecessario();
		SairPelaTangente st = new SairPelaTangente();
		SaveTheKing stk = new SaveTheKing();
		SilencioPorFavor spf = new SilencioPorFavor();
		
		Carta[] cartas = {ce,e,esp,ma,mb,rc,sn,st,stk,spf};
		
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
}