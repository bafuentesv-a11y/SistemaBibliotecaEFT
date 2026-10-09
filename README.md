# Sistema de Biblioteca

Sistema de gestión de biblioteca desarrollado en Java como proyecto académico.

El sistema permite administrar libros, estudiantes, préstamos y devoluciones,
incorporando persistencia de datos mediante MySQL y una interfaz gráfica
desarrollada con Java Swing.

## Tecnologías utilizadas

- Java
- Java Swing
- MySQL
- JDBC
- Maven
- IntelliJ IDEA
- Git y GitHub

## Arquitectura

El proyecto utiliza una arquitectura basada en el patrón MVC:

- **Model:** contiene las entidades del sistema.
- **View:** contiene las interfaces gráficas desarrolladas con JFrame.
- **Controller:** gestiona los eventos y comunica las vistas con la lógica
  de acceso a datos.

Además, se utiliza el patrón DAO para separar el acceso a la base de datos
de la lógica de la aplicación.

## Principales funcionalidades

### Autenticación

El sistema permite autenticar usuarios registrados en la base de datos.

### Gestión de libros

Permite:

- Agregar libros.
- Buscar libros.
- Actualizar información.
- Eliminar libros.
- Listar libros.

### Gestión de estudiantes

Permite:

- Agregar estudiantes.
- Buscar estudiantes.
- Actualizar información.
- Eliminar estudiantes.
- Listar estudiantes.

### Gestión de préstamos

Permite:

- Registrar préstamos.
- Registrar devoluciones.
- Consultar préstamos.
- Actualizar el stock de libros.
- Calcular la fecha de vencimiento.
- Identificar préstamos atrasados.

### Reportes

El sistema incorpora reportes de:

- Libros más prestados.
- Historial de préstamos por estudiante.
- Libros actualmente en préstamo.

## Programación Orientada a Objetos

El proyecto incorpora conceptos de programación orientada a objetos,
entre ellos:

- Abstracción mediante la clase `Usuario`.
- Herencia mediante las clases `Estudiante` y `Bibliotecario`.
- Polimorfismo mediante la implementación del método `getRol()`.
- Interfaces mediante `Prestable`.
- Encapsulamiento mediante atributos privados y métodos getter/setter.

## Patrones de diseño

### Singleton

La clase `DatabaseConnection` utiliza el patrón Singleton para mantener
una única instancia de la conexión utilizada por los DAO.

### DAO

Se implementan objetos de acceso a datos para separar la persistencia
de las entidades del resto de la aplicación.

Entre ellos:

- `CategoriaDAO`
- `EstudianteDAO`
- `LibroDAO`
- `PrestamoDAO`
- `UsuarioDAO`

## Concurrencia y sincronización

El registro de préstamos utiliza un hilo mediante la clase `PrestamoTask`,
que implementa `Runnable`.

La modificación del stock se gestiona mediante métodos sincronizados,
permitiendo controlar el acceso concurrente a este recurso.

## Base de datos

El sistema utiliza una base de datos MySQL llamada:

```text
biblioteca
