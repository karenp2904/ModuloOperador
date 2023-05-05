package VistaOperador;

import ControladorOperador.ClienteOperador;
import ControladorOperador.Controlador;
import Dominio.Cliente;
import Estructuras.Colas.ColasArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

public class VistaOperadorDatos extends JFrame {

    /*
    Datos de registro de cliente y actulizacion, ingreso y actulizacion del pedido
     */
    Controlador controlador=new Controlador();

    JPanel panelCentral =new JPanel();
    JPanel panelFrecuentes=new JPanel();
    JPanel panelCompletar=new JPanel();
    JLabel fondo = new JLabel();
    JLayeredPane contenedor=new JLayeredPane();

    //cuadros del cliente
    JTextField txtRegistroNombre = new JTextField();
    JTextField txtRegistroDireccion = new JTextField();
    JTextField txtRegistroTipo = new JTextField();
    JTextField txtRegistroTelefono = new JTextField();
    JTextField txtActNombre = new JTextField();
    JTextField txtActTelefono = new JTextField();
    JTextField txtActDireccion = new JTextField();
    JTextField txtActTipo = new JTextField();

    //cuadros del pedido
    JTextField txtIngreProducto = new JTextField();
    JTextField txtIngrCodigo = new JTextField();
    JTextField txtIngrCantidad = new JTextField();
    JTextField txtActProducto = new JTextField();
    JTextField txtActCodigo = new JTextField();
    JTextField txtActCantidad = new JTextField();

    //cuadros busqueda
    JTextField txtbusquedaPedido = new JTextField();
    JTextField txbusquedaCliente = new JTextField();

    VistaOperador vistaOperador;

    public VistaOperadorDatos(){
        this.setTitle("Hot Dogs Palace");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setBackground(Color.white);
        contenedor();
    }

    public void panelRegistroCliente(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 70, 900, 600);
        panelCentral.setBackground(Color.white);

