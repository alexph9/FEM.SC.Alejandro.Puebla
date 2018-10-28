package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class JuegoCelta {
	static final int TAMANIO = 7;
    private static final int NUM_MOVIMIENTOS = 4;
	private int[][] tablero;
	private RepositorioScores db;
    private static final int[][] TABLERO_INICIAL = { // Posiciones válidas del tablero
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0}
    };
    private static final int[][] desplazamientos = {
            { 0,  2},   // Dcha
            { 0, -2},   // Izda
            { 2,  0},   // Abajo
            {-2,  0}    // Arriba
    };
    private int iSeleccionada, jSeleccionada;   // coordenadas origen ficha
	private int iSaltada, jSaltada;             // coordenadas ficha sobre la que se hace el movimiento

	private enum Estado {
		ESTADO_SELECCION_FICHA, ESTADO_SELECCION_DESTINO, ESTADO_TERMINADO
	}

	private Estado estadoJuego;

	private int score = 0;

    /**
     * Constructor
     * Inicializa el tablero y el estado del miJuego
     */
    public JuegoCelta(RepositorioScores db) {
    	this.db = db;
		tablero = new int[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++)
        	System.arraycopy(TABLERO_INICIAL[i], 0, tablero[i], 0, TAMANIO);
        tablero[TAMANIO / 2][TAMANIO / 2] = 0;   // posición central

		estadoJuego = Estado.ESTADO_SELECCION_FICHA;
	}

    /**
     * Devuelve el contenido de una posición del tablero
     * @param i fila del tablero
     * @param j columna del tablero
     * @return contenido
     */
	protected int obtenerFicha(int i, int j) {
		return tablero[i][j];
	}

    /**
     * Determina si el movimiento (i1, j1) a (i2, j2) es aceptable
	 *
     * @param i1 fila origen
     * @param j1 columna origen
     * @param i2 fila destino
     * @param j2 columna destino
     * @return valor lógico
     */
    private boolean movimientoAceptable(int i1, int j1, int i2, int j2) {

        if (tablero[i1][j1] == 0 || tablero[i2][j2] == 1)
            return false;

        if ((j1 == j2 && Math.abs(i2 - i1) == 2)
                || (i1 == i2 && Math.abs(j2 - j1) == 2)) {
            iSaltada = (i1 + i2) / 2;
            jSaltada = (j1 + j2) / 2;
            if (tablero[iSaltada][jSaltada] == 1)
                return true;
        }

        return false;
    }

    /**
     * Recibe las coordenadas de la posición pulsada y dependiendo del estado, realiza la acción
	 *
     * @param iPulsada coordenada fila
     * @param jPulsada coordenada columna
     */
	public void jugar(int iPulsada, int jPulsada) {
		if (estadoJuego == Estado.ESTADO_SELECCION_FICHA) {
			iSeleccionada = iPulsada;
			jSeleccionada = jPulsada;
			estadoJuego = Estado.ESTADO_SELECCION_DESTINO;
		} else if (estadoJuego == Estado.ESTADO_SELECCION_DESTINO) {
			if (movimientoAceptable(iSeleccionada, jSeleccionada, iPulsada, jPulsada)) {
				estadoJuego = Estado.ESTADO_SELECCION_FICHA;

                // Actualizar tablero
				tablero[iSeleccionada][jSeleccionada] = 0;
				tablero[iSaltada][jSaltada] = 0;
				tablero[iPulsada][jPulsada] = 1;

				if (juegoTerminado())
					estadoJuego = Estado.ESTADO_TERMINADO;
			} else { // El movimiento no es aceptable, la última ficha pasa a ser la seleccionada
				iSeleccionada = iPulsada;
				jSeleccionada = jPulsada;
			}
		}
	}

    /**
     * Determina si el miJuego ha terminado (no se puede realizar ningún movimiento)
	 *
     * @return valor lógico
     */
    public boolean juegoTerminado() {

        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                if (tablero[i][j] == 1) {
                    for (int k = 0; k < NUM_MOVIMIENTOS; k++) {
                        int p = i + desplazamientos[k][0];
                        int q = j + desplazamientos[k][1];
                        if (p >= 0 && p < TAMANIO && q >= 0 && q < TAMANIO && tablero[p][q] == 0 && TABLERO_INICIAL[p][q] == 1)
                            if (movimientoAceptable(i, j, p, q))
                                return false;
                    }
                }

        return true;
    }

	/**
	 * Serializa el tablero, devolviendo una cadena de 7x7 caracteres (dígitos 0 o 1)
	 *
	 * @return tablero serializado
     */
	public String serializaTablero() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < TAMANIO; i++)
			for (int j = 0; j < TAMANIO; j++)
				str.append(tablero[i][j]);
		return str.toString();
	}

	/**
	 * Recupera el estado del tablero a partir de su representación serializada
	 *
	 * @param str representación del tablero
     */
	public void deserializaTablero(String str) {
		for (int i = 0, cont = 0; i < TAMANIO; i++)
			for (int j = 0; j < TAMANIO; j++)
				tablero[i][j] = str.charAt(cont++) - '0';
	}

	private int numberOfPieces() {
		int numTokens = 0;

		for (int i = 0; i < TAMANIO; i++)
			for (int j = 0; j < TAMANIO; j++)
				if (this.tablero[i][j] == 1)
					numTokens++;

		return numTokens;
	}

	private String serializeScore(String playerName) {
		return playerName + ";" + this.numberOfPieces() + ";" + System.currentTimeMillis();
	}

	/**
	 * Recupera el miJuego a su estado inicial
	 */

	public void reiniciar() {
        for (int i = 0; i < TAMANIO; i++)
			System.arraycopy(TABLERO_INICIAL[i], 0, tablero[i], 0, TAMANIO);
        tablero[TAMANIO / 2][TAMANIO / 2] = 0;   // posición central

        estadoJuego = Estado.ESTADO_SELECCION_FICHA;
	}

	public void saveGame(MainActivity main) {
		FileController file = new FileController(FileController.FILE_GAME);
		file.writeFile(main, main.mJuego.serializaTablero(), Context.MODE_PRIVATE);
		Toast.makeText(main, main.getString(R.string.toastSaveGame),
				Toast.LENGTH_SHORT).show();
	}

	public void loadGame(MainActivity main) {
		FileController file = new FileController(FileController.FILE_GAME);
		try {
			String data = file.readFile(main);
			main.mJuego.deserializaTablero(data);
			main.mostrarTablero();
			Toast.makeText(main, main.getString(R.string.toastGameLoaded),
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(main, main.getString(R.string.toastGameNotLoaded),
					Toast.LENGTH_SHORT).show();
		}
	}

	public int getScore(){
		int score = 0;

		for (int i = 0; i < JuegoCelta.TAMANIO; i++){
			for (int j = 0; j < JuegoCelta.TAMANIO; j++){
				score +=  this.obtenerFicha(i,j);
			}
		}
		return score;
	}

	public String getCurrentDate(){
		Date date = new Date();
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		return (formatoHora.format(date)+" "+formatoFecha.format(date));
	}

	public void saveScore(MainActivity main, String playerName) {
		this.score = getScore();
		db.add(playerName, score, this.getCurrentDate());
		Toast.makeText(main, main.getString(R.string.toastSavedScore),
				Toast.LENGTH_SHORT).show();
	}
}
