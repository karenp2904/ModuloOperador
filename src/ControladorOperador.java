import Estructuras.Colas.ColasArray;
import VistaOperador.VistaOperador;
import VistaOperador.VistaPrincipalOpera;
import VistaOperador.VistaOperadorDatos;
import ControladorOperador.ClienteOperador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Scanner;

public class ControladorOperador {
    public static void main(String[] args) throws RemoteException {
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
            ColasArray cola=  client.busquedaCliente(vistaOperador.buscarClienteAntes());
            //aparece en la vista los datos del cliente
            vistaOperador.setNombreCliente(cola.dequeue().toString());
            vistaOperador.setDireccionCliente(cola.dequeue().toString());
            vistaOperador.setTelefonoCliente(cola.dequeue().toString());
            vistaOperador.setTipoCliente(cola.dequeue().toString());
            //segun el numero de telefono registrado se muestran los pedidos frecuentes del cliente
            vistaOperador.setPedidosCliente(client.pedidosFrecuentesCliente(vistaOperador.buscarClienteAntes()));

            //para el ingreso de pedido y del nuevo cliente
            client.registrarCliente(vistaOperadorDatos.ingresarNombre(), vistaOperadorDatos.ingresarDireccion(), vistaOperadorDatos.ingresarTelefono(), vistaOperadorDatos.ingresarTipoCliente());
            client.actualizarCliente(vistaOperadorDatos.ingresarNombre(), vistaOperadorDatos.ingresarDireccion(), vistaOperadorDatos.ingresarTelefono(), vistaOperadorDatos.ingresarTipoCliente());
            client.ingresarPedido(vistaOperadorDatos.ingresarProducto(),vistaOperadorDatos.ingresarCodido(), vistaOperadorDatos.ingresarCantidad());
            client.actualizarPedido(vistaOperadorDatos.ingresarProducto(),vistaOperadorDatos.ingresarCodido(), vistaOperadorDatos.ingresarCantidad());

            //para buscar un pedido
            vistaOperadorDatos.mostrarPedidosEncontrados(client.busquedaPedido(vistaOperadorDatos.buscarPedido()));// se muestran los pedidos encontrados en la barra de busqueda

            //para la validacion del login
            //vistaOperador.accederLogin(client.validarUsuario("operador", vistaOperador.validarUsuario(), vistaOperador.validarContraseña())); // se valida el login

            ////vistaOperador.accederLogin(true);
            //para verificar si hay que registrar el usuario o se muestran los datos porque ya existe
            //.elegirPanelSiClienteExiste(client.clienteExistente(vistaOperador.buscarCliente()));//se busca al cliente si existe o no, segun su telefono y se notifica a la vista

            //vistaOperador.elegirPanelSiClienteExiste(true);

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("RMI Client");
    }


}
