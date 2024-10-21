package game;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tablero {
	
	private JFrame frameTable;
	
	public Tablero() {
		this.frameTable = new JFrame();
		this.frameTable.setBounds(100, 100, 591, 620);
		}
	
	public void crearTablero(int nivel) {
		try {
            int cantCasilleros = 0;
			
			if(nivel == 1){ // Facil
				this.frameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.frameTable.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
				
				cantCasilleros = 8;
				
			}else if(nivel == 2){ // Medio
				this.frameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.frameTable.getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
				
				cantCasilleros = 15;
				
			}else if(nivel == 3){ // Dificil
            	this.frameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	this.frameTable.getContentPane().setLayout(new GridLayout(0, 5, 0, 0));
            	
				cantCasilleros = 19;

			}else {
				throw new ArithmeticException("Error al elegir el nivel del tablero");
			}
			
			JButton listNum[] = new JButton[cantCasilleros];
			
			for(int i=0; i < cantCasilleros; i++) {
				// Creo una variable Image para obtenerme la imagen del paquete img
				Image img = new ImageIcon(getClass().getResource("/img/btn" + (i+1) + ".png")).getImage();
				
				// Creo un JButton con la imagen de cada numero
				JButton btnNumero = new JButton() {
					@Override
					protected void paintComponent(Graphics g) {
						g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
					}
				};
				listNum[i] = btnNumero;
            }
			
			// Voy agregando los botones al frame
			for (int i = 0; i < cantCasilleros; i++) {
				frameTable.getContentPane().add(listNum[i]);
			}
			
			this.frameTable.setVisible(true);
			
		} catch (ArithmeticException  e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setVisible(Boolean visible) {
		frameTable.setVisible(visible);
	}
}
