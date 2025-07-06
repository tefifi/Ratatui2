package org.example;

import javax.swing.*;

public class InterfaceCRUD {
    private JPanel interfaceCRUD;
    private JButton CREATEButton;
    private JButton READButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JFrame frame;

    public void mostrarInterfaceCRUD(){
        frame = new JFrame("InterfaceCRUD");
        frame.setContentPane(interfaceCRUD);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
