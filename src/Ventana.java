
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana extends JFrame{
	
	public Random rand = new Random();
	public static JButton[][] Matriz = new JButton[4][4];
	int [] numeros = new int[20];
	
	JLabel jl1 = new JLabel();
	JPanel fondoContador = new JPanel();
	
	int contGlobal=0,contLista=0;
	
	Lista lista = new Lista(4,4);
	
	public Ventana() {
		
		this.setVisible(true);
		this.setTitle("Hola Zumayaaaaaa");
		this.setSize(720,480);
		this.setResizable(true);
		//this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fondoContador.setBounds(720,480,0,0);
		fondoContador.setVisible(true);
		fondoContador.setBackground(Color.black);
		fondoContador.setLayout(new BorderLayout());
		this.add(fondoContador);
		
		JPanel principal = new JPanel();
		principal.setBounds(640,480,0,0);
		principal.setVisible(true);
		principal.setBackground(Color.white);
		principal.setLayout(new BorderLayout());
		fondoContador.add(principal);
		
		JLabel borderIzq = new JLabel("HOLA");
		borderIzq.setOpaque(true);
		borderIzq.setForeground(Color.black);
		borderIzq.setBackground(Color.black);
		principal.add(borderIzq,BorderLayout.WEST);
		JLabel borderDer = new JLabel("HOLA");
		borderDer.setOpaque(true);
		borderDer.setForeground(Color.black);
		borderDer.setBackground(Color.black);
		principal.add(borderDer,BorderLayout.EAST);
		JLabel borderArr = new JLabel("HOLA");
		borderArr.setOpaque(true);
		borderArr.setForeground(Color.black);
		borderArr.setBackground(Color.black);
		borderArr.setFont(new Font("Open sans", Font.BOLD,25));
		principal.add(borderArr,BorderLayout.NORTH);
		
		JPanel botones = new JPanel ();
		botones.setBackground(Color.black);
		principal.add(botones,BorderLayout.SOUTH);
		JButton btn1 = new JButton("REINICIAR");
		btn1.setBackground(Color.gray);
		botones.add(btn1);
		
		JButton btn2 = new JButton("BOTON PARA GANAR");
		btn2.setBackground(Color.gray);
		botones.add(btn2);
		
		JPanel fondo = new JPanel();
		fondo.setBounds(640,480,0,0);
		fondo.setVisible(true);
		fondo.setBackground(Color.magenta);
		fondo.setLayout(new GridLayout(4,4,2,2));
		principal.add(fondo,BorderLayout.CENTER);
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				desordenar(fondo);
				revalidate();
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				lista.colocarGanador();
				lista.colocarValores();
				lista.repintarMovimiento(Matriz);
				repintar(fondo);
			}
		});
		
		
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Matriz[i][j] = new JButton(""+(i*4+j+1));
                agregarAccion(Matriz[i][j],i,j,fondo);
                fondo.add(Matriz[i][j]);
            }
		}
		
		desordenar(fondo);
	}
	
	
	
	void agregarAccion(final JButton boton, final int x, final int y,JPanel fondo) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                lista.reinicioContadores();
                lista.desplegarLista();
                lista.moverFicha(Matriz[x][y],Matriz);
                repintar(fondo);
            }


        });
        
    }
	//Repintar
	public void repintar (JPanel fondo) {
		fondo.removeAll();
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            Matriz[i][j].setVisible(true);
            if (Matriz[i][j].getText().equals("2") || 
            	Matriz[i][j].getText().equals("4") ||
            	Matriz[i][j].getText().equals("6") ||
            	Matriz[i][j].getText().equals("8") ||
            	Matriz[i][j].getText().equals("10") || 
            	Matriz[i][j].getText().equals("12") ||
            	Matriz[i][j].getText().equals("14")) {
            	Matriz[i][j].setBackground(Color.blue);
            }else {
            	Matriz[i][j].setBackground(Color.cyan);            	
            }
            Matriz[i][j].setEnabled(true);
            fondo.add(Matriz[i][j]);
            if (contGlobal ==0){
            	lista.ColocarValores(Matriz[i][j]);  
            }
            if (Matriz[i][j].getText().equals("16")) {
            	Matriz[i][j].setBackground(Color.white);
            	Matriz[i][j].setVisible(false);
            	Matriz[i][j].setEnabled(false);
            }
            }
        }
		if (contGlobal ==0){
			lista.CrearLista();
			contGlobal++;
		}
		lista.comprobarGanador();
		
		jl1.setText("    Correctas: "+lista.getContt()+"           ");
		jl1.setForeground(Color.white);
		jl1.setFont(new Font("Open sans", Font.BOLD,25));
		jl1.setBackground(Color.black);
		fondoContador.add(jl1, BorderLayout.EAST);
		
		revalidate();
		repaint();
	}
	
	//DESORDENAR JAJAJA 
	public void desordenar(JPanel fondo) {
		int cont = 0,ii=0,jj=0;
		fondo.removeAll();
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	numeros[i*4+j] = 0;
            	Matriz[i][j].setBackground(Color.black);
            }	
        }
		//lista.reinicioContadores();
		try {
			do {		
				for (int i=0;i<4;i++) {
					 for (int j = 0; j < 4; j++) {
						 
				int n1 = rand.nextInt(4);
				int n2 = rand.nextInt(4);
				int num = n1*4+n2+1;
				if (Matriz[n1][n2].getBackground().equals(Color.black)) {
				Matriz[n1][n2].setBackground(Color.CYAN);
				Matriz[n1][n2].setText(""+(cont+1));
				Matriz[n1][n2].setFont(new Font("Open sans", Font.BOLD,40));
				fondo.add(Matriz[n1][n2]);
				cont++;
				//ok.out.println("CONT "+cont);
							}
					 }
				}
				
				} while (cont <16);
			lista.colocarValores2(Matriz);
		}catch (Exception e){
			
		}
		this.repintar(fondo);
		repaint();
		revalidate();
	}
	 public boolean buscarNumero(int numero) {
		 int cont=0;
	        for (int i = 0; i < numeros.length; i++) {
	            if (this.numeros[i] != numero) {
	            	cont++;
	            	if (cont == numeros.length-1) {
	            		this.numeros[i] = numero;
	            		return true;
	            	}
	            }
	        }
	        return false;
	    }

	 public JButton[][] getMatriz() {
			return Matriz;
		}

		public static void setValorMatriz(String[][] matriz,int i,int j) {
			Matriz[i][j].setText(matriz[i][j]);
		}




}