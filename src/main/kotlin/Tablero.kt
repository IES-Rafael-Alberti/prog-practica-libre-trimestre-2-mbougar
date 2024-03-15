import javax.swing.*
import java.awt.*

class Tablero(private val tamanioTablero: Int) : JPanel() {

    companion object {
        const val CASILLAS_FILA_TABLERO = 8 //Si cambias este valor rompes el juego
    }

    val tablero = Array(CASILLAS_FILA_TABLERO) { fila -> Array(CASILLAS_FILA_TABLERO) { columna -> Casilla(Posicion(fila,columna)) } }
    init {
        layout = GridLayout(CASILLAS_FILA_TABLERO, CASILLAS_FILA_TABLERO)

        for (fila in 0 until CASILLAS_FILA_TABLERO) {
            for (columna in 0 until CASILLAS_FILA_TABLERO) {
                val casilla = tablero[fila][columna]
                casilla.background = if ((fila + columna) % 2 == 0) Color(235, 236, 209) else Color(119, 149, 86)
                add(casilla)
            }
        }
        restablecerTablero()
    }

    /**
     * Pinta una casilla con el color especificado.
     * @param casilla La casilla a pintar.
     * @param color El color con el que se pintará la casilla.
     */
    fun pintarCasilla(casilla: Casilla, color: Color) {
        casilla.background = color
    }

    /**
     * Restablece los colores de todas las casillas del tablero.
     */
    fun restablecerColorCasillasTablero() {
        for (fila in 0 until CASILLAS_FILA_TABLERO) {
            for (columna in 0 until CASILLAS_FILA_TABLERO) {
                val casilla = tablero[fila][columna]
                casilla.background = if ((fila + columna) % 2 == 0) Color(235, 236, 209) else Color(119, 149, 86)
            }
        }
    }

    /**
     * Restablece el tablero con las piezas en sus posiciones iniciales.
     */
    fun restablecerTablero() {
        colocarPieza( Rook( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 0)), tablero[0][0])
        colocarPieza( Knight( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 1)), tablero[0][1])
        colocarPieza( Bishop( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 2)), tablero[0][2])
        colocarPieza( Queen( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 3)), tablero[0][3])
        colocarPieza( King( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 4)), tablero[0][4])
        colocarPieza( Bishop( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 5)), tablero[0][5])
        colocarPieza( Knight( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 6)), tablero[0][6])
        colocarPieza( Rook( ColoresPiezas.PIEZA_NEGRA.color, Posicion(0, 7)), tablero[0][7])

        for (columna in 0 until CASILLAS_FILA_TABLERO) {
            colocarPieza( Pawn( ColoresPiezas.PIEZA_NEGRA.color, Posicion(1, columna)), tablero[1][columna])
        }

        for (fila in 2 until 6) {
            for (columna in 0 until CASILLAS_FILA_TABLERO) {
                tablero[fila][columna].icon = null
                tablero[fila][columna].pieza = null
            }
        }

        for (columna in 0 until CASILLAS_FILA_TABLERO) {
            colocarPieza( Pawn( ColoresPiezas.PIEZA_BLANCA.color, Posicion(6, columna)), tablero[6][columna])
        }

        colocarPieza( Rook( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 0)), tablero[7][0])
        colocarPieza( Knight( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 1)), tablero[7][1])
        colocarPieza( Bishop( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 2)), tablero[7][2])
        colocarPieza( Queen( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 3)), tablero[7][3])
        colocarPieza( King( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 4)), tablero[7][4])
        colocarPieza( Bishop( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 5)), tablero[7][5])
        colocarPieza( Knight( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 6)), tablero[7][6])
        colocarPieza( Rook( ColoresPiezas.PIEZA_BLANCA.color, Posicion(7, 7)), tablero[7][7])
    }

    /**
     * Coloca una pieza en una casilla del tablero.
     * @param pieza La pieza que se colocará en la casilla.
     * @param casilla La casilla en la que se colocará la pieza.
     */
    private fun colocarPieza(pieza: Pieza, casilla: Casilla) {
        casilla.icon = Pieza.obtenerIconoPieza(pieza)
        casilla.pieza = pieza
    }

    /**
     * Devuelve las dimensiones preferidas del componente.
     * @return Un objeto Dimension que especifica el ancho y el alto preferidos del componente.
     */
    override fun getPreferredSize(): Dimension {
        return Dimension(tamanioTablero, tamanioTablero)
    }
}