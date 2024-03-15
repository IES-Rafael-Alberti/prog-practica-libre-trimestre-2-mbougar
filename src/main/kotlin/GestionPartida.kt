

/**
 * Objeto que gestiona una partida de ajedrez.
 */
object GestionPartida {

    lateinit var tableroPartida: Tablero
    var turno = ColoresPiezas.PIEZA_BLANCA.color
    private var movimientosBlancos: MutableSet<Posicion> = mutableSetOf()
    private var movimientosNegros: MutableSet<Posicion> = mutableSetOf()
    var posicionReyNegro = Posicion(0, 4)
    var posicionReyBlanco = Posicion(7, 4)


    /**
     * Cambia el turno de la partida.
     */
    fun cambiarTurno() {
        turno = if (turno == ColoresPiezas.PIEZA_BLANCA.color) ColoresPiezas.PIEZA_NEGRA.color else ColoresPiezas.PIEZA_BLANCA.color
    }

    /**
     * Actualiza los movimientos posibles de todas las piezas en el tablero.
     */
    fun actualizarMovimientosPiezas() {
        movimientosBlancos = mutableSetOf()
        movimientosNegros = mutableSetOf()
        tableroPartida.tablero.forEach { fila -> fila.forEach { casilla -> if (casilla.pieza != null) {
            casilla.pieza!!.actualizarMovimientosPosibles(); guardarMovimientosPorColor(casilla.pieza!!)
        } } }
    }

    /**
     * Guarda los movimientos posibles de una pieza en función de su color.
     * @param pieza La pieza cuyos movimientos se van a guardar.
     */
    private fun guardarMovimientosPorColor(pieza: Pieza) {
        if (pieza.color == ColoresPiezas.PIEZA_BLANCA.color) {
            movimientosBlancos.addAll(pieza.listaMovimientosPosibles)
        } else {
            movimientosNegros.addAll(pieza.listaMovimientosPosibles)
        }
    }

    /**
     * Comprueba si el rey del jugador en turno está en jaque.
     * @return true si el rey está en jaque, false en caso contrario.
     */
    fun comprobarReyEnJaque(): Boolean {
        return when (turno) {
            ColoresPiezas.PIEZA_BLANCA.color -> posicionReyBlanco in movimientosNegros
            else -> posicionReyNegro in movimientosBlancos
        }
    }

    /**
     * Inicia una nueva partida.
     */
    fun nuevaPartida() {
        tableroPartida.restablecerTablero()
        turno = ColoresPiezas.PIEZA_BLANCA.color
        Vista.tableroPanel = tableroPartida
        actualizarMovimientosPiezas()
    }

    /**
     * Busca una casilla en el tablero basándose en su posición.
     *
     * @param posicion La posición de la casilla a buscar.
     * @return La casilla encontrada o null si no se encontró ninguna casilla en la posición especificada.
     */
    fun buscarCasillaTablero(posicion: Posicion): Casilla {
        val casillasEncontradas: MutableList<Casilla> = mutableListOf()

        tableroPartida.tablero.forEach { fila -> fila.forEach { casilla -> if (casilla.posicion == posicion) casillasEncontradas.add(casilla) } }
        return casillasEncontradas[0]
    }
}