

/**
 * Clase que representa una pieza de ajedrez de tipo Bishop (Alfil).
 * @param color El color de la pieza (blanco o negro).
 * @param posicion La posición inicial de la pieza en el tablero.
 */
class Bishop(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoDiagonal {

    override var listaMovimientosPosibles: MutableList<Posicion> = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = "bishop"

    /**
     * Verifica si el movimiento a una casilla especificada es válido para un alfil.
     * @param casillaAMover La casilla a la que se intenta mover.
     * @return true si el movimiento es válido, false de lo contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles para el alfil.
     * Este método debe llamarse después de que la pieza se haya movido en el tablero.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosDiagonales(posicion, color))
    }
}