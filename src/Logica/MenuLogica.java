package Logica;

public class MenuLogica {

	private TableroLogica tableroLogica;
	
    public MenuLogica() {
        tableroLogica = new TableroLogica();
    }

    public void iniciarJuego(int dificultad) {
        tableroLogica.crearTablero(dificultad);
    }
    
}

