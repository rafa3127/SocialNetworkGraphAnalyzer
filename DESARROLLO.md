# Plan de Desarrollo - Social Network Graph Analyzer

## Análisis del Problema

Después de analizar los requerimientos del proyecto, se determinó que el desarrollo debe seguir un enfoque **bottom-up**, construyendo primero las bases sólidas (estructuras de datos y algoritmos) antes de agregar la interfaz de usuario. Esta estrategia permite:

1. **Validar la lógica core** independientemente de la UI
2. **Facilitar el testing** de componentes individuales
3. **Reducir el acoplamiento** entre capas

---

## Fases de Desarrollo

### ✅ Fase 0: Estructuras de Datos Base
**Objetivo:** Implementar estructuras de datos base para la creacion de los grafos
- [x] Implementacion de Node generico
- [x] Implementacion de lista enlazada (simple enlazada con tail)
- [x] Implementacion de hashmap (por definir si es posible). Posible ✅
- [x] Implementacion de grafo genérico
- [x] Implementar función para añadir nodos al grafo
- [x] Implementar función para remover nodos del grafo
- [x] Implementar función para establecer relaciones entre los nodos del grafo
- [x] Implementar función para remover relaciones entre nodos
- [x] Implementar función para listar nodos
- [x] Implementar función para obtener las relaciones de un nodo

### ⏳ Fase 1: Modelo de Red Social
**Objetivo:** Definir cómo se usará el grafo genérico para representar la red social

**Tareas:**
- [x] Definir tipo de dato user -> **DECISIÓN: Usar `String` directamente (handles como "@pepe")**
- [x] Crear clase Grafos aplicada a las relaciones de usuarios -> **DECISIÓN: Usar instancia `Graph<String>` directamente, sin wrapper**
- [x] Testing con data hardcode temporal usando datos de ejemplo

**Entregable:** Grafo funcional con todas las operaciones básicas validadas con datos de prueba

