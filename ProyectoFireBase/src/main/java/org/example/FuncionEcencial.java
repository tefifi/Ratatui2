package org.example;

import com.google.cloud.firestore.Firestore;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FuncionEcencial {
    protected Firestore connetarBaseDeDatos() {
        try {
            String json = new String(Files.readAllBytes(
                    Paths.get("proyectofinalratatui2-firebase-adminsdk-fbsvc-ba5b476da7.json")));

            String url = "https://proyectofinalratatui2.firebaseio.com/";

            ConnectionInfoFirebase connectionInfo = new ConnectionInfoFirebase(json, url);

            FirebaseAdapter adapter = new FirebaseAdapter();
            String resultado = adapter.createConnection(connectionInfo);
            System.out.println("Resultado conexión: " + resultado);

            Firestore db = adapter.getConnection();
            System.out.println("Instancia de Firestore obtenida: " + db);
            return db;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void crear(FirebaseCRUD crud) {
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", "Libro de Vicente");
        data.put("autor", "Vicente Hernández");
        data.put("año", 2025);

        String createRes = crud.create("libros", "libro1", data);
        System.out.println(createRes);


    }

    protected void leer(FirebaseCRUD crud) {

        Map<String, Object> leido = crud.read("libros", "libro1");
        System.out.println("Documento leído: " + leido);


    }

    protected void actualizar(FirebaseCRUD crud) {
        Map<String, Object> cambios = new HashMap<>();
        cambios.put("año", 2026);
        String updateRes = crud.update("libros", "libro1", cambios);
        System.out.println(updateRes);


    }

    protected void borrar(FirebaseCRUD crud) {
        String deleteRes = crud.delete("libros", "libro1");
        System.out.println(deleteRes);
    }

    protected String escribir(){
        Scanner teclado = new Scanner(System.in);
        String cadena = "";
        cadena = teclado.nextLine().trim();
        return cadena;

    }

    protected void mostrarQueIngrasar(){
        System.out.println("Ingrese el nombre de la colleción");
        System.out.println("Ingrese el nombre del documento");
        System.out.println();
    }
}
