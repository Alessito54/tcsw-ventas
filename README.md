# P01. Ambiente reproducible y Producto

## Objetivo

Este proyecto crea un ambiente reproducible con Java 11 y Maven para desarrollar una primera entidad de dominio: `Producto`. El propósito de esta etapa es preparar una base simple, clara y extensible para futuras actividades académicas.

## Tecnologías y versiones

- Java 11
- Maven 3.x
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
git clone <url-del-repositorio>
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

## Estructura del proyecto

```text
tcsw-ventas/
├── pom.xml
├── README.md
├── Dockerfile
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── tcsw/
    │               └── ventas/
    │                   └── Producto.java
    └── test/
        └── java/
            └── com/
                └── tcsw/
                    └── ventas/
                        └── ProductoTest.java
```

## Reproducción

Sigue estos pasos para reproducir el entorno y ejecutar las pruebas sin depender de una computadora personal:

1. Clona el repositorio con Git.
2. Asegúrate de tener Java 11, Maven, Git y Docker instalados.
3. Abre la carpeta del proyecto en Visual Studio Code.
4. Ejecuta la compilación con Maven:

```bash
mvn clean compile
```

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
