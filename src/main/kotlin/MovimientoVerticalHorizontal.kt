

/**
 * Interfaz que define los movimientos verticales y horizontales de una pieza en el ajedrez.
 */
interface MovimientoVerticalHorizontal {

    /**
     * Obtiene los movimientos verticales y horizontales posibles desde una posición dada.
     * @param posicionActual La posición actual de la pieza.
     * @param color El color de la pieza.
     * @return Una lista de posiciones que representan los movimientos verticales y horizontales posibles.
     */
    fun obtenerMovimientosVerticalesYHorizontales(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        movimientos.addAll(obtenerMovimientosVerticales(posicionActual, color))
        movimientos.addAll(obtenerMovimientosHorizontales(posicionActual, color))

        return movimientos
    }

    /**
     * Obtiene los movimientos verticales posibles desde una posición dada.
     * @param posicionActual La posición actual de la pieza.
     * @param color El color de la pieza.
     * @return Una lista de posiciones que representan los movimientos verticales posibles.
     */
    fun obtenerMovimientosVerticales(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        val direcciones = listOf(-1, 1)

        for (filaDir in direcciones) {
            var nuevaFila = posicionActual.fila + filaDir
            val nuevaColumna = posicionActual.columna
            var hayPiezaEnCamino = false

            while (nuevaFila in 0 until Tablero.CASILLAS_FILA_TABLERO && nuevaColumna in 0 until Tablero.CASILLAS_FILA_TABLERO && !hayPiezaEnCamino) {
                val casilla = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, nuevaColumna))
                if (!casilla.estaVacia) {
                    hayPiezaEnCamino = true
                    if (casilla.pieza!!.color != color) {
                        movimientos.add(Posicion(nuevaFila, nuevaColumna))
                    }
                } else {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                }
                nuevaFila += filaDir
            }
        }

        return movimientos
    }

    /**
     * Obtiene los movimientos horizontales posibles desde una posición dada.
     * @param posicionActual La posición actual de la pieza.
     * @param color El color de la pieza.
     * @return Una lista de posiciones que representan los movimientos horizontales posibles.
     */
    fun obtenerMovimientosHorizontales(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        val direcciones = listOf(-1, 1)

        for (columnaDir in direcciones) {
            val nuevaFila = posicionActual.fila
            var nuevaColumna = posicionActual.columna + columnaDir
            var hayPiezaEnCamino = false

            while (nuevaFila in 0 until Tablero.CASILLAS_FILA_TABLERO && nuevaColumna in 0 until Tablero.CASILLAS_FILA_TABLERO && !hayPiezaEnCamino) {
                val casilla = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, nuevaColumna))
                if (!casilla.estaVacia) {
                    hayPiezaEnCamino = true
                    if (casilla.pieza!!.color != color) {
                        movimientos.add(Posicion(nuevaFila, nuevaColumna))
                    }
                } else {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                }
                nuevaColumna += columnaDir
            }
        }

        return movimientos
    }
}