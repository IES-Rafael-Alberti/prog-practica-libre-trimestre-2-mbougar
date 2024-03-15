


/**
 * Clase que representa un peón en el juego de ajedrez.
 * @property color El color del peón.
 * @property posicion La posición actual del peón en el tablero.
 */
class Pawn(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoPeon {

    override var listaMovimientosPosibles: MutableList<Posicion> = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.PEON.desc

    /**
     * Verifica si un movimiento es válido para el peón.
     * @param casillaAMover La casilla a la que se desea mover el peón.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del peón después de realizar un movimiento.
     * Se deben llamar a estos métodos para mantener actualizada la lista de movimientos.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosPeon(posicion, color))
    }
}