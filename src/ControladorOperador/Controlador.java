package ControladorOperador;

import Dominio.Cliente;
import Dominio.Pedido;
import Dominio.Usuario;
import Estructuras.Colas.ColasArray;
import VistaOperador.VistaOperador;
import VistaOperador.VistaPrincipalOpera;
import VistaOperador.VistaOperadorDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Scanner;

public class Controlador {

    Cliente cliente;
    Pedido pedido;
    Usuario usuario;
    static String palabraPedido;
    static String telefonoABuscar;

    public Controlador(){

    }

    public void star(){
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("src/client.properties")));
            ClienteOperador client = new ClienteOperador(
                    (String) properties.get("IP"),
                    (String) properties.get("PORT"),
                    (String) properties.get("SERVICENAME"));
            Scanner sc= new Scanner(System.in);
            VistaPrincipalOpera vista= new VistaPrincipalOpera();
            vista.setVisible(true);

            VistaOperador vistaOperador= new VistaOperador();
            vistaOperador.setVisible(false);
            VistaOperadorDatos vistaOperadorDatos=new VistaOperadorDatos();
            vistaOperadorDatos.setVisible(false);

            //Aqui se conectan los datos que otorga la barra de busqueda del cliente por numero de telefono
            ColasArray cola=  client.busquedaCliente(clienteABuscar());
            //aparece en la vista los datos del cliente
            vistaOperador.setNombreCliente(cola.dequeue().toString());
            vistaOperador.setDireccionCliente(cola.dequeue().toString());
            vistaOperador.setTelefonoCliente(cola.dequeue().toString());
            vistaOperador.setTipoCliente(cola.dequeue().toString());
            //segun el numero de telefono registrado se muestran los pedidos frecuentes del cliente
            vistaOperador.setPedidosCliente(client.pedidosFrecuentesCliente(clienteABuscar()));

            //para el ingreso de pedido y del nuevo cliente
            client.registrarCliente(cliente.getNombreCliente(), cliente.getDireccionCliente(), cliente.getTelefono(),  cliente.getTipoCuenta());
            client.actualizarCliente(cliente.getNombreCliente(), cliente.getDireccionCliente(), cliente.getTelefono(),  cliente.getTipoCuenta());
            client.ingresarPedido(pedido.getProductoNombre(),pedido.getCodigo(), pedido.getCantidad());
            client.actualizarPedido(pedido.getProductoNombre(),pedido.getCodigo(), pedido.getCantidad());

            //para buscar un pedido
            vistaOperadorDatos.mostrarPedidosEncontrados(client.busquedaPedido(pedidoABuscar()));// se muestran los pedidos encontrados en la barra de busqueda
            //para la validacion del login
            vistaOperador.accederLogin(client.validarUsuario(usuario.getId(), usuario.getContraseña())); // se valida el login

            ////vistaOperador.accederLogin(true);
            //para verificar si hay que registrar el usuario o se muestran los datos porque ya existe
            vistaOperador.elegirPanelSiClienteExiste(client.clienteExistente(clienteABuscar()));//se busca al cliente si existe o no, segun su telefono y se notifica a la vista


            //vistaOperador.elegirPanelSiClienteExiste(true);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("RMI Client");
    }

   public String pedidoABuscar(){
        return palabraPedido;
   }

   public String clienteABuscar(){
        return telefonoABuscar;
   }

    public void registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) throws RemoteException {
       cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
    }


    public void actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta)throws RemoteException {
        cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
    }


    public void ingresarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
    }


    public void actualizarPedido(String producto, String codigo, String cantidad) throws RemoteException {
        pedido=new Pedido(producto,codigo,cantidad);
    }


    public void pedidosFrecuentesCliente(String telefono) throws RemoteException {
        cliente.setTelefono(telefono);
    }


    public void busquedaPedido(String pedidoABuscar) throws RemoteException {
       palabraPedido=pedidoABuscar;
    }


    public void busquedaCliente(String clienteTelefonoABuscar) throws RemoteException {
        telefonoABuscar=clienteTelefonoABuscar;
    }


    public void validarUsuario(String id, String contraseña) throws RemoteException {
        usuario = new Usuario(id,contraseña);
    }


    public void clienteExistente(String telefono) throws RemoteException {
        cliente.setTelefono(telefono);
    }
}
