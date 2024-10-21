package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import game.Logica;

import game.menuInicial;
import game.Tablero;


public class Interfaz {

	private MenuInicial menuInicial;
	private JTable table;
	private Logica logica;
	private Tablero tablero;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 Interfaz interfaz = new Interfaz();
				     interfaz.iniciar();
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   public void iniciar() {
        menuInicial.setVisible(true);
        
		// Creo el frame del menu inicial
		tablero = new Tablero();
		menuInicial.crearMenu(tablero);
		
		// Creo el frame del tablero
		/*while (menuInicial.getNivel() == 0)
		menuInicial.setVisible(false);
		tablero.setVisible(true);*/
    }


	/**
	 * Initialize the contents of the frameTable
	 */
	public Interfaz() {
        menuInicial = new MenuInicial();
	}
	
}
