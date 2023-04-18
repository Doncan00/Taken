import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Lista {
	Nodo   Head, P, Q, R, Z;
	private int renglones, columnas;
	String ganador = "";
	private int cont=0,cont2=0;
	
	public int[][] MatrizLista = new int[4][4];

	public Lista(int renglones, int columnas)
	{
		this.renglones=renglones;
		this.columnas=columnas;
		Head = null;
	} 
	
	public void ColocarValores(int i, int j) {
		if(cont < 4) {			
				MatrizLista[cont2][cont] = (i*4)+j+1;
				cont++;
		}else {
			cont2++;
			cont = 0;
			MatrizLista[cont2][cont] = (i*4)+j+1;
			cont++;
		}
	}
	
	public void CrearLista(){	
		for (int X = 1; X <= renglones; X++){
			for (int Y = 1; Y <= columnas; Y++){
				P = new Nodo();
				P.dato = MatrizLista[X-1][Y-1];
				
				if (Y == 1){
					if (Head == null){
						Head = P;
					}
					Q = P;
				}else{
					P.Izquierda = Q;
					Q.Derecha= P;
					Q = P;
				}
				
				if (X == 1){
					Q = P;
				}else{
					P.Arriba = R;
					R.Abajo = P;
					R = R.Derecha;
				}
			}// for columnas
			R = Head;
			while (R.Abajo != null){
				R = R.Abajo;
			}
		}// for renglones
	}
			
	public void desplegarLista(){
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					
					System.out.print(P.dato+" - ");
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	public void buscarEspacio(JButton boton, JButton[][] Matriz ,JPanel panel) {
		int cont =0;
		if (Head != null){
			Q = Head;
			while( Q != null && cont ==0)//renglon
			{
				P = Q;
				while(P != null && cont ==0)//columna
				{
					if (P.dato == 16) {
						int i=Integer.parseInt(boton.getText()); 
						if (P.Arriba != null) {							
						if (P.Arriba.dato == i) {
							Q = P;
							Q.dato = P.dato;
							P = P.Arriba;
							P.dato = i;
							}
						}
						if (P.Abajo != null) {							
							if (P.Abajo.dato == i) {
								Q = P;
								Q.dato = P.dato;
								P = P.Arriba;
								P.dato = i;
								}
							}
						if (P.Izquierda != null) {							
							if (P.Izquierda.dato == i) {
								Q = P;
								Q.dato = P.dato;
								P = P.Arriba;
								P.dato = i;
								}
							}
						if (P.Derecha != null) {							
							if (P.Derecha.dato == i) {
								Q = P;
								Q.dato = P.dato;
								P = P.Arriba;
								P.dato = i;
								}
							}
						reacomodarFichas(Matriz, panel);
						cont++;
						}
					P = P.Derecha;
				}
				Q = Q.Abajo;
			}
		}
	}
	
	public void reacomodarFichas(JButton[][] Matriz ,JPanel panel) {
		int aux = 0,aux2=0;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					for (int i = 0; i < 4; i++) {
			            for (int j = 0; j < 4; j++) {
			            	//int mat=Integer.parseInt(Matriz[i][j].getText());
			            	System.out.println(Matriz[i][j].getText());
			            	/*if (MatrizLista[aux][aux2] == mat)  {
			            		panel.add(Matriz[i][j]);
			            	}*/
			            }
			        }
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	public void comprobarEmpate () {
		int cont =0;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					if (P.dato != 0) {
						cont++;
					}
						
					System.out.print(P.dato);
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		if (cont == 42) {
			ganador = "empate";
		}
	}
	
	
	public void reiniciaLista(){
		ganador = "";
		
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					P.dato = 0;
					System.out.print(P.dato);
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	
	public void ComprobarGanadorVertical(){
		int contPri = 0,contPan=0;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{			
					if (P.dato == 1 && P.Abajo != null) { //Comprueba si el PRI gano
						Z = P;
						P = P.Abajo;
						contPri++;
					do {
						if (P.dato == 1) {
							contPri++;
							P = P.Abajo;
						}
						else 
							P = null;
						}while(P != null && contPri < 4);
					if (contPri >= 4) 
						ganador = "PRI";
					P = Z;
					contPri =0;
					}
					if (P.dato == 2 && P.Abajo != null ) { //Comprueba si el PAN gano
						Z = P;
						P = P.Abajo;
						contPan++;
						do {
							if (P.dato == 2) {
								contPan++;
								P = P.Abajo;
							}else 
								P = null;
							}while(P != null && contPan < 4);
						if (contPan >= 4) 
							ganador = "PAN";
						P = Z;
						contPan =0;
						}
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	
	public void ComprobarGanadorDiagonal(){
		int contPri = 0,contPan=0;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{			
					if (P.dato == 1 && P.Abajo != null && P.Derecha != null) { //Comprueba si el PRI gano diagonal derecha
						Z = P;
						P = P.Abajo;
						P = P.Derecha;
						contPri++;
					do {
						if (P.dato == 1) {
							contPri++;
							P = P.Abajo;
							if (P != null)
								P = P.Derecha;
						}
						else 
							P = null;
						}while(P != null && contPri < 4);
					if (contPri >= 4) 
						ganador = "PRI";
					P = Z;
					contPri =0;
					}
					if (P.dato == 2 && P.Abajo != null && P.Derecha != null) { //Comprueba si el PAN gano diagonal derecha
						Z = P;
						P = P.Abajo;
						P = P.Derecha;
						contPan++;
						do {
							if (P.dato == 2) {
								contPan++;
								P = P.Abajo;
								if (P != null)
									P = P.Derecha;
							}else 
								P = null;
							}while(P != null && contPan < 4);
						if (contPan >= 4) 
							ganador = "PAN";
						P = Z;
						contPan =0;
						}
					if (P.dato == 1 && P.Abajo != null && P.Izquierda != null) { //Comprueba si el PRI gano diagonal izquierda
						Z = P;
						P = P.Abajo;
						P = P.Izquierda;
						contPri++;
					do {
						if (P.dato == 1) {
							contPri++;
							P = P.Abajo;
							if (P != null)
								P = P.Izquierda;
						}
						else 
							P = null;
						}while(P != null && contPri < 4);
					if (contPri >= 4) 
						ganador = "PRI";
					P = Z;
					contPri =0;
					}
					if (P.dato == 2 && P.Abajo != null && P.Izquierda != null) { //Comprueba si el PAN gano diagonal izquierda
						Z = P;
						P = P.Abajo;
						P = P.Izquierda;
						contPan++;
						do {
							if (P.dato == 2) {
								contPan++;
								P = P.Abajo;
								if (P != null)
									P = P.Izquierda;
							}else 
								P = null;
							}while(P != null && contPan < 4);
						if (contPan >= 4) 
							ganador = "PAN";
						P = Z;
						contPan =0;
						}
					P = P.Derecha;
				}
				Q = Q.Abajo;
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	
	public void ComprobarGanadorHorizontal(){
		int contPri = 0,contPan=0;
		//String ganador = "";
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					if (P.dato == 1) {
						contPri ++;
					}else {
						contPri = 0;
					}
					if (P.dato == 2) {
						contPan ++;
					}else {
						contPan = 0;
					}
					
					if (contPri > 3) {
						ganador = "PRI";
					}
					if (contPan > 3) {
						ganador = "PAN";
					}
					P = P.Derecha;
				}
				Q = Q.Abajo;
			}
		}
	}
	

	
	public int moverficha(int datoFicha, int y) {
		P = Head;
		int cont = 0;
		int x = 0;
		while(cont != y) {
			P = P.Derecha;
			cont++;
		}
		while(P.dato == 0) {
			if(P.Abajo == null) {
				break;
			}
			P =  P.Abajo;
			x++;
			if(P.dato != 0) {
				x--;
				P = P.Arriba;
				break;
			}
		}
		if (P.dato==0) {
			P.dato=datoFicha;
			return x;
		}else {
			return -1;
		}
	}
	
    
	
}