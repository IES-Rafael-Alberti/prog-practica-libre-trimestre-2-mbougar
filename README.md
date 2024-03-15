[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/mnkKwimk)
# Actividad: Desarrollo de Proyecto Software en Kotlin

**ID actividad:** 2324_PRO_u4u5u6_libre

**Agrupamiento de la actividad**: Individual 

---

### Descripción:

La actividad consiste en el desarrollo de un proyecto software en Kotlin, permitiendo al estudiante elegir la temática. Este proyecto debe demostrar la comprensión y aplicación de los conceptos de programación orientada a objetos (POO), incluyendo la definición y uso de clases, herencia, interfaces, genericos, principios SOLID y el uso de librerías externas.

**Objetivo:**

- Demostrar comprensión de los fundamentos de POO mediante la instanciación y uso de objetos.
- Aplicar conceptos avanzados de POO como herencia, clases abstractas, e interfaces.
- Crear y usar clases que hagan uso de genéricos. 
- Aplicar principios SOLID.
- Integrar y utilizar librerías de clases externas para extender la funcionalidad del proyecto.
- Documentar y presentar el código de manera clara y comprensible.

**Trabajo a realizar:**

1. **Selección de la Temática:** Elige un tema de tu interés que pueda ser abordado mediante una aplicación software. Esto podría ser desde una aplicación de gestión para una pequeña empresa, una herramienta para ayudar en la educación, hasta un juego simple. Define claramente el problema que tu aplicación pretende resolver.

2. **Planificación:** Documenta brevemente cómo tu aplicación solucionará el problema seleccionado, incluyendo las funcionalidades principales que desarrollarás.

3. **Desarrollo:**
   - **Instancia de Objetos:** Tu aplicación debe crear y utilizar objetos, demostrando tu comprensión de la instanciación y el uso de constructores, métodos, y propiedades.
   - **Métodos Estáticos:** Define y utiliza al menos un método estático, explicando por qué es necesario en tu aplicación.
   - **Uso de IDE:** Desarrolla tu proyecto utilizando un IDE, aprovechando sus herramientas para escribir, compilar, y probar tu código.
   - **Definición de Clases:** Crea clases personalizadas con sus respectivas propiedades, métodos, y constructores.
   - **Clases con genéricos:** Define y utiliza al menos una clase que haga uso de genéricos en tu aplicación.
   - **Herencia y Polimorfismo:** Implementa herencia y/o interfaces en tu proyecto para demostrar la reutilización de código y la flexibilidad de tu diseño.  **Usa los principios SOLID:** Ten presente durante el desarrollo los principios SOLID y úsalos durante el diseño para mejorar tu aplicación.
   - **Librerías de Clases:** Integra y utiliza una o más librerías externas que enriquezcan la funcionalidad de tu aplicación.
   - **Documentación:** Comenta tu código de manera efectiva, facilitando su comprensión y mantenimiento.

4. **Prueba y Depuración:** Realiza pruebas para asegurarte de que tu aplicación funciona como se espera y depura cualquier error encontrado.
5. **Contesta a las preguntas** ver el punto **Preguntas para la Evaluación**

### Recursos

- Apuntes dados en clase sobre programación orientada a objetos, Kotlin, uso de IDEs, y manejo de librerías.
- Recursos vistos en clase, incluyendo ejemplos de código, documentación de Kotlin, y guías de uso de librerías.

### Evaluación y calificación

**RA y CE evaluados**: Resultados de Aprendizaje 2, 4, 6, 7 y Criterios de Evaluación asociados.

**Conlleva presentación**: SI

**Rubrica**: Mas adelante se mostrará la rubrica.

### Entrega

> **La entrega tiene que cumplir las condiciones de entrega para poder ser calificada. En caso de no cumplirlas podría calificarse como no entregada.**
>
- **Conlleva la entrega de URL a repositorio:** El contenido se entregará en un repositorio GitHub. 
- **Respuestas a las preguntas:** Deben contestarse en este fichero, README.md


# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, **asegúrate de hacer referencia y enlazar al código relevante** en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.f, 4.a)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos específicos de tu código.

_Respuesta: Para instanciar un objeto llamamos al constructor del objeto con los parametros que hagan falta dependiendo del constructor._

