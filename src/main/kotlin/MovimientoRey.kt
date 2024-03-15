


/**
 * Interfaz que define los movimientos del rey en el ajedrez.
 */
interface MovimientoRey {
    /**
     * Obtiene los movimientos posibles del rey desde una posición dada.
     * @param posicionActual La posición actual del rey.
     * @param color El color del rey.
     * @return Una lista de posiciones que representan los movimientos posibles del rey.
     */
    fun obtenerMovimientosRey(posicionActual: Posicion, color: String): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()

        for (fila in (posicionActual.fila - 1)..(posicionActual.fila + 1)) {
            for (columna in (posicionActual.columna - 1)..(posicionActual.columna + 1)) {
                if (fila in 0 until Tablero.CASILLAS_FILA_TABLERO && columna in 0 until Tablero.CASILLAS_FILA_TABLERO &&
                    (fila != posicionActual.fila || columna != posicionActual.columna)) {
                    val casillaAMover = GestionPartida.buscarCasillaTablero(Posicion(fila, columna))
                    if (casillaAMover.estaVacia || casillaAMover.pieza!!.color != color) {
                        movimientos.add(Posicion(fila, columna))
                    }
                }
            }
        }

        movimientos.addAll(obtenerEnroque(posicionActual))

        return movimientos
    }

    /**
     * Obtiene una casilla adyacente a la posición actual del rey.
     * @param posicionActual La posición actual del rey.
     * @param direccion La dirección en la que buscar la casilla adyacente.
     * @return La casilla adyacente.
     */
    fun obtenerCasillaAdyacente(posicionActual: Posicion, direccion: Int): Casilla {
        return GestionPartida.buscarCasillaTablero(Posicion(posicionActual.fila, posicionActual.columna + direccion))
    }

    /**
     * Obtiene los movimientos posibles para el enroque hacia la izquierda.
     * @param posicionActual La posición actual del rey.
     * @return Una lista de posiciones que representan los movimientos posibles para el enroque hacia la izquierda.
     */
    fun obtenerEnroqueIzquierda(posicionActual: Posicion): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        val casillasIzquierda = listOf(
            obtenerCasillaAdyacente(posicionActual, -1),
            obtenerCasillaAdyacente(posicionActual, -2),
            obtenerCasillaAdyacente(posicionActual, -3)
        )

        val libreIzquierda = casillasIzquierda.all { it.estaVacia } //Mejora con respecto a comprobar individualmente con forEach

        if (libreIzquierda && (obtenerCasillaAdyacente(posicionActual, -4).pieza?.cantidadDeMovimientos ?: 1) == 0) {
            movimientos.add(Posicion(posicionActual.fila, posicionActual.columna - 2))
        }

        return movimientos
    }

    /**
     * Obtiene los movimientos posibles para el enroque hacia la derecha.
     * @param posicionActual La posición actual del rey.
     * @return Una lista de posiciones que representan los movimientos posibles para el enroque hacia la derecha.
     */
    fun obtenerEnroqueDerecha(posicionActual: Posicion): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        val casillasDerecha = listOf(
            obtenerCasillaAdyacente(posicionActual, 1),
            obtenerCasillaAdyacente(posicionActual, 2)
        )

        val libreDerecha = casillasDerecha.all { it.estaVacia }

        if (libreDerecha && (obtenerCasillaAdyacente(posicionActual, 3).pieza?.cantidadDeMovimientos ?: 1) == 0) {
            movimientos.add(Posicion(posicionActual.fila, posicionActual.columna + 2))
        }

        return movimientos
    }

    /**
     * Obtiene los movimientos posibles para el enroque desde la posición actual del rey.
     * @param posicionActual La posición actual del rey.
     * @return Una lista de posiciones que representan los movimientos posibles para el enroque.
     */
    fun obtenerEnroque(posicionActual: Posicion): List<Posicion> {
        val movimientos: MutableList<Posicion> = mutableListOf()
        val rey = GestionPartida.buscarCasillaTablero(posicionActual).pieza!!

        if (rey.cantidadDeMovimientos != 0 || rey.posicion.columna != 4) {
            return movimientos
        } else {
            movimientos.addAll(obtenerEnroqueDerecha(posicionActual))
            movimientos.addAll(obtenerEnroqueIzquierda(posicionActual))
        }

        return movimientos
    }
}