package fi.tuni.prog3.round8.jsoncountries;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println(readFromJson(areaFile));

        return countryList;
    }

    public static void writeToJson(List<Country> countries, String countryFile) {
        System.out.println("This is writeToJson, called.");
    }

    private static MyJObject readFromJson(String fileName) {
        List<String> outputList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            MyJObject jObj = gson.fromJson(br, MyJObject.class);

            jObj.Root.data.record.stream()
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
