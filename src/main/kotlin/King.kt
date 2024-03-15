


/**
 * Clase que representa a un rey en el juego de ajedrez.
 * @param color El color del rey.
 * @param posicion La posición inicial del rey en el tablero.
 */
class King(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoRey {

    override var listaMovimientosPosibles: MutableList<Posicion> = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = "king"

    /**
     * Verifica si un movimiento es válido para el rey.
     * @param casillaAMover La casilla a la que se intenta mover el rey.
     * @return Verdadero si el movimiento es válido, falso en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del rey y su posición en el tablero.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosRey(posicion, color))

        //actualizar el pointer a la posicion del rey en el tablero
        actualizarPosicionRey()
    }

    /**
     * Actualiza la posición del rey en el tablero dependiendo de su color.
     */
    private fun actualizarPosicionRey() {
        when (color) {
            ColoresPiezas.PIEZA_BLANCA.color -> GestionPartida.posicionReyBlanco = posicion
            else -> GestionPartida.posicionReyNegro = posicion
        }
    }
}
