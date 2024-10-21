package game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuInicial {
	
	private JFrame frameMenuInicial;
	private Boolean facil = false;
	private Boolean medio = false;
	private Boolean dificil = false;
	
	public MenuInicial() {
		frameMenuInicial = new JFrame();
		frameMenuInicial.setBounds(100, 100, 769, 484);

		frameMenuInicial.getContentPane().setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Ingrese un nivel de dificultad: 1 (Facil), 2 (Medio), 3 (Dificil)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(62, 42, 636, 70);
		frameMenuInicial.getContentPane().add(lblNewLabel);

	};

	public void crearMenu(Tablero frameTable) {
		// Boton Facil
		JButton btnEasy = new JButton("Facil");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frameMenuInicial.setVisible(false);
                frameTable.crearTablero(1);
			}
		});
		btnEasy.setBounds(257, 143, 259, 70);
		frameMenuInicial.getContentPane().add(btnEasy);
		
		// Boton Medio
		JButton btnMedium = new JButton("Medio");
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenuInicial.setVisible(false);
                frameTable.crearTablero(2);
			}
		});
		btnMedium.setBounds(257, 224, 259, 70);
		frameMenuInicial.getContentPane().add(btnMedium);
		
		// Boton dificil
		JButton btnHard = new JButton("Dificil");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frameMenuInicial.setVisible(false);
                frameTable.crearTablero(3);
			}
		});
		btnHard.setBounds(257, 305, 259, 70);
		frameMenuInicial.getContentPane().add(btnHard);
	}
	
/*	public Integer getNivel() {
		if (facil) {
			return 1;
		} else if (medio) {
			return 2;
		} else if (dificil) {
			return 3;
		}
		return 0;
	}*/
	
	public void setVisible(Boolean visible) {
		frameMenuInicial.setVisible(visible);
	}
}
