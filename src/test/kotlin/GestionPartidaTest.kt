import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GestionPartidaTest {

    @Test
    /**
     * Comprueba que el método cambiarTurno de GestionPartida funcione.
     */
    fun cambiarTurno() {
        GestionPartida.turno = ColoresPiezas.PIEZA_BLANCA.color
        GestionPartida.cambiarTurno()
        assert(GestionPartida.turno == ColoresPiezas.PIEZA_NEGRA.color)
    }

    @Test
    /**
     * Comprueba que el comprobraReyEnJaque de GestionPartida funcione.
     */
    fun comprobarReyEnJaque(): Unit {
        assertEquals(GestionPartida.comprobarReyEnJaque(), false)
    }
}