import ControladorOperador.Controlador;
import Dominio.Cliente;
import Dominio.Pedido;
import Dominio.Usuario;
import Estructuras.Colas.ColasArray;
import VistaOperador.VistaOperador;
import VistaOperador.VistaPrincipalOpera;
import VistaOperador.VistaOperadorDatos;
import ControladorOperador.ClienteOperador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws RemoteException {
        VistaPrincipalOpera vistaPrincipalOpera=new VistaPrincipalOpera();
        vistaPrincipalOpera.setVisible(true);
        Controlador controlador=new Controlador();
        controlador.star();

        System.out.println("RMI Client");
    }







}