        //logo de la salchica para el fondo
        JLabel logo = new JLabel("Logo");
        logo.setBounds(70, 130, 350, 350);
        ImageIcon imgLogo = new ImageIcon("Imagenes/FotoPerrito.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(ilogo);
        panelCentral.add(logo);

        //letrero del registro de clinetes en el panel
        JLabel letreroRegistro = new JLabel(" REGISTRO DE CLIENTES ");
        letreroRegistro.setBackground(Color.black);
        letreroRegistro.setFont(new Font("Arial", Font.BOLD, 40));
        letreroRegistro.setBounds(230, -20, 600, 200);
        panelCentral.add(letreroRegistro);


        //registro con la informacion del cliente
        //Label del nombre
        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(480, 120, 200, 100);
        panelCentral.add(nombretext);

        txtRegistroNombre.setBackground(Color.white);
        txtRegistroNombre.setFont(new Font("Arial", Font.BOLD, 20));
        txtRegistroNombre.setBounds(640, 150, 220, 40);
        panelCentral.add(txtRegistroNombre);

        //Label del numero telefonico
        JLabel telefonoText = new JLabel("TELEFONO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(480, 170, 200, 100);
        panelCentral.add(telefonoText);

        txtRegistroTelefono.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txtRegistroTelefono.setFont(new Font("Arial", Font.BOLD, 20));
        txtRegistroTelefono.setBounds(640, 200, 220, 40);
        panelCentral.add(txtRegistroTelefono);

        JLabel direccionText = new JLabel("DIRECCIÓN: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(480, 220, 200, 100);
        panelCentral.add(direccionText);

        txtRegistroDireccion.setBackground(Color.white);
        txtRegistroDireccion.setFont(new Font("Arial", Font.BOLD, 20));
        txtRegistroDireccion.setBounds(640, 250, 220, 40);
        panelCentral.add(txtRegistroDireccion);

        JLabel tipoText = new JLabel("TIPO: ");
        tipoText.setBackground(Color.black);
        tipoText.setFont(new Font("Arial", Font.BOLD, 20));
        tipoText.setBounds(480, 270, 200, 100);
        panelCentral.add(tipoText);

        txtRegistroTipo.setBackground(Color.white);
        txtRegistroTipo.setFont(new Font("Arial", Font.BOLD, 20));
        txtRegistroTipo.setBounds(640, 300, 220, 40);
        panelCentral.add(txtRegistroTipo);


        JButton botonIngresar=new JButton(); //boton para ingresar pedido
        botonIngresar.setBounds(520, 400, 200, 100);
        ImageIcon imgR= new ImageIcon("Imagenes/btningresarPedido.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("Imagenes/btningresarPedido2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    //
                        boolean registrado = client.registrarCliente(txtRegistroNombre.getText(), txtRegistroDireccion.getText(), txtRegistroTelefono.getText(), txtRegistroTipo.getText());
                        // obtener una referencia al objeto remoto



                    // realizar operaciones con el objeto remoto
                    vistaOperador=new VistaOperador();
                    vistaOperador.setVisible(true);
                    vistaOperador.panelOperador();
                    dispose()
                    /*
                    ColasArray datosCliente=client.busquedaCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setNombreCliente(datosCliente.dequeue().toString());
                    vistaOperador.setDireccionCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTelefonoCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTipoCliente(datosCliente.dequeue().toString());
                    ColasArray pedidoCliente=client.pedidosFrecuentesCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setPedidosCliente(pedidoCliente);

                     */
                  ;

                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        panelCentral.add(botonIngresar);



        contenedor.add(panelCentral,Integer.valueOf(1));

    }

    //metodo para obtener el nombre el cliente
    public String ingresarNombre(){

        String usuario=txtRegistroNombre.getText();// se obtiene el usuario
        return usuario;// se retorna
    }

    //metodo para obtener el telefono el cliente
    public String ingresarTelefono(){
        String telefono=txtRegistroTelefono.getText();// se obtiene el telefonor
        return telefono;// se retorna
    }

    //metodo para obtener la direccion el cliente
    public String ingresarDireccion(){

        String direccion=txtRegistroDireccion.getText();
        return direccion;
    }

    //metodo para obtener el tipo de cliente el cliente
    public String ingresarTipoCliente(){
        String tipo=txtActTipo.getText();
        return tipo;
    }

    public void panelIngresarPedido(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 100, 900, 700);
        panelCentral.setBackground(Color.white);

        panelCompletar.setLayout(null);
        panelCompletar.setVisible(false);
        panelCompletar.setOpaque(true);
        panelCompletar.setBounds(350, 130, 500,200);
        panelCompletar.setBackground(Color.lightGray);

        panelFrecuentes.setLayout(null);
        panelFrecuentes.setVisible(true);
        panelFrecuentes.setOpaque(true);
        panelFrecuentes.setBorder(null);
        panelFrecuentes.setBounds(100, 300, 300, 300);
        panelFrecuentes.setBackground(new Color(135, 170, 248));

        //letrero del registro de clinetes en el panel
        JLabel letreroFrecuentes = new JLabel("MÁS PEDIDOS");
        letreroFrecuentes.setBackground(new Color(16, 1, 1));
        letreroFrecuentes.setFont(new Font("Arial", Font.BOLD, 20));
        letreroFrecuentes.setBounds(60, 0, 300, 70);
        panelFrecuentes.add(letreroFrecuentes);

        JLabel logo=new JLabel("logoPerrito");
        logo.setBounds(800,500,300,300);
        ImageIcon imlogo= new ImageIcon("Imagenes/logoPerro.png");// se le pone icono a boton
        Icon logo1= new ImageIcon(imlogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(logo1);

        //letrero del registro de clinetes en el panel
        JLabel letreroIngreso = new JLabel("INGRESAR PEDIDO");
        letreroIngreso.setBackground(Color.black);
        letreroIngreso.setFont(new Font("Arial", Font.BOLD, 40));
        letreroIngreso.setBounds(500, 150, 600, 200);

        Color colorPanel=new Color(234,234,234);
        txtbusquedaPedido.setBackground(colorPanel);
        txtbusquedaPedido.setFont(new Font("Arial", Font.BOLD, 30));
        txtbusquedaPedido.setBounds(350, 60, 500, 60);
        panelCentral.add(txtbusquedaPedido);

        String buscar=buscarPedido();
        String producto=ingresarProducto();
        String cantidad=ingresarCantidad();
        String tamaño= ingresarCodido();

        JLabel productoText = new JLabel("PRODUCTO: ");
        productoText.setBackground(Color.black);
        productoText.setFont(new Font("Arial", Font.BOLD, 20));
        productoText.setBounds(370, 190, 200, 100);
        panelCentral.add(productoText);

        txtIngreProducto.setBackground(new Color(234,234,234));
        txtIngreProducto.setFont(new Font("Arial", Font.BOLD, 20));
        txtIngreProducto.setBounds(500, 210, 400, 50);
        panelCentral.add(txtIngreProducto);

        JLabel tamañoText = new JLabel("CÓDIGO: ");
        tamañoText.setBackground(Color.black);
        tamañoText.setFont(new Font("Arial", Font.BOLD, 20));
        tamañoText.setBounds(370, 260, 200, 100);
        panelCentral.add(tamañoText);

        txtIngrCodigo.setBackground(new Color(234,234,234));
        txtIngrCodigo.setFont(new Font("Arial", Font.BOLD, 20));
        txtIngrCodigo.setBounds(500, 280, 400, 50);
        panelCentral.add(txtIngrCodigo);

        JLabel cantidadText = new JLabel("CANTIDAD: ");
        cantidadText.setBackground(Color.black);
        cantidadText.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadText.setBounds(370, 330, 200, 100);
        panelCentral.add(cantidadText);


        txtIngrCantidad.setBackground(new Color(234,234,234));
        txtIngrCantidad.setFont(new Font("Arial", Font.BOLD, 20));
        txtIngrCantidad.setBounds(500, 350, 400, 50);
        panelCentral.add(txtIngrCantidad);

        JButton botonBuscar=new JButton();
        botonBuscar=new JButton();//boton para buscar cliente
        botonBuscar.setBounds(900, 50, 200, 80);
        ImageIcon imgBus= new ImageIcon("Imagenes/botonBuscar.png");// se le pone icono a boton
        Icon ibus= new ImageIcon(imgBus.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setIcon(ibus);
        botonBuscar.setLayout(null);
        botonBuscar.setOpaque(true);
        botonBuscar.setBorderPainted(false);
        ImageIcon imgBus2= new ImageIcon("Imagenes/botonBuscar2.png");// se le pone icono a boton
        Icon ibus2= new ImageIcon(imgBus2.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setRolloverIcon(ibus2);
        botonBuscar.setBackground(Color.white);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    mostrarPedidosEncontrados(client.busquedaPedido(txtbusquedaPedido.getText()));
                    panelCompletar.setVisible(true);

                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        JButton botonIngresar=new JButton(); //boton para ingresar pedido
        botonIngresar.setBounds(350, 450, 200, 100);
        ImageIcon imgR= new ImageIcon("Imagenes/btnConfirmar.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("Imagenes/btnConfirmar2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                   boolean ingresado= client.ingresarPedido(txtIngreProducto.getText(),txtIngrCodigo.getText(),txtIngrCantidad.getText());

                     vistaOperador=new VistaOperador();
                    vistaOperador.setVisible(true);
                    vistaOperador.panelOperador();
                    dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        panelCentral.add(botonIngresar);

        contenedor.add(txtbusquedaPedido,Integer.valueOf(2));
        contenedor.add(panelFrecuentes,Integer.valueOf(4));
        contenedor.add(logo,Integer.valueOf(3));
        contenedor.add(letreroIngreso,Integer.valueOf(3));
        contenedor.add(botonBuscar,Integer.valueOf(2));
        contenedor.add(panelCentral,Integer.valueOf(2));
        contenedor.add(panelCompletar,Integer.valueOf(4));
    }

    public void botonBuscar(){

    }

    //metodo para buscar pedido- autocompletar
    public String buscarPedido(){

        String nombrePedido= txtbusquedaPedido.getText();
        return nombrePedido;
    }

    //metodo para segun el pedido de la barra de busqueda se muestren los parecidos
    public void mostrarPedidosEncontrados(ColasArray pedidosEncontrados){
        //letrero del registro de clinetes en el panel
        int y=0;//se define la altura
        while(pedidosEncontrados.size()!=0) {
            JLabel titulo=new JLabel(pedidosEncontrados.dequeue().toString());
            titulo.setBackground(Color.black);
            titulo.setFont(new Font("Arial", Font.BOLD, 10));
            titulo.setBounds(10,y,600,100);
            panelCompletar.add(titulo);
            y+=20;//se le agrega distancia a y para la ubicacion del texto

        }
    }

    //metodo para ingresar producto del pedido
    public String ingresarProducto(){

        String producto= txtIngreProducto.getText();
        return producto;
    }

    //metodo para ingresar codigo del producto del pedido
    public String ingresarCodido(){

        String tamaño=txtActCodigo.getText();
        return tamaño;
    }

    //metodo para ingresar cantidad del producto del pedido
    public String ingresarCantidad(){
        String Cantidad=txtIngrCantidad.getText();
        return Cantidad;
    }

    //metodo para editar los mas pedidos del restaurante
    public void editarPedidosFrecuentes(String[] pedidos){
        int y=50;
        for (int i = 0; i <pedidos.length ; i++) {
            JLabel titulo=new JLabel(pedidos[i]);
            titulo.setBackground(Color.black);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setBounds(50,y,600,100);
            panelFrecuentes.add(titulo);
            y+=50;//se le agrega distancia a y para la ubicacion del texto
            //otorga espacios
            JLabel espacio=new JLabel();
            espacio.setBackground(Color.white);
            espacio.setFont(new Font("Arial", Font.BOLD, 20));
            espacio.setBounds(100,y+10,600,100);
            panelFrecuentes.add(espacio);
        }
    }


    //panel para actualizar los datos del cliente
    public void panelActualizarDatos(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 70, 900, 600);
        panelCentral.setBackground(Color.white);

        //logo de la salchica para el fondo
        JLabel logo = new JLabel("Logo");
        logo.setBounds(70, 130, 350, 350);
        ImageIcon imgLogo = new ImageIcon("Imagenes/FotoPerrito.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(ilogo);
        panelCentral.add(logo);

        //letrero del registro de clinetes en el panel
        JLabel letreroRegistro = new JLabel(" ACTUALIZAR INFORMACIÓN");
        letreroRegistro.setBackground(Color.black);
        letreroRegistro.setFont(new Font("Arial", Font.BOLD, 40));
        letreroRegistro.setBounds(230, -20, 600, 200);
        panelCentral.add(letreroRegistro);


        //registro con la informacion del cliente
        //Label del nombre
        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(480, 120, 200, 100);
        panelCentral.add(nombretext);

        txtActNombre.setBackground(Color.white);
        txtActNombre.setFont(new Font("Arial", Font.BOLD, 20));
        txtActNombre.setBounds(640, 150, 220, 40);
        panelCentral.add(txtActNombre);

        //Label del numero telefonico
        JLabel telefonoText = new JLabel("TELEFONO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(480, 170, 200, 100);
        panelCentral.add(telefonoText);

        txtActTelefono.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txtActTelefono.setFont(new Font("Arial", Font.BOLD, 20));
        txtActTelefono.setBounds(640, 200, 220, 40);
        panelCentral.add(txtActTelefono);

        JLabel direccionText = new JLabel("DIRECCIÓN: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(480, 220, 200, 100);
        panelCentral.add(direccionText);

        txtActDireccion.setBackground(Color.white);
        txtActDireccion.setFont(new Font("Arial", Font.BOLD, 20));
        txtActDireccion.setBounds(640, 250, 220, 40);
        panelCentral.add(txtActDireccion);

        JLabel tipoText = new JLabel("TIPO: ");
        tipoText.setBackground(Color.black);
        tipoText.setFont(new Font("Arial", Font.BOLD, 20));
        tipoText.setBounds(480, 270, 200, 100);
        panelCentral.add(tipoText);

        txtActTipo.setBackground(Color.white);
        txtActTipo.setFont(new Font("Arial", Font.BOLD, 20));
        txtActTipo.setBounds(640, 300, 220, 40);
        panelCentral.add(txtActTipo);


        String nombre=ingresarNombre();
        String telefono=ingresarTelefono();
        String direccion=ingresarDireccion();
        String tipo=ingresarTipoCliente();

        JButton botonIngresar=new JButton(); //boton para ingresar pedido
        botonIngresar.setBounds(520, 400, 200, 100);
        ImageIcon imgR= new ImageIcon("Imagenes/btningresarPedido.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("Imagenes/btningresarPedido2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    boolean ingresado= client.actualizarCliente(txtActNombre.getText(),txtActDireccion.getText(),txtActTelefono.getText(),txtActTipo.getText());
 /*
                    ColasArray datosCliente=client.busquedaCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setNombreCliente(datosCliente.dequeue().toString());
                    vistaOperador.setDireccionCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTelefonoCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTipoCliente(datosCliente.dequeue().toString());
                    ColasArray pedidoCliente=client.pedidosFrecuentesCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setPedidosCliente(pedidoCliente);

                     */
                    int contador=10;
                    while(contador!=0){
                        contador--;

                        if(contador==0){
                            vistaOperador=new VistaOperador();
                            vistaOperador.setVisible(true);
                            vistaOperador.panelOperador();
                        }
                    }
                    System.out.println("ventana de prueba");
                    vistaOperador=new VistaOperador();
                    vistaOperador.setVisible(true);
                    vistaOperador.panelOperador();
                    dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panelCentral.add(botonIngresar);

        contenedor.add(panelCentral,Integer.valueOf(1));

    }

    //panel para actualizar el pedido
    public void panelActualizarPedido(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 100, 900, 700);
        panelCentral.setBackground(Color.white);

        panelCompletar.setLayout(null);
        panelCompletar.setVisible(false);
        panelCompletar.setOpaque(true);
        panelCompletar.setBounds(350, 130, 500, 50);
        panelCompletar.setBackground(Color.lightGray);

        panelFrecuentes.setLayout(null);
        panelFrecuentes.setVisible(true);
        panelFrecuentes.setOpaque(true);
        panelFrecuentes.setBorder(null);
        panelFrecuentes.setBounds(100, 300, 300, 300);
        panelFrecuentes.setBackground(new Color(135, 170, 248));

        //letrero del registro de clinetes en el panel
        JLabel letreroFrecuentes = new JLabel("MÁS PEDIDOS");
        letreroFrecuentes.setBackground(new Color(16, 1, 1));
        letreroFrecuentes.setFont(new Font("Arial", Font.BOLD, 20));
        letreroFrecuentes.setBounds(60, 0, 300, 70);
        panelFrecuentes.add(letreroFrecuentes);

        JLabel logo=new JLabel("logoPerrito");
        logo.setBounds(800,500,300,300);
        ImageIcon imlogo= new ImageIcon("Imagenes/logoPerro.png");// se le pone icono a boton
        Icon logo1= new ImageIcon(imlogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(logo1);

        //letrero del registro de clinetes en el panel
        JLabel letreroIngreso = new JLabel("ACTUALIZAR PEDIDO");
        letreroIngreso.setBackground(Color.black);
        letreroIngreso.setFont(new Font("Arial", Font.BOLD, 40));
        letreroIngreso.setBounds(500, 150, 600, 200);

        Color colorPanel=new Color(234,234,234);
        txtbusquedaPedido.setBackground(colorPanel);
        txtbusquedaPedido.setFont(new Font("Arial", Font.BOLD, 30));
        txtbusquedaPedido.setBounds(350, 60, 500, 60);
        panelCentral.add(txtbusquedaPedido);

        String buscar=buscarPedido();
        String producto=ingresarProducto();
        String cantidad=ingresarCantidad();
        String tamaño= ingresarCodido();

        JLabel productoText = new JLabel("PRODUCTO: ");
        productoText.setBackground(Color.black);
        productoText.setFont(new Font("Arial", Font.BOLD, 20));
        productoText.setBounds(370, 190, 200, 100);
        panelCentral.add(productoText);

        txtActProducto.setBackground(new Color(234,234,234));
        txtActProducto.setFont(new Font("Arial", Font.BOLD, 20));
        txtActProducto.setBounds(500, 210, 400, 50);
        panelCentral.add(txtActProducto);

        JLabel tamañoText = new JLabel("CÓDIGO: ");
        tamañoText.setBackground(Color.black);
        tamañoText.setFont(new Font("Arial", Font.BOLD, 20));
        tamañoText.setBounds(370, 260, 200, 100);
        panelCentral.add(tamañoText);

        txtActCodigo.setBackground(new Color(234,234,234));
        txtActCodigo.setFont(new Font("Arial", Font.BOLD, 20));
        txtActCodigo.setBounds(500, 280, 400, 50);
        panelCentral.add(txtActCodigo);

        JLabel cantidadText = new JLabel("CANTIDAD: ");
        cantidadText.setBackground(Color.black);
        cantidadText.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadText.setBounds(370, 330, 200, 100);
        panelCentral.add(cantidadText);

        txtActCantidad.setBackground(new Color(234,234,234));
        txtActCantidad.setFont(new Font("Arial", Font.BOLD, 20));
        txtActCantidad.setBounds(500, 350, 400, 50);
        panelCentral.add(txtActCantidad);

        JButton botonBuscar=new JButton();
        botonBuscar=new JButton();//boton para buscar cliente
        botonBuscar.setBounds(900, 50, 200, 80);
        ImageIcon imgBus= new ImageIcon("Imagenes/botonBuscar.png");// se le pone icono a boton
        Icon ibus= new ImageIcon(imgBus.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setIcon(ibus);
        botonBuscar.setLayout(null);
        botonBuscar.setOpaque(true);
        botonBuscar.setBorderPainted(false);
        ImageIcon imgBus2= new ImageIcon("Imagenes/botonBuscar2.png");// se le pone icono a boton
        Icon ibus2= new ImageIcon(imgBus2.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setRolloverIcon(ibus2);
        botonBuscar.setBackground(Color.white);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCompletar.setVisible(true);
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    mostrarPedidosEncontrados(client.busquedaPedido(txtbusquedaPedido.getText()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });



        JButton botonIngresar=new JButton(); //boton para ingresar pedido
        botonIngresar.setBounds(350, 450, 200, 100);
        ImageIcon imgR= new ImageIcon("Imagenes/btnConfirmar.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("Imagenes/btnConfirmar2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    boolean ingresado= client.actualizarPedido(txtActProducto.getText(),txtActCodigo.getText(),txtActCantidad.getText());
                    /*
                    ColasArray datosCliente=client.busquedaCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setNombreCliente(datosCliente.dequeue().toString());
                    vistaOperador.setDireccionCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTelefonoCliente(datosCliente.dequeue().toString());
                    vistaOperador.setTipoCliente(datosCliente.dequeue().toString());
                    ColasArray pedidoCliente=client.pedidosFrecuentesCliente(vistaOperador.txbuscarCliente.getText());
                    vistaOperador.setPedidosCliente(pedidoCliente);

                     */
                    int contador=10;
                    while(contador!=0){
                        contador--;

                        if(contador==0){
                            vistaOperador=new VistaOperador();
                            vistaOperador.setVisible(true);
                            vistaOperador.panelOperador();
                        }
                    }
                    if(ingresado){
                        vistaOperador=new VistaOperador();
                        vistaOperador.setVisible(true);
                        vistaOperador.panelOperador();
                    }else{
                        vistaOperador=new VistaOperador();
                        vistaOperador.setVisible(true);
                        vistaOperador.panelOperador();
                    }
                    System.out.println("ventana de prueba");

                    vistaOperador=new VistaOperador();
                    vistaOperador.setVisible(true);
                    vistaOperador.panelOperador();

                    dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panelCentral.add(botonIngresar);

        contenedor.add(txtbusquedaPedido,Integer.valueOf(2));
        contenedor.add(panelFrecuentes,Integer.valueOf(4));
        contenedor.add(logo,Integer.valueOf(3));
        contenedor.add(letreroIngreso,Integer.valueOf(3));
        contenedor.add(botonBuscar,Integer.valueOf(2));
        contenedor.add(panelCentral,Integer.valueOf(2));
        contenedor.add(panelCompletar,Integer.valueOf(4));
    }


    //metodo para el contendor
    public void contenedor(){
        ImageIcon imagen =new ImageIcon("src/Imagenes/fondoLetras.png");
        fondo.setIcon(imagen);
        fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        //fondo.setBounds(100,40,1400,600);


        contenedor.add(fondo,Integer.valueOf(0));

        //  contenedor.add(panelInicio,Integer.valueOf(2));

        this.getContentPane().add(contenedor);
        this.setSize(getMaximumSize());
        this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
