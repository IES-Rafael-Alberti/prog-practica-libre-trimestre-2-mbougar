



/**
 * Interfaz que define los movimientos del caballo en el ajedrez.
 */
interface MovimientoCaballo {

    /**
     * Obtiene los movimientos posibles del caballo desde una posición dada.
     * @param posicionActual La posición actual del caballo.
     * @param color El color del caballo.
     * @return Una lista de posiciones que representan los movimientos posibles del caballo.
     */
    fun obtenerMovimientosCaballo(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientosCaballo = listOf(
            Pair(-2, -1),
            Pair(-2, 1),
            Pair(-1, -2),
            Pair(-1, 2),
            Pair(1, -2),
            Pair(1, 2),
            Pair(2, -1),
            Pair(2, 1)
        )

        val movimientos = mutableListOf<Posicion>()

        for (movimiento in movimientosCaballo) {
            val nuevaFila = posicionActual.fila + movimiento.first
            val nuevaColumna = posicionActual.columna + movimiento.second
            // Comprobamos que la casilla este en el tamaño del tablero
            if (nuevaFila in 0 until  Tablero.CASILLAS_FILA_TABLERO && nuevaColumna in 0 until  Tablero.CASILLAS_FILA_TABLERO) {
                val casillaAMover = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, nuevaColumna))
                if (casillaAMover.estaVacia) {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                } else if (casillaAMover.pieza!!.color != color) {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                }
            }
        }

        return movimientos
    }
}