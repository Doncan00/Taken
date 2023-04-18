
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
	public JButton[][] Matriz = new JButton[4][4];
	
	Lista lista = new Lista(4,4);
	
	public Ventana() {
		
		this.setVisible(true);
		this.setTitle("Hola Zumayaaaaaa");
		this.setSize(640,480);
		this.setResizable(true);
		//this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel principal = new JPanel();
		principal.setBounds(640,480,0,0);
		principal.setVisible(true);
		principal.setBackground(Color.white);
		principal.setLayout(new BorderLayout());
		this.add(principal);
		
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
		principal.add(borderArr,BorderLayout.SOUTH);
		JLabel borderAba = new JLabel("HOLA");
		borderAba.setOpaque(true);
		borderAba.setForeground(Color.black);
		borderAba.setBackground(Color.black);
		borderAba.setFont(new Font("Open sans", Font.BOLD,25));
		principal.add(borderAba,BorderLayout.NORTH);
		
		
		JPanel fondo = new JPanel();
		fondo.setBounds(640,480,0,0);
		fondo.setVisible(true);
		fondo.setBackground(Color.magenta);
		fondo.setLayout(new GridLayout(4,4,2,2));
		principal.add(fondo,BorderLayout.CENTER);
		
		
		Matriz[3][3] = new JButton ("");
	    Matriz[3][3].setEnabled(false);
	       fondo.add(Matriz[3][3]);
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	if (i != 3 || j != 3) {
                Matriz[i][j] = new JButton(""+(i*4+j+1));
                agregarAccion(Matriz[i][j],i,j,Matriz,fondo);
                fondo.add(Matriz[i][j]);
            	}
            }
		}
		
		desordenar(fondo);
	}
	
	void agregarAccion(final JButton boton, final int x, final int y,JButton[][] Matriz , JPanel panel) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                lista.buscarEspacio(boton,Matriz,panel);
                lista.desplegarLista();
                
            }


        });
        
    }
	
	public void ordenar(JButton[][] Matriz , JPanel panel) {
		lista.reacomodarFichas(Matriz,panel);
	}
	
	//DESORDENAR JAJAJA 
	public void desordenar(JPanel fondo) {
		int cont = 0;
		fondo.removeAll();
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	Matriz[i][j].setBackground(Color.black);
            }	
        }
		try {
			do {				
			int n1 = rand.nextInt(4);
			int n2 = rand.nextInt(4);
			if (Matriz[n1][n2].getBackground().equals(Color.black)) {
			Matriz[n1][n2].setBackground(Color.CYAN);
			lista.ColocarValores(n1, n2);
			fondo.add(Matriz[n1][n2]);
			cont++;
			//System.out.println("CONT "+cont);
			}
			
			} while (cont <16);
			lista.CrearLista();
		}catch (Exception e){
			
		}
		
		Matriz[3][3].setBackground(Color.white);
		fondo.revalidate();
	}
}