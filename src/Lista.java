import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Lista{
	Nodo   Head, P, Q, R, Z;
	private int renglones, columnas;
	String ganador = "";
	private int cont=0,cont2=0,contt=0;

	public String[][] matriz = new String[4][4];

	public Lista(int renglones, int columnas)
	{
		this.renglones=renglones;
		this.columnas=columnas;
		Head = null;
	} 
	
	public void reinicioContadores() {
		cont = 0;cont2=0;
	}
	
	public void ColocarValores(JButton boton) {
		if(cont < 4) {
			matriz[cont2][cont] = boton.getText();
			cont++;
	}else {
		cont2++;
		cont = 0;
		matriz[cont2][cont] = boton.getText();
		cont++;
	}
	}
	
	public void CrearLista(){	
		for (int X = 1; X <= renglones; X++){
			for (int Y = 1; Y <= columnas; Y++){
				P = new Nodo();
				int s = Integer.parseInt(matriz[X-1][Y-1]);
				P.dato = s;
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
					//ok.out.print(P.dato+" - ");
					P = P.Derecha;
				}
				Q = Q.Abajo;
			}
		}
	}
	
	public void repintarMovimiento(JButton Matriz[][]) {
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
				Matriz[i][j].setText(matriz[i][j]);
				Ventana.setValorMatriz(matriz, i, j);
            	}
            }
	}
	
	public void moverFicha (JButton btn,JButton Matriz[][]) {
		int cont =0;
		int i=Integer.parseInt(btn.getText()); 
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					if (P.dato == 16) {
						if (P.Derecha != null) {
							if (P.Derecha.dato == i) {
								P.dato = i;
								P.Derecha.dato = 16;
								cont++;
							}
						}
						if (P.Izquierda != null) {
							if (P.Izquierda.dato == i) {
								P.dato = i;
								P.Izquierda.dato = 16;
								cont++;
							}
						}
						if (P.Abajo!= null) {
							if (P.Abajo.dato == i) {
								P.dato = i;
								P.Abajo.dato = 16;
								cont++;
							}
						}
						if (P.Arriba != null) {
							if (P.Arriba.dato == i) {
								P.dato = i;
								P.Arriba.dato = 16;
								cont++;
							}
						}
					}
					if (cont != 0) {
						colocarValores();
						reinicioContadores();
						repintarMovimiento(Matriz);
					break;
					}
					P = P.Derecha;
				}
				if (cont != 0) {
					break;
					}
				Q = Q.Abajo;
			}
		}
	}
	
	public void colocarValores(){
		int i = 0,j=0;
		String s; 
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					s=Integer.toString(P.dato); 
					if (i <= 3 && j <= 3) {
						matriz[i][j] = s;						
					}
					P = P.Derecha;
					j++;
				}
				j=0;
				i++;
				Q = Q.Abajo;
			}
		}
	}
	
	public void colocarValores2(JButton[][] Matriz){
		int i = 0,j=0;
		int s; 
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					if (i <= 3 && j <= 3) {
						matriz[i][j] = Matriz[i][j].getText();	
						s=Integer.parseInt(matriz[i][j]); 
						P.dato = s;
					}
					P = P.Derecha;
					j++;
				}
				j=0;
				i++;
				Q = Q.Abajo;
			}
		}
	}
	
	public void comprobarGanador(){
		int i=1;
		contt=0;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					if (P.dato == i) {
						contt++;
					}
					P = P.Derecha;
					i++;
				}
				Q = Q.Abajo;
			}
		}
		if (contt == 16) {
			JOptionPane.showMessageDialog(null, "GANADOR");
		}
		if (contt > 16) {
			contt -= contt;
		}
	}

	public void colocarGanador() {
		int i=1;
		if (Head != null){
			Q = Head;
			while( Q != null)//renglon
			{
				P = Q;
				while(P != null)//columna
				{
					P.dato = i;
					P = P.Derecha;
					i++;
				}
				Q = Q.Abajo;
			}
		}
	}
	
	public int getContt() {
		return contt;
	}

	public void setContt(int contt) {
		this.contt = contt;
	}
}
