package ep2;
import static net.dam23.jororo.ep2.MiMiCo.*;

public class CartaBrisca {
	private int numero;
	private Palos palo;
	public enum Palos{
		BASTOS , OROS, COPAS, ESPADAS, REVERSO
	}
	//Constructor
	public CartaBrisca(Palos palo, int numero) {
		if(numero > 12 || numero < 1 || numero ==8 || numero ==9 ) throw new IllegalArgumentException("Número no válido");
		this.palo = palo;
		this.numero = numero;
	}
	
	//Getters
	public int getPuntos() {
		if (this.numero == 1) return  11;
		if (this.numero == 3) return  10;
		if (this.numero == 12) return  4;
		if (this.numero == 11) return  3;
		if (this.numero == 10) return  2; return 0;
	}
	public int getNumero() {  return numero;   }
	@Override
	public String toString() {
		String carta = "";
		carta += numero;
		if (numero == 1) carta = "AS";
		if (numero == 11) carta = "CABALLO";
		if (numero == 12) carta = "REY";
		if (numero == 10) carta = "SOTA";
		return carta + " de " + palo;
	}
	public Palos getPalo() {return this.palo;}
	public boolean mayor(CartaBrisca otra) {
		if(this.palo != otra.palo) throw new IllegalArgumentException("No son del mismo palo");
		if(this.getPuntos() > otra.getPuntos()) return true;
		if(this.getPuntos() < otra.getPuntos()) return false;
		if(this.getNumero() > otra.getPuntos()) return true;
		if(this.getNumero() < otra.getPuntos()) return false;
		return false;
	}
	public String numToString() {
		if(numero == 1) return "AS";
		if(numero == 2) return "DOS";
		if(numero == 3) return "TRES";
		if(numero == 4) return "CUATRO";
		if(numero == 5) return "CINCO";
		if(numero == 6) return "SEIS";
		if(numero == 7) return "SIETE";
		if(numero == 10) return "SOTA";
		if(numero == 11) return "CABALLO";
		if(numero == 12) return "REY";
		return "hola";
	}
	public void dibuja(int x, int y) {
		setColor(T_NEGRO, F_BLANCO);
		setCursor(y,x);
		if (palo != Palos.REVERSO) {
			System.out.print("+------------+");
			setCursor(y+1,x);
			System.out.printf("|%-12s|", this.numToString());
			setCursor(y+2,x);
			System.out.printf("|%-12s|", this.palo);
		}
		System.out.print("+------------+");
		setCursor(y+1,x);
		System.out.printf("|%-12s|", this.numToString());
		setCursor(y+2,x);
		System.out.printf("|%-12s|", this.palo);
		if (palo == Palos.ESPADAS) {
			setCursor(y+3,x);
			System.out.printf("|%12s|", "");
			setCursor(y+4,x);
			System.out.print("|      o     |");
			setCursor(y+5,x);
			System.out.print("|     -+-    |");
			setCursor(y+6,x);
			System.out.print("|      |     |");
			setCursor(y+7,x);
			System.out.print("|      |     |");
			setCursor(y+8,x);
			System.out.print("|      |     |");
			setCursor(y+9,x);
			System.out.print("+------------+");
		}
		if (palo == Palos.BASTOS) {
			setCursor(y+3,x);
			System.out.printf("|%12s|", "");
			setCursor(y+4,x);
			System.out.print("|     / /    |");
			setCursor(y+5,x);
			System.out.print("|    /  /    |");
			setCursor(y+6,x);
			System.out.print("|   /   |    |");
			setCursor(y+7,x);
			System.out.print("|   |   /    |");
			setCursor(y+8,x);
			System.out.print("|    v       |");
			setCursor(y+9,x);
			System.out.print("+------------+");
		}
		if (palo == Palos.COPAS) {
			setCursor(y+3,x);
			System.out.printf("|%12s|", "");
			setCursor(y+4,x);
			System.out.print("|  VVVVVVVVV |");
			setCursor(y+5,x);
			System.out.print("|   VVVVVVV  |");
			setCursor(y+6,x);
			System.out.print("|      X     |");
			setCursor(y+7,x);
			System.out.print("|      X     |");
			setCursor(y+8,x);
			System.out.print("|     XXX    |");
			setCursor(y+9,x);
			System.out.print("+------------+");
		}
		if (palo == Palos.OROS) {
			setCursor(y+3,x);
			System.out.printf("|%12s|", "");
			setCursor(y+4,x);
			System.out.print("|     ___    |");
			setCursor(y+5,x);
			System.out.print("|  / . . \\   |");
			setCursor(y+6,x);
			System.out.print("|  |  _  |   |");
			setCursor(y+7,x);
			System.out.print("|   \\____/   |");
			setCursor(y+8,x);
			System.out.print("|            |");
			setCursor(y+9,x);
			System.out.print("+------------+");
		}
		if (palo == Palos.REVERSO) {
			setColor(T_BLANCO, F_ROJO);
			setCursor(y,x);
			System.out.print("+--------------------+");
			setCursor(y+1,x);
			System.out.print("|XXXXXXXXXXXXXXXXXXXX|");
			setCursor(y+2,x);
			System.out.print("|XXXXXXXXXXXXXXXXXXXX|");
			setCursor(y+3,x);
			System.out.print("|XXXXXXXXXXXXXXXXXXXX|");
			setCursor(y+4,x);
			System.out.print("|XXXXXXXXXXXXXXXXXXXX|");
			setCursor(y+5,x);
			System.out.print("|XXXXXXXXXXXXXXXXXXXX|");
			setCursor(y+6,x);
			System.out.print("+--------------------+");
		}
	}
	
}//Cartabrisca

