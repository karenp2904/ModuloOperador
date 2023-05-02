import ControladorOperador.ClienteOperador;
import ControladorOperador.Controlador;
import Dominio.Cliente;
import Dominio.Pedido;
import Dominio.Usuario;
import Estructuras.Colas.ColasArray;
import VistaOperador.VistaOperador;
import VistaOperador.VistaPrincipalOpera;
import VistaOperador.VistaOperadorDatos;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Properties;

public class Main implements Serializable {


    public static void main(String[] args) throws RemoteException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/client.properties")));
            ClienteOperador client = new ClienteOperador(
                    (String) properties.get("IP"),
                    (String) properties.get("PORTS"),
                    (String) properties.get("SERVICES"));

            VistaPrincipalOpera vistaPrincipalOpera=new VistaPrincipalOpera();

            VistaOperador vistaOperador= new VistaOperador();
            vistaOperador.setVisible(false);
            VistaOperadorDatos vistaOperadorDatos=new VistaOperadorDatos();
            vistaOperadorDatos.setVisible(false);

            Controlador controlador=new Controlador();
            Cliente cliente=new Cliente(controlador.cliente.getNombreCliente(),controlador.cliente.getDireccionCliente(),controlador.cliente.getTelefono(),controlador.cliente.getTipoCuenta());
            Pedido pedido=new Pedido(controlador.pedido.getProductoNombre(),controlador.pedido.getCodigo(),controlador.pedido.getCantidad());

            Usuario usuario=controlador.usuario;


            //Aqui se conectan los datos que otorga la barra de busqueda del cliente por numero de telefono
            ColasArray cola=new ColasArray();
            cola=client.busquedaCliente(controlador.clienteABuscar());
            cola.enqueue(cliente.getNombreCliente());
            cola.enqueue(cliente.getNombreCliente());
            cola.enqueue(cliente.getNombreCliente());
            cola.enqueue(cliente.getNombreCliente());

            //aparece en la vista los datos del cliente
            vistaOperador.setNombreCliente(cola.dequeue().toString());
            vistaOperador.setDireccionCliente(cola.dequeue().toString());
            vistaOperador.setTelefonoCliente(cola.dequeue().toString());
            vistaOperador.setTipoCliente(cola.dequeue().toString());
            //segun el numero de telefono registrado se muestran los pedidos frecuentes del cliente
            vistaOperador.setPedidosCliente(client.pedidosFrecuentesCliente(controlador.clienteABuscar()));



            client.registrarCliente(cliente.getNombreCliente(), cliente.getDireccionCliente(), cliente.getTelefono(),  cliente.getTipoCuenta());
            client.actualizarCliente(cliente.getNombreCliente(), cliente.getDireccionCliente(), cliente.getTelefono(),  cliente.getTipoCuenta());
            client.ingresarPedido(pedido.getProductoNombre(),pedido.getCodigo(), pedido.getCantidad());
            client.actualizarPedido(pedido.getProductoNombre(),pedido.getCodigo(), pedido.getCantidad());

            //para buscar un pedido
            vistaOperadorDatos.mostrarPedidosEncontrados(client.busquedaPedido(controlador.pedidoABuscar()));// se muestran los pedidos encontrados en la barra de busqueda

            //para la validacion del login
            vistaOperador.accederLogin(client.validarUsuario(usuario.getId(), usuario.getContraseña())); // se valida el login

            ////vistaOperador.accederLogin(true);
            //para verificar si hay que registrar el usuario o se muestran los datos porque ya existe
            vistaOperador.elegirPanelSiClienteExiste(client.clienteExistente(controlador.clienteABuscar()));//se busca al cliente si existe o no, segun su telefono y se notifica a la vista
            vistaOperador.elegirPanelSiClienteExiste(client.clienteExistente(controlador.clienteABuscar()));//se busca al cliente si existe o no, segun su telefono y se notifica a la vista


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("RMI Client");
    }




}
