import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class FileHandler {
    public List<Building> loadBuildings(String filePath) throws Exception {
        if (filePath.endsWith(".csv")) {
            return loadBuildingsFromCSV(filePath);
        } else if (filePath.endsWith(".xml")) {
            return loadBuildingsFromXML(filePath);
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат файла.");
        }
    }

    private List<Building> loadBuildingsFromCSV(String filePath) throws IOException {
        List<Building> buildings = new ArrayList<>();

        Reader in = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(in);

        for (CSVRecord record : records) {
            String cityName = record.get("city");
            String streetName = record.get("street");
            int houseNumber = Integer.parseInt(record.get("house"));
            int floors = Integer.parseInt(record.get("floor"));
            buildings.add(new Building(cityName, streetName, houseNumber, floors));
        }

        return buildings;
    }

    private List<Building> loadBuildingsFromXML(String filePath) throws Exception {
        List<Building> buildings = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));

        NodeList itemNodes = doc.getElementsByTagName("item");

        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element itemElement = (Element) itemNodes.item(i);
            String cityName = itemElement.getAttribute("city");
            String streetName = itemElement.getAttribute("street");
            int houseNumber = Integer.parseInt(itemElement.getAttribute("house"));
            int floors = Integer.parseInt(itemElement.getAttribute("floor"));
            buildings.add(new Building(cityName, streetName, houseNumber, floors));
        }

        return buildings;
    }
}