```kotlin
val tablero = Array(CASILLAS_FILA_TABLERO) { fila -> Array(CASILLAS_FILA_TABLERO) { columna -> Casilla(Posicion(fila,columna)) } }
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/446d33d8bf48a515dee6a4358a4e5fa2a1fe6f20/src/main/kotlin/Tablero.kt#L10)_

_En este ejemplo estamos creando 64 instancias de la clase Casilla las cuales toman como parámetro una instancia de la clase Posicion que tiene como parámetros fila y columna. Con los métodos ocurre algo similar, a la hora de llamarlos pondremos siempre () al final de su llamada con los parámetros del método dentro_

---

#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.i)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?
- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?

_Respuesta: He definido tanto métodos como propiedades estáticas, la razón de implementarlos ha sido para permitir el acceso a ellos a todos los objetos de la clase y a clases ajenas a ellos._

```kotlin
companion object {
   /**
    * Método estático que obtiene el icono de una pieza.
    * @param pieza La pieza de la que se desea obtener el icono.
    * @return ImageIcon representando el icono de la pieza.
    */
   fun <T : Pieza> obtenerIconoPieza(pieza: T): ImageIcon {
      val logo = ImageIcon("src/main/resources/images/${pieza.color}_${pieza.nombrePieza}.png")
      val medidaFicha = Vista.tamanioTablero / Tablero.CASILLAS_FILA_TABLERO
      val logoMin = ImageIcon(logo.image.getScaledInstance(medidaFicha, medidaFicha, Image.SCALE_SMOOTH))
      return logoMin
   }
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/446d33d8bf48a515dee6a4358a4e5fa2a1fe6f20/src/main/kotlin/Pieza.kt#L40-L45)_

_En este ejemplo podemos ver un método estático que existe dentro de una clase abstracta y que todas las clases que hereden de ella podran llamar, asi como lo podrán llamar objetos de otras clases que quieran obtener el icono de una pieza especifica (lo hace por ejemplo la clase Casilla)_

---

#### **Criterio global 3: Uso de entornos**
- **(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.

_Crear un proyecto en Intellij es tan simple como pulsar nuevo proyecto desde el menú de IntelliJ y elejir el Lenguaje y Build System (en este caso gradle). La compilación del proyecto se puede hacer desde el menú Build, de todas maneras el IDE compila automáticamente mientras programamos. Para las pruebas del programa he usado junit.jupiter, usarlo es tan sencillo como añadir su libreria desde Maven en Project Structure y selecionar la clase o funcion a la que quieres generarle pruebas. Además he usado el debugger y algún que otro print para evaluar la ejecución del programa mientras lo programaba._

---

#### **Criterio global 4: Definir clases y su contenido**
- **(4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?

_La definición de clases ha ayudado a encapsular las diferentes funcionalidades del programa de forma eficiente y lógica._

```kotlin
/**
 * Clase que representa a un caballo en el juego de ajedrez.
 * @param color El color del caballo.
 * @param posicion La posición inicial del caballo en el tablero.
 */
class Knight(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoCaballo {

    override var listaMovimientosPosibles: MutableList<Posicion>  = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.CABALLO.desc

    /**
     * Verifica si un movimiento es válido para el caballo.
     * @param casillaAMover La casilla a la que se intenta mover el caballo.
     * @return Verdadero si el movimiento es válido, falso en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del caballo.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosCaballo(posicion, color))
    }
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Knight.kt#L4-L32)_

_En este ejemplo podemos ver como he definido la clase Knight la cual representa la pieza del caballo en el ajedrez. Para identificar las propiedades y métodos lo más sencillo me pareció crear un esquema inicial de todo el programa en el que definia todos aquellos métodos, clases y atributos que necesitaría, despues añadi y eliminé algunos de ellos durante la programación._

---

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.h, 4.j, 7.a, 7.b, 7.c)**: Describe sobre tu código cómo has implementado la herencia o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? ¿Mostrando tu código, contesta a qué principios has utilizado y qué beneficio has obtenido?

_Tomare la clase Knight otra vez como ejemplo._

```kotlin
/**
 * Clase que representa a un caballo en el juego de ajedrez.
 * @param color El color del caballo.
 * @param posicion La posición inicial del caballo en el tablero.
 */
class Knight(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoCaballo {

    override var listaMovimientosPosibles: MutableList<Posicion>  = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.CABALLO.desc

    /**
     * Verifica si un movimiento es válido para el caballo.
     * @param casillaAMover La casilla a la que se intenta mover el caballo.
     * @return Verdadero si el movimiento es válido, falso en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del caballo.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosCaballo(posicion, color))
    }
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Knight.kt#L4-L32)_

_La clase knight hereda de la clase abstracta Pieza e implementa la interfaz Movimiento Caballo. En mi programa cada una de las piezas del ajedrez implementa al menos una interfaz de movimiento las cuales aplican el principio de segregación de interfaces(ISP), gracias a eso me he evitado tener que creau una interfaz de movimiento para la clase Queen, ya que podía implementar los interfaces de movimiento diagonal y en linea recta ya definidos para el alfil y la torre. Además de ese principio e intentado implementa el resto de principios SOLID encontrando mayor dificultad en el principio de sustitución de Liskov(LSP)._

---

#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?

_Este programa tiene muchas clases con diferentes tipos de herencias. El programa consta de 2 Singletons Vista y GestiónPartida. Vista se encarga de toda la parte del GUI y contiene instancias de la clase Tablero que a su vez tiene instancias de la clase Casilla, GestionTablero por otro lado se centra en la gestión del tablero de juego, toma el tablero inicializado por Vista y gestiona las piezas que están en cada una de las Casillas del Tablero_

```kotlin
/**
 * Clase que representa a un caballo en el juego de ajedrez.
 * @param color El color del caballo.
 * @param posicion La posición inicial del caballo en el tablero.
 */
class Knight(color: String, posicion: Posicion): Pieza(color, posicion), MovimientoCaballo {

    override var listaMovimientosPosibles: MutableList<Posicion>  = mutableListOf()
    override var cantidadDeMovimientos: Int = 0
    override val nombrePieza = PiezasDeAjedrez.CABALLO.desc

    /**
     * Verifica si un movimiento es válido para el caballo.
     * @param casillaAMover La casilla a la que se intenta mover el caballo.
     * @return Verdadero si el movimiento es válido, falso en caso contrario.
     */
    override fun esMovimientoValido(casillaAMover: Casilla): Boolean {

        return casillaAMover.posicion in listaMovimientosPosibles
    }

    /**
     * Actualiza los movimientos posibles del caballo.
     */
    override fun actualizarMovimientosPosibles() {
        listaMovimientosPosibles = mutableListOf()
        listaMovimientosPosibles.addAll(obtenerMovimientosCaballo(posicion, color))
    }
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Knight.kt#L4-L32)_

_La clase Knight es un ejemplo de herencia por especialización, ya que representa una versión más especializada de Pieza_


```kotlin
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
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Vista.kt#L6-L37)_

_Vista es un ejemplo de herencia por epecificación, ya que toma JFrame y lo convierte en un Singleton que representa únicamente una ventana con un tablero de Ajedrez_


```kotlin
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
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Casilla.kt#L140-L156)_

_CasillaEnMovimiento en cambio es un ejemplo de herencia por construcción ya que implementa la interfaz Transferable_

---

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.k)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.

-En mi programa utilizo multiples librerias, quizá las más relevantes son Java Swing y Java AWT(Abstract Window Toolkit) las cuales me permiten implementar una interfaz gráfica e interactuar con ella. Las elegçi porque me parecían la forma mas sencilla y práctica de implementar una simple interfaz gráfica para alguien con pocos conocimientos sobre ello.-

```kotlin
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
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/Vista.kt#L6-L37)_

_Como se puede ver, Swing me permite implementar la clase JFrame la cual genera una ventana sobre la cual añadir elementos gráficos._

---

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?

_He documentado mi código aportando la mayor luz posible a su funcionamiento, he usado KDoc para definir clases y funciones y comentarios en linea para las partes que consideraba que necesitaban especificaciones extra. Para mejorart mis comentarios he utilizado replit.com para que reformule mis comentarios dado que no soy muy bueno explicandome._

```kotlin
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
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/06453efe5d031272a627fe389836fea897a9b2d1/src/main/kotlin/GestionPartida.kt#L67-L78)_

---

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?

_Respuesta: He implementado un metodo genérico en la clase Pieza que devuelve el icono de una pieza de Clase desconocida que herede de Pieza._

```kotlin
companion object {
   /**
    * Método estático que obtiene el icono de una pieza.
    * @param pieza La pieza de la que se desea obtener el icono.
    * @return ImageIcon representando el icono de la pieza.
    */
   fun <T : Pieza> obtenerIconoPieza(pieza: T): ImageIcon {
      val logo = ImageIcon("src/main/resources/images/${pieza.color}_${pieza.nombrePieza}.png")
      val medidaFicha = Vista.tamanioTablero / Tablero.CASILLAS_FILA_TABLERO
      val logoMin = ImageIcon(logo.image.getScaledInstance(medidaFicha, medidaFicha, Image.SCALE_SMOOTH))
      return logoMin
   }
}
```
_[Link al código en el repositorio](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-mbougar/blob/446d33d8bf48a515dee6a4358a4e5fa2a1fe6f20/src/main/kotlin/Pieza.kt#L40-L45)_

_Implementar este método genérico ha hecho que sea más flexible y reusable, probablemente habría implementado más si me hubiera dado tiempo. Por ejemplo podría haber creado una única clase Pieza sin subclasses que herede un interfaz de movimiento genérico._
