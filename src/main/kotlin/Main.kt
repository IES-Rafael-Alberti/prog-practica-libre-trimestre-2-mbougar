import javax.swing.SwingUtilities


fun main() {

    SwingUtilities.invokeLater {
        Vista
        GestionPartida.tableroPartida = Vista.tableroPanel
        GestionPartida.actualizarMovimientosPiezas() //Actualización inicial de los movimientos de cada pieza una vez esten posicionadas
    }
}
