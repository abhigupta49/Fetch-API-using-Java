package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        String urlString = "https://api.chucknorris.io/jokes/random";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(responseCode==200){
            BufferedReader byteCodes = null;
            try {
                byteCodes = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder apiData = new StringBuilder();
            String readline = null;
            while (true){
                try {
                    if (!((readline = byteCodes.readLine())!=null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                apiData.append(readline);
            }

            try {
                byteCodes.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JSONObject jsonAPIResponse = new JSONObject(apiData.toString());

            System.out.println(jsonAPIResponse);
        }
        else{
            System.out.println("Bad request");
        }


    }
}