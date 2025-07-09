package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List; // Asegúrate de importar List

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; // Si usas alguna aserción booleana

class DatosNutricionalesTest {

    private DatosNutricionales datosNutricionales;

    @BeforeEach
    void setUp() {
        datosNutricionales = new DatosNutricionales();
    }

    @Test
    void testInfoNutricionalPrimeraRecetaReal() throws IOException {
        // NOTA IMPORTANTE: Para que esta prueba funcione correctamente,
        // necesitas que GestorArchivo.leerArchivo("Data/recetas.csv")
        // lea el archivo real que tienes en tu proyecto.
        // Si GestorArchivo está mockeado o lee un archivo temporal,
        // el contenido de ese mock/temporal debe coincidir con la línea 1 del CSV real.

        // Si GestorArchivo lee directamente el archivo real, no necesitas crear un tempFile aquí.
        // Si GestorArchivo está siendo mockeado o se le pasa un path,
        // asegúrate de que el mock o el path apunten al contenido deseado.

        // Para fines de esta explicación, asumiremos que GestorArchivo
        // lee el archivo real y que la línea 1 es la "Red Lentil Soup..."

        // Llamar al método infoNutricional para la primera receta (índice 1)
        datosNutricionales.infoNutricional(1);

        // Verificar que los valores se establecieron correctamente
        // Estos valores son los de la primera receta de tu recetas.csv
        assertEquals(477, datosNutricionales.getCalorias()); // 477.24 redondeado a int
        assertEquals(51, datosNutricionales.getCarbohidratos()); // 51.78 redondeado a int
        assertEquals(20, datosNutricionales.getGrasas()); // 20.34 redondeado a int
        assertEquals(26, datosNutricionales.getProteina()); // 26.93 redondeado a int
        assertEquals(1335, datosNutricionales.getSodio()); // 1335.78 redondeado a int
        assertEquals(10, datosNutricionales.getAzucar()); // 10.55 redondeado a int
        assertEquals(29, datosNutricionales.getColesterol()); // 29.75 redondeado a int
        assertEquals(23, datosNutricionales.getFibra()); // 23.79 redondeado a int
    }

    @Test
    void testInfoNutricionalInvalidIndex() {
        // Llamar al método con un índice inválido
        datosNutricionales.infoNutricional(-1); // Índice inválido
        // No se debe lanzar ninguna excepción, simplemente no se debe modificar el estado
        assertEquals(0, datosNutricionales.getCalorias()); // Asumiendo que los atributos se inicializan en 0
    }

    // Puedes agregar más tests para otras líneas del CSV si lo deseas
    @Test
    void testInfoNutricionalSegundaRecetaReal() throws IOException {
        // La segunda receta en tu CSV es "Asparagus and Pea Soup: Real Convenience Food"
        // Sus valores son: 217.43, 28.83, 7.76, 10.84, 11.26, 11.54, 0.0, 11.17
        datosNutricionales.infoNutricional(2); // Índice 2 para la tercera línea del archivo (segunda receta)

        assertEquals(217, datosNutricionales.getCalorias());
        assertEquals(28, datosNutricionales.getCarbohidratos());
        assertEquals(7, datosNutricionales.getGrasas());
        assertEquals(10, datosNutricionales.getProteina());
        assertEquals(11, datosNutricionales.getSodio());
        assertEquals(11, datosNutricionales.getAzucar());
        assertEquals(0, datosNutricionales.getColesterol());
        assertEquals(11, datosNutricionales.getFibra());
    }
}
