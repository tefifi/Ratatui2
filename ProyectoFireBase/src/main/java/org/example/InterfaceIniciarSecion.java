package org.example;

import javax.swing.*;

public class InterfaceIniciarSecion {
    private JPanel interfaceIniciarSecion;
    private JPasswordField passwordUsuario;
    private JTextField textoUsername;
    private JLabel LabelUsername;
    private JLabel LabelPassword;
    private JButton atrasButton;
    private JCheckBox mostrarContraseñaCheckBox;
    private JFrame frame;

    InterfaceIniciarSecion() {
        atrasButton.addActionListener(e -> abrirInterfaz(new MenuPrincipal()));
        mostrarContraseñaCheckBox.addActionListener(e -> {
            if (mostrarContraseñaCheckBox.isSelected()) {
                passwordUsuario.setEchoChar((char) 0); // Mostrar
            } else {
                passwordUsuario.setEchoChar('*'); // Ocultar
            }
        });
    }

    private void abrirInterfaz(MenuPrincipal interfaz) {
        interfaz.mostrarMenuPrincipal();
        cerrarVentana();
    }

    private void cerrarVentana() {
        if (frame != null) {
            frame.dispose();
        }
    }

    public void obtenerTexto(){
        //esto es para obtener el username
        String usuario = textoUsername.getText();
        //esto es para obtener la contraseña
        String password = new String(passwordUsuario.getPassword());

        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);

    }

    public void mostarInterfaceIniciarSecion(){
        frame = new JFrame("InterfaceIniciarSecion");
        frame.setContentPane(interfaceIniciarSecion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);


        frame.setVisible(true);
    }
}
