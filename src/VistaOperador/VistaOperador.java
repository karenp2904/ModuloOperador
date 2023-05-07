package VistaOperador;


import ControladorOperador.ClienteOperador;
import ControladorOperador.Controlador;
import Estructuras.Colas.ColasArray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.*;

public class VistaOperador extends JFrame {
    /*
    //
    //
    En esta ventana estara el menu de  busqueda
    Se encuentra el login del operador con usuario y contraseña
    Se reclama el numero del cliente y se muestran los datos del mismo
    se muestran datos de nombre- telefono -tipo de cliente- direccion y pedidos frecuentes

     */

//
    Controlador controlador=new Controlador();
    JLabel fondo = new JLabel();//fondo
    JLayeredPane contenedor=new JLayeredPane();//contenedor de capas en la ventana
    JPanel panelInicio = new JPanel();
    JPanel panelFondo=new JPanel();
    JPanel panelBusqueda=new JPanel();
    JPanel panelBlanco = new JPanel();
    JButton botonLogin=new JButton();
    JPanel panelPedido = new JPanel();
    JPanel panelInformacion = new JPanel();
    JTextField txbuscarCliente =new JTextField();
    JButton botonBuscar=new JButton();
    JButton  botonBuscCliente=new JButton();//boton para buscar cliente

    JButton botonRegistrar=new JButton();
    JButton botonIngresar=new JButton();
    JButton botonActPedido = new JButton();
    JButton botonActDatos=new JButton();
    JTextField txusuario = new JTextField();//caja de texto
    JTextField txcontraseña = new JTextField();//caja de texto

    //Constructor de la ventana con las propiedades de la misma
    public VistaOperador(){
        this.setTitle("Hot Dogs Palace");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setBackground(Color.white);
    }

