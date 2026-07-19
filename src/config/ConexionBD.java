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
 * Autor: giovanny a tapiero c :. . / .
 * Java: 21
 * Base de Datos: MySQL 8
 * ==========================================================
 *
 * Responsabilidades:
 *  - Abrir conexión con MySQL.
 *  - Cerrar conexión.
 *  - Ejecutar instrucciones INSERT, UPDATE y DELETE.
 *  - Ejecutar consultas SELECT.
 *
 */

public class ConexionBD {

    // ==========================
    // Datos de conexion
    // ==========================
    private static final String URL =
            "jdbc:mysql://localhost:3306/simulador_pc?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Tapiero123";

    private Connection conexion;

    public static Connection obtenerConexion() {
        return null;
    }

    /**
     *  abre la conexion con la bd .
     */

    public Connection abrirConexion() {
        try {
            if(conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(
                        URL,
                        USUARIO,
                        PASSWORD
                );

                System.out.println("conexion establecida con mySQL. ");
            }
        } catch (SQLException e) {

            System.out.println("error al conectar con mysql!");

            e.printStackTrace();
        }

        return conexion;
    }

    /**
     *  cierra la conexion .
     */
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();

                System.out.println("conexion cerrada!");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }

    /**
     * Ejecuta INSERT, UPDATE o DELETE .
     *
     * @param sql Sentencia SQL .
     * @param parametros Valores para el PreparedStatement.
     * @return true si la operación fue exitosa.
     */
    public boolean ejecutarInsert(String sql, Object... parametros) {

        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            for (int i = 0; i < parametros.length; i++) {

                ps.setObject(i + 1, parametros[i]);
            }

            int filas = ps.executeUpdate();

            ps.close();
            return filas > 0;

        } catch (SQLException e) {

            System.out.println("Error al ejecutar INSERT.");
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Ejecuta una consulta SELECT .
     *
     * @param sql Sentencia SQL.
     * @param parametros Parámetros del PreparedStatement.
     * @return ResultSet con los datos encontrados.
     */

    public ResultSet ejecutarSelect(String sql, Object... parametros) {

        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            for (int i = 0; i < parametros.length; i++) {

                ps.setObject(i + 1, parametros[i]);
            }

            return ps.executeQuery();

        } catch (SQLException e) {

            System.out.println("Error al ejecutar SELECT.");

            e.printStackTrace();
        }

        return null;
    }

    /**
     * Devuelve la conexión activa.
     */
    public Connection getConexion() {

        return conexion;
    }
}
