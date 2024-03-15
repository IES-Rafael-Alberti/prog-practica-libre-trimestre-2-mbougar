import java.io.Serializable
import java.awt.Image
import javax.swing.ImageIcon


/**
 * Clase abstracta que representa una pieza de ajedrez.
 * @property color El color de la pieza.
 * @property posicion La posición actual de la pieza en el tablero.
 */
abstract class Pieza(var color: String, var posicion: Posicion): Serializable {

    /** Lista de movimientos posibles de la pieza */
    abstract var listaMovimientosPosibles: MutableList<Posicion>

    /** Nombre de la pieza */
    abstract val nombrePieza: String

    /** Cantidad de movimientos realizados por la pieza */
    abstract var cantidadDeMovimientos: Int

    /**
     * Verifica si un movimiento es válido para la pieza.
     * @param casillaAMover La casilla a la que se desea mover la pieza.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    abstract fun esMovimientoValido(casillaAMover: Casilla): Boolean

    /**
     * Actualiza los movimientos posibles de la pieza después de realizar un movimiento.
     */
    abstract fun actualizarMovimientosPosibles()

    companion object {
        /**
         * Método estático que obtiene el icono de una pieza.
         * @param pieza La pieza de la que se desea obtener el icono.
         * @return ImageIcon representando el icono de la pieza.
         */
        fun <T : Pieza> obtenerIconoPieza(pieza: T): ImageIcon {
            val logo = ImageIcon("src/main/resources/images/${pieza.color}_${pieza.nombrePieza}.png")
            val medidaFicha = Vista.tamanioTablero / Tablero.CASILLAS_FILA_TABLERO
            val logoMin = ImageIcon(logo.image.getScaledInstance(medidaFicha, medidaFicha, Image.SCALE_SMOOTH))
            return logoMin
        }
    }
}