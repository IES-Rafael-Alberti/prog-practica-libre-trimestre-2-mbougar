


/**
 * Clase que representa a un caballo en el juego de ajedrez.
 * @param color El color del caballo.
 * @param posicion La posición inicial del caballo en el tablero.
 */
class Knight(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoCaballo {

    override var listaMovimientosPosibles: MutableList<Posicion>  = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.CABALLO.desc

    /**
     * Verifica si un movimiento es válido para el caballo.
     * @param casillaAMover La casilla a la que se intenta mover el caballo.
     * @return Verdadero si el movimiento es válido, falso en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del caballo.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosCaballo(posicion, color))
    }
}