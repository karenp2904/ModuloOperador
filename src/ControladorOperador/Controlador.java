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
    public  String palabraPedido=" ";
    public  String telefonoABuscar="";

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPalabraPedido() {
        return palabraPedido;
    }

    public void setPalabraPedido(String palabraPedido) {
        this.palabraPedido = palabraPedido;
    }

    public String getTelefonoABuscar() {
        return telefonoABuscar;
    }

    public void setTelefonoABuscar(String telefonoABuscar) {
        this.telefonoABuscar = telefonoABuscar;
    }

    public String pedidoABuscar(){
        return palabraPedido;
    }

    public String clienteABuscar(){
        return getTelefonoABuscar();
    }



    public Cliente registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) throws RemoteException {
        cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
        return new Cliente(nombre,direccion,telefono,tipoDeCuenta);

    }


    public Cliente actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta)throws RemoteException {
        cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
        setCliente(cliente);
        return new Cliente(nombre,direccion,telefono,tipoDeCuenta);
    }


    public Pedido ingresarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
        setPedido(pedido);
        return new Pedido(producto,codigo,cantidad);
    }


    public Pedido actualizarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
        setPedido(pedido);setPedido(pedido);
        return new Pedido(producto,codigo,cantidad);
    }


    public String busquedaPedido(String pedidoABuscar) throws RemoteException {
        palabraPedido=pedidoABuscar;
        setPalabraPedido(pedidoABuscar);
        return palabraPedido;
    }


    public String busquedaCliente(String clienteTelefonoABuscar) throws RemoteException {
        telefonoABuscar=clienteTelefonoABuscar;
        setTelefonoABuscar(clienteTelefonoABuscar);
        return telefonoABuscar;
    }


    public Usuario validarUsuario(String id, String contraseña) throws RemoteException {
        usuario = new Usuario(id,contraseña);
        setUsuario(usuario);
        return  new Usuario(id,contraseña);
    }


    public String clienteExistente(String telefono) throws RemoteException {
        telefonoABuscar=telefono;
        setTelefonoABuscar(telefono);
        return telefonoABuscar;
    }

}
