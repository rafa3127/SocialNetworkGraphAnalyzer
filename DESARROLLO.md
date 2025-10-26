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
- [ ] Implementacion de Node generico
- [ ] Implementacion de lista (por definir si doble o simplemente enlazada. quizas ambas)
- [ ] Implementacion de hashmap (por definir si es posible)
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

(Se irán agregando conforme se tomen decisiones técnicas durante el desarrollo)
