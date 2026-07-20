package servicio;

import dao.DispositivoDAO;
import dao.EventoDAO;
import modelo.Dispositivo;
import modelo.EventoInicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * Clase: SimuladorInicio
 * Proyecto: SimuladorInicioPC
 * Java 21
 * ==========================================================
 *
 * Simula el proceso de arranque de un computador,
 * mostrando cada etapa en consola y almacenando
 * dispositivos y eventos en MySQL  .
 *
 */
public class SimuladorInicio {

    private final DispositivoDAO dispositivoDAO;
    private final EventoDAO eventoDAO;

    private final List<Dispositivo> dispositivos;

    private long tiempoInicio;
    private long tiempoFin;

    //==========================================================
    // Constructor
    //==========================================================

    public SimuladorInicio() {

        this.dispositivoDAO = new DispositivoDAO();
        this.eventoDAO = new EventoDAO();

        this.dispositivos = new ArrayList<>();
    }

    //==========================================================
    // Inicio de la simulación
    //==========================================================

    public void iniciarSimulacion() {

        tiempoInicio = System.currentTimeMillis();

        System.out.println();
        System.out.println("======================================");
        System.out.println("      SIMULADOR DE ARRANQUE DEL PC");
        System.out.println("======================================");
        System.out.println();

        iniciarBIOS();
        verificarRAM();
        detectarCPU();
        detectarEntradas();
        detectarSalidas();
        detectarDiscos();
        iniciarSO();

        tiempoFin = System.currentTimeMillis();

        mostrarResumen();

        //==========================================================
        // BIOS
        //==========================================================


    }

    public void iniciarBIOS() {

        imprimir("inicializando BIOS...");

        guardarEvento("inicializando BIOS..");

        pausa();

        imprimir("BIOS encontrada");

        imprimir("VERSION: 2,31");

        guardarEvento("BIOS encontrada");

        pausa();

    }

    //==========================================================
    // RAM
    //==========================================================

    public void verificarRAM() {

        imprimir("Comprobando Memoria RAM...");

        guardarEvento("RAM detectada");

        pausa();

        imprimir("16384 MB");

        pausa();

    }

    //==========================================================
    // CPU
    //==========================================================

    public void detectarCPU() {

        imprimir("Procesador encontrado");

        imprimir("Intel Core i7");

        imprimir("8 Núcleos");

        imprimir("3.80 GHz");

        guardarEvento("Procesador encontrado");

        pausa();

    }

    //==========================================================
    // Dispositivos de Entrada
    //==========================================================

    public void detectarEntradas() {

        imprimir("Buscando dispositivos de entrada...");

        registrarDispositivo(
                "Teclado USB",
                "Entrada",
                "Logitech",
                256,
                "Activo"
        );

        registrarDispositivo(
                "Mouse USB",
                "Entrada",
                "Genius",
                128,
                "Activo"
        );

        registrarDispositivo(
                "Micrófono",
                "Entrada",
                "HyperX",
                256,
                "Activo"
        );

        registrarDispositivo(
                "WebCam",
                "Entrada",
                "Logitech",
                512,
                "Activo"
        );

        registrarDispositivo(
                "Escáner",
                "Entrada",
                "Canon",
                1024,
                "Activo"
        );

    }

    //==========================================================
    // Dispositivos de Salida
    //==========================================================

    public void detectarSalidas() {

        imprimir("Buscando dispositivos de salida...");

        registrarDispositivo(
                "Monitor Samsung",
                "Salida",
                "Samsung",
                2048,
                "Activo"
        );

        registrarDispositivo(
                "Parlantes Logitech",
                "Salida",
                "Logitech",
                256,
                "Activo"
        );

        registrarDispositivo(
                "Impresora HP",
                "Salida",
                "HP",
                1024,
                "Activo"
        );

    }

    //==========================================================
    // Discos
    //==========================================================

    public void detectarDiscos() {

        imprimir("Detectando discos...");

        registrarDispositivo(
                "SSD Kingston 1TB",
                "Entrada/Salida",
                "Kingston",
                4096,
                "Activo"
        );

        pausa();

    }

    //==========================================================
    // Sistema Operativo
    //==========================================================

    public void iniciarSO() {

        imprimir("Inicializando Sistema Operativo...");

        guardarEvento("Sistema Operativo iniciado");

        pausa();

        imprimir("Windows 11");

        pausa();

    }

    //==========================================================
    // Resumen
    //==========================================================

    public void mostrarResumen() {

        long segundos = (tiempoFin - tiempoInicio) / 1000;

        System.out.println();
        System.out.println("======================================");
        System.out.println("RESUMEN DEL ARRANQUE");
        System.out.println("======================================");

        System.out.println("Dispositivos detectados : " + dispositivos.size());

        System.out.println("Tiempo de inicio        : " + segundos + " segundos");

        System.out.println();

        System.out.println("Todos los eventos fueron almacenados correctamente en MySQL.");

        System.out.println("======================================");

    }

    //==========================================================
    // Registrar dispositivo
    //==========================================================

    private void registrarDispositivo(String nombre,
                                      String tipo,
                                      String fabricante,
                                      int memoria,
                                      String estado) {

        Dispositivo dispositivo = new Dispositivo();

        dispositivo.setNombre(nombre);
        dispositivo.setTipo(tipo);
        dispositivo.setFabricante(fabricante);
        dispositivo.setMemoria(memoria);
        dispositivo.setEstado(estado);

        dispositivos.add(dispositivo);

        dispositivoDAO.guardar(dispositivo);

        guardarEvento(nombre + " detectado");

        System.out.printf("%-30s OK%n", nombre);

        pausa();

    }

    //==========================================================
    // Registrar evento
    //==========================================================

    private void guardarEvento(String descripcion) {

        EventoInicio evento = new EventoInicio();

        evento.setDescripcion(descripcion);
        evento.setFecha(LocalDateTime.now());

        eventoDAO.guardarEvento(evento);

    }

    //==========================================================
    // Imprimir texto
    //==========================================================

    private void imprimir(String mensaje) {

        System.out.println(mensaje);

    }

    //==========================================================
    // Simulación del tiempo
    //==========================================================

    private void pausa() {

        try {

            Thread.sleep(700);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }
    }
}
