import fi.tuni.prog3.round8.xmlcountries.Country;
import fi.tuni.prog3.round8.xmlcountries.CountryData;

import java.io.IOException;
import java.util.List;

public class CountriesXML {

    public static void main(String[] args) throws IOException {
        String areaFile = args[0];
        String populationFile = args[1];
        String gdpFile = args[2];
        String outputFile = args[3];

        List<Country> cData = CountryData.readFromXmls(areaFile, populationFile, gdpFile);
        CountryData.writeToXml(cData, outputFile);
    }
}
