package modelo;

import java.util.Objects;

/**
 * ==========================================================
 * Clase: Dispositivo
 * Proyecto: SimuladorInicioPC
 * Java: 21
 * ==========================================================
 *
 * Representa un dispositivo detectado durante el proceso
 * de arranque del computador .
 *
 * Puede ser un dispositivo de:
 *  - Entrada
 *  - Salida
 *  - Entrada/Salida
 *
 */

public class Dispositivo {

    //==========================================
    // Atributos
    //==========================================

    private int id;
    private String nombre;
    private String tipo;
    private String fabricante;
    private int memoria;
    private String estado;

    //==========================================
    // Constructor vacío
    //==========================================
    public Dispositivo() {

    }

    //==========================================
    // Constructor con parámetros
    //==========================================
  public Dispositivo(int id,
                     String nombre,
                     String tipo,
                     String fabricante,
                     int memoria,
                     String estado) {

      this.id = id;
      this.nombre = nombre;
      this.tipo = tipo;
      this.fabricante = fabricante;
      this.memoria = memoria;
      this.estado = estado;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //==========================================
    // Métodos auxiliares
    //==========================================

    /**
     * Indica si el dispositivo se encuentra activo .
     *
     * @return true si el estado es "Activo" .
     */
    public boolean estaActivo() {

        return estado != null &&
                estado.equalsIgnoreCase("Activo");
    }

    /**
     * Muestra la información del dispositivo.
     */
    public void mostrarInformacion() {

        System.out.println("----------------------------------------");
        System.out.println("ID           : " + id);
        System.out.println("Nombre       : " + nombre);
        System.out.println("Tipo         : " + tipo);
        System.out.println("Fabricante   : " + fabricante);
        System.out.println("Memoria      : " + memoria + " MB");
        System.out.println("Estado       : " + estado);
        System.out.println("----------------------------------------");
    }

    //==========================================
    // toString()
    //==========================================

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", memoria=" + memoria +
                ", estado='" + estado + '\'' +
                '}';
    }

    //==========================================
    // equals()
    //==========================================

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Dispositivo that)) return false;

        return id == that.id;
    }

    //==========================================
    // hashCode()
    //==========================================

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
