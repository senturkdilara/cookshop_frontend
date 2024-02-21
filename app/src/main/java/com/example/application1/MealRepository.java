package com.example.application1;

import android.os.Handler;
import android.os.Message;

import com.google.android.material.expandable.ExpandableWidget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MealRepository {

    public void getListOfRecipes(ExecutorService srv) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://localhost:8080/getMeals");

                // Convert URLConnection to HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // Set the request method to GET
                conn.setRequestMethod("GET");

                // Access input stream using the conn object
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                // Read the JSON response
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                // Disconnect the connection
                conn.disconnect();

                // Parse the JSON response
                JSONArray jsonArray = new JSONArray(buffer.toString());

                // Process each recipe in the array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject recipeObject = jsonArray.getJSONObject(i);

                    // Extract recipe details
                    String id = recipeObject.getString("id");
                    String name = recipeObject.getString("name");
                    JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");
                    String recipeText = recipeObject.getString("recipe");

                    // Do something with the recipe details, e.g., create Recipe objects, update UI, etc.

                    // For now, print the details to the console
                    System.out.println("Recipe ID: " + id);
                    System.out.println("Recipe Name: " + name);
                    System.out.println("Ingredients: " + ingredientsArray.toString());
                    System.out.println("Recipe: " + recipeText);
                    System.out.println("------------------------");
                }



                // Convert the JSONArray to a String to send to the UI thread
                String jsonString = jsonArray.toString();

                // Send the parsed data to the UI using a Message
                Message msg = new Message();
                msg.obj = jsonString;  // Send the JSON string
                //uiHandler.sendMessage(msg);

                System.out.println(msg.obj.toString());



            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }





}
