package dao;

import config.ConexionBD;
import modelo.EventoInicio;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    /**
     * Guarda un evento en la base de datos .
     *
     * @param evento Evento a almacenar.
     */
    public void guardarEvento(EventoInicio evento) {

        String sql = """
                INSERT INTO eventos
                (descripcion, fecha)
                VALUES (?, ?)
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, evento.getDescripcion());
            ps.setTimestamp(2, Timestamp.valueOf(evento.getFechaHora()));

            ps.executeUpdate();

            System.out.println("[MYSQL] Evento almacenado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar el evento.");
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los eventos registrados.
     *
     * @return Lista de eventos.
     */
    public List<EventoInicio> listarEventos() {

        List<EventoInicio> lista = new ArrayList<>();

        String sql = """
                SELECT id,
                       descripcion,
                       fecha
                FROM eventos
                ORDER BY id
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                EventoInicio evento = new EventoInicio();

                evento.setId(rs.getInt("id"));
                evento.setDescripcion(rs.getString("descripcion"));

                Timestamp ts = rs.getTimestamp("fecha");

                if (ts != null) {
                    evento.setFechaHora(ts.toLocalDateTime());
                }

                lista.add(evento);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar eventos.");
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Busca un evento por su ID.
     *
     * @param id Identificador.
     * @return Evento encontrado o null.
     */
    public EventoInicio buscarPorId(int id) {

        String sql = """
                SELECT *
                FROM eventos
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                EventoInicio evento = new EventoInicio();

                evento.setId(rs.getInt("id"));
                evento.setDescripcion(rs.getString("descripcion"));

                Timestamp ts = rs.getTimestamp("fecha");

                if (ts != null) {
                    evento.setFechaHora(ts.toLocalDateTime());
                }

                return evento;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el evento.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Elimina un evento.
     *
     * @param id Identificador.
     * @return true si fue eliminado.
     */
    public boolean eliminarEvento(int id) {

        String sql = """
                DELETE FROM eventos
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el evento.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina todos los eventos.
     *
     * @return true si la operación fue exitosa.
     */
    public boolean eliminarTodos() {

        String sql = "DELETE FROM eventos";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el historial.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Imprime todos los eventos registrados.
     */
    public void mostrarEventos() {

        List<EventoInicio> lista = listarEventos();

        System.out.println();
        System.out.println("========================================================");
        System.out.println("          HISTORIAL DEL PROCESO DE ARRANQUE");
        System.out.println("========================================================");

        if (lista.isEmpty()) {

            System.out.println("No existen eventos registrados.");
            return;
        }

        for (EventoInicio evento : lista) {

            System.out.println("--------------------------------------------");
            System.out.println("ID          : " + evento.getId());
            System.out.println("Evento      : " + evento.getDescripcion());
            System.out.println("Fecha/Hora  : " + evento.getFechaFormateada());
        }

        System.out.println("--------------------------------------------");
    }

    /**
     * Devuelve la cantidad de eventos almacenados.
     *
     * @return Número de eventos.
     */
    public int contarEventos() {

        String sql = "SELECT COUNT(*) FROM eventos";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error al contar eventos.");
            e.printStackTrace();
        }

        return 0;
    }
}