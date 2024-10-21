package Logica;


public class TableroLogica {
	private int[][] tablero;
	private int[] posicionVacia;
    private int moves, filaVacia, columnaVacia;

    public TableroLogica() {
        this.moves = 0;
        this.posicionVacia = new int[2];
    }
    
	public void crearTablero(int dificultad) {
		if(dificultad == 1) {
			this.tablero = new int[3][3];
			
		}else if(dificultad == 2) {
			this.tablero = new int[4][4];
			
		}else if(dificultad == 3) {
			this.tablero = new int[5][5];
		}	
		llenarTablero();
	}
	
	private void llenarTablero() {
	    int contador = 0;
	    for (int i = 0; i < tablero.length; i++) {
	        for (int j = 0; j < tablero[i].length; j++) {
	        	tablero[i][j] = contador++;
	        }
	    }
	}
	
    public void moverPieza(int fila, int columna, int tamanioMatriz) {
        if  (fila >= 0 && columna >= 0 && (Math.abs(fila - filaVacia) + Math.abs(columna - columnaVacia) == 1)) {
            tablero[filaVacia][columnaVacia] = tablero[fila][columna];
            tablero[fila][columna] = 0;
            filaVacia = fila;
            columnaVacia = columna;

			posicionVacia[0] = filaVacia;
			posicionVacia[1] = columnaVacia;
            
            moves++;            										
        }
    }
    
    public boolean verificarTablero(int tamanioMatriz) {
        int valorEsperado = 1;
        int text = 0;
        
        for (int i = 0; i < tamanioMatriz; i++) {
            for (int j = 0; j < tamanioMatriz; j++) {
            	if(tablero == null) {
            		text = tablero[i][j];
            	}else{
            		text = tablero[i][j];            		
            	}
                
                if (text == 0) {
                    continue;
                }

                if (!(text == valorEsperado) && !(text == 0)) {
                    return false;  // Si hay un valor incorrecto, el tablero no está ordenado
				} else if (text == 0 && valorEsperado == tamanioMatriz * tamanioMatriz) {
					continue;
                }
                valorEsperado++;
            }
        }
        return true;  
    }
    
    
    public void mezclarRompecabezas(int movimientos, int tamanioMatriz) {
    	// Arrays para representar los cambios de fila y columna
        int[] dFila = {-1, 1, 0, 0};  // Arriba, abajo
        int[] dColumna = {0, 0, -1, 1};  // Izquierda, derecha

        for (int i = 0; i < movimientos; i++) {
            int randomDirection = (int) (Math.random() * 4);  // Generar una dirección aleatoria

            int nuevaFila = filaVacia + dFila[randomDirection];  // Cambiar la fila de acuerdo con la dirección
            int nuevaColumna = columnaVacia + dColumna[randomDirection];  // Cambiar la columna de acuerdo con la dirección

            // Verificar si la nueva posición es válida
            if (nuevaFila >= 0 && nuevaFila < tamanioMatriz && nuevaColumna >= 0 && nuevaColumna < tamanioMatriz) {
                // Intercambiar las posiciones
            	intercambiarCasilleros(filaVacia, columnaVacia, nuevaFila, nuevaColumna);
            	filaVacia = nuevaFila;
            	columnaVacia = nuevaColumna;
            }
        }
        posicionVacia[0] = filaVacia;
        posicionVacia[1] = columnaVacia;
    }
    
    
    private void intercambiarCasilleros(int fila1, int columna1, int fila2, int columna2) {
        int tempText = tablero[fila1][columna1];
        tablero[fila1][columna1] = tablero[fila2][columna2];
        tablero[fila2][columna2] = tempText;
    }
    
    public int[] obtenerPosicionVacia() {
       return posicionVacia;
    }
    
	public int[][] obtenerTablero() {
		return tablero;
	}
	
	public int obtenerMovimientos() {
		return moves;
	}
}

