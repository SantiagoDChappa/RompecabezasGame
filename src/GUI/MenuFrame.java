package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Logica.MenuLogica;


public class MenuFrame {
	
	private TableroFrame TableroFrame;
	private MenuLogica menuLogica;
	private JFrame MenuFrame;
	private String imagen;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int dificultad;

	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame window = new MenuFrame();
					window.MenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenuFrame() {
		if (MenuFrame != null && MenuFrame.isVisible() == false) {
			MenuFrame.setVisible(true);
		}
		MenuFrame = new JFrame();
		MenuFrame.setBounds(100, 100, 769, 484);
		MenuFrame.getContentPane().setLayout(null);
		MenuFrame.setLocationRelativeTo(null);
		menuLogica = new MenuLogica();
				
		JLabel lblNewLabel = new JLabel("ROMPECABEZAS DESLIZANTE!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 30, 733, 70);
		
		MenuFrame.getContentPane().add(lblNewLabel);
		MenuFrame.getContentPane().setLayout(null);
		
		JLabel lblIngrese = new JLabel("Ingrese modo de juego:");
		lblIngrese.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngrese.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblIngrese.setBounds(255, 110, 222, 30);
		lblIngrese.setVisible(false);
		
		MenuFrame.getContentPane().setLayout(null);
		MenuFrame.getContentPane().add(lblIngrese);
		
		JRadioButton radioNumeros = new JRadioButton("Numeros");
		radioNumeros.setHorizontalAlignment(SwingConstants.CENTER);
		radioNumeros.setBounds(297, 147, 137, 38);
		buttonGroup.add(radioNumeros);
		MenuFrame.getContentPane().add(radioNumeros);
		radioNumeros.setVisible(false);
		
		JRadioButton radioImagenes = new JRadioButton("Imágenes");
		radioImagenes.setHorizontalAlignment(SwingConstants.CENTER);
		radioImagenes.setBounds(300, 185, 137, 38);
		buttonGroup.add(radioImagenes);
		MenuFrame.getContentPane().add(radioImagenes);
		radioImagenes.setVisible(false);
		
		JComboBox comboImagenes = new JComboBox();
		comboImagenes.setBounds(285, 230, 165, 38);
		comboImagenes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una imagen", "Futbolista", "Musico", "Paisaje", "Perros"}));
		MenuFrame.getContentPane().add(comboImagenes);
		comboImagenes.setVisible(false);
		
		JButton btnElegirNivel = new JButton("ELEGIR NIVEL");
		btnElegirNivel.setBounds(268, 304, 201, 70);
		btnElegirNivel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		MenuFrame.getContentPane().add(btnElegirNivel);
		btnElegirNivel.setVisible(false);
		
		// Boton Facil
		JButton btnEasy = new JButton("Facil - 3x3");
		btnEasy.setBounds(257, 143, 259, 70);
		MenuFrame.getContentPane().add(btnEasy);
		btnEasy.setVisible(false);

		
		// Boton Medio
		JButton btnMedium = new JButton("Medio - 4x4");
		btnMedium.setBounds(257, 224, 259, 70);
		MenuFrame.getContentPane().add(btnMedium);
		btnMedium.setVisible(false);

		
		// Boton dificil
		JButton btnHard = new JButton("Dificil - 5x5");
		btnHard.setBounds(257, 305, 259, 70);
		MenuFrame.getContentPane().add(btnHard);
		btnHard.setVisible(false);
		
		lblIngrese.setVisible(true);
		radioImagenes.setVisible(true);
		radioNumeros.setVisible(true);
		radioNumeros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnElegirNivel.setVisible(true);
				comboImagenes.setVisible(false);
				
				btnElegirNivel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lblIngrese.setVisible(false);
						radioImagenes.setVisible(false);
						radioNumeros.setVisible(false);
						btnElegirNivel.setVisible(false);
						
						btnEasy.setVisible(true);
						btnMedium.setVisible(true);
						btnHard.setVisible(true);
					}
				});
			}
		});
		radioImagenes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboImagenes.setVisible(true);	
				btnElegirNivel.setVisible(false);
				
				comboImagenes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String item = comboImagenes.getSelectedItem().toString();

						if(item == "Futbolista") {
							imagen = "Futbolista";
							btnElegirNivel.setVisible(true);
						}
						if(item == "Musico") {
							imagen = "Musico";
							btnElegirNivel.setVisible(true);
						}
						if(item == "Paisaje") {
							imagen = "Paisaje";
							btnElegirNivel.setVisible(true);
						}
						if(item == "Perros") {
							imagen = "Perros";
							btnElegirNivel.setVisible(true);
						}
						if (item == "Seleccione una imagen") {
							btnElegirNivel.setVisible(false);
						}
						
						
						btnElegirNivel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
                                lblIngrese.setVisible(false);
                                radioImagenes.setVisible(false);
                                radioNumeros.setVisible(false);
								btnElegirNivel.setVisible(false);


                				comboImagenes.setVisible(false);	

                                btnEasy.setVisible(true);
                                btnMedium.setVisible(true);
                                btnHard.setVisible(true);
							}
						});
					}
				});
			}
		});
		
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame.setVisible(false);
				TableroFrame = new TableroFrame(1, imagen);
				TableroFrame.setVisible(true);
				menuLogica.iniciarJuego(1);	

			}
		});
		
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame.setVisible(false);
				TableroFrame = new TableroFrame(2, imagen);
				TableroFrame.setVisible(true);
				menuLogica.iniciarJuego(2);	
			}
		});
		
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame.setVisible(false);
				TableroFrame = new TableroFrame(3, imagen);
				TableroFrame.setVisible(true);
				menuLogica.iniciarJuego(3);	
			}
		});
	};
	
	public void cerrar() {
		try {
			MenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			MenuFrame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			MenuFrame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int obtenerDificultadSeleccionada() {
		return dificultad;
	}
	
	public String obtenerImagenSeleccionada() {
		return imagen;
	}
	
	public void setVisible(boolean b) {
		MenuFrame.setVisible(b);
		
	}
}
