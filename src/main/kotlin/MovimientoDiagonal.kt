



/**
 * Interfaz que define los movimientos diagonales de una pieza en el ajedrez.
 */
interface MovimientoDiagonal {

    /**
     * Obtiene los movimientos diagonales posibles desde una posición dada.
     * @param posicionActual La posición actual de la pieza.
     * @param color El color de la pieza.
     * @return Una lista de posiciones que representan los movimientos diagonales posibles.
     */
    fun obtenerMovimientosDiagonales(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos = mutableListOf<Posicion>()
        val direcciones = listOf(-1, 1)

        for (filaDir in direcciones) {
            for (columnaDir in direcciones) {
                val (nuevaFila, nuevaColumna) = calcularNuevaPosicion(posicionActual, filaDir, columnaDir)
                agregarMovimientosEnDiagonal(nuevaFila, nuevaColumna, filaDir, columnaDir, movimientos, color)
            }
        }
        return movimientos
    }

    /**
     * Calcula la nueva posición en la diagonal.
     * @param posicionActual La posición actual de la pieza.
     * @param filaDir La dirección del movimiento en fila.
     * @param columnaDir La dirección del movimiento en columna.
     * @return Una pareja de valores que representan la nueva fila y columna.
     */
    private fun calcularNuevaPosicion(posicionActual: Posicion, filaDir: Int, columnaDir: Int): Pair<Int, Int> {
        var (nuevaFila, nuevaColumna) = posicionActual.fila to posicionActual.columna
        nuevaFila += filaDir
        nuevaColumna += columnaDir
        return nuevaFila to nuevaColumna
    }

    /**
     * Agrega movimientos diagonales a la lista de movimientos posibles.
     * @param fila La fila de la casilla actual.
     * @param columna La columna de la casilla actual.
     * @param filaDir La dirección del movimiento en fila.
     * @param columnaDir La dirección del movimiento en columna.
     * @param movimientos La lista de movimientos posibles.
     * @param color El color de la pieza.
     */

    private fun agregarMovimientosEnDiagonal(
        fila: Int,
        columna: Int,
        filaDir: Int,
        columnaDir: Int,
        movimientos: MutableList<Posicion>,
        color: String
    ) {
        var (nuevaFila, nuevaColumna) = fila to columna
        var hayPiezaEnCamino = false

        while (nuevaFila in 0 until Tablero.CASILLAS_FILA_TABLERO && nuevaColumna in 0 until Tablero.CASILLAS_FILA_TABLERO && !hayPiezaEnCamino) {
            val casilla = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, nuevaColumna))
            val (deltaFila, deltaColumna) = filaDir to columnaDir

            if (!casilla.estaVacia) {
                hayPiezaEnCamino = true
                if (casilla.pieza!!.color != color) {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                }
            } else {
                movimientos.add(Posicion(nuevaFila, nuevaColumna))
            }
            nuevaFila += deltaFila
            nuevaColumna += deltaColumna
        }
    }
}