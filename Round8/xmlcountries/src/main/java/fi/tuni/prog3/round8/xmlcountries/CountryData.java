package fi.tuni.prog3.round8.xmlcountries;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryData {

    public static void main(String[] args) throws IOException {
        String areaFile = args[0];
        String populationFile = args[1];
        String gdpFile = args[2];
        String outputFile = args[3];

        List<Country> cData = readFromXmls(areaFile, populationFile, gdpFile);
        writeToXml(cData, outputFile);
    }

    public static List<Country> readFromXmls(
            String areaFile, String populationFile, String gdpFile) {
        List<Country> countryList = new ArrayList<Country>();

        try {
            SAXBuilder sax = new SAXBuilder();

            Document areaDoc = sax.build(new File(areaFile));
            Document popDoc = sax.build(new File(populationFile));
            Document gdpDoc = sax.build(new File(gdpFile));

            ArrayList<String> nameList = xmlToList(areaDoc, "Country or Area");
            ArrayList<String> areaList = xmlToList(areaDoc, "Value");
            ArrayList<String> popList = xmlToList(popDoc, "Value");
            ArrayList<String> gdpList = xmlToList(gdpDoc, "Value");

            for (int i = 0; i < nameList.size(); i++) {
                String cName = nameList.get(i);
                double cArea = Double.parseDouble(areaList.get(i));
                long cPop = Long.parseLong(popList.get(i));
                double cGdp = Double.parseDouble(gdpList.get(i));
                countryList.add(new Country(cName, cArea, cPop, cGdp));
            }

        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return countryList.stream().sorted().toList(); // Sorted alphabetically
    }

    public static void writeToXml(List<Country> countries, String countryFile)
        throws IOException {
        try {
            Element pCountries = new Element("countries");
            for (Country c : countries) {
                Element name = new Element("name");
                name.addContent(c.getName());
                Element area = new Element("area");
                area.addContent(Double.toString(c.getArea()));
                Element population = new Element("population");
                population.addContent(Long.toString(c.getPopulation()));
                Element gdp = new Element("gdp");
                gdp.addContent(Double.toString(c.getGdp()));

                // Country element
                Element country = new Element("country");
                country.addContent(name);
                country.addContent(area);
                country.addContent(population);
                country.addContent(gdp);

                // Countries parent
                pCountries.addContent(country);
            }
            // Generate a new document to write to output
            Document countriesDoc = new Document(pCountries);
            XMLOutputter xOut = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fileOutputStream = new FileOutputStream(countryFile)) {
                xOut.output(countriesDoc, fileOutputStream);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static ArrayList<String> xmlToList(Document doc, String attribute) {
        ArrayList<String> attributeList = new ArrayList<String>();
        Element root = doc.getRootElement();
        Element data = root.getChild("data");
        for (Element e: data.getChildren("record")){
            for (Element f: e.getChildren("field")) {
                String name = f.getAttributeValue("name");
                if (name.equals(attribute)) {
                    attributeList.add(f.getValue());
                }
            }
        }
        return attributeList;
    }
}
