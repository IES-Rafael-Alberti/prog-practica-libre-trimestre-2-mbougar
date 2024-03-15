import java.awt.*
import java.net.URI
import javax.swing.*
import kotlin.system.exitProcess

/**
 * Objeto que representa la vista del juego de ajedrez.
 */
object Vista: JFrame() {
    private fun readResolve(): Any = Vista //Debe implementarse al ser serializable y un singleton. Evita la nueva instanciacion de Vista por la JVM

    /** Panel que contiene el tablero de juego */
    var tableroPanel: Tablero
    private val screenSize = Toolkit.getDefaultToolkit().screenSize
    val tamanioTablero = screenSize.width / 2
    init {
        this.iconImage = ImageIcon("src/main/resources/images/iconoApp.png").image
        this.title = "Tablero de Ajedrez"
        this.defaultCloseOperation = EXIT_ON_CLOSE

        val barraMenu = JMenuBar()

        val archivoMenu = JMenu("Archivo")
        archivoMenu.add(JMenuItem("Nueva Partida")).addActionListener {
            restablecerColoresCasillas()
            GestionPartida.nuevaPartida()
        }
        archivoMenu.addSeparator()
        archivoMenu.add(JMenuItem("Salir")).addActionListener {

            exitProcess(0) //Sale del programa
        }
        barraMenu.add(archivoMenu)

        val ayudaMenu = JMenu("Ayuda")
        ayudaMenu.add(JMenuItem("Reglas")).addActionListener {
            abrirURL("https://www.chess.com/es/como-jugar-ajedrez")
        }
        barraMenu.add(ayudaMenu)

        // Asignar la barra de menús al JFrame
        this.jMenuBar = barraMenu

        tableroPanel = Tablero(tamanioTablero)
        this.add(tableroPanel)
        this.pack()
        this.isVisible = true
    }

    /**
     * Abre la URL especificada en el navegador web predeterminado del sistema.
     * @param url La URL que se abrirá en el navegador.
     */
    private fun abrirURL(url: String) {
        try {
            val uri = URI(url)
            Desktop.getDesktop().browse(uri)
        } catch (e: Exception) {
            println("No se pudo abrir la URL: $url")
        }
    }

    /**
     * Restablece los colores de las casillas del tablero.
     */
    fun restablecerColoresCasillas() {
        tableroPanel.restablecerColorCasillasTablero()
    }

    /**
     * Pinta la casilla del rey en el tablero.
     * @param turno El color del rey a pintar.
     */
    fun pintarCasillaRey(turno: String) {
        val casillaRey = GestionPartida.buscarCasillaTablero(if (turno == ColoresPiezas.PIEZA_BLANCA.color) GestionPartida.posicionReyBlanco else GestionPartida.posicionReyNegro)
        tableroPanel.pintarCasilla(casillaRey, Color(147, 58, 22))
    }

    /**
     * Pinta las casillas que han sido movidas en el tablero.
     * @param casilla1 La primera casilla movida.
     * @param casilla2 La segunda casilla movida.
     */
    fun pintarCasillasQueHanMovido(casilla1: Casilla, casilla2: Casilla) {
        tableroPanel.pintarCasilla(casilla1, Color(252, 209, 52))
        tableroPanel.pintarCasilla(casilla2, Color(252, 209, 52))
    }
}