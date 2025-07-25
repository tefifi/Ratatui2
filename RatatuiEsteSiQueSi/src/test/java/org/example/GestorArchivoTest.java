package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorArchivoTest {

    private Path tempFilePath;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() throws IOException {

        if (tempFilePath != null && Files.exists(tempFilePath)) {
            Files.delete(tempFilePath);
            tempFilePath = null;
        }
    }


    @Test
    void testParseCsvLine_SimpleFields() {
        String line = "campo1,campo2,campo3";
        List<String> expected = Arrays.asList("campo1", "campo2", "campo3");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_FieldsWithCommasInQuotes() {
        String line = "campo1,\"campo,2\",campo3";
        List<String> expected = Arrays.asList("campo1", "campo,2", "campo3");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_FieldsWithEscapedQuotes() {
        String line = "campo1,\"campo\\\"2\",campo3";
        List<String> expected = Arrays.asList("campo1", "campo\"2", "campo3");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_EmptyFields() {
        String line = "campo1,,campo3";
        List<String> expected = Arrays.asList("campo1", "", "campo3");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_LeadingAndTrailingCommas() {
        String line = ",campo1,campo2,";
        List<String> expected = Arrays.asList("", "campo1", "campo2", "");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_OnlyQuotes() {
        String line = "\"\"";
        List<String> expected = Arrays.asList("");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_ComplexLine() {
        String line = "id,\"nombre, completo\",\"descripción con \\\"comillas\\\" y comas, y más\",valor";
        List<String> expected = Arrays.asList("id", "nombre, completo", "descripción con \"comillas\" y comas, y más", "valor");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_SingleField() {
        String line = "singlefield";
        List<String> expected = Arrays.asList("singlefield");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }

    @Test
    void testParseCsvLine_EmptyLine() {
        String line = "";
        List<String> expected = Arrays.asList("");
        assertEquals(expected, GestorArchivo.parseCsvLine(line));
    }


    @Test
    void testValidar_ValidLine() {
        GestorArchivo gestor = new GestorArchivo();
        String line = "campo1,campo2,campo3,campo4,campo5";
        assertTrue(gestor.validar(line));
    }

    @Test
    void testValidar_ValidLineMoreFields() {
        GestorArchivo gestor = new GestorArchivo();
        String line = "campo1,campo2,campo3,campo4,campo5,campo6";
        assertTrue(gestor.validar(line));
    }

    @Test
    void testValidar_InvalidLineFewerFields() {
        GestorArchivo gestor = new GestorArchivo();
        String line = "campo1,campo2,campo3,campo4";
        assertFalse(gestor.validar(line));
    }

    @Test
    void testValidar_NullLine() {
        GestorArchivo gestor = new GestorArchivo();
        assertFalse(gestor.validar(null));
    }

    @Test
    void testValidar_EmptyLine() {
        GestorArchivo gestor = new GestorArchivo();
        assertFalse(gestor.validar(""));
    }

    @Test
    void testValidar_BlankLine() {
        GestorArchivo gestor = new GestorArchivo();
        assertFalse(gestor.validar("   "));
    }


    @Test
    void testLeerArchivo_FileExistsAndReadsContent() throws IOException {

        String content = "Contenido de prueba\nOtra línea";
        tempFilePath = Files.createTempFile("testFile", ".txt");
        Files.writeString(tempFilePath, content);
        String readContent = GestorArchivo.leerArchivo(tempFilePath.toString());
        assertEquals(content, readContent);
    }

    @Test
    void testLeerArchivo_FileDoesNotExist() {

        String nonExistentPath = "non_existent_file_" + System.currentTimeMillis() + ".txt";
        String readContent = GestorArchivo.leerArchivo(nonExistentPath);
        assertEquals("", readContent);

    }

    @Test
    void testLeerArchivo_EmptyFile() throws IOException {

        tempFilePath = Files.createTempFile("emptyFile", ".txt");
        Files.writeString(tempFilePath, "");

        String readContent = GestorArchivo.leerArchivo(tempFilePath.toString());
        assertEquals("", readContent);
    }
}
