package VistaOperador;

import Estructuras.Colas.ColasArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOperador extends JFrame {

    JPanel panelGeneral = new JPanel();
    JPanel panelRegistro = new JPanel();
    JPanel panelInfo = new JPanel();
    JPanel panelBuscar = new JPanel();
    JPanel panelActCliente = new JPanel();
    JPanel panelActPedido = new JPanel();
    JPanel panelIngrePedido = new JPanel();
    JPanel panelPedidosEncontrados=new JPanel();

    JPanel panelPedidosFrecuentes=new JPanel();

    JPanel panelEditarInfo =new JPanel();



    JLayeredPane contenedor = new JLayeredPane();

    public VentanaOperador() {
        this.setTitle("OPERADOR");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.white);
        panelGeneral();
    }

    public void panelGeneral() {
        panelGeneral.setLayout(null);
        panelGeneral.setVisible(true);
        panelGeneral.setOpaque(true);
        panelGeneral.setBounds(0, 0, 1400, 800);
        panelGeneral.setBackground(Color.white);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);
        panelBotones.setVisible(true);
        panelBotones.setOpaque(true);
        panelBotones.setBounds(100, 100, 300, 500);
        panelBotones.setBackground(Color.white);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(30, 10, 200, 70);
        botonBuscar.setBackground(new Color(157,165,243));
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGeneral();
                panelGeneral.setVisible(true);
                panelBuscar.setVisible(true);

                panelInfo.setVisible(false);
                panelActPedido.setVisible(false);
                panelIngrePedido.setVisible(false);
                panelActCliente.setVisible(false);
                panelRegistro.setVisible(false);
                panelEditarInfo.setVisible(false);
                panelPedidosEncontrados.setVisible(false);
                panelPedidosFrecuentes.setVisible(false);
                panelBuscar();
            }
        });
        panelBotones.add(botonBuscar);

        JButton botonRegistrar = new JButton("Registrar Cliente");
        botonRegistrar.setBounds(30, 100, 200, 70);
        botonRegistrar.setBackground(new Color(157,165,243));
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGeneral();
                panelGeneral.setVisible(true);
                panelRegistro.setVisible(true);

                panelBuscar.setVisible(false);
                panelInfo.setVisible(false);
                panelActPedido.setVisible(false);
                panelIngrePedido.setVisible(false);
                panelActCliente.setVisible(false);
                panelEditarInfo.setVisible(false);
                panelPedidosEncontrados.setVisible(false);
                panelPedidosFrecuentes.setVisible(false);
                panelRegistro();
            }
        });
        panelBotones.add(botonRegistrar);

        JButton botonActCliente = new JButton("Actualizar Información");
        botonActCliente.setBounds(30, 190, 200, 70);
        botonActCliente.setBackground(new Color(157,165,243));
        botonActCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGeneral();
                panelGeneral.setVisible(true);
                panelActCliente.setVisible(true);

                panelBuscar.setVisible(false);
                panelInfo.setVisible(false);
                panelActPedido.setVisible(false);
                panelIngrePedido.setVisible(false);
                panelRegistro.setVisible(false);
                panelEditarInfo.setVisible(false);
                panelPedidosEncontrados.setVisible(false);
                panelPedidosFrecuentes.setVisible(false);
                panelActualizarInfoCliente();

            }
        });
        panelBotones.add(botonActCliente);

        JButton botonIngPedido = new JButton("Ingresar Pedido");
        botonIngPedido.setBounds(30, 280, 200, 70);
        botonIngPedido.setBackground(new Color(157,165,243));
        botonIngPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelIngresarPedido();
                panelGeneral.setVisible(true);
                panelIngrePedido.setVisible(true);

                panelBuscar.setVisible(false);
                panelInfo.setVisible(false);
                panelActPedido.setVisible(false);
                panelActCliente.setVisible(false);
                panelRegistro.setVisible(false);
                panelEditarInfo.setVisible(false);
                panelPedidosEncontrados.setVisible(false);
                panelPedidosFrecuentes.setVisible(false);
                panelGeneral();
            }
        });
        panelBotones.add(botonIngPedido);

        JButton botonActPedido = new JButton("Actualizar pedido ");
        botonActPedido.setBounds(30, 370, 200, 70);
        botonActPedido.setBackground(new Color(157,165,243));
        botonActPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelActualizarPedido();
                panelGeneral.setVisible(true);
                panelBuscar.setVisible(false);
                panelInfo.setVisible(false);
                panelActPedido.setVisible(true);
                panelIngrePedido.setVisible(false);
                panelActCliente.setVisible(false);
                panelRegistro.setVisible(false);
                panelEditarInfo.setVisible(false);
                panelPedidosEncontrados.setVisible(false);
                panelPedidosFrecuentes.setVisible(false);
                panelGeneral();
            }
        });
        panelBotones.add(botonActPedido);


        contenedor.add(panelGeneral, Integer.valueOf(1));
        contenedor.add(panelBotones, Integer.valueOf(8));
        contenedor();


    }

    public void panelRegistro(){

        panelRegistro.setLayout(null);
        panelRegistro.setVisible(true);
        panelRegistro.setOpaque(true);
        panelRegistro.setBounds(550, 100, 700, 500);
        panelRegistro.setBackground(new Color(157,165,243));

        JLabel registrarText = new JLabel("REGISTRAR: ");
        registrarText.setBackground(Color.black);
        registrarText.setFont(new Font("Arial", Font.BOLD, 20));
        registrarText.setBounds(300, 20, 200, 100);
        panelRegistro.add(registrarText);

        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(60, 120, 200, 100);
        panelRegistro.add(nombretext);
        //Label del numero telefonico
        JLabel telefonoText = new JLabel("TELEFONO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(60, 170, 200, 100);
        panelRegistro.add(telefonoText);

        JLabel direccionText = new JLabel("DIRECCIÓN: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(60, 220, 200, 100);
        panelRegistro.add(direccionText);

        JLabel tipoText = new JLabel("TIPO: ");
        tipoText.setBackground(Color.black);
        tipoText.setFont(new Font("Arial", Font.BOLD, 20));
        tipoText.setBounds(60, 270, 200, 100);
        panelRegistro.add(tipoText);

        JButton aceptar=new JButton("Aceptar");
        aceptar.setBounds(300,350,100,70);
        panelGeneral.add(aceptar);


        String nombre=ingresarNombre();
        String telefono=ingresarTelefono();
        String direccion=ingresarDireccion();
        String tipo=ingresarTipoCliente();

        contenedor.add(panelRegistro, Integer.valueOf(2));
    }

    public void panelBuscar(){

        panelBuscar.setLayout(null);
        panelBuscar.setVisible(true);
        panelBuscar.setOpaque(true);
        panelBuscar.setBounds(550, 100, 700, 500);
        panelBuscar.setBackground(new Color(157,165,243));

        String cliente= buscarCliente();

        JLabel operadorText = new JLabel("BUSCAR CLIENTE");
        operadorText.setBackground(Color.black);
        operadorText.setFont(new Font("Arial", Font.BOLD, 40));
        operadorText.setBounds(180, 0, 600, 100);
        panelBuscar.add(operadorText);

        JButton botonAceptar=new JButton("Aceptar");
        botonAceptar.setBounds(250,300,200,80);
        panelBuscar.add(botonAceptar);
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean estado=true;
                if(estado){
                    panelGeneral.setVisible(true);
                    panelBuscar.setVisible(false);
                    panelPedidosEncontrados.setVisible(true);
                    panelEditarInfo.setVisible(true);
                    panelInfo.setVisible(true);
                    panelInfoCliente();
                }else{
                    panelGeneral.setVisible(true);
                    panelBuscar.setVisible(false);
                    panelRegistro.setVisible(true);
                    panelRegistro();
                }
            }
        });

        contenedor.add(panelBuscar,Integer.valueOf(3));

    }

    public void panelInfoCliente(){
        panelPedidosFrecuentes.setLayout(null);
        panelPedidosFrecuentes.setVisible(true);
        panelPedidosFrecuentes.setOpaque(true);
        panelPedidosFrecuentes.setBounds(900, 100, 380, 450);
        panelPedidosFrecuentes.setBackground(new Color(157,165,243));

        //panel para la informacion del usuario
        panelEditarInfo.setLayout(null);
        panelEditarInfo.setVisible(true);
        panelEditarInfo.setOpaque(true);
        panelEditarInfo.setBounds(500, 100, 380, 450);
        panelEditarInfo.setBackground(new Color(157,165,243));

        JLabel pedidosCliente = new JLabel("Pedidos Frecuentes");//letrero de titulo
        pedidosCliente.setBackground(Color.black);
        pedidosCliente.setFont(new Font("Arial", Font.BOLD, 30));
        pedidosCliente.setBounds(40, 30, 300, 80);
        panelPedidosFrecuentes.add(pedidosCliente);

        JLabel infoCliente = new JLabel("Información Cliente");//letrero de titulo
        infoCliente.setBackground(Color.black);
        infoCliente.setFont(new Font("Arial", Font.PLAIN, 30));
        infoCliente.setBounds(70, 5, 300, 80);
        panelEditarInfo.add(infoCliente);

        contenedor.add(panelInfo,Integer.valueOf(3));
        contenedor.add(panelEditarInfo,Integer.valueOf(4));
        contenedor.add(panelPedidosFrecuentes,Integer.valueOf(5));
        contenedor();

        setNombreCliente("Santiago");
        setDireccionCliente("Poblado Giron");
        setTelefonoCliente("31494949494");
        setTipoCliente("Premium");
        setPedidosCliente(new ColasArray());

    }

    public void panelActualizarInfoCliente(){
        panelActCliente.setLayout(null);
        panelActCliente.setVisible(true);
        panelActCliente.setOpaque(true);
        panelActCliente.setBounds(550, 100, 700, 500);
        panelActCliente.setBackground(new Color(157,165,243));

        JLabel registrarText = new JLabel("ACTUALIZAR: ");
        registrarText.setBackground(Color.black);
        registrarText.setFont(new Font("Arial", Font.BOLD, 20));
        registrarText.setBounds(300, 20, 200, 100);
        panelActCliente.add(registrarText);

        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(60, 120, 200, 100);
        panelActCliente.add(nombretext);
        //Label del numero telefonico
        JLabel telefonoText = new JLabel("TELEFONO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(60, 170, 200, 100);
        panelActCliente.add(telefonoText);

        JLabel direccionText = new JLabel("DIRECCIÓN: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(60, 220, 200, 100);
        panelActCliente.add(direccionText);

        JLabel tipoText = new JLabel("TIPO: ");
        tipoText.setBackground(Color.black);
        tipoText.setFont(new Font("Arial", Font.BOLD, 20));
        tipoText.setBounds(60, 270, 200, 100);
        panelActCliente.add(tipoText);

        JButton aceptar=new JButton("Aceptar");
        aceptar.setBounds(300,350,100,70);
        panelGeneral.add(aceptar);


        String nombre=actualizarNombre();
        String telefono=actualizarTelefono();
        String direccion=actualizarDireccion();
        String tipo=actualizarTipoCliente();

        contenedor.add(panelActCliente, Integer.valueOf(2));
        contenedor();

    }

    public void panelIngresarPedido(){
        panelIngrePedido.setLayout(null);
        panelIngrePedido.setVisible(true);
        panelIngrePedido.setOpaque(true);
        panelIngrePedido.setBounds(550, 100, 700, 500);
        panelIngrePedido.setBackground(new Color(157,165,243));

        panelPedidosEncontrados.setLayout(null);
        panelPedidosEncontrados.setVisible(false);
        panelPedidosEncontrados.setOpaque(true);
        panelPedidosEncontrados.setBounds(100, 140, 200, 80);
        panelPedidosEncontrados.setBackground(Color.white);


        String buscar=buscarPedido();
        String producto=ingresarProducto();
        String cantidad=ingresarCantidad();
        String tamaño= ingresarReferencia();

        JLabel productoText = new JLabel("PRODUCTO: ");
        productoText.setBackground(Color.black);
        productoText.setFont(new Font("Arial", Font.BOLD, 20));
        productoText.setBounds(70, 190, 200, 100);
        panelIngrePedido.add(productoText);

        JLabel tamañoText = new JLabel("REFERENCIA: ");
        tamañoText.setBackground(Color.black);
        tamañoText.setFont(new Font("Arial", Font.BOLD, 20));
        tamañoText.setBounds(70, 260, 200, 100);
        panelIngrePedido.add(tamañoText);

        JLabel cantidadText = new JLabel("CANTIDAD: ");
        cantidadText.setBackground(Color.black);
        cantidadText.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadText.setBounds(70, 330, 200, 100);
        panelIngrePedido.add(cantidadText);

        JButton botonBuscar=new JButton("Buscar");
        botonBuscar.setBounds(510, 60, 90, 70);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPedidosEncontrados.setVisible(true);
                panelIngrePedido.setVisible(true);
            }
        });
        panelIngrePedido.add(botonBuscar);


        contenedor.add(panelIngrePedido, Integer.valueOf(2));
        contenedor.add(panelPedidosEncontrados, Integer.valueOf(3));

    }

    public void panelActualizarPedido(){
        panelActPedido.setLayout(null);
        panelActPedido.setVisible(true);
        panelActPedido.setOpaque(true);
        panelActPedido.setBounds(550, 100, 700, 500);
        panelActPedido.setBackground(new Color(157,165,243));

        panelPedidosEncontrados.setLayout(null);
        panelPedidosEncontrados.setVisible(false);
        panelPedidosEncontrados.setOpaque(true);
        panelPedidosEncontrados.setBounds(100, 140, 200, 80);
        panelPedidosEncontrados.setBackground(Color.white);


        String buscar=buscarPedido();
        String producto=actualizarProducto();
        String cantidad=actualizarCantidad();
        String tamaño= actualizarReferencia();

        JLabel letreroIngreso = new JLabel("ACTUALIZAR PEDIDO");
        letreroIngreso.setBackground(Color.black);
        letreroIngreso.setFont(new Font("Arial", Font.BOLD, 30));
        letreroIngreso.setBounds(100, 50, 600, 80);
        panelActPedido.add(letreroIngreso);


        JLabel productoText = new JLabel("PRODUCTO: ");
        productoText.setBackground(Color.black);
        productoText.setFont(new Font("Arial", Font.BOLD, 20));
        productoText.setBounds(70, 190, 200, 100);
        panelActPedido.add(productoText);

        JLabel tamañoText = new JLabel("REFERENCIA: ");
        tamañoText.setBackground(Color.black);
        tamañoText.setFont(new Font("Arial", Font.BOLD, 20));
        tamañoText.setBounds(70, 260, 200, 100);
        panelActPedido.add(tamañoText);

        JLabel cantidadText = new JLabel("CANTIDAD: ");
        cantidadText.setBackground(Color.black);
        cantidadText.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadText.setBounds(70, 330, 200, 100);
        panelActPedido.add(cantidadText);

        JButton botonBuscar=new JButton("Aceptar");
        botonBuscar.setBounds(510, 60, 90, 70);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPedidosEncontrados.setVisible(true);
                panelIngrePedido.setVisible(true);
            }
        });
        panelActPedido.add(botonBuscar);


        contenedor.add(panelPedidosEncontrados, Integer.valueOf(3));
        contenedor.add(panelActPedido, Integer.valueOf(2));
    }


    public boolean clienteExistente(boolean estado){
        return estado;
    }

    public String ingresarNombre(){
        JTextField txusuario = new JTextField();
        txusuario.setBackground(Color.white);
        txusuario.setFont(new Font("Arial", Font.BOLD, 20));
        txusuario.setBounds(300, 150, 220, 40);
        panelRegistro.add(txusuario);
        String usuario=txusuario.getText();// se obtiene el usuario
        return usuario;// se retorna
    }

    //metodo para obtener el telefono el cliente
    public String ingresarTelefono(){
        JTextField txTelefono = new JTextField();
        txTelefono.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txTelefono.setFont(new Font("Arial", Font.BOLD, 20));
        txTelefono.setBounds(300, 200, 220, 40);
        panelRegistro.add(txTelefono);
        String telefono=txTelefono.getText();// se obtiene el telefonor
        return telefono;// se retorna
    }

    //metodo para obtener la direccion el cliente
    public String ingresarDireccion(){
        JTextField txDireccion = new JTextField();
        txDireccion.setBackground(Color.white);
        txDireccion.setFont(new Font("Arial", Font.BOLD, 20));
        txDireccion.setBounds(300, 250, 220, 40);
        panelRegistro.add(txDireccion);
        String direccion=txDireccion.getText();
        return direccion;
    }

    //metodo para obtener el tipo de cliente el cliente
    public String ingresarTipoCliente(){
        JTextField txCliente = new JTextField();
        txCliente.setBackground(Color.white);
        txCliente.setFont(new Font("Arial", Font.BOLD, 20));
        txCliente.setBounds(300, 300, 220, 40);
        panelRegistro.add(txCliente);
        String tipo=txCliente.getText();
        return tipo;
    }


    public String actualizarNombre(){
        JTextField txusuario = new JTextField();
        txusuario.setBackground(Color.white);
        txusuario.setFont(new Font("Arial", Font.BOLD, 20));
        txusuario.setBounds(300, 150, 220, 40);
        panelActCliente.add(txusuario);
        String usuario=txusuario.getText();// se obtiene el usuario
        return usuario;// se retorna
    }

    //metodo para obtener el telefono el cliente
    public String actualizarTelefono(){
        JTextField txTelefono = new JTextField();
        txTelefono.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txTelefono.setFont(new Font("Arial", Font.BOLD, 20));
        txTelefono.setBounds(300, 200, 220, 40);
        panelActCliente.add(txTelefono);
        String telefono=txTelefono.getText();// se obtiene el telefonor
        return telefono;// se retorna
    }

    //metodo para obtener la direccion el cliente
    public String actualizarDireccion(){
        JTextField txDireccion = new JTextField();
        txDireccion.setBackground(Color.white);
        txDireccion.setFont(new Font("Arial", Font.BOLD, 20));
        txDireccion.setBounds(300, 250, 220, 40);
        panelActCliente.add(txDireccion);
        String direccion=txDireccion.getText();
        return direccion;
    }

    //metodo para obtener el tipo de cliente el cliente
    public String actualizarTipoCliente(){
        JTextField txCliente = new JTextField();
        txCliente.setBackground(Color.white);
        txCliente.setFont(new Font("Arial", Font.BOLD, 20));
        txCliente.setBounds(300, 300, 220, 40);
        panelActCliente.add(txCliente);
        String tipo=txCliente.getText();
        return tipo;
    }


    public String buscarCliente(){
        JTextField txbuscarCliente=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txbuscarCliente.setBackground(colorPanel);
        txbuscarCliente.setFont(new Font("Arial", Font.BOLD, 40));
        txbuscarCliente.setBounds(60, 150, 600, 60);
        String telefonoCliente= txbuscarCliente.getText();//se obtiene el telefono
        panelBuscar.add(txbuscarCliente);
        return telefonoCliente;//se retorna el numero telefonico
    }

    public String ingresarProducto(){
        JTextField txIngresarProducto=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarProducto.setBackground(colorPanel);
        txIngresarProducto.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarProducto.setBounds(300, 210, 300, 50);
        String producto=txIngresarProducto.getText();
        panelIngrePedido.add(txIngresarProducto);
        return producto;
    }

    //metodo para ingresar codigo del producto del pedido
    public String ingresarReferencia(){
        JTextField txIngresarTamaño=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarTamaño.setBackground(colorPanel);
        txIngresarTamaño.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarTamaño.setBounds(300, 280, 300, 50);
        String tamaño=txIngresarTamaño.getText();
        panelIngrePedido.add(txIngresarTamaño);
        return tamaño;
    }

    //metodo para ingresar cantidad del producto del pedido
    public String ingresarCantidad(){
        JTextField txIngresarCantidad=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarCantidad.setBackground(colorPanel);
        txIngresarCantidad.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarCantidad.setBounds(300, 350, 300, 50);
        String Cantidad=txIngresarCantidad.getText();
        panelIngrePedido.add(txIngresarCantidad);
        return Cantidad;
    }

    public String actualizarProducto(){
        JTextField txIngresarProducto=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarProducto.setBackground(colorPanel);
        txIngresarProducto.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarProducto.setBounds(300, 210, 300, 50);
        String producto=txIngresarProducto.getText();
        panelActPedido.add(txIngresarProducto);
        return producto;
    }

    //metodo para ingresar codigo del producto del pedido
    public String actualizarReferencia(){
        JTextField txIngresarTamaño=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarTamaño.setBackground(colorPanel);
        txIngresarTamaño.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarTamaño.setBounds(300, 280, 300, 50);
        String tamaño=txIngresarTamaño.getText();
        panelActPedido.add(txIngresarTamaño);
        return tamaño;
    }

    //metodo para ingresar cantidad del producto del pedido
    public String actualizarCantidad(){
        JTextField txIngresarCantidad=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txIngresarCantidad.setBackground(colorPanel);
        txIngresarCantidad.setFont(new Font("Arial", Font.BOLD, 20));
        txIngresarCantidad.setBounds(300, 350, 300, 50);
        String Cantidad=txIngresarCantidad.getText();
        panelActPedido.add(txIngresarCantidad);
        return Cantidad;
    }

    public String buscarPedido(){
        JTextField txbuscarPedido=new JTextField();
        Color colorPanel=new Color(234,234,234);
        txbuscarPedido.setBackground(colorPanel);
        txbuscarPedido.setFont(new Font("Arial", Font.BOLD, 30));
        txbuscarPedido.setBounds(40, 60, 450, 60);
        String nombrePedido=txbuscarPedido.getText();
        panelIngrePedido.add(txbuscarPedido);
        return nombrePedido;
    }

    public void mostrarPedidos(ColasArray pedidosEncontrados){
        //letrero del registro de clinetes en el panel
        int y=10;//se define la altura
        while(pedidosEncontrados.size()==0) {
            JLabel titulo=new JLabel(pedidosEncontrados.dequeue().toString());
            titulo.setBackground(Color.black);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setBounds(50,y,600,100);
            panelPedidosEncontrados.add(titulo);
            y+=40;//se le agrega distancia a y para la ubicacion del texto

        }
    }


    //estos metodos debe llamarlos el controlador

    //segun la busqueda editar el nombre del cliente
    public void setNombreCliente(String name){
        JLabel nombreEstatico = new JLabel("Nombre: ");
        nombreEstatico.setBackground(Color.black);
        nombreEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        nombreEstatico.setBounds(50, 150, 300, 40);
        panelEditarInfo.add(nombreEstatico);

        JLabel nombreUsuario = new JLabel(name);
        nombreUsuario.setBackground(Color.black);
        nombreUsuario.setFont(new Font("Arial", Font.ITALIC, 20));
        nombreUsuario.setBounds(160, 150, 300, 40);
        panelEditarInfo.add(nombreUsuario);
    }

    //segun la busqueda editar el telefono del cliente
    public void setTelefonoCliente(String telefono){
        JLabel telefonoEstatico = new JLabel("Telefono: ");// letra estatica
        telefonoEstatico.setBackground(Color.black);
        telefonoEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoEstatico.setBounds(50, 180, 300, 40);
        panelEditarInfo.add(telefonoEstatico);

        JLabel numtelefono = new JLabel(telefono);
        numtelefono.setBackground(Color.black);
        numtelefono.setFont(new Font("Arial", Font.ITALIC, 20));
        numtelefono.setBounds(160, 180, 300, 40);
        panelEditarInfo.add(numtelefono);
    }

    //segun la busqueda editar el direccion del cliente
    public void setDireccionCliente(String direccion){

        JLabel direccionFija = new JLabel("Direccion: ");// letra estatica
        direccionFija.setBackground(Color.black);
        direccionFija.setFont(new Font("Arial", Font.BOLD, 20));
        direccionFija.setBounds(50, 210, 300, 40);
        panelEditarInfo.add(direccionFija);

        JLabel direccionCliente = new JLabel(direccion);//letra del cliente
        direccionCliente.setBackground(Color.black);
        direccionCliente.setFont(new Font("Arial", Font.ITALIC, 20));
        direccionCliente.setBounds(160, 210, 300, 40);
        panelEditarInfo.add(direccionCliente);
    }

    //segun la busqueda editar el tipo del cliente
    public void setTipoCliente(String tipo){

        JLabel tipoEstatico = new JLabel("Tipo Cliente: ");// letra estatica
        tipoEstatico.setBackground(Color.black);
        tipoEstatico.setFont(new Font("Arial", Font.BOLD, 20));
        tipoEstatico.setBounds(50, 240, 300, 40);
        panelEditarInfo.add(tipoEstatico);

        JLabel tipoCliente = new JLabel(tipo);//letra del cliente
        tipoCliente.setBackground(Color.black);
        tipoCliente.setFont(new Font("Arial", Font.ITALIC, 20));
        tipoCliente.setBounds(180, 240, 300, 40);
        panelEditarInfo.add(tipoCliente);
    }


    //metodo para editan los pedidos frecuentes del cliente
    public void setPedidosCliente(ColasArray pedidosFrecuentes){
        int y=50;//se define la altura
        while(pedidosFrecuentes.size()==0) {
            JLabel titulo=new JLabel(pedidosFrecuentes.dequeue().toString());
            titulo.setBackground(Color.black);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setBounds(100,y,600,100);
            panelInfo.add(titulo);
            y+=40;//se le agrega distancia a y para la ubicacion del texto
            //otorga espacios
            JLabel espacio=new JLabel();
            espacio.setBackground(Color.white);
            espacio.setFont(new Font("Arial", Font.BOLD, 20));
            espacio.setBounds(100,y+10,600,100);
            panelPedidosFrecuentes.add(espacio);
        }
    }



    public void contenedor(){
        //  contenedor.add(panelInicio,Integer.valueOf(2));

        this.getContentPane().add(contenedor);
        this.setSize(getMaximumSize());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
