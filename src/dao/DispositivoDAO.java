package dao;

import config.ConexionBD;
import modelo.Dispositivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO {

    /**
     *  guarda un dispositivo en la base de datos!
     */
    public void guardar(Dispositivo dispositivo) {

        String sql = """
                INSERT INTO dispositivos
                (nombre, tipo, fabricante, memoria, estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dispositivo.getNombre());
            ps.setString(2, dispositivo.getTipo());
            ps.setString(3, dispositivo.getFabricante());
            ps.setInt(4, dispositivo.getMemoria());
            ps.setString(5, dispositivo.getEstado());

            ps.executeUpdate();

            System.out.println("[MYSQL] Dispositivo almacenado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar dispositivo.");
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los dispositivos registrados .
     */
    public List<Dispositivo> listar() {

        List<Dispositivo> lista = new ArrayList<>();

        String sql = """
                SELECT id,
                        nombre,
                        tipo,
                        fabricante,
                        memoria,
                        estado
                FROM dispositivos
                ORDER BY id
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while ( rs.next()) {

                Dispositivo dispositivo = new Dispositivo();

                dispositivo.setId(rs.getInt("id"));
                dispositivo.setNombre(rs.getString("nombre"));
                dispositivo.setTipo(rs.getString("tipo"));
                dispositivo.setFabricante(rs.getString("fabricante"));
                dispositivo.setMemoria(rs.getInt("memoria"));
                dispositivo.setEstado(rs.getString("estado"));

                lista.add(dispositivo);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar dispositivos.");
            e.printStackTrace();

        }

        return lista;
    }

    /**
     * Busca un dispositivo por ID.
     */
    public Dispositivo buscarPorId(int id) {

        String sql = """
                SELECT *
                FROM dispositivos
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Dispositivo dispositivo = new Dispositivo();

                dispositivo.setId(rs.getInt("id"));
                dispositivo.setNombre(rs.getString("nombre"));
                dispositivo.setTipo(rs.getString("tipo"));
                dispositivo.setFabricante(rs.getString("fabricante"));
                dispositivo.setMemoria(rs.getInt("memoria"));
                dispositivo.setEstado(rs.getString("estado"));

                return dispositivo;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar dispositivo.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Actualiza un dispositivo.
     */
    public boolean actualizar(Dispositivo dispositivo) {

        String sql = """
                UPDATE dispositivos
                SET nombre = ?,
                    tipo = ?,
                    fabricante = ?,
                    memoria = ?,
                    estado = ?
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dispositivo.getNombre());
            ps.setString(2, dispositivo.getTipo());
            ps.setString(3, dispositivo.getFabricante());
            ps.setInt(4, dispositivo.getMemoria());
            ps.setString(5, dispositivo.getEstado());
            ps.setInt(6, dispositivo.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar dispositivo.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina un dispositivo.
     */
    public boolean eliminar(int id) {

        String sql = """
                DELETE FROM dispositivos
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar dispositivo.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Imprime todos los dispositivos en consola.
     */
    public void mostrarDispositivos() {

        List<Dispositivo> lista = listar();

        System.out.println();
        System.out.println("==============================================");
        System.out.println("     DISPOSITIVOS REGISTRADOS EN MYSQL");
        System.out.println("==============================================");

        if (lista.isEmpty()) {
            System.out.println("No existen dispositivos registrados.");
            return;
        }

        for (Dispositivo d : lista) {

            System.out.println("----------------------------------------------");
            System.out.println("ID          : " + d.getId());
            System.out.println("Nombre      : " + d.getNombre());
            System.out.println("Tipo        : " + d.getTipo());
            System.out.println("Fabricante  : " + d.getFabricante());
            System.out.println("Memoria     : " + d.getMemoria() + " KB");
            System.out.println("Estado      : " + d.getEstado());
        }

        System.out.println("----------------------------------------------");
    }   
}
