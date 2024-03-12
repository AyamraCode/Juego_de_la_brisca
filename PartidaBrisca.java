package ep2;

import java.util.Scanner;
import java.util.Arrays;
import static net.dam23.jororo.ep2.MiMiCo.*;

public class PartidaBrisca {
	//datos
	BarajaBrisca baraja = new BarajaBrisca();
	JugadorBrisca jugador = new JugadorBrisca();
	JugadorBrisca maquina = new JugadorBrisca();
	boolean turno;  //
	
	//metodos
	public void repartir() {
		jugador.cartas[1] = baraja.extraerCarta();
		jugador.cartas[2] = baraja.extraerCarta();
		jugador.cartas[3] = baraja.extraerCarta();
		maquina.cartas[1] = baraja.extraerCarta();
		maquina.cartas[2] = baraja.extraerCarta();
		maquina.cartas[3] = baraja.extraerCarta();
		jugador.tieneCartas = 3;
		maquina.tieneCartas = 3;
	}

	public void dibuja() {
		if ( jugador.cartas[0] != null)  jugador.cartas[0].dibuja(10, 10);
		/*
		if ( jugador.cartas[1] != null)  jugador.cartas[1].dibuja(10, 100);
		if ( jugador.cartas[2] != null)  jugador.cartas[2].dibuja(10, 200);
	
		baraja.reverso.dibuja(10,500);
		if ( baraja.getPuntero() > -1) baraja.getMuestra().dibuja(10, 500);
		*/
	}
	
	
	public void jugar() {
		Scanner sc = new Scanner(System.in);
		baraja.barajar();
		//Elegir turno
		double unNum = Math.random();
		if(unNum >0.5) turno = true; turno = false;
		
		
		boolean hayCartas = true;
		while(hayCartas) {
			if(turno) {
				//Pido carta y compruebo que sea buena
				int carta;
				System.out.println("Selecciona Carta: ");
				try {
					carta = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					continue;
				}
				if( carta>3|| carta < 1) continue;
				//Maquina elige carta
				int cartaM = (int)(Math.random()* 3 +1);
				//se juegan las cartas
				if(baraja.ganaPrimera(jugador.cartas[carta], maquina.cartas[cartaM])) {
					jugador.puntos = jugador.cartas[carta].getPuntos() + maquina.cartas[cartaM].getPuntos();
					turno = true;
					jugador.cogerCarta(carta);
					maquina.cogerCarta(cartaM);
				}else {
					maquina.puntos = jugador.cartas[carta].getPuntos() + maquina.cartas[cartaM].getPuntos();
					turno = false;
					maquina.cogerCarta(cartaM);
					jugador.cogerCarta(carta);
				}
			}else {
				//Maquina elige carta
				int cartaM = (int)(Math.random()* 3 +1);
				//Pido carta y compruebo que sea buena
				int carta;
				System.out.println("Selecciona Carta: ");
				try {
					carta = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					continue;
				}
				if( carta>3|| carta < 1) continue;
				//Se juegan las cartas
				if(baraja.ganaPrimera(maquina.cartas[cartaM],jugador.cartas[carta] )) {
					maquina.puntos = jugador.cartas[carta].getPuntos() + maquina.cartas[cartaM].getPuntos();
					turno = false;
					maquina.cogerCarta(cartaM);
					jugador.cogerCarta(carta);
				}else {
					jugador.puntos = jugador.cartas[carta].getPuntos() + maquina.cartas[cartaM].getPuntos();
					turno = true;
					jugador.cogerCarta(carta);
					maquina.cogerCarta(cartaM);
				}
			}
			if(  jugador.cartas[0] == null &&  jugador.cartas[1] == null &&  jugador.cartas[2] == null) hayCartas = false;
		}//while
		System.out.println(turno);
	}
	
	
	class JugadorBrisca{
		int puntos = 0;
		CartaBrisca[] cartas = new CartaBrisca[3];
		int tieneCartas;
		public void cogerCarta(int c) {
			if(baraja.getPuntero() >=0) {
			cartas[c] = baraja.extraerCarta();
			}else {
				cartas[c] = null;
			}
		}//método
	}//JugadorBrisca
	
	public static void main(String[] args) {
		PartidaBrisca pb = new PartidaBrisca();
		
		pb.jugador.cartas[0] = new CartaBrisca(CartaBrisca.Palos.BASTOS, 3);
		pb.jugador.cartas[1] = new CartaBrisca(CartaBrisca.Palos.COPAS, 11);
		pb.jugador.cartas[2] = new CartaBrisca(CartaBrisca.Palos.ESPADAS, 10);
		pb.dibuja();
		CartaBrisca cb = new CartaBrisca(CartaBrisca.Palos.OROS, 10);
		cb.dibuja(0, 0);
	}
}//PartidaBrisca

/*
Scanner s = new Scanner(System.in);
System.out.println(s.nextLine());
*/