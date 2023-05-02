package ControladorOperador;

import Dominio.Cliente;
import Dominio.Pedido;
import Dominio.Usuario;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Controlador implements Serializable {

     public Cliente cliente=new Cliente();
    public Pedido pedido=new Pedido();
    public Usuario usuario=new Usuario();
    public  String palabraPedido;
    public  String telefonoABuscar;


    public String pedidoABuscar(){
        return palabraPedido;
    }

    public String clienteABuscar(){
        return telefonoABuscar;
    }

    public Cliente registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) throws RemoteException {
        cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
        return cliente;
    }


    public Cliente actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta)throws RemoteException {
        cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
        return cliente;
    }


    public Pedido ingresarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
        return pedido;
    }


    public Pedido actualizarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
        return pedido;
    }


    public String busquedaPedido(String pedidoABuscar) throws RemoteException {
        palabraPedido=pedidoABuscar;
        return palabraPedido;
    }


    public String busquedaCliente(String clienteTelefonoABuscar) throws RemoteException {
        telefonoABuscar=clienteTelefonoABuscar;
        return telefonoABuscar;
    }


    public Usuario validarUsuario(String id, String contraseña) throws RemoteException {
        usuario = new Usuario(id,contraseña);
        return  usuario;
    }


    public String clienteExistente(String telefono) throws RemoteException {
        telefonoABuscar=telefono;
        return telefonoABuscar;
    }

}
