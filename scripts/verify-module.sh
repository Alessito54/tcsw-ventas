#!/bin/bash

# Script de verificación para módulo M02 (Venta en memoria)
# Verifica que las clases, pruebas y compilación estén correctas

set -e

MODULE="M02"
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

echo "================================================"
echo "Verificación de $MODULE - Venta en memoria"
echo "================================================"
echo ""

# Verificar que estamos en la raíz del proyecto
if [ ! -f "$PROJECT_ROOT/pom.xml" ]; then
    echo "ERROR: No se encontró pom.xml. Ejecuta este script desde la raíz del proyecto."
    exit 1
fi

echo "✓ Raíz del proyecto identificada: $PROJECT_ROOT"
echo ""

# Verificar existencia de clases principales de P02
echo "Verificando clases principales..."
CLASSES=(
    "src/main/java/com/tcsw/ventas/Moneda.java"
    "src/main/java/com/tcsw/ventas/DetalleVenta.java"
    "src/main/java/com/tcsw/ventas/Venta.java"
)

for class in "${CLASSES[@]}"; do
    if [ -f "$PROJECT_ROOT/$class" ]; then
        echo "  ✓ Encontrada: $class"
    else
        echo "  ✗ NO ENCONTRADA: $class"
        exit 1
    fi
done
echo ""

# Verificar existencia de pruebas
echo "Verificando pruebas..."
TESTS=(
    "src/test/java/com/tcsw/ventas/MonedaTest.java"
    "src/test/java/com/tcsw/ventas/DetalleVentaTest.java"
    "src/test/java/com/tcsw/ventas/VentaTest.java"
)

for test in "${TESTS[@]}"; do
    if [ -f "$PROJECT_ROOT/$test" ]; then
        echo "  ✓ Encontrada: $test"
    else
        echo "  ✗ NO ENCONTRADA: $test"
        exit 1
    fi
done
echo ""

# Verificar que P01 sigue existiendo
echo "Verificando que P01 se mantiene..."
P01_CLASSES=(
    "src/main/java/com/tcsw/ventas/Producto.java"
    "src/test/java/com/tcsw/ventas/ProductoTest.java"
)

for class in "${P01_CLASSES[@]}"; do
    if [ -f "$PROJECT_ROOT/$class" ]; then
        echo "  ✓ P01 intacto: $class"
    else
        echo "  ✗ ERROR: P01 fue modificado o eliminado: $class"
        exit 1
    fi
done
echo ""

# Verificar diagrama UML
echo "Verificando documentación..."
if [ -f "$PROJECT_ROOT/docs/P02_diagrama_uml.puml" ]; then
    echo "  ✓ Diagrama UML encontrado: docs/P02_diagrama_uml.puml"
else
    echo "  ✗ NO ENCONTRADO: docs/P02_diagrama_uml.puml"
    exit 1
fi
echo ""

# Compilar y ejecutar pruebas
echo "Compilando y ejecutando pruebas..."
cd "$PROJECT_ROOT"

if mvn clean test -q; then
    echo "  ✓ Compilación y pruebas exitosas"
else
    echo "  ✗ Error en compilación o pruebas"
    exit 1
fi
echo ""

# Verificar que todas las pruebas pasaron
echo "Verificando resultados de pruebas..."
TEST_RESULTS=$(mvn test 2>&1 | grep "Tests run:")
if echo "$TEST_RESULTS" | grep -q "Failures: 0"; then
    echo "  ✓ Todas las pruebas pasaron"
    echo "  $TEST_RESULTS"
else
    echo "  ✗ Algunas pruebas fallaron"
    exit 1
fi
echo ""

# Verificar que los archivos de salida existen
if [ -d "$PROJECT_ROOT/target/classes/com/tcsw/ventas" ]; then
    COMPILED_CLASSES=$(ls -1 "$PROJECT_ROOT/target/classes/com/tcsw/ventas/"*.class | wc -l)
    echo "  ✓ Clases compiladas: $COMPILED_CLASSES archivos .class"
else
    echo "  ✗ No se encontraron clases compiladas"
    exit 1
fi
echo ""

echo "================================================"
echo "✓ VERIFICACIÓN DE $MODULE EXITOSA"
echo "================================================"
echo ""
echo "Resumen:"
echo "  - Clases principales: ✓ 3 (Moneda, DetalleVenta, Venta)"
echo "  - Pruebas: ✓ 3 test classes"
echo "  - P01: ✓ Intacto"
echo "  - Documentación: ✓ Diagrama UML"
echo "  - Compilación: ✓ Exitosa"
echo "  - Pruebas automatizadas: ✓ Todas pasadas"
echo ""
echo "El módulo $MODULE está completo y funcional."
