

/**
 * Clase que representa una reina en el juego de ajedrez.
 * @property color El color de la reina.
 * @property posicion La posición actual de la reina en el tablero.
 */
class Queen(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoDiagonal, MovimientoVerticalHorizontal {

    override var listaMovimientosPosibles: MutableList<Posicion> = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.REINA.desc

    /**
     * Verifica si un movimiento es válido para la reina.
     * @param casillaAMover La casilla a la que se desea mover la reina.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles de la reina después de realizar un movimiento.
     * Se deben llamar a estos métodos para mantener actualizada la lista de movimientos.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosDiagonales(posicion, color))
        listaMovimientosPosibles.addAll(obtenerMovimientosVerticalesYHorizontales(posicion, color))
    }
}