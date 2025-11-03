# Plan de Desarrollo - Social Network Graph Analyzer

## Análisis del Problema

Después de analizar los requerimientos del proyecto, se determinó que el desarrollo debe seguir un enfoque **bottom-up**, construyendo primero las bases sólidas (estructuras de datos y algoritmos) antes de agregar la interfaz de usuario. Esta estrategia permite:

1. **Validar la lógica core** independientemente de la UI
2. **Facilitar el testing** de componentes individuales
3. **Reducir el acoplamiento** entre capas

---

## Fases de Desarrollo

### Fase 0: Estructuras de Datos Base
**Objetivo:** Implementar estructuras de datos base para la creacion de los grafos
- [x] Implementacion de Node generico
- [x] Implementacion de lista enlazada (simple enlazada con tail)
- [x] Implementacion de hashmap (por definir si es posible). Posible ✅
- [ ] Implementacion de grafo genérico
- [ ] Implementar función para añadir nodos al grafo
- [ ] Implementar función para remover nodos del grafo
- [ ] Implementar función para establecer relaciones entre los nodos del grafo
- [ ] Implementar función para remover relaciones entre nodos
- [ ] Implementar función para listar nodos
- [ ] Implementar función para obtener las relaciones de un nodo

### ☐ Fase 1: Estructuras de Datos Core
**Objetivo:** Implementar el grafo dirigido y sus operaciones básicas

**Tareas:**
- [ ] Definir tipo de dato user ( una clase user o un string simple? )
- [ ] Crear clase Grafos aplicada a las relaciones de usuarios ( por definir si por herencia o simplemente una instancia del grafo base )
- [ ] Testing con data hardcode temporal usando datos de ejemplo

**Entregable:** Grafo funcional con todas las operaciones básicas validadas

---

### ☐ Fase 2: Algoritmo de Kosaraju
**Objetivo:** Implementar la detección de componentes fuertemente conectados

**Tareas:**
- (a desarrollar previa investigación sobre el algoritmo y su implementación)

**Entregable:** Algoritmo de Kosaraju funcionando correctamente

---

### ☐ Fase 3: Gestión de Archivos
**Objetivo:** Leer y escribir archivos de texto con formato especificado

**Tareas:**
- (a desarrollar)

**Entregable:** Sistema completo de persistencia de datos

---

### ☐ Fase 4: Interfaz Gráfica Básica
**Objetivo:** Crear UI funcional con Swing usando NetBeans GUI Builder

**Tareas:**
- (a desarrollar)

**Entregable:** Interfaz gráfica completamente funcional

---

### ☐ Fase 5: Visualización del Grafo
**Objetivo:** Integrar representación visual

**Tareas:**
- (a desarrollar)

**Entregable:** Visualización completa con componentes

---

### ☐ Fase 6: Refinamiento
**Objetivo:** Pulir el proyecto y preparar entrega

**Tareas:**
- (a definir)

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
