package fi.tuni.prog3.round8.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountryData {

    private class MyJObject {
        private Root Root;
    }
    private class Root {
        private Data data;
    }
    private class Data {
        private List<Record> record;
    }
    private class Record {
        private List<Field> field;
    }
    private class Field {
        private String value;
        private Attributes attributes;
    }
    private class Attributes {
        private String name;
        private String key;
    }

    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) {
        List<Country> countryList = new ArrayList<>();
        List<String> nameList = readFromJson(areaFile, "Country or Area");
        List<String> areaList = readFromJson(areaFile, "Value");
        List<String> popList = readFromJson(populationFile, "Value");
        List<String> gdpList = readFromJson(gdpFile, "Value");

        for (int i = 0; i < nameList.size(); i++) {
            String cName = nameList.get(i);
            double cArea = Double.parseDouble(areaList.get(i));
            long cPop = Long.parseLong(popList.get(i));
            double cGdp = Double.parseDouble(gdpList.get(i));
            countryList.add(new Country(cName, cArea, cPop, cGdp));
        }
        return countryList;
    }

    public static void writeToJson(List<Country> countries, String countryFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(countryFile)) {
            gson.toJson(countries, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readFromJson(String fileName, String attributeName) {
        List<String> outputList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            MyJObject jObj = gson.fromJson(br, MyJObject.class);

            // Producing a list of given attributes
            outputList = jObj.Root.data.record.stream()
                    .map(a -> a.field.stream()
                            .filter(f -> f.attributes.name.equals(attributeName))
                            .map(b -> b.value).collect(Collectors.joining()))
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return outputList;
    }
}