**Decisión tomada:** Se usará `Graph<String>` directamente por principio YAGNI (You Aren't Gonna Need It) y simplicidad. No se creará clase User ni SocialNetworkGraph wrapper a menos que surjan requerimientos que lo justifiquen.

---

### ☐ Fase 2: Algoritmo de Kosaraju
**Objetivo:** Implementar la detección de componentes fuertemente conectados

**Tareas:**
- [x] Investigar el algoritmo de Kosaraju y sus 3 pasos:
  1. DFS en grafo original guardando orden de finalización
  2. Transponer el grafo (invertir todas las aristas)
  3. DFS en grafo transpuesto procesando nodos en orden de pila
- [x] Implementar clase `Kosaraju` en paquete `algorithm`
- [x] Implementar método estático genérico `findSCC(Graph<T>)`
- [x] Implementar métodos auxiliares privados (DFS, transpose)
- [x] Validar que detecta correctamente los 3 componentes de los datos de ejemplo

**Entregable:** Algoritmo de Kosaraju funcionando correctamente

**Decisión de diseño:** Kosaraju será una clase separada con método estático genérico, siguiendo el patrón estándar de algoritmos de grafos (separación de estructura y algoritmo).

---

### Fase 3: Gestión de Archivos
**Objetivo:** Leer y escribir archivos de texto con formato especificado

**Tareas:**
- [x] **Capa 1 - Capa de lectura/escritura de archivos:** Implementar lectura/escritura básica de archivos
  - `readFile(String filepath)` -> retorna LinkedList<String> con todas las líneas
  - `writeFile(String filepath, LinkedList<String> lines)` -> escribe líneas al archivo (decidir si sobreescribir todo o agregar)
- [x] **Capa 2 - parser por secciones:** Implementar parser genérico de secciones
  - `parseFileSections(LinkedList<String> lines, LinkedList<String> sectionNames)` -> resultados por secciones (podría ser HashMap<String, LinkedList<String>>)
  - Usa whitelist de nombres de sección para detectar headers
  - Agrupa líneas entre secciones
- [x] **Capa 3 - capa para cargar data en grafo:** Implementar lógica específica del grafo
  - `loadGraphFromFile(String filepath)` -> Graph<String>
  - `saveGraphToFile(Graph<String> graph, String filepath)` -> void
  - Validaciones: verificar si agregar validaciones (usuarios con @, relaciones con formato correcto, etc)
- [x] Testing con archivo de datos de ejemplo
- [x] Manejo de errores (archivo no existe, formato inválido, etc.)

**Entregable:** Sistema completo de persistencia de datos con arquitectura en 3 capas

**Decisión de arquitectura:** Separar en 3 capas para separación de responsabilidades y reusabilidad

---

### Fase 4: Interfaz Gráfica Básica
**Objetivo:** Crear UI funcional con Swing usando NetBeans GUI Builder

**Tareas:**
- [x] Crear JFrame principal en paquete `ui`
- [x] Diseñar layout con NetBeans GUI Builder (controles, visualización placeholder, información)
- [x] Implementar controles para agregar/eliminar usuarios
- [x] Implementar controles para agregar/eliminar relaciones
- [x] Implementar carga de archivo con JFileChooser
- [x] Implementar guardado de archivo
- [x] Integrar ejecución de Kosaraju y mostrar resultados
- [x] Testing manual de funcionalidades

**Entregable:** Interfaz gráfica completamente funcional

**Decisión de diseño:** Una sola clase por simplicidad. Usar NetBeans GUI Builder para diseño visual, escribir solo event handlers manualmente.

---

### Fase 5: Visualización del Grafo
**Objetivo:** Integrar representación visual usando GraphStream

**Tareas:**
- [x] Agregar dependencias de GraphStream al `pom.xml` (`gs-core` y `gs-ui-swing`)
- [x] Crear clase `GraphVisualizer.java` en paquete `ui` (lógica de conversión y estilo)
- [x] Implementar clase `VisualizationPanel.java` en paquete `ui` (contenedor UI con GraphStream viewer)
- [x] Integrar componente de gráfico
- [x] Testing manual con datos de ejemplo

**Entregable:** Visualización completa del grafo con componentes coloreados

---

### ☐ Fase 6: Refinamiento
**Objetivo:** Pulir el proyecto y preparar entrega

**Tareas:**
- [x] Revisar completitud de javadocs
- [x] Documentacion de usuario
- [x] Funcionalidad para precargar data de prueba
- [x] Diagrama de clases
- [x] Revision de cumplimiento de requerimientos
- [ ] Entrega 🥳

**Entregable:** Proyecto completo, documentado y listo para entrega

---

## Notas de Implementación

### Decisiones sobre LinkedList
**Implementación de Lista Enlazada:**
- Se utilizará lista **simple enlazada** con puntero al último nodo.
- Suficiente para todas las operaciones requeridas (agregar, eliminar, buscar, recorrer)
- Ventajas sobre doble enlazada:
  - Implementación más simple y menos propensa a errores
  - Menor consumo de memoria (un solo puntero por nodo)
  - Complejidad adecuada: O(1) para agregar al final (con tail), O(n) para búsqueda/eliminación
- La doble enlazada no aporta beneficios significativos para este caso de uso

**Métodos omitidos en LinkedList:**
- **Inserción en posiciones específicas**: No se implementan porque requerirían exponer la clase `Node` públicamente o usar índices (ineficiente en listas enlazadas). Para el caso de uso actual (HashMap y lista de adyacencia), solo se necesita agregar al final.
- **Acceso por índice** (`get(index)`, `set(index)`): Operación O(n) poco eficiente en listas enlazadas.
- **Acceso directo a nodos** (`getHead()`, `getTail()`): Se omite para mantener `Node` como detalle de implementación interna. No se expone la estructura interna de la lista (de ser necesario, se puede dejar de evitar usar los nodos desde fuera).

Estos métodos pueden agregarse posteriormente si surgen nuevos requerimientos durante el desarrollo.

### Decisiones sobre HashMap:
**HashMap - Load Factor y Resize:**
- Load factor de 0.75 elegido como balance entre memoria y performance
- Es el estándar de la industria (usado por Java HashMap)
- Resize duplica la capacidad y rehashea todos los elementos
- Costo de resize es O(n) pero amortizado es O(1) porque ocurre pocas veces

**HashMap - Manejo de Colisiones:**
- Usa separate chaining (listas enlazadas) para manejar colisiones
- Cada bucket es el inicio de una lista enlazada de Entry
- Complejidad: O(1) promedio, O(n) peor caso si todas las claves colisionan

**HashMap - Naming Convention:**
- Atributo `elementCount` en lugar de `size` para evitar confusión con el método `size()`
- Aunque en Java es práctica estándar tener ambos con el mismo nombre, se optó por claridad


### Métodos para hacer iterable y testeable las estructuras básicas:
**LinkedList - Métodos para iteración y visualización:**
- `getHead()`: Retorna el primer nodo para permitir iteración manual externa
- `toString()`: Genera representación en string para debugging (formato: `[elem1, elem2, ...]`)
- Expone la estructura interna mínimamente necesaria para casos de uso externos d
- Decisión tomada después de implementar HashMap, necesario para iterar `getKeys()`

**Node - Getters públicos:**
- `getData()`: Retorna el dato almacenado en el nodo
- `getNext()`: Retorna el siguiente nodo en la lista
- Necesarios para iteración externa desde paquetes diferentes a `basicdatastructures`
- Los atributos permanecen package-private para acceso interno eficiente

**HashMap - Método getKeys():**
- Retorna una `LinkedList<K>` con todas las claves del mapa
- Itera sobre todos los buckets y sus listas enlazadas
- Complejidad O(n) donde n es el número de elementos
- Necesario para implementar `getNodes()` en el grafo

### Decisiones sobre grafos 
**Estructura del Grafo:**
- Implementación con clase genérica `Graph<T>`
- Los usuarios se representaran en nuestra instanciación como `String` (handle: "@pepe")
- Uso: `Graph<String>` para el grafo de la red social
- Lista de adyacencia implementada con `HashMap<T, LinkedList<T>>` (nuestras estructuras propias)
- Contador de aristas (`edgeCount`) para operaciones O(1) de conteo
- Clase `Edge<T>` para representar pares (from, to) en `getEdges()`

**Graph - Operaciones:**
- Grafo dirigido: `addEdge(from, to)` solo crea arista `from -> to` (no bidireccional)
- `removeNode()` elimina tanto aristas salientes como entrantes del nodo
- `getEdges()` tiene complejidad O(n + m) donde n=nodos, m=aristas

### Decisiones sobre el modelo de red social (Fase 1)

**String vs clase User**
- **Decisión: Usar `String` directamente**
- Razones:
  - Suficiente para los requerimientos actuales (solo se necesita identificar usuarios)
  - Los datos del archivo que usaremos para persistencia ya son Strings.
  - Más simple de testear y debuggear
  - String.hashCode() ya existe y es eficiente
- Considerado pero descartado:
  - Clase `User` permitiría agregar atributos (nombre, bio, etc.) pero no hay requerimiento actual
  - Validación de formato (@ al inicio) se puede hacer en FileManager al cargar

**¿Wrapper class vs instancia directa?**
- **Decisión: Usar `Graph<String>` directamente sin wrapper**
- Razones:
  - Máxima simplicidad
  - No hay necesidad de métodos específicos de red social
  - Evita código boilerplate de delegación
  - Separación clara: Graph = estructura, Kosaraju = algoritmo
- Considerado pero descartado:
  - `SocialNetworkGraph` wrapper permitiría métodos como `addFollowing()` o `findCommunities()`
  - Herencia (`extends Graph<String>`) violaría principios de diseño
  - Para proyecto académico, claridad > arquitectura

**¿Dónde vive el algoritmo de Kosaraju?**
- **Decisión: Clase separada en paquete `algorithm`**
- Razones:
  - Separación de responsabilidades (estructura vs algoritmo)
  - Reusabilidad: funciona con cualquier `Graph<T>`
  - Testing independiente
  - Patrón estándar en algoritmos de grafos
  - Facilita evaluación académica (clara separación de conceptos)
- Estructura:
  ```java
  public class Kosaraju {
      public static <T> LinkedList<LinkedList<T>> findSCC(Graph<T> graph) { }
  }
  ```

### Extensibilidad futura
Si en el futuro se necesitan características más complejas:
- **Múltiples atributos por usuario**: Crear clase `User` y cambiar a `Graph<User>`
- **Validaciones complejas**: Implementar en constructor de `User`
- **Métodos específicos de red social**: Crear clase `SocialNetworkAnalyzer` con métodos estáticos
- **Otros algoritmos**: Agregar clases en `algorithm` (ej: `PageRank`, `BFS`, etc.)

La arquitectura actual permite estas extensiones sin necesidad de refactorización mayor, solo adiciones.


### Decisiones sobre el algoritmo de Kosaraju (Fase 2)

**Método getOutgoingNodes() agregado a Graph**
- **Decisión:** Agregar método público `getOutgoingNodes(T node)` en `Graph.java`
- **Razón:** DFS necesita acceder a los nodos adyacentes de un nodo dado
- **Alternativas consideradas:**
  - Hacer DFS dentro de Graph: descartado, ya decidimos separar algoritmos de estructuras
  - Exponer `adjacencyList`: descartado, rompe encapsulación

**Implementación de DFS (Depth-First Search)**
- **Decisión:** Método privado `dfs()` recursivo
- **Orden de agregado:** Post-order (agregar nodo después de visitar todos sus descendientes)
  - Crítico para Kosaraju: garantiza que nodos se agregan en orden de tiempo de finalización
- **Estructura de visitados:** Usar `HashMap<T, Boolean>` en lugar de `LinkedList<T>`
  - Razón: O(1) para verificar si fue visitado vs O(n) con lista
  - HashMap más eficiente para grafos grandes
- **Parámetro result:** LinkedList que puede ser usado como:
  - Pila de finalización en primer DFS
  - Componente individual en segundo DFS

**Implementación de transpose()**
- **Decisión:** Usar `getEdges()` y objetos `Edge` para invertir aristas
- **Alternativa considerada:** Iterar manualmente con `getOutgoingNodes()`
  - Descartado: más complejo, doble while loop anidado
- **Ventajas de usar Edge:**
  - Código más simple y legible: `edge.to` y `edge.from` son claros
  - Reutiliza estructura existente
  - Un solo while loop en lugar de dos anidados
- **Complejidad:** O(n + m) donde n=nodos, m=aristas (óptimo)

### Decisiones sobre gestión de archivos (Fase 3)

**Arquitectura en capas:**
- **Decisión:** Implementar sistema de archivos en 3 capas independientes
- **Razones:**
  - Separación de responsabilidades (I/O básico, parsing genérico, lógica de negocio)
  - Parser genérico reutilizable para cualquier archivo con secciones
  - Facilita testing individual de cada capa
  - Modificar lógica del grafo no afecta capas inferiores
- **Alternativa considerada:** Implementación monolítica en una sola clase
  - Descartado: difícil de mantener, testear y extender

**Detección de secciones:**
- **Decisión:** Usar whitelist de nombres de sección conocidos
- **Razones:**
  - Genérico: funciona independientemente del formato de datos
  - Flexible: agregar secciones solo requiere actualizar whitelist
- **Alternativas consideradas:**
  - verificar las secciones basado en símbolos: descartado por no ser genérico

**Manejo de errores:**
- **Decisión:** Estrategia fail-fast - si hay error de formato, no cargar nada
- **Razón:** Mantener integridad de datos en proyecto académico

**Decisiones de implementación - Capa 1 (FileIO):**

**writeFile() genérico con validación:**
- **Decisión:** Usar `LinkedList<?>` para aceptar cualquier tipo, no solo String
- **Razón:** Mayor flexibilidad - puede escribir LinkedList de cualquier objeto que tenga toString()
- **Validación en dos pasos:**
  1. Primero convierte todos los elementos a String y valida
  2. Solo si todo OK, escribe al archivo
- **Ventaja:** Si hay error, no deja archivo corrupto a medio escribir (fail-fast)
- **Maneja:** elementos null, toString() que retorna null, objetos sin toString() válido

**Decisiones de implementación - Capa 2 (SectionParser):**

**Parser como clase instanciable:**
- **Decisión:** SectionParser se instancia con constructor que recibe whitelist, no métodos estáticos
- **Estructura:** `parser = new SectionParser(whitelist)` -> `parser.parse(lines)`
- **Razones:**
  - Encapsulación: cada parser mantiene su propia whitelist
  - Reutilizable: crear una vez, usar múltiples veces
  - Flexible: múltiples parsers con diferentes whitelists si es necesario
  - OOP correcto: estado (whitelist) + comportamiento (parse)
- **Alternativa considerada:** Métodos estáticos con whitelist como parámetro
  - Descartado: menos orientado a objetos, whitelist se pasa en cada llamada
- **Alternativa considerada:** Whitelist como atributo estático mutable
  - Descartado: estado global.

**Manejo de casos especiales:**
- **Líneas fuera de sección:** Ignorar (antes del primer header)
- **Líneas vacías:** Ignorar siempre
- **Case sensitivity:** Nombres de sección son case-sensitive
- **Secciones duplicadas:** Sumar líneas a la sección existente (no sobrescribir)
  - Permite archivos con múltiples bloques de la misma sección
- **Secciones vacías:** Crear LinkedList vacía (válido)

**Algoritmo de parseo:**
1. Iterar sobre todas las líneas
2. Si línea vacía -> ignorar
3. Si línea está en whitelist -> iniciar/continuar sección
4. Si no -> agregar a sección actual (si existe)
5. Si sección ya existe en HashMap -> agregar líneas a la existente

**HashMap como Set para whitelist:**
- **Decisión:** Usar `HashMap<String, Boolean>` para almacenar nombres de sección válidos
- **Razones:**
  - O(1) vs O(n) con array o Lista
  - Garantiza unicidad (no permite duplicados)
  - Escalable: si el proyecto crece y se agregan más secciones, mantiene performance
  - Parser genérico reutilizable: puede usarse en otros contextos con más secciones
- **Trade-off:** Pequeño overhead de memoria para pocos elementos (2-10 secciones típicas)
- **Alternativa considerada:** Array de Strings
  - Descartado: no garantiza unicidad, O(n) (aunque n pequeño en este caso)
- **Nota:** Java tiene java.util.Set pero está restringido. HashMap funciona perfectamente como Set usando solo las keys.

**Método serialize() - operación inversa:**
- **Decisión:** Agregar método `serialize()` que convierte HashMap de secciones de vuelta a líneas
- **Razones:**
  - Simetría: parse() convierte líneas -> HashMap, serialize() convierte HashMap -> líneas
  - Cohesión: el parser conoce el formato de secciones, debe poder construirlo también
  - Reutilizable: La clase que manipulo la info de los Grafos no necesita conocer detalles del formato de secciones
  - evita duplicar lógica de formato en múltiples lugares
- **Flujo completo:** FileIO.read() -> parse() -> [modificar] -> serialize() -> FileIO.write()

**Decisiones de implementación - Capa 3 (GraphFileManager):**
**Manejo de secciones faltantes:**
- **Decisión:** Permitir archivos sin sección "usuarios" o sin sección "relaciones"
- **Comportamiento:**
  - Sin "usuarios": Crea grafo vacío (0 nodos, 0 aristas)
  - Sin "relaciones": Crea grafo solo con nodos (N nodos, 0 aristas)
- **Razón:** Flexibilidad para casos edge (archivos vacíos, grafos sin relaciones)

### Decisiones sobre interfaz gráfica (Fase 4)

**Arquitectura componencial vs clase monolítica:**
- **Decisión:** Usar arquitectura componencial - cada sección de UI es una clase JPanel separada
- **Razones:**
  - Código más organizado y mantenible (evita clase de muchas líneas)
  - Cada panel se diseña independientemente en NetBeans GUI Builder (hasta donde me es posible, Tambien se añaden props programaticamente)
  - Facilita testing y debugging
  - Natural para desarrollador con mi experiencia en frameworks componenciales
- **Alternativa considerada:** Todo en una sola clase 
  - Ventajas: Más simple, menos archivos, estándar en proyectos pequeños Swing
  - Descartado: Preferencia por código modular
- **Estructura:**
  ```
  SocialNetworkUI (JFrame) - orquestador principal
    ├── InfoPanel (JPanel) - muestra información 
    ├── ControlsPanel (JPanel) - controles de modificación (futuro)
    └── VisualizationPanel (JPanel) - visualización GraphStream (futuro)
  ```

**Comunicación entre componentes:**
- **Decisión:** Métodos públicos que reciben el grafo como parámetro
- **Estructura:** `infoPanel.updateGraphInfo(currentGraph)`
- **Razones:**
  - Simple y directo
  - No requiere interfaces complejas o listeners para este caso de uso
  - Desacoplado: InfoPanel no necesita conocer detalles del JFrame principal
- **Alternativas consideradas:**
  - Pasar grafo en constructor: más acoplado, menos flexible
  - Callbacks complejos: over-engineering para caso particular
- **Flujo de datos:** SocialNetworkUI (dueño del estado) -> llama métodos públicos -> paneles se actualizan

**Ensamblado de componentes:**
- **Decisión:** Ensamblar paneles programáticamente en constructor de SocialNetworkUI
- **Razones:**
  - NetBeans GUI Builder tiene limitaciones con BorderLayout dinámico
  - Más control sobre posicionamiento y tamaños
  - Cada panel se diseña cómodamente en GUI Builder por separado
- **Nota importante:** `initComponents()` es generado por NetBeans - NUNCA modificar. Toda lógica va después en el constructor

**InfoPanel - Diseño y actualización:**
- **Layout:** GridBagLayout para control preciso de posicionamiento y expansión

**Carga de archivos con JFileChooser:**
- **Implementación:** Método `loadFile()` privado llamado desde MenuItem
- **Flujo:**
  1. Abrir JFileChooser con filtro .txt
  2. Si usuario selecciona archivo -> `GraphFileManager.loadGraphFromFile()`
  3. Actualizar `currentGraph` y `currentFilePath`
  4. Llamar `infoPanel.updateGraphInfo(currentGraph)`
  5. Mostrar diálogo de éxito con conteos
- **Manejo de errores:** try-catch con JOptionPane.showMessageDialog para mostrar errores al usuario

**Trade-offs aceptados:**
- Arquitectura componencial toma más tiempo inicialmente pero mejora mantenibilidad
- Mezclar GUI Builder con código manual (ensamblado) es necesario por limitaciones de NetBeans
- Algunos estilos visuales se configuran por código en lugar de GUI Builder (más control, menos visual)

**Patrón de comunicación hijo -> padre:**
- **Decisión:** Implementar interface `GraphUpdateListener` con método `onGraphUpdated()`
- **Razones:**
  - En el caso de ControlsPanel, no se pudo solo pasar el grafo como estaba previsto en decisiones anteriores, ya que se manipula el grafo. Se debe usar un listener para notificar cambios al padre y propagarlos
  - Simple: Solo una interface con un método
  - Escalable: Fácil agregar más listeners si es necesario
  - Patrón estándar: Observer/Listener

**Manejo de estado del grafo:**
- **Decisión:** Grafo siempre existe (nunca null), se inicializa como `new Graph<>()` vacío
- **Razones:**
  - Usuario puede empezar agregando usuarios sin cargar archivo
  - Simplifica código (no validar `graph == null` en cada operación)
  - Comportamiento más natural y flexible
- **Implicación:** `currentFilePath` puede ser null si no se ha cargado/guardado archivo
- **Manejo de guardado sin archivo:**
  - Si `currentFilePath == null` al guardar, abrir JFileChooser (Save As dialog) (Por implementar al momento de escribir esta nota)
  - Guardados subsecuentes usan la misma ruta
- **Comportamiento estándar:** Similar a editores de texto (nuevo documento -> guardar -> pedir ruta)

**Actualización de ComboBoxes en ControlsPanel:**
- **Decisión:** Iterar LinkedList una sola vez agregando ítems directamente con `addItem()`
- **Razones:**
  - Más eficiente que crear array temporal y volver a iterarlo
  - ComboBox permite agregar ítems uno por uno (a diferencia de JList que necesita array completo)
  - Código más simple y directo
- **Placeholder para selección:**
  - Agregar ítem "-- Seleccionar usuario --" al inicio de ambos comboboxes
  - Queda seleccionado por defecto, guía al usuario visualmente
  - Validar en event handlers que no sea el placeholder (startsWith("--"))

**Validaciones en operaciones de usuarios:**
- **addNode:**
  - Validaciones manuales: campo no vacío, formato correcto (empieza con @)
  - Validación automática: usuario no existe (IllegalArgumentException del grafo)
- **removeNode:**
  - Validación manual: campo no vacío
  - Validación automática: usuario existe (IllegalArgumentException del grafo)
  - no necesita validar formato @ porque si no existe, lanzará excepción

**Centrado de diálogos JOptionPane:**
- **Decisión:** Pasar referencia del JFrame padre en lugar de `this` (panel actual)
- **Implementación:** Agregar parámetro `parentFrame` en `setGraphAndListener()`
- **Razón:** Diálogos se centran en la aplicación completa, no en el panel lateral

**Validación de arista autoapuntada:**
- **Decisión:** Validar a nivel de UI, no en la estructura Graph
- **Razones:**
  - Graph es una estructura genérica que matemáticamente permite autoapuntados
  - La restricción es de lógica de negocio (red social), no de estructura de datos
  - No requiere modificar fases ya completadas
  - Más flexible: si en el futuro se necesita permitir self-loops, solo se cambia la UI
- **Implementación:** Validar si los valores de los fields son iguales antes de llamar `graph.addEdge()`

**Patrón de reutilización de código entre menú y panel:**
- **Decisión:** Extraer lógica de archivo a métodos privados (`loadFile()`, `saveFile()`, `newGraph()`, `exitApplication()`) y extender `GraphUpdateListener` con callbacks para operaciones de archivo
- **Razones:**
  - Evitar duplicación entre MenuItems y botones del panel
  - Centralizar validaciones y lógica compleja
  - Mantener consistencia de comportamiento
- **Flujo:** ControlsPanel invoca callback -> SocialNetworkUI ejecuta método privado compartido

**Validación de cambios sin guardar:**
- **Decisión:** Validar `hasUnsavedChanges` antes de operaciones destructivas (cargar, nuevo grafo, cerrar)
- **Razón:** Prevenir pérdida accidental de datos
- **Flag `hasUnsavedChanges`:** Se activa en `onGraphUpdated()`, se desactiva después de guardar/cargar

**Función "Nuevo Grafo":**
- **Decisión:** Permitir crear grafo vacío sin cargar archivo
- **Razón:** Usuario puede trabajar desde cero, consistente con diseño de "grafo siempre existe"
- **Implementación:** Crea `new Graph<>()`, resetea `currentFilePath` a `null`

**Sistema de guardado adaptativo:**
- **Decisión:** Si `currentFilePath == null` abre Save As dialog, si existe guarda directamente
- **Razón:** Comportamiento estándar de aplicaciones desktop

**Panel de archivos en ControlsPanel:**
- **Decisión:** Agregar tercer sub-panel (`filePanel`) con botones de cargar/guardar
- **Razón:** Acceso rápido sin ir al menú, mantiene arquitectura componencial

**Visualización de componentes en InfoPanel:**
- **Decisión:** Mostrar componentes en formato de lista plana
- **Formato:** "Componente X (N usuarios): @user1, @user2, @user3..."
- **Razones:**
  - Consistente con el diseño existente (usuarios y relaciones también usan JList)
  - Toda la información visible sin interacción adicional
  - Simple de implementar y mantener
  - Fácil de leer y copiar para análisis

**Manejo de estado del análisis:**
- **Decisión:** Indicar explícitamente cuando no se ha ejecutado el análisis
- **Comportamiento:**
  - Inicial: "Componentes (No analizado)"
  - Después de ejecutar Kosaraju: "Componentes (N)"
  - Al modificar el grafo: resetear a "No analizado" (los componentes previos ya no son válidos)
- **Razón:** Evitar confusión cuando el label muestra "0" vs "análisis no ejecutado"

**Actualización de ruta de archivo en ControlsPanel:**
- **Decisión:** Centralizar actualización del label de archivo en `updatePanelsInfo()`
- **Razones:**
  - Única fuente de verdad para actualizar todos los paneles
  - Se ejecuta automáticamente en todos los flujos (cargar, guardar, nuevo, modificar)
  - Mantiene sincronización sin código duplicado
- **Formato:** "Archivo: ruta/completa" o "Archivo: No asignado" si `currentFilePath == null`

### Decisiones sobre visualización con GraphStream (Fase 5)

**Arquitectura en 2 capas:**
- **Decisión:** Separar en `GraphVisualizer` (lógica) + `VisualizationPanel` (UI)
- **Razones:**
  - GraphVisualizer es clase utilitaria con métodos estáticos, sin estado, reutilizable y testeable
  - VisualizationPanel es contenedor UI simple que delega toda la lógica
  - Separación de responsabilidades clara (conversión vs presentación)
- **API limpia:** VisualizationPanel expone solo metodos para actualizar el grafico y sus caracteristicas desde el componente padre

**Conversión Graph<String> -> GraphStream Graph:**
- **Decisión:** Convertir la estructura custom a la estructura interna de GraphStream
- **Razones:**
  - GraphStream es un framework completo, no solo librería de dibujo
  - Necesita su propio modelo para manejar metadatos (posiciones, estilos, eventos)
  - No puede "dibujar directamente" estructuras custom que no conoce
- **Costo:** O(n+m) una sola vez cuando cambia el grafo (operacion costosa pero necesaria)
- **Fuente de verdad:** `Graph<String>` sigue siendo el modelo principal, GraphStream solo para renderizado

**Alternativas consideradas y descartadas:**
- Usar GraphStream como estructura principal: viola restricción académica

**Operaciones incrementales vs rebuild completo:**
- **Decisión:** Implementar ambos enfoques - rebuild completo (`buildCompleteGraph`) y operaciones incrementales (`addNode`, `removeNode`, `addEdge`, `removeEdge`) *CONTRADICE UNA DECISION ANTERIOR DADO QUE SE ENCONTRÓ UNA MANERADE OPTIMIZAR LAS ACTUALIZACIONES*
- **Razones:**
  - Rebuild completo necesario para carga inicial de archivos (O(n+m) inevitable)
  - Operaciones incrementales optimizan modificaciones individuales (O(1) vs O(n+m))
  - Mejor experiencia de usuario: animaciones suaves al agregar/eliminar elementos
  - Mantiene posiciones de nodos existentes (no se reposicionan todos al agregar uno)
- **Implementación:**
  - `buildCompleteGraph()`: limpia y reconstruye el grafo completo desde cero
  - Operaciones incrementales: solo modifican el elemento específico en GraphStream
  - Cada operación del grafo dispara su operación equivalente en el grafo de visualización
- **Flujo de actualización:**
  - Carga de archivo: `rebuildGraph()` (build completo)
  - Agregar usuario: `onNodeAdded()` -> `addNode()` (incremental)
  - Eliminar usuario: `onNodeRemoved()` -> `removeNode()` (incremental)
  - Agregar relación: `onEdgeAdded()` -> `addEdge()` (incremental)
  - Eliminar relación: `onEdgeRemoved()` -> `removeEdge()` (incremental)

**Sistema de listeners específicos:**
- **Decisión:** Extender `GraphUpdateListener` con métodos específicos para cada operación
- **Interface expandida:**
  - `onGraphUpdated()` - notificación genérica (mantiene compatibilidad)
  - `onNodeAdded(String username)` - notificación específica de nuevo nodo
  - `onNodeRemoved(String username)` - notificación específica de nodo eliminado
  - `onEdgeAdded(String from, String to)` - notificación específica de nueva arista
  - `onEdgeRemoved(String from, String to)` - notificación específica de arista eliminada
- **Razones:**
  - Permite operaciones incrementales en visualización
  - Mantiene separación de responsabilidades (ControlsPanel notifica qué cambió, SocialNetworkUI decide qué hacer)
  - Escalable para futuras optimizaciones
- **Implementación en ControlsPanel:**
  - Cada event handler llama al listener específico en lugar del genérico
  - Ejemplo: botón "Agregar Usuario" llama `listener.onNodeAdded(username)`

**Generación de IDs únicos para aristas:**
- **Decisión:** Usar formato `"from->to"` como ID de arista en GraphStream
- **Razones:**
  - GraphStream requiere ID único para cada arista
  - Formato descriptivo y fácil de debuggear
  - Garantiza unicidad (dos usuarios no pueden tener arista duplicada en grafo dirigido)
- **Implementación:** `String edgeId = from + "->" + to;`
- **Contraste con `buildCompleteGraph`:** Usa IDs numerados secuenciales (`"e0"`, `"e1"`, etc.) porque no necesita rastrear aristas específicas
