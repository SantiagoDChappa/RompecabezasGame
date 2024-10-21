package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.awt.event.MouseEvent;
//Add this line at the beginning of your file
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import Logica.TableroLogica;

public class TableroFrame {
	
	private Hashtable<String, BufferedImage> Imagenes;
	private JButton[][] botones;
	private MenuFrame menuInicial;
	private TableroLogica TableroLogica; 
	private BufferedImage[] buffer;
	private JFrame frameGame;
    private JPanel gridPanel;
	private JLabel moveLabel, textTime; // Para mostrar el número de movimientos
	private Timer timer;
	private String nombre;
	private int emptyRow, emptyCol;
	private int tamanioMatriz;
	private int[] timeCounter;
	private int[] posicionVacia;
	private String[] opciones;
	private int nivelElegido;
;
	
    public TableroFrame(int nivel, String nombreImagen) {
    	initialize(nivel, nombreImagen);
    }
    
	private void initialize(int nivel, String nombreImagen) {
	    TableroLogica = new TableroLogica();  // Inicialización de TableroLogica
	    frameGame = new JFrame("Trabajo Practico N°1");
		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frameGame.setBounds(100, 100, 592, 401);
    	frameGame.setSize(700, 600);
    	frameGame.getContentPane().setLayout(null);
    	frameGame.setLocationRelativeTo(null);
		    	
    	textTime = new JLabel("Tiempo: 00:00");
    	textTime.setBounds(537, 298, 137, 37);
	    textTime.setHorizontalAlignment(SwingConstants.CENTER);
	    textTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    frameGame.getContentPane().add(textTime, BorderLayout.NORTH);
	    
	    moveLabel = new JLabel("Movimientos: 0");
	    moveLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    moveLabel.setBounds(537, 250, 137, 37);
	    frameGame.getContentPane().add(moveLabel, BorderLayout.SOUTH);
	    moveLabel.setHorizontalAlignment(SwingConstants.CENTER);

		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridPanel = new JPanel(new GridLayout(4, 4));
		gridPanel.setBounds(5, 23, 512, 512);
		gridPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		frameGame.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        frameGame.requestFocusInWindow();
		    }
		});
		
		JButton botonArriba = new JButton("\u2191"); // BOTON ARRIBA
		botonArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicionVacia = TableroLogica.obtenerPosicionVacia();
				int filaAMover = posicionVacia[0];
				int columnaAMover = posicionVacia[1];
				
				if(filaAMover + 1 < tamanioMatriz) {
					moverPieza(filaAMover + 1, columnaAMover);

				}
			}
		});
		botonArriba.setBounds(583, 63, 44, 44);
		frameGame.getContentPane().add(botonArriba);
		
		JButton botonIzq = new JButton("\u2190"); // BOTON IZQUIERDA
		botonIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicionVacia = TableroLogica.obtenerPosicionVacia();
				int filaAMover = posicionVacia[0];
				int columnaAMover = posicionVacia[1];
				
				if(columnaAMover + 1 < tamanioMatriz) {
					moverPieza(filaAMover, columnaAMover + 1);

				}
			}
		});
		botonIzq.setBounds(549, 118, 44, 44);
		frameGame.getContentPane().add(botonIzq);
		
		JButton botonDer = new JButton("\u2192"); // BOTON DERECHA
		botonDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicionVacia = TableroLogica.obtenerPosicionVacia();
				int filaAMover = posicionVacia[0];
				int columnaAMover = posicionVacia[1];
				
				if(columnaAMover - 1 >= 0) {
					moverPieza(filaAMover, columnaAMover - 1);
				}
			}
		});
		botonDer.setBounds(614, 118, 44, 44); 
		frameGame.getContentPane().add(botonDer);
		
		JButton botonAbajo = new JButton("\u2193"); // BOTON ABAJO
		botonAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicionVacia = TableroLogica.obtenerPosicionVacia();
				int filaAMover = posicionVacia[0];
				int columnaAMover = posicionVacia[1];
				
	        	if(filaAMover - 1 >= 0) {
					moverPieza(filaAMover - 1, columnaAMover);
	        	}
			}
		});
		botonAbajo.setBounds(583, 173, 44, 44);
		frameGame.getContentPane().add(botonAbajo);
		
		JButton buttonReturn = new JButton("Volver al Menú");
		buttonReturn.setBounds(10, 0, 150, 23);
		frameGame.getContentPane().add(buttonReturn);
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int valor = JOptionPane.showConfirmDialog(frameGame, "¿Esta seguro de volver al menu?\n","Advertencia", JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.YES_OPTION) {
					// Creo el frame del menu inicial
					menuInicial = new MenuFrame();	
					menuInicial.setVisible(true);
					frameGame.setVisible(false);
				}
			}
		});

		opciones = new String[3];
        opciones[0] = "Repetir Nivel";
        opciones[1] = "Volver al Menú";
        opciones[2] = "Cerrar Juego";
		
		frameGame.setFocusable(true);
		frameGame.requestFocusInWindow(); 
		
		nombre = nombreImagen;
		nivelElegido = nivel;
	    		
		Imagenes = new Hashtable();
        cargarImagenes();
        crearTablero(nivelElegido);
    }
    
	public void crearTablero(int nivel) {
		try {
            int cantCasilleros = 0;
			
			if(nivel == 1){ // Facil
				this.frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gridPanel = new JPanel(new GridLayout(3, 3));
				gridPanel.setBounds(5, 23, 512, 512);
				gridPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				botones = new JButton[3][3];
				tamanioMatriz = 3;
				cantCasilleros = 8;
				
			}else if(nivel == 2){ // Medio
				this.frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gridPanel = new JPanel(new GridLayout(4, 4));
				gridPanel.setBounds(5, 23, 512, 512);
				gridPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				botones = new JButton[4][4];
				tamanioMatriz = 4;
				cantCasilleros = 15;
				
			}else if(nivel == 3){ // Dificil
            	this.frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gridPanel = new JPanel(new GridLayout(5, 5));
				gridPanel.setBounds(5, 23, 512, 512);
				gridPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				botones = new JButton[5][5];
				tamanioMatriz = 5;
				cantCasilleros = 19;

			}else {
				throw new ArithmeticException("Error al elegir el nivel del tablero");
			}
			
			frameGame.getContentPane().add(gridPanel);
			
			TableroLogica.crearTablero(nivel);
			TableroLogica.mezclarRompecabezas(500, tamanioMatriz);
			int[][] tablero = TableroLogica.obtenerTablero();

			int index = 0;
			posicionVacia = new int[2];
			for (int i = 0; i < tamanioMatriz; i++) {
				for (int j = 0; j < tamanioMatriz; j++) {
					if (index == 0 && nombre == null) {
						// Espacio vacío
						botones[i][j] = new JButton("");
						botones[i][j].setVisible(false);
		            } else {
		                botones[i][j] = new JButton(String.valueOf(tablero[i][j]));
		            }
					gridPanel.add(botones[i][j]);
					
					final int fila = i;
					final int columna = j;
							            
					
					botones[i][j].addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {		          
		                	moverPieza(fila, columna);
		                }
		            });
	            	index++;
				}
			}
			
			frameGame.getContentPane().setLayout(null);
			frameGame.addKeyListener(new KeyAdapter(){	
				public void keyPressed(KeyEvent e) {
					int codigoTecla = e.getKeyCode();
					posicionVacia = TableroLogica.obtenerPosicionVacia();
					
					int filaAMover = posicionVacia[0];
					int columnaAMover = posicionVacia[1];
					
			        if(codigoTecla == KeyEvent.VK_W || codigoTecla == KeyEvent.VK_UP) {
		            	if(filaAMover + 1 < tamanioMatriz) {
		            		moverPieza(filaAMover + 1, columnaAMover);
		            	}
			        }
			        if((codigoTecla == KeyEvent.VK_S) || (codigoTecla == KeyEvent.VK_DOWN)) {
			        	if(filaAMover - 1 >= 0) {
			        		moverPieza(filaAMover - 1, columnaAMover);
			        	}
			        }
			        if(codigoTecla == KeyEvent.VK_A || codigoTecla == KeyEvent.VK_LEFT) {
		            	if(columnaAMover + 1 < tamanioMatriz) {
		            		moverPieza(filaAMover, columnaAMover + 1);
		            	}
			        }

			        if(codigoTecla == KeyEvent.VK_D || codigoTecla == KeyEvent.VK_RIGHT) {
			        	if(columnaAMover - 1 >= 0) {
			        		moverPieza(filaAMover, columnaAMover - 1);
			        	}
			        }
			    }
			});
			
			this.frameGame.setVisible(true);
			
			//EMPEZAR A JUGAR
			actualizarPantalla(botones, gridPanel, tamanioMatriz);
			int valor = JOptionPane.showConfirmDialog(frameGame,"Presione ENTER/ESPACIO o darle click a ACEPTAR para iniciar a jugar",null,JOptionPane.DEFAULT_OPTION);
			if(valor == JOptionPane.OK_OPTION && nombre != null) {
                frameGame.setFocusable(true);
                frameGame.requestFocusInWindow();
			}
			
		   	// Inicializa el contador de tiempo
	    	timeCounter = new int[2];
	    	ActionListener timerListener = new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	timeCounter[1]++;
	    	         if (timeCounter[1] == 60) {
	    	        	 timeCounter[0]++;
	    	        	 timeCounter[1] = 0;
	    	         }
	    	         textTime.setText("Tiempo: " + String.format("%02d:%02d", timeCounter[0], timeCounter[1]));
	    	    }
	    	};
	    	timer = new Timer(1000, timerListener);
	    	timer.start();
	         
			
		} catch (ArithmeticException  e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void cargarImagenes(){
		try {
			BufferedImage BuffImage = ImageIO.read(new File("src/images/futbolista.png"));
			Imagenes.put("Futbolista", BuffImage);
			BuffImage = ImageIO.read(new File("src/images/perros.png"));
			Imagenes.put("Perros", BuffImage);
			BuffImage = ImageIO.read(new File("src/images/musico.png"));
			Imagenes.put("Musico", BuffImage);
			BuffImage = ImageIO.read(new File("src/images/paisaje.png"));
			Imagenes.put("Paisaje", BuffImage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage[] dividirImagen(String n, int tamanioMatriz){
	    BufferedImage BuffImage = Imagenes.get(n);
	    
	    // Escalar la imagen al tamaño del grid panel
	    Image scaledImage = BuffImage.getScaledInstance(gridPanel.getWidth(), gridPanel.getHeight(), Image.SCALE_DEFAULT);
	    BufferedImage scaledBufferedImage = new BufferedImage(gridPanel.getWidth(), gridPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    
	    Graphics2D g2d = scaledBufferedImage.createGraphics();
	    g2d.drawImage(scaledImage, 0, 0, null);
	    g2d.dispose();
	    
	    int ancho = scaledBufferedImage.getWidth() / tamanioMatriz;
	    int alto = scaledBufferedImage.getHeight() / tamanioMatriz;
	    BufferedImage[] subImage = new BufferedImage[tamanioMatriz * tamanioMatriz];
	    
	    int cont = 0;
	    for (int y = 0; y < tamanioMatriz; y++) {
	        for (int x = 0; x < tamanioMatriz; x++) {
	            if (cont < subImage.length - 1) { // Deja la última imagen vacía para el espacio en blanco
	                subImage[cont] = scaledBufferedImage.getSubimage(x * ancho, y * alto, ancho, alto);
	            }
	            cont++;
	        }
	    }
	    return subImage;
	}
	
	//ACTUALIZAR PANTALLA
	private void actualizarPantalla(JButton[][] botones, JPanel panel, int tamanioMatriz) {
		int[][] tablero = TableroLogica.obtenerTablero();
		posicionVacia = TableroLogica.obtenerPosicionVacia();
		emptyRow = posicionVacia[0];
		emptyCol = posicionVacia[1];
		botones[emptyRow][emptyCol].setVisible(false);		
		botones[emptyRow][emptyCol].setText("");
		panel.removeAll();
		
		for (int i = 0; i < tamanioMatriz; i++) {
			for (int j = 0; j < tamanioMatriz; j++) {
				botones[i][j] = new JButton(tablero[i][j] != 0 ? String.valueOf(tablero[i][j]) : "");
				if(nombre != null) {
					buffer = dividirImagen(this.nombre, tamanioMatriz);
					ponerImagen(botones, buffer, tamanioMatriz);
				}
				panel.add(botones[i][j]);
				
				final int row = i;
				final int col = j;
				
				botones[i][j].addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	moverPieza(row, col);
        		    }
                });
			}
		}
		botones[emptyRow][emptyCol].setVisible(false);	
		actualizarMovimientos(TableroLogica.obtenerMovimientos());
	}
	

	private void ponerImagen(JButton[][] botones, BufferedImage[] imagen, int tamanioMatriz) {
	    ImageIcon imagenBoton;
	    for (int j = 0; j < tamanioMatriz; j++) {
	        for (int i = 0; i < tamanioMatriz; i++) {
	            String buttonText = botones[i][j].getText();
	            if (!buttonText.isEmpty()) {
	                int index = Math.abs((Integer.parseInt(buttonText) - 1) % imagen.length);
	                imagenBoton = new ImageIcon(imagen[index]);
	                botones[i][j].setIcon(imagenBoton);
	            }
	        }
	    }
	}
	
	private void moverPieza(int fila, int columna) {
		TableroLogica.moverPieza(fila, columna, tamanioMatriz);
		actualizarPantalla(botones, gridPanel, tamanioMatriz);
		if (TableroLogica.verificarTablero(tamanioMatriz)) {
			mensajeGanador();
		}
		
		frameGame.setFocusable(true);
        frameGame.requestFocusInWindow();
	}
	
	public void setVisible(Boolean visible) {
		frameGame.setVisible(visible);
	}

	public int obtenerMinutosTimer() {
		return timeCounter[0];
	}
	
	public int obtenerSegundosTimer() {
		return timeCounter[1];
	}
	
	// Método para actualizar el contador de movimientos en la interfaz
	public void actualizarMovimientos(int movimientos) {
		moveLabel.setText("Movimientos: " + movimientos);
	}
	
	public void mensajeGanador() {
		timer.stop();
		 int seleccion = JOptionPane.showOptionDialog(frameGame, 
	                "¡Felicidades! Has completado el rompecabezas. Tus movimientos fueron: " + /*logica.getMoves() +*/ " y tu tiempo fue de " + obtenerMinutosTimer() + ":" + obtenerSegundosTimer() + " segundos.", 
	                "Opciones del Juego", 
	                JOptionPane.DEFAULT_OPTION, 
	                JOptionPane.INFORMATION_MESSAGE, 
	                null, opciones, opciones[0]);
		 
	        if(seleccion == 0) {
                frameGame.setVisible(false);
                initialize(nivelElegido, nombre);
	        }
	        if(seleccion == 1) {
                frameGame.setVisible(false);
                reiniciarValores();
                menuInicial = new MenuFrame();
                menuInicial.setVisible(true);
	        }
	        if(seleccion == 2) {
                System.exit(0);
	        }
	}
	
	private void reiniciarValores() {
		nombre = "";
	}
	
}
