


/**
 * Enumeración que representa las piezas de ajedrez.
 * @property desc La descripción de la pieza.
 */
enum class PiezasDeAjedrez(val desc: String) {
    PEON("pawn"),
    TORRE("rook"),
    CABALLO("knight"),
    ALFIL("bishop"),
    REINA("queen"),
    REY("king")
}