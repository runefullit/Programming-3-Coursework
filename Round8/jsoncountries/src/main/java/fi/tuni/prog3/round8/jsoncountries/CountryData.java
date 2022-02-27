package fi.tuni.prog3.round8.jsoncountries;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryData {

    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) {
        List<Country> countryList = new ArrayList<>();
        System.out.println(readFromJson(areaFile));

        return countryList;
    }

    public static void writeToJson(List<Country> countries, String countryFile) {
        System.out.println("This is writeToJson, called.");
    }

    private static List<String> readFromJson(String fileName) {
        List<String> outputList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileName));

            Map<?,?> map = gson.fromJson(reader, Map.class);

            for (Map.Entry<?,?> entry : map.entrySet()) {
                System.out.println(entry);
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return outputList;
    }

}
