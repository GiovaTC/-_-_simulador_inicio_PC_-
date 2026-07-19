package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un evento ocurrido durante el proceso
 * de arranque del computador .
 *
 * Ejemplos:
 * - Inicializando BIOS
 * - Verificando Memoria RAM
 * - Detectando Procesador
 * - Detectando Teclado
 * - Cargando Sistema Operativo
 *
 * @giovanny a tapiero c .
 * @version 1.0
 */

public class EventoInicio {

    private int id;
    private String descripcion;
    private LocalDateTime fechaHora;

    /**
     * Constructor vacío .
     */
    public EventoInicio() {
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Constructor sin ID.
     *
     * @param descripcion Descripción del evento.
     */
    public EventoInicio(String descripcion) {
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Constructor completo .
     *
     * @param id Identificador del evento.
     * @param descripcion Descripción.
     * @param fechaHora Fecha y hora.
     */
    public EventoInicio(int id, String descripcion, LocalDateTime fechaHora) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    //====================================================
    // GETTERS
    //====================================================
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    //====================================================
    // SETTERS
    //====================================================

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve la fecha con formato legible.
     *
     * @return Fecha formateada.
     */

    public String getFechaFormateada() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return fechaHora.format(formato);
    }

    /**
     * Imprime el evento en consola .
     */
    public void mostrarEvento() {

        System.out.println("--------------------------------------------");
        System.out.println("ID          : " + id);
        System.out.println("Evento      : " + descripcion);
        System.out.println("Fecha/Hora  : " + getFechaFormateada());
        System.out.println("--------------------------------------------");
    }

    @Override
    public String toString() {

        return "EventoInicio{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", fechaHora=" + getFechaFormateada() +
                '}';
    }
}
