import javax.swing.*
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.dnd.*


/**
 * Representa una casilla en un tablero de juego.
 * Esta clase extiende JButton y se utiliza para implementar
 * el comportamiento de arrastrar y soltar (drag and drop) en la interfaz de usuario.
 *
 * @property posicion La posición de la casilla en el tablero, representada como un par de coordenadas (fila, columna).
 * @constructor Crea una nueva casilla en la posición especificada.
 */
class Casilla(var posicion: Posicion) : JButton() {
    var estaVacia = true
    var pieza: Pieza? = null //El setter de pieza actualiza la posicion e icono de la pieza
        set(value) {
            if (value == null) {
                estaVacia = true
                icon = null

            } else {
                value.posicion = posicion
                estaVacia = false
                icon = Pieza.obtenerIconoPieza(value)
            }
            field = value
        }

    init {
        transferHandler = TransferHandler("icon")

        DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
            this, DnDConstants.ACTION_COPY, DragGestureListenerCasilla()
        )
        DropTarget(this, DropTargetListenerCasilla())
    }

    /**
     * Listener para el gesto de arrastre de la casilla.
     * Se utiliza para iniciar el proceso de arrastre cuando se detecta un gesto de arrastre.
     */
    class DragGestureListenerCasilla : DragGestureListener {
        override fun dragGestureRecognized(eventoArrastrar: DragGestureEvent) {
            val casillaOriginal = eventoArrastrar.component as? Casilla //Hay que especificar que sea una Casilla sino devuelve como Any
            casillaOriginal?.let { casilla ->
                val transferable = CasillaEnMovimiento(casilla)
                eventoArrastrar.startDrag(null, transferable)
            }
            Vista.restablecerColoresCasillas()
        }
    }

    //Dejo todas las clases relacionadas con los eventos de Casilla nested dentro de Casilla

    /**
     * Listener para el objetivo de soltar la casilla.
     * Se utiliza para manejar las acciones relacionadas con soltar una casilla en una ubicación objetivo.
     */
    class DropTargetListenerCasilla : DropTargetListener {

        //Me veo obligado hacer override de estas funciones al usar el interfaz, pero no hago uso de ellas por tanto las dejo vacias.
        override fun dragEnter(eventoSoltar: DropTargetDragEvent) {}
        override fun dragOver(eventoSoltar: DropTargetDragEvent) {}
        override fun dropActionChanged(eventoSoltar: DropTargetDragEvent) {}
        override fun dragExit(eventoSoltar: DropTargetEvent) {}

        /**
         * Maneja el evento de soltar una casilla en la ubicación objetivo.
         * Realiza las validaciones necesarias y ejecuta el movimiento si es válido.
         */
        override fun drop(eventoSoltar: DropTargetDropEvent) {
            try {
                val datosCasillaOriginal = eventoSoltar.transferable.getTransferData(CasillaEnMovimiento.CASILLA_FLAVOR) as? Casilla //Genera un objeto con los datos de la Casilla que estamos arrastrando que no es la Casilla en si
                val casillaObjetivo = eventoSoltar.dropTargetContext.component as? Casilla
                val piezaCasillaObjetivoPrevioMovimiento = casillaObjetivo?.pieza

                //Comprobamos que los datos del arrastre se han guardado, que la casilla donde arrastramos no sea nula y que la casilla que la casilla que arrastramos no es del color contrario al turno
                if (datosCasillaOriginal != null && casillaObjetivo != null && datosCasillaOriginal.pieza != null && datosCasillaOriginal.pieza!!.color == GestionPartida.turno) {

                    //Accedemos al objeto de la casilla que estamos moviendo, ya que si trabajamos sobre datosCasillaOriginal no cambiaremos realmente la casilla
                    val casillaOriginal = GestionPartida.buscarCasillaTablero(datosCasillaOriginal.posicion)

                    //Comprobamos que la Casilla a la que movemos la pieza esta en su lista de movimientos
                    if (!casillaOriginal.pieza!!.esMovimientoValido(casillaObjetivo)) {
                        eventoSoltar.rejectDrop() //Cancelamos el arrastre

                    } else {
                        casillaObjetivo.cambiarPiezaDeCasilla(casillaOriginal) // El icono y posicion de la pieza se actualizan en el setter de casillaObjetivo
                        GestionPartida.actualizarMovimientosPiezas() //Actualizamos los movimientos de todas las piezas del tablero para comprobar si hemos puesto nuestro Rey en jaque
                        if (GestionPartida.comprobarReyEnJaque()) {

                            //Deshacemos el movimiento si el Rey esta en jaque
                            deshacerMovimiento(casillaOriginal, casillaObjetivo, piezaCasillaObjetivoPrevioMovimiento)
                            eventoSoltar.dropComplete(true)
                        } else {
                            //Completamos el movimiento
                            realizarMovimiento(casillaOriginal, casillaObjetivo)
                            eventoSoltar.dropComplete(true)
                        }
                    }
                } else {
                    eventoSoltar.rejectDrop()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                eventoSoltar.rejectDrop()
            }
        }

        /**
         * Deshace un movimiento realizado.
         * @param casillaOriginal Casilla original del movimiento.
         * @param casillaObjetivo Casilla objetivo del movimiento.
         * @param piezaCasillaObjetivoPrevioMovimiento Pieza previa en la casilla objetivo antes del movimiento.
         */
        private fun deshacerMovimiento(casillaOriginal: Casilla, casillaObjetivo: Casilla, piezaCasillaObjetivoPrevioMovimiento: Pieza?) {
            casillaOriginal.deshacerMovimiento(casillaObjetivo, piezaCasillaObjetivoPrevioMovimiento)
            Vista.pintarCasillaRey(GestionPartida.turno)
        }

        /**
         * Realiza un movimiento válido.
         * @param casillaOriginal Casilla original del movimiento.
         * @param casillaObjetivo Casilla objetivo del movimiento.
         */
        private fun realizarMovimiento(casillaOriginal: Casilla, casillaObjetivo: Casilla) {
            GestionPartida.cambiarTurno()
            Vista.pintarCasillasQueHanMovido(casillaOriginal, casillaObjetivo)
        }
    }

    /**
     * Clase para representar una casilla en movimiento.
     * Implementa la interfaz Transferable para permitir el arrastre y soltar de la casilla.
     *
     * @property casilla La casilla que se está moviendo.
     */
    class CasillaEnMovimiento(private val casilla: Casilla) : Transferable {
        override fun getTransferDataFlavors(): Array<DataFlavor> {
            return arrayOf(CASILLA_FLAVOR)
        }

        override fun isDataFlavorSupported(flavor: DataFlavor): Boolean {
            return flavor == CASILLA_FLAVOR //Forzado a implementarlo al usar la clase abstracta Transferable
        }

        override fun getTransferData(flavor: DataFlavor): Any {
            return casilla
        }

        companion object {
            val CASILLA_FLAVOR = DataFlavor(Casilla::class.java, "Casilla")
        }
    }

    /**
     * Cambia la pieza de la casilla actual por la pieza de la casilla original y realiza acciones para ciertas piezas.
     * Si la pieza es un peón y llega al extremo opuesto del tablero, se convierte en una reina.
     * Si la pieza es un rey y se realiza un enroque, actualiza la cantidad de movimientos de la pieza y realiza el movimiento de la torre correspondiente.
     * En todos los casos actualiza la cantidad de movimientos de la pieza.
     *
     * @param casillaOriginal La casilla original desde la cual se movió la pieza.
     */
    fun cambiarPiezaDeCasilla(casillaOriginal: Casilla) {
        pieza = casillaOriginal.pieza
        val distanciaMovimientoColumna = casillaOriginal.posicion.columna - posicion.columna
        val distanciaMovimientoFila = casillaOriginal.posicion.fila - posicion.fila

        //Podemos asegurar que hay pieza porque esta funcion se llama solo cuando hay una Pieza
        when (pieza!!.nombrePieza) {
            "pawn" -> {
                if (pieza!!.posicion.fila in listOf(0, 7)) {
                    pieza = Queen(pieza!!.color, pieza!!.posicion) //Colocamos una Reina en la posicion del Peon
                }
            }
            "king" -> {
                //Comprobamos si se ha movido 2 casillas
                if (distanciaMovimientoFila == 0 && distanciaMovimientoColumna % 2 == 0) {
                    moverPiezasEnroque(distanciaMovimientoColumna)
                }
            }
        }

        pieza!!.cantidadDeMovimientos++
        casillaOriginal.pieza = null
    }

    /**
     * Realiza el enroque moviendo la torre correspondiente.
     *
     * @param distanciaMovimientoColumna La distancia de movimiento de la pieza.
     */
    private fun moverPiezasEnroque(distanciaMovimientoColumna: Int) {

        //Teniendo la resta entre la columna original del rey y la columna final del rey podemos saber si ha hecho enroque hacia la izquierdo o derecha
        val torreDestinoColumna = when (distanciaMovimientoColumna) {
            -2 -> posicion.columna + 1
            else -> posicion.columna - 2
        }
        val torreOrigenColumna = when (distanciaMovimientoColumna) {
            -2 -> posicion.columna - 1
            else -> posicion.columna + 1
        }
        val torreDestino = GestionPartida.buscarCasillaTablero(Posicion(posicion.fila, torreDestinoColumna))
        val torreOrigen = GestionPartida.buscarCasillaTablero(Posicion(posicion.fila, torreOrigenColumna))
        torreOrigen.cambiarPiezaDeCasilla(torreDestino)
    }

    /**
     * Deshace el movimiento realizado en la casilla objetivo.
     *
     * @param casillaObjetivo La casilla en la que se realizó el movimiento.
     * @param antiguaPiezaCasillaObjetivo La pieza que estaba en la casilla objetivo antes de realizar el movimiento.
     */
    fun deshacerMovimiento(casillaObjetivo: Casilla, antiguaPiezaCasillaObjetivo: Pieza?) {
        pieza = casillaObjetivo.pieza
        casillaObjetivo.pieza = antiguaPiezaCasillaObjetivo
        GestionPartida.actualizarMovimientosPiezas()
    }
}
