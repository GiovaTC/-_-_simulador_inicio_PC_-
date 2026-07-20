# -_-_simulador_inicio_PC_- :.
# 💻 SimuladorInicioPC:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/aa8b5b07-ddf4-4313-a6b3-08f708cfa1c9" />   

<img width="2553" height="1040" alt="image" src="https://github.com/user-attachments/assets/df71dae2-8ff9-4f49-b42c-0224f5b3f507" />   

<img width="2544" height="1028" alt="image" src="https://github.com/user-attachments/assets/87a764d9-5239-4980-a572-b25f168deaeb" />   
   
```

Proyecto desarrollado en **Java 21** utilizando **IntelliJ IDEA**, **Consola**, **MySQL 8** y **JDBC**, que simula el proceso de arranque (*boot*) de un computador, e
l reconocimiento de dispositivos de entrada/salida y el almacenamiento de todos los eventos generados durante la ejecución en una base de datos MySQL.

---

# 📚 Objetivo

Desarrollar una aplicación de consola capaz de simular el inicio de un computador.

Durante la ejecución el programa deberá:

- ✅ Simular el BIOS/UEFI.
- ✅ Verificar la memoria RAM.
- ✅ Detectar el procesador.
- ✅ Detectar dispositivos de entrada.
- ✅ Detectar dispositivos de salida.
- ✅ Detectar dispositivos de almacenamiento.
- ✅ Cargar el sistema operativo.
- ✅ Mostrar todo el proceso en consola.
- ✅ Guardar cada evento en MySQL.

---

# 🛠 Tecnologías utilizadas

- Java 21
- IntelliJ IDEA
- JDBC
- MySQL 8
- Consola

---

# 📁 Estructura del proyecto

```text
SimuladorInicioPC
│
├── src
│   │
│   ├── config
│   │      ConexionBD.java
│   │
│   ├── modelo
│   │      Dispositivo.java
│   │      EventoInicio.java
│   │
│   ├── dao
│   │      DispositivoDAO.java
│   │      EventoDAO.java
│   │
│   ├── servicio
│   │      SimuladorInicio.java
│   │
│   └── Main.java
│
└── README.md
```

---

# 🧠 Variables necesarias

En un computador real, cada componente utiliza una cantidad determinada de memoria para almacenar su configuración.

En Java estas configuraciones pueden representarse mediante distintos tipos de datos.

| Variable | Tipo Java | Tamaño aproximado |
|----------|-----------|------------------:|
| id | int | 4 bytes |
| nombre | String | Variable |
| fabricante | String | Variable |
| tipo | String | Variable |
| memoriaRAM | long | 8 bytes |
| frecuenciaCPU | double | 8 bytes |
| nucleos | byte | 1 byte |
| activo | boolean | 1 byte |
| puertoUSB | short | 2 bytes |
| tiempoInicio | long | 8 bytes |
| estado | String | Variable |

---

# 🖥 Dispositivos simulados

## ⌨ Dispositivos de entrada

- Teclado USB
- Mouse USB
- Cámara Web
- Micrófono
- Escáner
- Lector Biométrico

---

## 🖥 Dispositivos de salida

- Monitor
- Impresora
- Parlantes
- Proyector

---

## 🔄 Dispositivos de Entrada / Salida

- Disco SSD
- Memoria USB
- Tarjeta de Red
- Pantalla táctil

---

# 🔄 Flujo del programa

```text
BIOS
   │
   ▼
Comprobación RAM
   │
   ▼
Procesador
   │
   ▼
Dispositivos USB
   │
   ▼
Entrada
   │
   ▼
Salida
   │
   ▼
Discos
   │
   ▼
Red
   │
   ▼
Sistema Operativo
   │
   ▼
Escritorio listo
```

---

# 🖥 Salida esperada en consola

```text
======================================
      SIMULADOR DE ARRANQUE DEL PC
======================================

Inicializando BIOS...

BIOS encontrada

Versión: 2.31

Memoria RAM detectada...

16384 MB

Procesador encontrado

Intel Core i7
8 Núcleos
3.80 GHz

Buscando dispositivos USB...

Teclado USB ............ OK
Mouse USB .............. OK
Micrófono .............. OK
WebCam ................. OK
Escáner ................ OK

Buscando dispositivos de salida...

Monitor Samsung ........ OK
Parlantes Logitech ..... OK
Impresora HP ........... OK

Detectando discos...

SSD Kingston 1TB ....... OK

Inicializando Red...

Tarjeta Ethernet ....... OK

Sistema Operativo cargado

Windows 11

Tiempo total de inicio

6 segundos

Todos los eventos fueron almacenados correctamente en MySQL.
```

---

# 🗄 Base de datos

## Crear la base de datos

```sql
CREATE DATABASE simulador_pc;

