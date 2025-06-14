

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeExtractor {
    private static final String SPOONACULAR_KEY = "fd4b1a32fcea4e8fb5a6d912b936dc09";
    private static final String OUTPUT_FILE = "data/recetas (1).csv";

    public List<JSONObject> obtenerRecetas(int n) {
        List<JSONObject> recetas = new ArrayList<>();
        int offset = 0;

        while (recetas.size() < n) {
            try {
                String urlString = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + SPOONACULAR_KEY + "&number=10&offset=" + offset;
                HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                if (conn.getResponseCode() != 200) {
                    System.out.println("Error en la conexión: " + conn.getResponseCode());
                    break;
                }

                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject data = new JSONObject(inline.toString());
                JSONArray resultados = data.getJSONArray("results");

                for (int i = 0; i < resultados.length(); i++) {
                    JSONObject receta = resultados.getJSONObject(i);
                    int recetaId = receta.getInt("id");
                    JSONObject recetaInfo = obtenerRecetaInfo(recetaId);
                    if (recetaInfo != null) {
                        recetas.add(recetaInfo);
                        System.out.println("✓ Receta agregada: " + recetaInfo.getString("title"));
                    }
                    if (recetas.size() >= n) {
                        break;
                    }
                }
                offset += 10;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
        return recetas;
    }

    private JSONObject obtenerRecetaInfo(int recetaId) {
        try {
            String urlString = "https://api.spoonacular.com/recipes/" + recetaId + "/information?apiKey=" + SPOONACULAR_KEY + "&includeNutrition=true";
            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                return null;
            }

            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            return new JSONObject(inline.toString());
        } catch (Exception e) {
            System.out.println("Error al obtener información de la receta: " + e.getMessage());
            return null;
        }
    }

    public void guardarCSV(List<JSONObject> recetas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            writer.write("title,servings,readyInMinutes,cookingMinutes,preparationMinutes,healthScore,spoonacularScore,pricePerServing," +
                    "Calories,Carbohydrates,Fat,Protein,Saturated Fat,Sodium,Sugar,Cholesterol,Fiber," +
                    "cheap,dairyFree,glutenFree,sustainable,vegan,vegetarian,veryHealthy,veryPopular,dishTypes,tipoComida,ingredientes\n");
            for (JSONObject receta : recetas) {
                writer.write(receta.getString("title") + "," +
                        getIntOrDefault(receta, "servings") + "," +
                        getIntOrDefault(receta, "readyInMinutes") + "," +
                        getIntOrDefault(receta, "cookingMinutes") + "," +
                        getIntOrDefault(receta, "preparationMinutes") + "," +
                        getIntOrDefault(receta, "healthScore") + "," +
                        getDoubleOrDefault(receta, "spoonacularScore") + "," +
                        getDoubleOrDefault(receta, "pricePerServing") + "," +
                        getNutrientOrDefault(receta, "Calories") + "," +
                        getNutrientOrDefault(receta, "Carbohydrates") + "," +
                        getNutrientOrDefault(receta, "Fat") + "," +
                        getNutrientOrDefault(receta, "Protein") + "," +
                        getNutrientOrDefault(receta, "Saturated Fat") + "," +
                        getNutrientOrDefault(receta, "Sodium") + "," +
                        getNutrientOrDefault(receta, "Sugar") + "," +
                        getNutrientOrDefault(receta, "Cholesterol") + "," +
                        getNutrientOrDefault(receta, "Fiber") + "," +
                        getBooleanOrDefault(receta, "cheap") + "," +
                        getBooleanOrDefault(receta, "dairyFree") + "," +
                        getBooleanOrDefault(receta, "glutenFree") + "," +
                        getBooleanOrDefault(receta, "sustainable") + "," +
                        getBooleanOrDefault(receta, "vegan") + "," +
                        getBooleanOrDefault(receta, "vegetarian") + "," +
                        getBooleanOrDefault(receta, "veryHealthy") + "," +
                        getBooleanOrDefault(receta, "veryPopular") + "," +
                        getDishTypes(receta) + "," +
                        clasificarTipoComida(receta) + "," +
                        getIngredientes(receta) + "\n");
            }
            System.out.println("✅ Datos guardados en " + OUTPUT_FILE);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    private String getIngredientes(JSONObject jsonObject) {
        if (jsonObject.has("extendedIngredients")) {
            JSONArray ingredientsArray = jsonObject.getJSONArray("extendedIngredients");
            StringBuilder ingredients = new StringBuilder();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                JSONObject ingredient = ingredientsArray.getJSONObject(i);
                ingredients.append(ingredient.getString("original"));
                if (i < ingredientsArray.length() - 1) {
                    ingredients.append(", ");
                }
            }
            return ingredients.toString();
        }
        return "";
    }

    private String clasificarTipoComida(JSONObject jsonObject) {
        if (jsonObject.has("dishTypes")) {
            JSONArray dishTypesArray = jsonObject.getJSONArray("dishTypes");
            for (int i = 0; i < dishTypesArray.length(); i++) {
                String dishType = dishTypesArray.getString(i);
                if (dishType.equals("breakfast")) {
                    return "desayuno";
                } else if (dishType.equals("main course") || dishType.equals("lunch") || dishType.equals("dinner")) {
                    return "almuerzo/cena";
                } else if (dishType.equals("snack")) {
                    return "snack";
                }
            }
        }
        return "otro";
    }

    private boolean getBooleanOrDefault(JSONObject jsonObject, String key) {
        return jsonObject.has(key) && !jsonObject.isNull(key) && jsonObject.getBoolean(key);
    }

    private double getNutrientOrDefault(JSONObject jsonObject, String nutrient) {
        if (jsonObject.has("nutrition")) {
            JSONObject nutrition = jsonObject.getJSONObject("nutrition");
            if (nutrition.has("nutrients")) {
                JSONArray nutrientsArray = nutrition.getJSONArray("nutrients");
                for (int i = 0; i < nutrientsArray.length(); i++) {
                    JSONObject nut = nutrientsArray.getJSONObject(i);
                    if (nut.getString("name").equals(nutrient)) {
                        return nut.getDouble("amount");
                    }
                }
            }
        }
        return 0.0;
    }

    private String getDishTypes(JSONObject jsonObject) {
        if (jsonObject.has("dishTypes")) {
            JSONArray dishTypesArray = jsonObject.getJSONArray("dishTypes");
            StringBuilder dishTypes = new StringBuilder();
            for (int i = 0; i < dishTypesArray.length(); i++) {
                dishTypes.append(dishTypesArray.getString(i));
                if (i < dishTypesArray.length() - 1) {
                    dishTypes.append(", ");
                }
            }
            return dishTypes.toString();
        }
        return "";
    }



    private int getIntOrDefault(JSONObject jsonObject, String key) {
        return jsonObject.has(key) && !jsonObject.isNull(key) ? jsonObject.getInt(key) : 0;
    }


    private double getDoubleOrDefault(JSONObject jsonObject, String key) {
        return jsonObject.has(key) && !jsonObject.isNull(key) ? jsonObject.getDouble(key) : 0.0;
    }



}
