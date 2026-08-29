# TCSW Ventas - P01 y P02

## Descripción General

Proyecto académico de una aplicación de ventas en memoria. 

**P01**: Ambiente reproducible con la entidad Producto.  
**P02**: Venta en memoria con partidas/detalles y objeto de valor Moneda.

---

# P01. Ambiente reproducible y Producto

## Objetivo

Este proyecto crea un ambiente reproducible con Java 11 y Maven para desarrollar una primera entidad de dominio: `Producto`. El propósito de esta etapa es preparar una base simple, clara y extensible para futuras actividades académicas.

## Tecnologías y versiones

- Java 11
- Maven 3.9.16  
- JUnit 5
- Git
- Docker

## Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- Java 11
- Maven
- Git
- Docker
- Visual Studio Code

## Clonar el proyecto

```bash
git clone https://github.com/Alessito54/tcsw-ventas.git
cd tcsw-ventas
```

## Compilar

```bash
mvn clean compile
```

## Ejecutar pruebas

```bash
mvn clean test
```

## Producto

La entidad `Producto` representa un artículo comercial y contiene:

- código
- nombre
- precio
- existencia

Estos datos se encapsulan y se validan antes de aceptar la creación o modificación del objeto.

## Validaciones

Se rechazan datos inválidos como:

- código nulo o vacío
- nombre nulo o vacío
- precio negativo
- existencia negativa

También se valida que el precio no sea nulo.

---

# P02. Venta en memoria

## Objetivo

Modelar una venta en memoria con objetos colaborantes que incluyen partidas/detalles y un objeto de valor para dinero.

## Modelo de Dominio

### Producto (desde P01)

Entidad inmutable que representa un artículo comercial.

- `código`: String (único, no nulo, no vacío)
- `nombre`: String (no nulo, no vacío)
- `precio`: BigDecimal (>= 0)
- `existencia`: int (>= 0)

### Moneda (Objeto de Valor)

Encapsula una cantidad de dinero.

**Justificación**: Se eligió Moneda como objeto de valor porque:
1. Encapsula la lógica de dinero en un tipo sólido.
2. Proporciona operaciones seguras: multiplicación y suma.
3. Garantiza que las cantidades nunca sean negativas.
4. Facilita la reutilización en distintos contextos (precios, totales).
5. Implementa igualdad por valor, no por referencia.

**Características**:
- `cantidad`: BigDecimal (>= 0, no nulo)
- Operaciones: `multiplicar(factor)`, `sumar(otra)`, `obtener()`
- Validación: rechaza cantidades negativas y nulas.
- Inmutable: no se puede modificar una Moneda después de crear.

### DetalleVenta (Partida)

Representa una línea de venta con un producto, cantidad y precio unitario.

**Responsabilidades**:
- Encapsular producto, cantidad y precio.
- Validar que el producto no sea nulo.
- Validar que la cantidad sea > 0.
- Validar que el precio unitario sea >= 0.
- Calcular automáticamente el subtotal (precio × cantidad).

**Invariantes**:
- `producto` != null
- `cantidad` > 0
- `precioUnitario` >= 0
- `subtotal` = `precioUnitario` × `cantidad`

**Atributos**:
- `producto`: Producto (no nulo)
- `cantidad`: int (> 0)
- `precioUnitario`: Moneda (>= 0)

### Venta

Compuesta por múltiples detalles/partidas.

**Responsabilidades**:
- Mantener encapsulada la lista de detalles.
- Permitir agregar detalles válidos.
- Calcular automáticamente el total (suma de subtotales).
- Proteger la colección interna de modificaciones externas.

**Invariantes**:
- `detalles` no puede ser null (es List vacía inicialmente).
- Cada detalle agregado debe ser no-null.
- `total` = Σ (subtotal de cada detalle)

**Operaciones públicas**:
- `agregarDetalle(detalle)`: agrega si no es null
- `obtenerDetalles()`: retorna copia no modificable
- `obtenerTotal()`: calcula suma de subtotales
- `cantidadDetalles()`: número de partidas

## Excepciones

Se utilizan excepciones de tipo `IllegalArgumentException` para rechazar operaciones inválidas:

### Moneda
- "La cantidad de dinero no puede ser nula."
- "La cantidad de dinero no puede ser negativa."
- "El factor de multiplicación no puede ser negativo."
- "No se puede sumar una moneda nula."

