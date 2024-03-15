



/**
 * Interfaz que define los movimientos del peón en el ajedrez.
 */
interface MovimientoPeon {

    /**
     * Obtiene los movimientos posibles del peón desde una posición dada.
     * @param posicionActual La posición actual del peón.
     * @param color El color del peón.
     * @return Una lista de posiciones que representan los movimientos posibles del peón.
     */
    fun obtenerMovimientosPeon(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()

        val movimientoFilaDir = if (color == ColoresPiezas.PIEZA_BLANCA.color) -1 else 1

        // Comprobacion hacia delante
        val nuevaFila = posicionActual.fila + movimientoFilaDir
        val casillaAdelante = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, posicionActual.columna))
        if (casillaAdelante.estaVacia) {
            movimientos.add(Posicion(nuevaFila, posicionActual.columna))
            // Primer movimiento de dos casillas si nunca se ha movido
            if (GestionPartida.buscarCasillaTablero(posicionActual).pieza!!.cantidadDeMovimientos == 0) {
                val nuevaFilaDos = nuevaFila + movimientoFilaDir
                val casillaDosAdelante = GestionPartida.buscarCasillaTablero(Posicion(nuevaFilaDos, posicionActual.columna))
                if (casillaDosAdelante.estaVacia) {
                    movimientos.add(Posicion(nuevaFilaDos, posicionActual.columna))
                }
            }
        }

        // Comprobacion de movimientos posibles comiendo hacia los lados
        val direcciones = listOf(-1, 1)
        for (columnaDir in direcciones) {
            val nuevaColumna = posicionActual.columna + columnaDir
            if (nuevaColumna in 0 until Tablero.CASILLAS_FILA_TABLERO) {
                val casillaDiagonal = GestionPartida.buscarCasillaTablero(Posicion(nuevaFila, nuevaColumna))
                if (!casillaDiagonal.estaVacia && casillaDiagonal.pieza!!.color != color) {
                    movimientos.add(Posicion(nuevaFila, nuevaColumna))
                }
            }
        }

        return movimientos
    }
}