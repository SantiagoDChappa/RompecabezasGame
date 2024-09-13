package game;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class interfazGame {

	private JFrame frmJuego;
	private JTextField textTitle;
	private JButton[][] buttons;
	private JLabel moveLabel; // Para mostrar el número de movimientos
	private logicaGame logica; // Referencia a la clase de lógica

	// Constructor
	public interfazGame() {
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazGame window = new interfazGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmJuego = new JFrame("Rompecabezas Deslizante");
		frmJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJuego.setSize(400, 400);
		frmJuego.getContentPane().setLayout(new BorderLayout());

		textTitle = new JTextField("¡Este es el juego del ROMPECABEZAS DESLIZANTE!");
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		frmJuego.getContentPane().add(textTitle, BorderLayout.NORTH);
		textTitle.setColumns(10);

		JPanel gridPanel = new JPanel(new GridLayout(4, 4));
		buttons = new JButton[4][4];

		// Generar una lista de números desordenados
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= 15; i++) {
			numbers.add(i);
		}
		numbers.add(0); 				// 0 representa el espacio vacío
		Collections.shuffle(numbers); 	// Mezcla aleatoria

		// Asignar números desordenados a los botones
		int index = 0;
		int emptyRow = 0, emptyCol = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int value = numbers.get(index++);
				JButton button = new JButton(value == 0 ? "" : String.valueOf(value));
				buttons[i][j] = button;
				gridPanel.add(button);

				if (value == 0) {
					emptyRow = i;
					emptyCol = j;
				}
				final int row = i;
				final int col = j;

				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Mover el casillero
						logica.moveTile(row, col);
					}
				});
			}
		}

		moveLabel = new JLabel("Movimientos: 0");
		moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmJuego.getContentPane().add(moveLabel, BorderLayout.SOUTH);
		frmJuego.getContentPane().add(gridPanel, BorderLayout.CENTER);

		// Inicializar la clase de lógica
		logica = new logicaGame(buttons, emptyRow, emptyCol, this);

		frmJuego.setVisible(true);
	}

	// Método para actualizar el contador de movimientos en la interfaz
	public void updateMove(int movimientos) {
		moveLabel.setText("Movimientos: " + movimientos);
	}
	
	public void messegeWinner() {
		JOptionPane.showMessageDialog(frmJuego, "¡Felicidades! Has completado el rompecabezas. "
				+ "Tus movimientos fueron: " + logica.getMoves());
		
	}
	
}
