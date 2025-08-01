package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class InterfazGrafica extends JFrame {

    private Usuario usuario;

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = ImageIO.read(new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al cargar la imagen de fondo: " + fileName);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    private JButton crearBotonConImagen(String texto, String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);

        Image image = icono.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        icono = new ImageIcon(image);

        JButton boton = new JButton(texto, icono);

        boton.setHorizontalTextPosition(JButton.CENTER);
        boton.setVerticalTextPosition(JButton.CENTER);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);

        return boton;
    }


    public InterfazGrafica() {
        usuario = new Usuario();

        setTitle("Ratatui - Tu Asistente de Recetas Saludables");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel mainPanel = new BackgroundPanel("Data/remy.png");
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setOpaque(false);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("¡Bienvenido a Ratatui!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnIngresarDatos = crearBotonConImagen("Ingresar Datos", "Data/botonin.png");
        btnIngresarDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnIngresarDatos);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnAgregarCondicion = crearBotonConImagen("Agregar Condición de Salud","Data/botonin.png");
        btnAgregarCondicion.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnAgregarCondicion);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnMostrarCondiciones = crearBotonConImagen("Mostrar Condiciones", "Data/botonin.png");
        btnMostrarCondiciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnMostrarCondiciones);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnCalcularIMC = crearBotonConImagen("Calcular IMC", "Data/botonin.png");
        btnCalcularIMC.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnCalcularIMC);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnRecomendarReceta = crearBotonConImagen("Recomendar Receta", "Data/botonin.png");
        btnRecomendarReceta.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnRecomendarReceta);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnMostrarRecetas = crearBotonConImagen("Mostrar Recetas Guardadas", "Data/botonin.png");
        btnMostrarRecetas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnMostrarRecetas);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        Font font = new Font("Arial", Font.BOLD, 16);
        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setOpaque(false);
        outputArea.setBackground(new Color(0, 0, 0, 0));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);


        btnIngresarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(InterfazGrafica.this, "Ingresa tu nombre:");
                if (nombre != null && !nombre.trim().isEmpty()) {
                    usuario.setNombre(nombre);
                    String edadStr = JOptionPane.showInputDialog(InterfazGrafica.this, "Ingresa tu edad:");
                    try {
                        int edad = Integer.parseInt(edadStr);

                        if (edad < 0) throw Error.ingrDatos("La edad no puede ser negativa");
                        if (edad > 110) throw Error.ingrDatos("Edad inválida (máximo 110 años)");

                        usuario.setEdad(edad);
                        outputArea.append("Datos de usuario ingresados: " + usuario.getNombre() + ", " + usuario.getEdad() + " años.\n");

                    } catch (Exception ex) {
                        String mensaje;
                        if (ex instanceof Error) {
                            mensaje = ex.getMessage();
                        } else if (ex instanceof NumberFormatException) {
                            mensaje = "Se ingresaron mal los datos. Intentelo de nuevo.";
                        } else {
                            mensaje = "Error inesperado: " + ex.getMessage();
                        }
                        outputArea.append("Error: " + mensaje + "\n");
                    }
                }
            }
        });

        btnAgregarCondicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] condicionesDisponibles = {"diabetes", "hipertension", "celiaquia","hipolactasia","vegano","vegetariano"};
                String seleccion = (String) JOptionPane.showInputDialog(
                        InterfazGrafica.this,
                        "Selecciona una condición:",
                        "Agregar Condición",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        condicionesDisponibles,
                        condicionesDisponibles[0]);

                if (seleccion != null) {
                    switch (seleccion.toLowerCase()) {
                        case "diabetes":
                            usuario.getCondiciones().add(new Diabetes());
                            outputArea.append("Condición 'Diabetes' agregada.\n");
                            break;
                        case "hipertension":
                            usuario.getCondiciones().add(new Hipertension());
                            outputArea.append("Condición 'Hipertensión' agregada.\n");
                            break;
                        case "celiaquia":
                            usuario.getCondiciones().add(new Celiaquia());
                            outputArea.append("Condición 'Celiaquia' agregada.\n");
                            break;
                        case "hipolactasia":
                            usuario.getCondiciones().add(new Hipolactasia());
                            outputArea.append("Condición 'Hipolactasia' agregada.\n");
                            break;
                        case "vegano":
                            usuario.getCondiciones().add(new Vegano());
                            outputArea.append("Condición 'Vegano' agregada.\n");
                            break;
                        case "vegetariano":
                            usuario.getCondiciones().add(new Vegetariano());
                            outputArea.append("Condición 'Vegetariano' agregada.\n");
                            break;
                        default:
                            outputArea.append("Condición no reconocida.\n");
                            break;
                    }
                }
            }
        });

        btnMostrarCondiciones.addActionListener(e -> {
            if (usuario.getCondiciones().isEmpty()) {
                outputArea.append("No tienes condiciones registradas.\n");
            } else {
                outputArea.append("\n--- TUS CONDICIONES ---\n");
                usuario.getCondiciones().stream()
                        .map(Condicion::getNombre)
                        .forEach(nombre -> outputArea.append("- " + nombre + "\n"));
            }
        });

        btnCalcularIMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alturaStr = JOptionPane.showInputDialog(InterfazGrafica.this, "Ingresa tu altura (en metros, ej. 1.75):");
                String pesoStr = JOptionPane.showInputDialog(InterfazGrafica.this, "Ingresa tu peso (en kg, ej. 70.5):");

                try {
                    if (!alturaStr.matches("\\d+(\\.\\d+)?")) throw Error.formatoNum();
                    if (!pesoStr.matches("\\d+(\\.\\d+)?")) throw Error.formatoNum();

                    float altura = Float.parseFloat(alturaStr);
                    float peso = Float.parseFloat(pesoStr);


                    if (altura <= 0 || altura > 3) throw Error.datosIMC("La altura debe ser entre 0.1 y 3 metros");
                    if (peso <= 0 || peso > 300) throw Error.datosIMC("EL peso debe ser entre 1 y 300 kg");

                    usuario.setAltura(altura);
                    usuario.setPeso(peso);
                    String resultadoIMC = usuario.calcularIMC();
                    outputArea.append(resultadoIMC + "\n");

                } catch (Error err) {
                    outputArea.append(err.getMessage() + "\n");
                }
            }
        });

        btnRecomendarReceta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);
                Receta recetaRecomendada = null;
                int maxIntentos = 80;
                int intentos = 0;
                while (intentos < maxIntentos && recetaRecomendada == null) {
                    Receta candidata = new Receta();
                    candidata.atributosReceta();
                    recetaRecomendada = sistema.filtrarReceta(candidata);
                    intentos++;
                }

                if (recetaRecomendada != null) {
                    outputArea.append("\n--- Receta Recomendada ---\n");
                    outputArea.append("Nombre: " + recetaRecomendada.getNombre() + "\n");
                    outputArea.append("Tipo de comida: " + recetaRecomendada.getTipoComida() + "\n");
                    outputArea.append("Porciones: " + recetaRecomendada.getCantPorciones() + "\n");
                    outputArea.append("Listo en: " + recetaRecomendada.getListoEn() + " minutos\n");
                    outputArea.append("Vegano: " + recetaRecomendada.getVegano() + "\n");
                    outputArea.append("Vegetariano: " + recetaRecomendada.getVegetariano() + "\n");
                    outputArea.append("Lactosa: "+ recetaRecomendada.getLactosa()+ "\n");
                    outputArea.append("Gluten: "+ recetaRecomendada.getGluten()+ "\n");
                    outputArea.append("Saludable: " + recetaRecomendada.getSaludable() + "\n");
                    outputArea.append("Ingredientes: " + recetaRecomendada.getIngredientes() + "\n");
                    outputArea.append(recetaRecomendada.getDatosNutricionales().toString() + "\n");

                    int guardar = JOptionPane.showConfirmDialog(InterfazGrafica.this,
                            "¿Deseas guardar esta receta?", "Guardar Receta", JOptionPane.YES_NO_OPTION);
                    if (guardar == JOptionPane.YES_OPTION) {
                        usuario.agregarReceta(recetaRecomendada);
                        outputArea.append("Receta '" + recetaRecomendada.getNombre() + "' guardada.\n");
                    }
                } else {
                    outputArea.append("No se encontró una receta adecuada después de varios intentos.\n");
                }
            }
        });

        btnMostrarRecetas.addActionListener(e -> {
            if (usuario.getRecetas().isEmpty()) {
                outputArea.append("No tienes recetas guardadas.\n");
                return;
            }

            String[] nombresRecetas = usuario.getRecetas().stream()
                    .map(Receta::getNombre)
                    .toArray(String[]::new);

            String recetaSeleccionada = (String) JOptionPane.showInputDialog(
                    InterfazGrafica.this,
                    "Selecciona una receta para ver detalles:",
                    "Recetas Guardadas",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombresRecetas,
                    nombresRecetas[0]
            );

            usuario.getRecetas().stream()
                    .filter(r -> r.getNombre().equals(recetaSeleccionada))
                    .findFirst()
                    .ifPresent(receta -> {
                        outputArea.append("\n=== DETALLES DE RECETA ===\n");
                        outputArea.append("Nombre: " + receta.getNombre() + "\n");
                        outputArea.append("Tipo: " + receta.getTipoComida() + "\n");
                        outputArea.append("Porciones: " + receta.getCantPorciones() + "\n");
                        outputArea.append("Tiempo: " + receta.getListoEn() + " minutos\n");
                        outputArea.append("Vegano: " + (receta.getVegano() ? "Sí" : "No") + "\n");
                        outputArea.append("Vegetariano: " + (receta.getVegetariano() ? "Sí" : "No") + "\n");
                        outputArea.append("Lactosa: "+ (receta.getLactosa() ? "Si" : "No")+"\n");
                        outputArea.append("Gluten: "+ (receta.getGluten() ? "Si" : "No")+"\n");


                        DatosNutricionales datos = receta.getDatosNutricionales();
                        outputArea.append("\n--- Información Nutricional ---\n");
                        outputArea.append("Calorías: " + datos.getCalorias() + " kcal\n");
                        outputArea.append("Proteínas: " + datos.getProteina() + " g\n");
                        outputArea.append("Carbohidratos: " + datos.getCarbohidratos() + " g\n");
                        outputArea.append("Grasas: " + datos.getGrasas() + " g\n");
                        outputArea.append("Fibra: " + datos.getFibra() + " g\n");
                        outputArea.append("Azúcares: " + datos.getAzucar() + " g\n");
                        outputArea.append("Colesterol: " + datos.getColesterol() + " g\n");
                        outputArea.append("\nIngredientes:\n" + receta.getIngredientes() + "\n");
                        outputArea.append("----------------------------\n");
                    });
        });
    }


}

