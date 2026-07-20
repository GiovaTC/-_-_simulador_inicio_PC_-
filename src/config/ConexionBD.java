package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ==========================================================
 * Clase: ConexionBD
 * Proyecto: SimuladorInicioPC
 * Java: 21
 * Base de Datos: MySQL 8
 * ==========================================================
 *
 * Responsabilidades:
 * - Obtener una conexión con MySQL.
 * - Cerrar una conexión.
 * - Ejecutar INSERT, UPDATE y DELETE.
 * - Ejecutar consultas SELECT.
 */
public class ConexionBD {

    // ======================================================
    // Datos de conexión
    // ======================================================

    private static final String URL =
            "jdbc:mysql://localhost:3306/simulador_pc?serverTimezone=UTC";

    private static final String USUARIO = "root";

    private static final String PASSWORD = "Tapiero123";

    /**
     * Constructor privado para evitar instancias.
     */
    private ConexionBD() {
    }

    // ======================================================
    // OBTENER CONEXIÓN
    // ======================================================

    /**
     * Obtiene una conexión con MySQL.
     *
     * @return Connection
     */
    public static Connection obtenerConexion() {

        try {

            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );

            System.out.println("[MYSQL] Conexión establecida correctamente.");

            return conexion;

        } catch (SQLException e) {

            System.out.println("[MYSQL] Error al conectar.");

            e.printStackTrace();

            return null;
        }

    }

    // ======================================================
    // CERRAR CONEXIÓN
    // ======================================================

    /**
     * Cierra una conexión.
     *
     * @param conexion Conexión a cerrar.
     */
    public static void cerrarConexion(Connection conexion) {

        try {

            if (conexion != null && !conexion.isClosed()) {

                conexion.close();

                System.out.println("[MYSQL] Conexión cerrada.");

            }

        } catch (SQLException e) {

            System.out.println("Error al cerrar la conexión.");

            e.printStackTrace();

        }

    }

    // ======================================================
    // INSERT - UPDATE - DELETE
    // ======================================================

    /**
     * Ejecuta una sentencia INSERT, UPDATE o DELETE.
     *
     * @param sql SQL a ejecutar.
     * @param parametros Parámetros del PreparedStatement.
     * @return true si la operación fue exitosa.
     */
    public static boolean ejecutarUpdate(String sql, Object... parametros) {

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            for (int i = 0; i < parametros.length; i++) {

                ps.setObject(i + 1, parametros[i]);

            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al ejecutar UPDATE.");

            e.printStackTrace();

            return false;

        }

    }

    // ======================================================
    // SELECT
    // ======================================================

    /**
     * Ejecuta una consulta SELECT.
     *
     * IMPORTANTE:
     * El ResultSet depende de la conexión, por lo que el método
     * NO debe cerrarla automáticamente.
     *
     * @param conexion Conexión abierta.
     * @param sql Consulta SQL.
     * @param parametros Parámetros.
     * @return ResultSet.
     * @throws SQLException Error SQL.
     */
    public static ResultSet ejecutarSelect(
            Connection conexion,
            String sql,
            Object... parametros) throws SQLException {

        PreparedStatement ps = conexion.prepareStatement(sql);

        for (int i = 0; i < parametros.length; i++) {

            ps.setObject(i + 1, parametros[i]);

        }

        return ps.executeQuery();
    }
}