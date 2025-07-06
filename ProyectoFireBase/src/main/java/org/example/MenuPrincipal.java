package org.example;

import javax.swing.*;

public class MenuPrincipal {
    private JPanel menuPrincipal;
    private JButton usuarioButton;
    private JButton superusuarioButton;
    private JButton salirButton;
    private JFrame frame;

    public MenuPrincipal() {
        usuarioButton.addActionListener(e -> abrirInterfaz(new InterfaceIniciarSecion()));
        superusuarioButton.addActionListener(e -> abrirInterfaz(new InterfaceIniciarSecion()));
        salirButton.addActionListener(e -> cerrarVentana());
    }

    private void abrirInterfaz(InterfaceIniciarSecion interfaz) {
        interfaz.mostarInterfaceIniciarSecion();
        cerrarVentana();
    }

    private void cerrarVentana() {
        if (frame != null) {
            frame.dispose();
        }
    }

    public void mostrarMenuPrincipal(){
        frame = new JFrame("MenuPrincipal");
        frame.setContentPane(menuPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
