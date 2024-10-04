package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sqlSelect = "SELECT * FROM cliente ORDER BY idcliente";
        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        // ? parametros posicionales
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        // ? parametros posicionales
        String sqlCreate = "INSERT INTO cliente(nombre, apellido, membresia) " + " VALUES(?, ?, ?)";
        try {
            ps = con.prepareStatement(sqlCreate);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente" + e.getMessage());
        } finally {

            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        // ? parametros posicionales
        String sqlUpdate = "UPDATE cliente SET nombre=? , apellido=? , membresia=? " + " WHERE idcliente=?";
        try {

            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sqlDelete = " DELETE FROM cliente WHERE idcliente = ?";
        try {
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();
/*
        //Listar clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);
*/
        /*
        // Buscar por id
        Cliente cliente1 = new Cliente(2);
        System.out.println("Cliente antes de la busqueda: " + cliente1);
        boolean encontrado = clienteDao.buscarClientePorId(cliente1);
        if (encontrado)
            System.out.println("Cliente encontrado: " + cliente1);
        else
            System.out.println("No se encontro cliente: " + cliente1.getId());
*/
        //Agregar cliente - Crear- INSERT TO
      /*  Cliente nuevoCliente = new Cliente("Danna", "Vega", 60023);
        boolean agregado = clienteDao.agregarCliente(nuevoCliente);
        if (agregado)
            System.out.println("Cliente agregado: " + nuevoCliente);
        else
            System.out.println("No se agregi cliente: " + nuevoCliente);

        //Listar clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);

        */
        /*Modificar cliente
         UPDATE
         * */

        Cliente modificarCliente = new Cliente(1, "Carlos Daniel", "Ortiz", 355);
        var modificado = clienteDao.modificarCliente(modificarCliente);
        if (modificado)
            System.out.println("Cliente modificado" + modificarCliente);
        else
            System.out.println("No se modifico el cliente" + modificarCliente);


        /*Eliminar Cliente
        DELETE
         */
        Cliente clienteEliminado = new Cliente(7);
        boolean eliminado = clienteDao.eliminarCliente(clienteEliminado);
        if (eliminado)
            System.out.println("Cliente eliminado" + clienteEliminado);
        else
            System.out.println("No se elimino el cliente" + clienteEliminado);


        //Listar clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);


    }

}
