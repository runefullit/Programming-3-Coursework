import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class SevenZipSearchMain {

    public static void main(String[] args) throws IOException {
        String fName = args[0];
        String sTerm = args[1];
        SevenZipSearchMain searcher = new SevenZipSearchMain(fName, sTerm);
        searcher.findTextFiles();
    }

    private final String filename;
    private final String sTerm;

    SevenZipSearchMain(String filename, String sTerm){
        this.filename = filename;
        this.sTerm = sTerm;
    }

    /*
    Find and return any .txt files within given .7z file.
     */
    private void findTextFiles() throws IOException{
        SevenZFile sevenZFile = new SevenZFile(new File(this.filename));
        SevenZArchiveEntry entry;
        try {
            while ((entry = sevenZFile.getNextEntry()) != null) {
                String name = entry.getName();
                if(name.endsWith(".txt")) {
                    System.out.println(name);
                    InputStream stream = sevenZFile.getInputStream(entry);
                    BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                    int lineNumber = 1;
                    String line;
                    while ((line = br.readLine()) != null){
                        // Finding case insensitive matches; contains doesn't support regex
                        if (line.toLowerCase().contains(sTerm.toLowerCase())){
                            String capsLine = line.replaceAll("(?i)" + sTerm, sTerm.toUpperCase());
                            System.out.format("%d: %s%n", lineNumber, capsLine);
                        }
                        lineNumber++;
                    }
                    System.out.println();

                }
            }
        }
        finally {
            sevenZFile.close();
        }
    }

}