### DetalleVenta
- "El producto de la partida no puede ser nulo."
- "La cantidad en la partida debe ser mayor que cero."
- "El precio unitario no puede ser nulo."
- "El precio unitario no puede ser negativo."

### Venta
- "No se puede agregar un detalle nulo a la venta."

## Pruebas

Se incluyen 35 pruebas JUnit 5 (7 de Producto + 10 de Moneda + 9 de DetalleVenta + 9 de Venta):

### ProductoTest (P01)
- Creación con datos válidos
- Rechazo de código/nombre nulo/vacío
- Rechazo de precio negativo
- Rechazo de existencia negativa

### MonedaTest
- Creación con cantidad válida
- Rechazo de cantidad nula/negativa
- Multiplicación correcta
- Suma correcta
- Igualdad por valor

### DetalleVentaTest
- Creación con datos válidos
- Cálculo correcto de subtotal
- Rechazo de producto nulo
- Rechazo de cantidad inválida (<= 0)
- Rechazo de precio inválido
- Igualdad por valor

### VentaTest
- Creación válida (vacía)
- Agregar un detalle válido
- Calcular total con uno o varios detalles
- Rechazo de detalle nulo
- Protección de encapsulamiento (lista no modificable)

## Cómo ejecutar

### Compilar

```bash
mvn clean compile
```

### Ejecutar pruebas

```bash
mvn clean test
```

### Verificar módulo P02 (si existe)

```bash
./scripts/verify-module.sh M02
```

### Con Docker

```bash
docker build -t tcsw-ventas .
docker run --rm tcsw-ventas
```

### Ejecutar Sonar (si está disponible)

```bash
mvn clean sonar:sonar -Dsonar.projectKey=tcsw-ventas
```

## Diagrama UML

Ubicación: `docs/P02_diagrama_uml.puml`

El diagrama muestra:
- Clases: Producto, Moneda, DetalleVenta, Venta
- Atributos principales de cada clase
- Operaciones públicas
- Relaciones de composición y asociación
- Notas explicativas

Relaciones:
- `Venta` "1" --> "*" `DetalleVenta` (composición)
- `DetalleVenta` "1" --> "1" `Producto` (referencia)
- `DetalleVenta` "1" --> "1" `Moneda` (precioUnitario)
- `Venta` calcula su total en `Moneda`

## Estructura del proyecto

```text
tcsw-ventas/
├── .git/
├── .gitignore
├── Dockerfile
├── README.md
├── pom.xml
├── docs/
│   └── P02_diagrama_uml.puml
├── scripts/
│   └── verify-module.sh
├── evidencia/
│   └── (capturas de ejecución y pruebas)
└── src/
    ├── main/java/com/tcsw/ventas/
    │   ├── Producto.java
    │   ├── Moneda.java
    │   ├── DetalleVenta.java
    │   └── Venta.java
    └── test/java/com/tcsw/ventas/
        ├── ProductoTest.java
        ├── MonedaTest.java
        ├── DetalleVentaTest.java
        └── VentaTest.java
```

## Reproducción

Sigue estos pasos para reproducir el entorno y ejecutar las pruebas:

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/Alessito54/tcsw-ventas.git
   cd tcsw-ventas
   ```

2. **Verificar Java y Maven**:
   ```bash
   java -version
   mvn -version
   ```

3. **Compilar el proyecto**:
   ```bash
   mvn clean compile
   ```

4. **Ejecutar todas las pruebas** (P01 + P02):
   ```bash
   mvn clean test
   ```

5. **Ejecutar verificación de módulo P02** (si existe):
   ```bash
   ./scripts/verify-module.sh M02
   ```

6. **Reproducir con Docker**:
   ```bash
   docker build -t tcsw-ventas .
   docker run --rm tcsw-ventas
   ```

El resultado esperado es que todas las pruebas pasen sin errores.

5. Ejecuta las pruebas automatizadas:

```bash
mvn clean test
```

6. Si deseas reproducir el entorno con Docker, construye la imagen desde la raíz del proyecto:

```bash
docker build -t tcsw-ventas .
```

7. Luego puedes ejecutar la imagen con:

```bash
docker run --rm tcsw-ventas
```

Esto ejecutará la validación Maven dentro del contenedor.
