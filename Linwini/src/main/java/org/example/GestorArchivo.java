package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivo {
    public static String leerArchivo(String ruta) {
        Path rutaArchivo = Paths.get(ruta);
        String contenido = "";
        try{
            contenido = new String(Files.readAllBytes(rutaArchivo));
        }catch (IOException e){
            System.out.println("El archivo no puede ser leido");
        }
        return  contenido;
    }


    public static List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        boolean escapeNext = false;

        for (char c : line.toCharArray()) {
            if (escapeNext) {
                currentField.append(c);
                escapeNext = false;
                continue;
            }

            if (c == '\\') {
                escapeNext = true;
            } else if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString());
        return fields;
    }

}

