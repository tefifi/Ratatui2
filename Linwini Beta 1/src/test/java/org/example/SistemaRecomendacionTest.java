package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SistemaRecomendacionTest {

    static class TestReceta extends Receta {
        public TestReceta(int azucar, int sodio, boolean vegan, boolean vegetarian, boolean gluten, boolean lactosa) {
            DatosNutricionales datos = new DatosNutricionales();
            datos.setAzucar(azucar);
            datos.setSodio(sodio);
            datos.setCarbohidratros(30);

            this.setDatosNutricionales(datos);
            this.setVegano(vegan);
            this.setVegetariano(vegetarian);
            this.setGluten(gluten);
            this.setLactosa(lactosa);
        }
    }

    @Test
    void testFiltrarReceta_SinCondiciones_AceptaReceta() {
        Usuario usuario = new Usuario();
        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);

        Receta receta = new TestReceta(15, 200, false, false, true, true);

        // Verificación
        Receta resultado = sistema.filtrarReceta(receta);
        assertNotNull(resultado, "Debería aceptar receta cuando no hay condiciones");
    }

    @Test
    void testFiltrarReceta_Diabetes_RechazaAzucarAlto() {
        Usuario usuario = new Usuario();
        usuario.getCondiciones().add(new Diabetes());

        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);
        Receta receta = new TestReceta(15, 50, true, true, false, false); // Azúcar alto

        Receta resultado = sistema.filtrarReceta(receta);
        assertNull(resultado, "Debería rechazar receta con azúcar alto (>10) para diabetes");
    }

    @Test
    void testFiltrarReceta_Hipertension_RechazaSodioAlto() {
        Usuario usuario = new Usuario();
        usuario.getCondiciones().add(new Hipertension());

        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);
        Receta receta = new TestReceta(5, 200, true, true, false, false); // Sodio alto

        Receta resultado = sistema.filtrarReceta(receta);
        assertNull(resultado, "Debería rechazar receta con sodio alto (>150) para hipertensión");
    }

    @Test
    void testFiltrarReceta_Vegano_RechazaNoVegana() {
        Usuario usuario = new Usuario();
        usuario.getCondiciones().add(new Vegano());

        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);
        Receta receta = new TestReceta(5, 50, false, true, false, false); // No vegana

        Receta resultado = sistema.filtrarReceta(receta);
        assertNull(resultado, "Debería rechazar receta no vegana para usuario vegano");
    }

    @Test
    void testFiltrarReceta_Vegano_AceptaVegana() {
        Usuario usuario = new Usuario();
        usuario.getCondiciones().add(new Vegano());

        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);
        Receta receta = new TestReceta(5, 50, true, true, false, false); // Vegana

        Receta resultado = sistema.filtrarReceta(receta);
        assertNotNull(resultado, "Debería aceptar receta vegana para usuario vegano");
    }

    @Test
    void testFiltrarReceta_MultiplesCondiciones_AceptaSiCumpleTodas() {
        Usuario usuario = new Usuario();
        usuario.getCondiciones().add(new Diabetes());
        usuario.getCondiciones().add(new Hipertension());
        usuario.getCondiciones().add(new Vegano());

        SistemaRecomendacion sistema = new SistemaRecomendacion(usuario);

        Receta receta = new TestReceta(5, 50, true, true, false, false);

        Receta resultado = sistema.filtrarReceta(receta);
        assertNotNull(resultado, "Debería aceptar receta que cumple todas las condiciones");
    }
}
