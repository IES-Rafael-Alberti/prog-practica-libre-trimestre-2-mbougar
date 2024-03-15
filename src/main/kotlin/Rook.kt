

/**
 * Clase que representa una torre en el juego de ajedrez.
 * @property color El color de la torre.
 * @property posicion La posición actual de la torre en el tablero.
 */
class Rook(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoVerticalHorizontal {

    override var listaMovimientosPosibles: MutableList<Posicion>  = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.TORRE.desc

    /**
     * Verifica si un movimiento es válido para la torre.
     * @param casillaAMover La casilla a la que se desea mover la torre.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles de la torre después de realizar un movimiento.
     * Se deben llamar a estos métodos para mantener actualizada la lista de movimientos.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosVerticalesYHorizontales(posicion, color))
    }
}