USE simulador_pc;
```

---

## Tabla `dispositivos`

```sql
CREATE TABLE dispositivos (

    id INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(80),

    tipo VARCHAR(30),

    fabricante VARCHAR(60),

    memoria INT,

    estado VARCHAR(30)

);
```

---

## Tabla `eventos`

```sql
CREATE TABLE eventos (

    id INT AUTO_INCREMENT PRIMARY KEY,

    descripcion VARCHAR(200),

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
```

---

# 💾 Información almacenada

## Tabla `dispositivos`

| id | nombre | tipo | fabricante | memoria | estado |
|---:|---------|------|-------------|---------:|--------|
| 1 | Teclado USB | Entrada | Logitech | 256 | Activo |
| 2 | Mouse USB | Entrada | Genius | 128 | Activo |
| 3 | Monitor | Salida | Samsung | 2048 | Activo |
| 4 | SSD Kingston | Entrada/Salida | Kingston | 4096 | Activo |

---

## Tabla `eventos`

| id | descripción |
|---:|-------------|
| 1 | Inicializando BIOS |
| 2 | RAM detectada |
| 3 | Procesador encontrado |
| 4 | Teclado detectado |
| 5 | Mouse detectado |
| 6 | Monitor detectado |
| 7 | SSD detectado |
| 8 | Sistema Operativo iniciado |

---

# ☕ Clases Java

## 📦 Dispositivo

### Atributos

```java
int id;
String nombre;
String tipo;
String fabricante;
int memoria;
String estado;
```

---

## 📦 EventoInicio

### Atributos

```java
int id;
String descripcion;
LocalDateTime fecha;
```

---

## 📦 ConexionBD

### Responsabilidades

- Abrir conexión.
- Cerrar conexión.
- Ejecutar instrucciones `INSERT`.
- Ejecutar consultas `SELECT`.

---

## 📦 DispositivoDAO

### Métodos

```text
guardar()

listar()

buscar()

eliminar()
```

---

## 📦 EventoDAO

### Métodos

```text
guardarEvento()

listarEventos()
```

---

## 📦 SimuladorInicio

### Métodos

```text
iniciarBIOS()

verificarRAM()

detectarCPU()

detectarEntradas()

detectarSalidas()

detectarDiscos()

iniciarSO()

mostrarResumen()
```

---

# 🧩 Conceptos de Programación Orientada a Objetos aplicados

- Clases
- Objetos
- Encapsulamiento
- Colecciones (`ArrayList`)
- JDBC
- DAO (*Data Access Object*)
- Separación por capas
- Simulación de procesos
- Manejo de excepciones
- Impresión en consola

---

# ☕ Conceptos de Java utilizados

- ArrayList
- for-each
- Thread.sleep()
- LocalDateTime
- PreparedStatement
- ResultSet
- Connection
- Scanner
- try-with-resources

---

# 🚀 Mejoras opcionales

Este proyecto puede ampliarse con funcionalidades adicionales, por ejemplo:

- Simular porcentajes de avance del arranque (0 % a 100 %).
- Registrar el tiempo empleado por cada etapa utilizando `System.currentTimeMillis()` o `Instant`.
- Simular errores de hardware (RAM defectuosa, disco no encontrado, teclado desconectado, etc.).
- Leer la configuración de dispositivos desde MySQL al iniciar la simulación.
- Generar un reporte del arranque en formato `.txt` o `.pdf`.
- Incorporar una interfaz gráfica con Swing o JavaFX que represente visualmente el proceso de inicio.
- Mostrar estadísticas históricas de los arranques almacenados en la base de datos.

---

# 🎯 Competencias desarrolladas

Al completar este proyecto se practican los siguientes conocimientos:

- Programación Orientada a Objetos.
- Modelado de entidades.
- Arquitectura por capas.
- Persistencia de datos mediante JDBC.
- Manejo de MySQL.
- Simulación de procesos del sistema.
- Uso de colecciones (`ArrayList`).
- Manejo de fechas con `LocalDateTime`.
- Programación estructurada.
- Manejo de excepciones.
- Buenas prácticas de organización de proyectos Java.

---

# 📌 Resultado esperado

Al ejecutar el proyecto, el usuario podrá observar paso a paso el proceso de arranque de un computador desde la consola, 
incluyendo la detección del BIOS, memoria RAM, procesador, dispositivos de entrada, salida y almacenamiento.

Cada evento generado será registrado automáticamente en una base de datos MySQL mediante JDBC, permitiendo 
consultar posteriormente el historial completo de los procesos de inicio.

Este proyecto integra **Programación Orientada a Objetos**, **simulación de procesos**, **acceso a bases de datos mediante JDBC** y 
**manejo de estructuras de datos**, constituyendo un ejercicio práctico para comprender tanto el ciclo de arranque de un computador como el desarrollo de aplicaciones Java conectadas a MySQL.
:. . / .
