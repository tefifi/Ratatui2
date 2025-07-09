package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testSetNombre() {
        usuario.setNombre("Juan");
        assertEquals("Juan", usuario.getNombre());
    }

    @Test
    void testSetEdadValida() {
        usuario.setEdad(30);
        assertEquals(30, usuario.getEdad());
    }

    @Test
    void testCalcularIMCPesoBajo() {
        usuario.setPeso(50);
        usuario.setAltura(1.70f);
        String resultado = usuario.calcularIMC();
        assertTrue(resultado.contains("Tienes un peso bajo"));
    }

    @Test
    void testCalcularIMCPesoNormal() {
        usuario.setPeso(70);
        usuario.setAltura(1.75f);
        String resultado = usuario.calcularIMC();
        assertTrue(resultado.contains("Tienes un peso normal"));
    }

    @Test
    void testAgregarCondicionDiabetes() {
        usuario.getCondiciones().add(new Diabetes());
        assertFalse(usuario.getCondiciones().isEmpty());
        assertEquals("diabetes", usuario.getCondiciones().get(0).getNombre());
    }

    @Test
    void testAgregarReceta() {
        Receta receta = new Receta();

        receta.atributosReceta();
        usuario.agregarReceta(receta);
        assertFalse(usuario.getRecetas().isEmpty());
        assertEquals(receta, usuario.getRecetas().get(0));
    }
}