class BarajaBrisca{
	private int puntero = 39;
	CartaBrisca reverso;
	private CartaBrisca[] baraja;
	public int getPuntero() {return puntero;}
	//Constructor
	public BarajaBrisca() {
		baraja = new CartaBrisca[40];
		int[] numeros = {1,2,3,4,5,6,7,10,11,12};
		int temp = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < numeros.length; j++) {
				baraja[temp] = new CartaBrisca(CartaBrisca.Palos.values()[i], numeros[j]);
				temp++;
			}
		}
		reverso = new CartaBrisca(CartaBrisca.Palos.REVERSO, 1);
	}
	
	public void barajar() {
		for(int i = 0; i < 40; i++) {
			int boleo = (int) (Math.random()*40);
			 CartaBrisca copia = new CartaBrisca(baraja[i].getPalo(), baraja[i].getNumero());
			 baraja[i] = new CartaBrisca(baraja[boleo].getPalo(), baraja[boleo].getNumero());
			 baraja[boleo] = copia;
		}
	}
	public int cuantasQuedan() {return puntero + 1;}
	public CartaBrisca extraerCarta() {
		puntero--;
		return new CartaBrisca(baraja[puntero +1 ].getPalo(), baraja[puntero +1].getNumero());
	}
	public CartaBrisca getCarta(int i) {
		if(i < puntero || i < 0 | i >39 ) { return null;}else {
			return new CartaBrisca(baraja[i ].getPalo(), baraja[i].getNumero());
		}
	}
	public CartaBrisca getMuestra() {
		return new CartaBrisca(baraja[0 ].getPalo(), baraja[0].getNumero());
	}
	public boolean ganaPrimera(CartaBrisca primera, CartaBrisca segunda) {
		if(primera.getPalo() == segunda.getPalo()) return primera.mayor(segunda);
		if(primera.getPalo() == getMuestra().getPalo()) return true;
		if(segunda.getPalo() == getMuestra().getPalo()) return false; return true;
	}
	public String toString() {
		String resultado = "Marca: ";
		resultado += puntero + ". Cartas: ";
		for(int i = 0; i < baraja.length;i++) {
			resultado += baraja[i].toString();
			if (i<baraja.length-1) resultado += ", "; 
		}
		return resultado + ".";
	}
	
}//Baraja brisca


