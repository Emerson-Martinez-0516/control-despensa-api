# Actividad: API REST Control de Despensa

## Descripción del problema
API REST básica para consultar y analizar los productos almacenados en la despensa de un hogar. Los datos se mantienen en memoria y solo se permiten operaciones de consulta (GET).

## Tecnologías utilizadas
- Java 17
- Spring Boot
- Spring Web
- Maven

## Requisitos para ejecutar el proyecto
- JDK 17 o superior
- Maven 
- IntelliJ IDEA 

## Estructura principal
```
control-despensa-api/
|── src/main/java/com/estudiante/despensa/
|   |── ControlDespensaApiApplication.java
|   |── controller/ProductoController.java
|   |── model/
|       |── Producto.java
|       |── ResumenInventario.java
|── evidencias/
|── pom.xml
|── README.md
```

## Explicación de las clases
- **ControlDespensaApiApplication**: clase principal que inicia la aplicación Spring Boot.
- **Producto**: representa un producto de la despensa (id, nombre, categoría, cantidad, precio unitario). Valida sus datos en el constructor y calcula el subtotal.
- **ResumenInventario**: representa el resumen general (cantidad de productos, total de unidades y valor total).
- **ProductoController**: expone los endpoints REST bajo `/api/productos` y mantiene la lista de productos en memoria.

## Tabla de endpoints
| Operación | Método | Ruta | Estado esperado |
|---|---|---|---|
| Listar productos | GET | /api/productos | 200 |
| Buscar por identificador | GET | /api/productos/{id} | 200 o 404 |
| Buscar por categoría | GET | /api/productos/categoria/{categoria} | 200 |
| Consultar stock bajo | GET | /api/productos/stock-bajo | 200 |
| Consultar producto de mayor valor | GET | /api/productos/mayor-valor | 200 |
| Obtener resumen | GET | /api/productos/resumen | 200 |

## Instrucciones para ejecutar la aplicación
1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Ejecutar la clase `ControlDespensaApiApplication`.
4. La API estará disponible en `http://localhost:8080`.

## Ejemplos de respuestas JSON

GET /api/productos/3
```json
{
  "id": 3,
  "nombre": "Arroz",
  "categoria": "Granos",
  "cantidad": 4,
  "precioUnitario": 8.5
}
```

GET /api/productos/resumen
```json
{
  "cantidadProductos": 6,
  "totalUnidades": 25,
  "valorTotal": 297.25
}
```

## Autor
Nombre completo: Emerson Omar Martínez Porras
Carné: 9941-23-764