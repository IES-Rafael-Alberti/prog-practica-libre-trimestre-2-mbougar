import java.io.Serializable

/**
 * Clase que representa una posición en el tablero de ajedrez.
 * @property fila El número de fila de la posición.
 * @property columna El número de columna de la posición.
 */
data class Posicion(
    val fila: Int,
    val columna: Int): Serializable //Tiene que heredar la interfaz Serializable para que swing pueda serializarlo al mover las fichas