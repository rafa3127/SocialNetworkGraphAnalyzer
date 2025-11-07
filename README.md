# Analizador de Grafos de Redes Sociales

## Descripción
Aplicación para analizar grafos dirigidos que representan redes sociales, enfocándose en la detección de componentes fuertemente conectados mediante el algoritmo de Kosaraju con búsqueda en profundidad (DFS).

## Autor
Rafael Correa

## Repositorio
[![GitHub](https://img.shields.io/badge/GitHub-Repository-blue?logo=github)](https://github.com/rafa3127/SocialNetworkGraphAnalyzer)

## Requisitos
- Java 11 o superior
  - Por defecto el proyecto usa Java 24
  - Para usar Java 11: cambiar `<maven.compiler.release>24</maven.compiler.release>` a `11` en `pom.xml`
- Maven 3.6+
- GraphStream 2.0 (incluido en dependencias)

## Estructura del Proyecto
```
src/main/java/com/graph/socialnetworkgraphanalyzer/
├── basicdatastructures/    # Estructuras de datos propias (LinkedList, HashMap, Graph)
├── algorithm/              # Implementación del algoritmo de Kosaraju
├── io/                     # Lectura/escritura de archivos y parseo
└── ui/                     # Componentes de interfaz gráfica Swing
```

## Funcionalidades
- **Cargar Grafo desde Archivo**: Importar datos de red social desde archivos de texto
- **Modificar Grafo**: Agregar/eliminar usuarios y relaciones
- **Guardar Cambios**: Exportar grafo modificado a archivo
- **Representación Visual**: Visualización interactiva del grafo con GraphStream
- **Detección de Componentes**: Encontrar componentes fuertemente conectados usando algoritmo de Kosaraju
- **Código de Colores**: Cada componente se muestra con un color único

## Formato del Archivo de Entrada
```
usuarios
@usuario1
@usuario2
@usuario3
relaciones
@usuario1, @usuario2
@usuario2, @usuario3
```

## Implementación Técnica
- **Estructura del Grafo**: Grafo dirigido con implementación de lista de adyacencia
- **Estructuras de Datos Propias**: Todas las estructuras core (LinkedList, HashMap, Graph) implementadas desde cero
- **Algoritmo**: Algoritmo de Kosaraju con complejidad O(V + E)
- **Framework UI**: Java Swing con arquitectura basada en componentes
- **Visualización**: Librería GraphStream para renderizado del grafo

## Cómo Ejecutar
1. Clonar el repositorio
2. Abrir el proyecto en NetBeans o tu IDE preferido
3. Construir el proyecto con Maven
4. Ejecutar `SocialNetworkUI.java`

---

## Guía de Usuario

### Interfaz de la Aplicación

La aplicación se compone de cuatro componentes principales:

#### 1. Menú Principal (parte superior)
- **Archivo**: Operaciones de gestión de archivos (Nuevo, Cargar, Guardar, Cerrar)
- **Analizar**: Ejecutar el algoritmo de Kosaraju para encontrar componentes fuertemente conectados

#### 2. Panel de Información (lado derecho)
Muestra en tiempo real:
- **Usuarios**: Lista de todos los usuarios en el grafo con contador total
- **Relaciones**: Lista de todas las relaciones (formato: @usuario1 → @usuario2) con contador total
- **Componentes**: Lista de componentes fuertemente conectados encontrados por el algoritmo de Kosaraju

#### 3. Panel de Controles (lado izquierdo)
Contiene tres sub-paneles:
- **Gestionar Usuarios**: Agregar y eliminar usuarios del grafo
- **Gestionar Relaciones**: Agregar y eliminar relaciones entre usuarios existentes
- **Gestionar Archivo**: Botones de acceso rápido para cargar y guardar archivos

#### 4. Panel de Visualización (centro)
Muestra el grafo de forma interactiva con:
- Nodos representando usuarios
- Flechas representando relaciones dirigidas (quién sigue a quién)
- Colores diferenciados cuando se ejecuta el análisis de componentes

---

### Operaciones Disponibles

#### Gestión de Archivos

##### Crear Nuevo Grafo
1. Ir a **Menú → Archivo → Nuevo**
2. Si hay cambios sin guardar, el sistema solicitará confirmación
3. Se creará un grafo vacío listo para agregar usuarios

##### Cargar Archivo
1. Ir a **Menú → Archivo → Cargar** o usar el botón "Cargar Archivo" en el Panel de Controles
2. Seleccionar un archivo `.txt` con el formato correcto
3. El sistema validará el formato y mostrará un mensaje de éxito o error
4. El grafo se visualizará automáticamente

**Formato del archivo:**
```
usuarios
@usuario1
@usuario2
relaciones
@usuario1, @usuario2
```

##### Guardar Archivo
1. Ir a **Menú → Archivo → Guardar** o usar el botón "Guardar Archivo" en el Panel de Controles
2. Si es un grafo nuevo, se abrirá un diálogo para seleccionar ubicación y nombre
3. Si ya tiene archivo asociado, se guardará automáticamente
4. El archivo mantendrá el mismo formato de entrada

##### Cerrar Grafo
1. Ir a **Menú → Archivo → Cerrar**
2. Si hay cambios sin guardar, el sistema solicitará confirmación
3. La aplicación se cerrará

---

#### Gestión de Usuarios

##### Agregar Usuario
1. En el Panel de Controles, sección "Gestionar Usuarios"
2. Escribir el nombre del usuario en el campo de texto (debe empezar con @)
3. Hacer clic en el botón "Agregar"
4. El usuario aparecerá en la lista de usuarios y en la visualización

**Validaciones:**
- El nombre debe empezar con `@`
- El usuario no debe existir previamente

##### Eliminar Usuario
1. En el Panel de Controles, sección "Gestionar Usuarios"
2. Escribir el nombre del usuario a eliminar en el campo de texto
3. Hacer clic en el botón "Eliminar"
4. El usuario desaparecerá junto con todas sus relaciones (entrantes y salientes)

---

#### Gestión de Relaciones

##### Agregar Relación
1. En el Panel de Controles, sección "Gestionar Relaciones"
2. Seleccionar el usuario origen en el primer ComboBox ("Desde")
3. Seleccionar el usuario destino en el segundo ComboBox ("Hasta")
4. Hacer clic en el botón "Agregar Relación"
5. La relación aparecerá en la lista y en la visualización como una flecha

**Validaciones:**
- Ambos usuarios deben existir en el grafo
- No se permiten relaciones de un usuario consigo mismo
- No se permite duplicar relaciones existentes

##### Eliminar Relación
1. En el Panel de Controles, sección "Gestionar Relaciones"
2. Seleccionar el usuario origen en el primer ComboBox ("Desde")
3. Seleccionar el usuario destino en el segundo ComboBox ("Hasta")
4. Hacer clic en el botón "Eliminar Relación"
5. La relación desaparecerá de la lista y de la visualización

---

#### Análisis del Grafo

##### Ejecutar Algoritmo de Kosaraju
1. Ir a **Menú → Analizar → Encontrar Componentes**
2. El algoritmo procesará el grafo y encontrará todos los componentes fuertemente conectados
3. Los resultados se mostrarán en dos lugares:
   - **Panel de Información**: Lista de componentes con formato "Componente N (X usuarios): @user1, @user2..."
   - **Visualización**: Cada componente se coloreará con un color único

**¿Qué es un componente fuertemente conectado?**
Un grupo de usuarios donde cada usuario puede alcanzar a cualquier otro usuario del mismo grupo siguiendo las relaciones (flechas) del grafo.

**Nota:** Cada vez que se modifica el grafo (agregar/eliminar usuarios o relaciones), los colores de los componentes se resetean. Es necesario ejecutar el análisis nuevamente para ver los componentes actualizados.

---

### Visualizaciones

#### Lista de Usuarios
- Muestra todos los usuarios del grafo en orden
- Actualización automática al agregar o eliminar usuarios
- Incluye contador total en el encabezado

#### Lista de Relaciones
- Muestra todas las relaciones en formato `@origen → @destino`
- Actualización automática al modificar relaciones
- Incluye contador total en el encabezado

#### Lista de Componentes
- Muestra "No analizado" hasta que se ejecute el algoritmo de Kosaraju
- Después del análisis, lista cada componente encontrado con:
  - Número de componente
  - Cantidad de usuarios en el componente
  - Lista de usuarios separados por comas
- Ejemplo: `Componente 1 (5 usuarios): @pepe, @mazinger, @juanc, @sancho23, @terciopelo`

#### Gráfico Interactivo
- **Nodos**: Representan usuarios, tamaño 25-35px
- **Flechas**: Representan relaciones dirigidas (quién sigue a quién)
- **Colores**: 
  - Gris por defecto
  - Cada componente con color único después del análisis
  - Flechas entre componentes permanecen grises
- **Interactividad**: El grafo se auto-organiza usando algoritmos de layout automático

---

### Consejos de Uso

1. **Guardar frecuentemente**: El sistema alertará sobre cambios sin guardar, pero es buena práctica guardar después de modificaciones importantes

2. **Validar archivos**: Asegurarse de que los archivos `.txt` sigan el formato correcto antes de cargar

3. **Re-analizar después de cambios**: Los componentes no se actualizan automáticamente, es necesario ejecutar el análisis nuevamente

4. **Nombres de usuarios**: Siempre deben empezar con `@` para mantener consistencia con el formato de redes sociales