    //panel para el login del operador. usuario y contraseña
    public void panelLogin() {
        //Panel que tendrá las etiquetas y botones
        panelInicio.setLayout(null);
        panelInicio.setVisible(true);
        panelInicio.setOpaque(true);
        //panelBlanco.setBorder(BorderFactory.createLineBorder(Color.black, 80));
        panelInicio.setBounds(160, 80, 400, 500);
        panelInicio.setBackground(Color.white);

        //logo de la salchica para el fondo
        JLabel logo = new JLabel("Logo");
        logo.setBounds(110, 10, 150, 150);
        ImageIcon imgLogo = new ImageIcon("src/Imagenes/logoPerrito.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(ilogo);
        panelInicio.add(logo);

        JLabel operadorText = new JLabel("OPERADOR");
        operadorText.setFont(new Font("Arial", Font.BOLD, 20));
        operadorText.setBounds(130, 140, 200, 100);
        panelInicio.add(operadorText);


        //nombre del usuario en el login
        JLabel nombreUsuario = new JLabel("Usuario");
        nombreUsuario.setBackground(Color.black);
        nombreUsuario.setBounds(40, 180, 200, 100);
        panelInicio.add(nombreUsuario);

        //txusuario.setBackground(Color.white);//color
        txusuario.setBounds(30, 250, 300, 40);//ubicacion y tamaño
        panelInicio.add(txusuario);//se añade al panel
        //Aqui se llama al metodo//TEMPORAL
        String usuario=validarUsuario();

        //contraseña del usuario en el login
        JLabel contraseña = new JLabel("Contraseña");
        contraseña.setBackground(Color.black);
        contraseña.setBounds(40, 290, 200, 100);
        panelInicio.add(contraseña);

        txcontraseña.setBackground(Color.white);//color
        txcontraseña.setBounds(30,360,300,40);//ubicacion y tamaño
        panelInicio.add(txcontraseña);//se añade al panel
        //Aqui se llama al metodo
        String contraseñaa= validarContraseña();

        //boton que da acceso al modulo en el login//TEMPORAL
        botonLogin.setBounds(140, 420, 100, 50);
        ImageIcon imgR= new ImageIcon("src/Imagenes/INGRESAR.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonLogin.getWidth(), botonLogin.getHeight(), Image.SCALE_DEFAULT));
        botonLogin.setIcon(i);
        botonLogin.setLayout(null);
        botonLogin.setOpaque(true);
        botonLogin.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("src/Imagenes/INGRESAR2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonLogin.getWidth(), botonLogin.getHeight(), Image.SCALE_DEFAULT));
        botonLogin.setRolloverIcon(iconAdmin);
        botonLogin.setBackground(Color.white);
        panelInicio.add(botonLogin);

        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(new File("src/client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    boolean valido=client.validarUsuario(txusuario.getText(),txcontraseña.getText());
                    if(valido){
                        panelComprobarCliente();
                        panelBusqueda.setVisible(true);
                        panelInicio.setVisible(false);
                        contenedor.add(panelBlanco,Integer.valueOf(4));
                        contenedor.add(fondo,Integer.valueOf(5));
                        contenedor.add(panelBusqueda,Integer.valueOf(8));
                        contenedor.add(botonBuscCliente,Integer.valueOf(9));
                    }else{
                        JOptionPane.showInputDialog("NO ES POSIBLE ACCEDER- ERROR DE USUARIO Y/0 CONTRASEÑA");
                    }

                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //fondo del login
        ImageIcon imagen =new ImageIcon("src/Imagenes/loginOperador.png");
        fondo.setIcon(imagen);
        fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        //capas de la ventana
        contenedor.add(panelInicio,Integer.valueOf(5));
        contenedor.add(fondo,Integer.valueOf(4));
        this.setSize(imagen.getIconWidth(), imagen.getIconHeight());//tamaño de la imagen ajustado a la ventana
        contenedor();//llama al contenedor

        //accederLogin(true);
    }



    public void accederLogin(boolean validacion){
        if(validacion){
            botonLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   panelComprobarCliente();
                   panelBusqueda.setVisible(true);
                    panelInicio.setVisible(false);

                    contenedor.add(panelBlanco,Integer.valueOf(4));
                    contenedor.add(fondo,Integer.valueOf(5));
                    contenedor.add(panelBusqueda,Integer.valueOf(8));
                    contenedor.add(botonBuscCliente,Integer.valueOf(9));


                }
            });
        }else{
            //JOptionPane.showInputDialog("NO ES POSIBLE ACCEDER- ERROR DE USUARIO Y/0 CONTRASEÑA");
        }

    }

    //metodo para verificar usuario ingresada por el operador
    public String validarUsuario(){
        String usuario=txusuario.getText();//SE TOMA EL VALOR QUE SE DIGITA
        return usuario;
    }

    //metodo para verificar contraseña ingresada por el operador
    public String validarContraseña(){
        String contraseña=txcontraseña.getText();//SE TOMA EL VALOR QUE SE DIGITA
        return contraseña;
    }


    public void panelComprobarCliente(){
        //Panel que tendrá las etiquetas y botones
        //fondo blanco del panel
        panelFondo.setLayout(null);
        panelFondo.setVisible(true);
        panelFondo.setOpaque(true);
        panelFondo.setBounds(0, 0, 1400, 800);
        panelFondo.setBackground(Color.white);

        panelBusqueda.setLayout(null);
        panelBusqueda.setVisible(true);
        panelBusqueda.setOpaque(true);
        panelBusqueda.setBounds(300, 150, 700, 400);
        panelBusqueda.setBackground(Color.white);

        String cliente= buscarClienteAntes();

        JLabel operadorText = new JLabel("BUSCAR CLIENTE");
        operadorText.setBackground(Color.black);
        operadorText.setFont(new Font("Arial", Font.BOLD, 40));
        operadorText.setBounds(180, 0, 600, 100);
        panelBusqueda.add(operadorText);

        txbuscarCliente.setBackground(new Color(234,234,234));
        txbuscarCliente.setFont(new Font("Arial", Font.BOLD, 40));
        txbuscarCliente.setBounds(60, 150, 600, 60);
        panelBusqueda.add(txbuscarCliente);


        botonBuscCliente.setVisible(true);
        botonBuscCliente.setBounds(600, 400, 150, 80);
        ImageIcon imgBus= new ImageIcon("src/Imagenes/botonBuscar.png");// se le pone icono a boton
        Icon ibus= new ImageIcon(imgBus.getImage().getScaledInstance(botonBuscCliente.getWidth(), botonBuscCliente.getHeight(), Image.SCALE_DEFAULT));
        botonBuscCliente.setIcon(ibus);
        botonBuscCliente.setLayout(null);
        botonBuscCliente.setOpaque(true);
        botonBuscCliente.setBorderPainted(false);
        ImageIcon imgBus2= new ImageIcon("src/Imagenes/botonBuscar2.png");// se le pone icono a boton
        Icon ibus2= new ImageIcon(imgBus2.getImage().getScaledInstance(botonBuscCliente.getWidth(), botonBuscCliente.getHeight(), Image.SCALE_DEFAULT));
        botonBuscCliente.setRolloverIcon(ibus2);
        botonBuscCliente.setBackground(Color.white);
        panelBusqueda.add(botonBuscCliente);
        botonBuscCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File("src/client.properties")));
                    ClienteOperador client = new ClienteOperador(
                            (String) properties.get("IP"),
                            (String) properties.get("PORTS"),
                            (String) properties.get("SERVICES"));

                    boolean valido=client.clienteExistente(txbuscarCliente.getText());

                    if(valido){
                        try {
                            ColasArray datosCliente=client.busquedaCliente(txbuscarCliente.getText());
                            setNombreCliente(datosCliente.dequeue().toString());
                            setDireccionCliente(datosCliente.dequeue().toString());
                            setTelefonoCliente(datosCliente.dequeue().toString());
                            setTipoCliente(datosCliente.dequeue().toString());
                            ColasArray pedidoCliente=client.pedidosFrecuentesCliente(txbuscarCliente.getText());
                            setPedidosCliente(pedidoCliente);
                            txbuscarCliente.setText(txbuscarCliente.getText());


                            panelOperador();
                            panelBusqueda.setVisible(false);
                            panelPedido.setVisible(true);
                            panelInformacion.setVisible(true);
                            panelPedido.setVisible(true);
                            botonBuscCliente.setVisible(false);
                            panelBlanco.setVisible(true);
                            panelFondo.setVisible(false);
                            fondo.setVisible(false);

                            txbuscarCliente.getText();
                            contenedor.add(panelBlanco,Integer.valueOf(7));
                            contenedor.add(panelPedido,Integer.valueOf(10));
                            contenedor.add(panelInformacion,Integer.valueOf(9));
                            contenedor.add(botonBuscar,Integer.valueOf(9));


                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        VistaOperadorDatos vistaOperadorDatos=new VistaOperadorDatos();
                        vistaOperadorDatos.setVisible(true);
                        vistaOperadorDatos.panelRegistroCliente();
                        panelBlanco.setVisible(false);
                        botonBuscCliente.setVisible(false);
                        fondo.setVisible(false);
                        dispose();
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        ImageIcon imagen =new ImageIcon("src/Imagenes/fondoLetras.png");
        fondo.setIcon(imagen);
        fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());



        contenedor.add(fondo,Integer.valueOf(10));
        contenedor.add(panelBusqueda,Integer.valueOf(12));
        contenedor.add(botonBuscCliente,Integer.valueOf(13));
        contenedor();

        //elegirPanelSiClienteExiste(true);


    }

    public String buscarClienteAntes(){
        String telefonoCliente= txbuscarCliente.getText();//se obtiene el telefono
        return telefonoCliente;//se retorna el numero telefonico
    }

    public void elegirPanelSiClienteExiste(Boolean existe){
        if(existe){
            botonBuscCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        Properties properties = new Properties();
                        properties.load(new FileInputStream(new File("src/client.properties")));
                        ClienteOperador client = new ClienteOperador(
                                (String) properties.get("IP"),
                                (String) properties.get("PORTS"),
                                (String) properties.get("SERVICES"));

                        ColasArray datosCliente=client.busquedaCliente(txbuscarCliente.getText());
                        setNombreCliente(datosCliente.dequeue().toString());
                        setDireccionCliente(datosCliente.dequeue().toString());
                        setTelefonoCliente(datosCliente.dequeue().toString());
                        setTipoCliente(datosCliente.dequeue().toString());
                        ColasArray pedidoCliente=client.pedidosFrecuentesCliente(txbuscarCliente.getText());
                        setPedidosCliente(pedidoCliente);


                        panelOperador();
                        panelBusqueda.setVisible(false);
                        panelPedido.setVisible(true);
                        panelInformacion.setVisible(true);
                        panelPedido.setVisible(true);
                        botonBuscCliente.setVisible(false);
                        panelBlanco.setVisible(true);
                        panelFondo.setVisible(false);
                        fondo.setVisible(false);

                        txbuscarCliente.getText();
                        contenedor.add(panelBlanco,Integer.valueOf(7));
                        contenedor.add(panelPedido,Integer.valueOf(10));
                        contenedor.add(panelInformacion,Integer.valueOf(9));
                        contenedor.add(botonBuscar,Integer.valueOf(9));


                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }else{
            botonBuscCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaOperadorDatos vistaOperadorDatos=new VistaOperadorDatos();
                    vistaOperadorDatos.setVisible(true);
                    vistaOperadorDatos.panelRegistroCliente();
                    panelBlanco.setVisible(false);
                    botonBuscCliente.setVisible(false);
                    fondo.setVisible(false);
                    dispose();
                }
            });
        }

    }

    //panel donde se busca al cliente
    public void panelOperador(){
        //Panel que tendrá las etiquetas y botones
        //fondo blanco del panel
        panelBlanco.setLayout(null);
        panelBlanco.setVisible(true);
        panelBlanco.setOpaque(true);
        panelBlanco.setBounds(0, 0, 1400, 800);
        panelBlanco.setBackground(Color.white);

        //panel para mostrar los pedidos frecuentes
        panelPedido.setLayout(null);
        panelPedido.setVisible(true);
        panelPedido.setOpaque(true);
        Color colorPanel=new Color(234,234,234);
        panelPedido.setBounds(700, 150, 550, 400);
        panelPedido.setBackground(colorPanel);

        //panel para la informacion del usuario
        panelInformacion.setLayout(null);
        panelInformacion.setVisible(true);
        panelInformacion.setOpaque(true);
        panelInformacion.setBounds(100, 150, 450, 450);
        panelInformacion.setBackground(Color.white);

        JLabel pedidosCliente = new JLabel("Pedidos Frecuentes");//letrero de titulo
        pedidosCliente.setBackground(Color.black);
        pedidosCliente.setFont(new Font("Arial", Font.BOLD, 30));
        pedidosCliente.setBounds(110, -5, 300, 80);
        panelPedido.add(pedidosCliente);

        JLabel infoCliente = new JLabel("Información Cliente");//letrero de titulo
        infoCliente.setBackground(Color.black);
        infoCliente.setFont(new Font("Arial", Font.PLAIN, 30));
        infoCliente.setBounds(70, 5, 300, 80);
        panelInformacion.add(infoCliente);

        JLabel fondoCliente = new JLabel("Logo");//Foto de la salchicha
        fondoCliente.setBounds(100, 50, 150, 200);
        ImageIcon imgLogo = new ImageIcon("src/Imagenes/tipoCliente.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(fondoCliente.getWidth(), fondoCliente.getHeight(), Image.SCALE_DEFAULT));
        fondoCliente.setIcon(ilogo);
        panelInformacion.add(fondoCliente);

        botonBuscar.setBounds(1050, 40, 150, 80);
        ImageIcon imgBus= new ImageIcon("src/Imagenes/botonBuscar.png");// se le pone icono a boton
        Icon ibus= new ImageIcon(imgBus.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setIcon(ibus);
        botonBuscar.setLayout(null);
        botonBuscar.setOpaque(true);
        botonBuscar.setBorderPainted(false);
        ImageIcon imgBus2= new ImageIcon("src/Imagenes/botonBuscar2.png");// se le pone icono a boton
        Icon ibus2= new ImageIcon(imgBus2.getImage().getScaledInstance(botonBuscar.getWidth(), botonBuscar.getHeight(), Image.SCALE_DEFAULT));
        botonBuscar.setRolloverIcon(ibus2);
        botonBuscar.setBackground(Color.white);


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelComprobarCliente();
                panelInformacion.setVisible(false);
                panelPedido.setVisible(false);
                botonBuscar.setVisible(false);
                panelBlanco.setVisible(false);
                botonBuscCliente.setVisible(true);
                panelBusqueda.setVisible(true);
                panelFondo.setVisible(true);
                fondo.setVisible(true);

                contenedor.add(panelFondo,Integer.valueOf(11));
                contenedor.add(panelBusqueda,Integer.valueOf(12));
                contenedor.add(botonBuscCliente,Integer.valueOf(13));
            }
        });
        panelBlanco.add(botonBuscar);


        botonRegistrar.setBounds(300, 600, 200, 100);
        ImageIcon imgRegis= new ImageIcon("src/Imagenes/botonRegistrar.png");// se le pone icono a boton
        Icon iRegis= new ImageIcon(imgRegis.getImage().getScaledInstance(botonRegistrar.getWidth(), botonRegistrar.getHeight(), Image.SCALE_DEFAULT));
        botonRegistrar.setIcon(iRegis);
        botonRegistrar.setLayout(null);
        botonRegistrar.setOpaque(true);
        botonRegistrar.setBorderPainted(false);
        ImageIcon imgRegis2= new ImageIcon("src/Imagenes/botonRegistrar2.png");// se le pone icono a boton
        Icon iRegis2= new ImageIcon(imgRegis2.getImage().getScaledInstance(botonRegistrar.getWidth(), botonRegistrar.getHeight(), Image.SCALE_DEFAULT));
        botonRegistrar.setRolloverIcon(iRegis2);
        botonRegistrar.setBackground(Color.white);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                panelPedido.setVisible(false);
                VistaOperadorDatos ventanaDatos=new VistaOperadorDatos();
                ventanaDatos.setVisible(true);
                ventanaDatos.panelRegistroCliente();
                dispose();
            }
        }); // added closing brace here
        panelBlanco.add(botonRegistrar);

        botonIngresar=new JButton(); //boton para ingresar pedido
        botonIngresar.setBounds(500, 600, 200, 100);
        ImageIcon imgR= new ImageIcon("src/Imagenes/btningresarPedido.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("src/Imagenes/btningresarPedido2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaOperadorDatos ventanaDatos=new VistaOperadorDatos();
                ventanaDatos.setVisible(true);
                ventanaDatos.panelIngresarPedido();
                dispose();
            }
        });
        panelBlanco.add(botonIngresar);

        botonActPedido=new JButton();//boton para actualizar pedido
        botonActPedido.setBounds(700, 600, 200, 100);
        ImageIcon imgactPedido= new ImageIcon("src/Imagenes/botonActPedido.png");// se le pone icono a boton
        Icon iActPedi= new ImageIcon(imgactPedido.getImage().getScaledInstance(botonActPedido.getWidth(), botonActPedido.getHeight(), Image.SCALE_DEFAULT));
        botonActPedido.setIcon(iActPedi);
        botonActPedido.setLayout(null);
        botonActPedido.setOpaque(true);
        botonActPedido.setBorderPainted(false);
        ImageIcon imgactPedido2= new ImageIcon("src/Imagenes/botonActPedido2.png");// se le pone icono a boton
        Icon iActPedi2= new ImageIcon(imgactPedido2.getImage().getScaledInstance(botonActPedido.getWidth(), botonActPedido.getHeight(), Image.SCALE_DEFAULT));
        botonActPedido.setRolloverIcon(iActPedi2);
        botonActPedido.setBackground(Color.white);

        botonActPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaOperadorDatos ventanaDatos=new VistaOperadorDatos();
                ventanaDatos.setVisible(true);
                ventanaDatos.panelActualizarPedido();
                dispose();
            }
        });
        panelBlanco.add(botonActPedido);

        botonActDatos=new JButton();//boton para actualizar datos
        botonActDatos.setBounds(900, 600, 200, 100);
        ImageIcon imgdatos= new ImageIcon("src/Imagenes/botonActDatos.png");// se le pone icono a boton
        Icon idatos= new ImageIcon(imgdatos.getImage().getScaledInstance(botonActDatos.getWidth(), botonActDatos.getHeight(), Image.SCALE_DEFAULT));
        botonActDatos.setIcon(idatos);
        botonActDatos.setLayout(null);
        botonActDatos.setOpaque(true);
        botonActDatos.setBorderPainted(false);
        ImageIcon imgadatos= new ImageIcon("src/Imagenes/botonActDatos2.png");// se le pone icono a boton
        Icon iconDatos= new ImageIcon(imgadatos.getImage().getScaledInstance(botonActDatos.getWidth(), botonActDatos.getHeight(), Image.SCALE_DEFAULT));
        botonActDatos.setRolloverIcon(iconDatos);
        botonActDatos.setBackground(Color.white);

        botonActDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaOperadorDatos ventanaDatos=new VistaOperadorDatos();
                ventanaDatos.setVisible(true);
                ventanaDatos.panelActualizarDatos();
                dispose();
            }
        });
        panelBlanco.add(botonActDatos);


      //informacionCliente();
      contenedor();
        contenedor.add(panelBlanco,Integer.valueOf(6));
        contenedor.add(panelPedido,Integer.valueOf(7));
        contenedor.add(panelInformacion,Integer.valueOf(7));
        contenedor.add(botonBuscar,Integer.valueOf(7));
    }

    //metodo en donde se da la informacion del cliente
    public void informacionCliente(){ //metodo temporal
        setNombreCliente("Maria Perez");
        setTelefonoCliente("3157660279");
        setDireccionCliente("calle 21 d # 26");
        setTipoCliente("Premium");
    }

    //metodo para busqueda de cliente por telefono del mismo


    //estos metodos debe llamarlos el controlador

    //segun la busqueda editar el nombre del cliente
    public void setNombreCliente(String name){
        JLabel nombreEstatico = new JLabel("Nombre: ");
        nombreEstatico.setBackground(Color.black);
        nombreEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        nombreEstatico.setBounds(50, 250, 300, 40);
        panelInformacion.add(nombreEstatico);

        JLabel nombreUsuario = new JLabel(name);
        nombreUsuario.setBackground(Color.black);
        nombreUsuario.setFont(new Font("Arial", Font.ITALIC, 20));
        nombreUsuario.setBounds(160, 250, 300, 40);
        panelInformacion.add(nombreUsuario);
    }

    //segun la busqueda editar el telefono del cliente
    public void setTelefonoCliente(String telefono){
        JLabel telefonoEstatico = new JLabel("Telefono: ");// letra estatica
        telefonoEstatico.setBackground(Color.black);
        telefonoEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoEstatico.setBounds(50, 280, 300, 40);
        panelInformacion.add(telefonoEstatico);

        JLabel numtelefono = new JLabel(telefono);
        numtelefono.setBackground(Color.black);
        numtelefono.setFont(new Font("Arial", Font.ITALIC, 20));
        numtelefono.setBounds(160, 280, 300, 40);
        panelInformacion.add(numtelefono);
    }

    //segun la busqueda editar el direccion del cliente
    public void setDireccionCliente(String direccion){

        JLabel direccionFija = new JLabel("Direccion: ");// letra estatica
        direccionFija.setBackground(Color.black);
        direccionFija.setFont(new Font("Arial", Font.BOLD, 20));
        direccionFija.setBounds(50, 310, 300, 40);
        panelInformacion.add(direccionFija);

        JLabel direccionCliente = new JLabel(direccion);//letra del cliente
        direccionCliente.setBackground(Color.black);
        direccionCliente.setFont(new Font("Arial", Font.ITALIC, 20));
        direccionCliente.setBounds(160, 310, 300, 40);
        panelInformacion.add(direccionCliente);
    }

    //segun la busqueda editar el tipo del cliente
    public void setTipoCliente(String tipo){

        JLabel tipoEstatico = new JLabel("Tipo Cliente: ");// letra estatica
        tipoEstatico.setBackground(Color.black);
        tipoEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        tipoEstatico.setBounds(50, 340, 300, 40);
        panelInformacion.add(tipoEstatico);

        JLabel tipoCliente = new JLabel(tipo);//letra del cliente
        tipoCliente.setBackground(Color.black);
        tipoCliente.setFont(new Font("Arial", Font.ITALIC, 20));
        tipoCliente.setBounds(180, 340, 300, 40);
        panelInformacion.add(tipoCliente);
    }


    //metodo para editan los pedidos frecuentes del cliente
    public void setPedidosCliente(ColasArray pedidosFrecuentes){
        int y=50;//se define la altura

        while(pedidosFrecuentes.size()!=0 || pedidosFrecuentes!=null) {
            JLabel titulo = new JLabel(pedidosFrecuentes.dequeue().toString());
            titulo.setBackground(Color.black);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setBounds(100, y, 600, 100);
            panelPedido.add(titulo);

            JLabel producCantidad = new JLabel(pedidosFrecuentes.dequeue().toString());
            producCantidad.setBackground(Color.black);
            producCantidad.setFont(new Font("Arial", Font.BOLD, 20));
            producCantidad.setBounds(320, y, 600, 100);
            panelPedido.add(producCantidad);

            JLabel prodCodigo = new JLabel(pedidosFrecuentes.dequeue().toString());
            prodCodigo.setBackground(Color.black);
            prodCodigo.setFont(new Font("Arial", Font.BOLD, 20));
            prodCodigo.setBounds(450, y, 600, 100);
            panelPedido.add(prodCodigo);

            y += 40;//se le agrega distancia a y para la ubicacion del texto
            //otorga espacios
            JLabel espacio = new JLabel();
            espacio.setBackground(Color.white);
            espacio.setFont(new Font("Arial", Font.BOLD, 20));
            espacio.setBounds(100, y + 10, 600, 100);
            panelPedido.add(espacio);
        }

    }


    //contenedor de capas
    public void contenedor(){

        //  contenedor.add(panelInicio,Integer.valueOf(2));

        this.getContentPane().add(contenedor);
        this.setSize(getMaximumSize());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
