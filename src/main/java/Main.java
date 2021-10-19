import entity.Car;
import entity.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException {

        File file = new File("test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = null;
        try {
            doc = db.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Open error " + e);
            return;
        }

        Node rootNode = doc.getFirstChild();
//        System.out.println("Root: " + rootNode.getNodeName());
        NodeList rootChilds = rootNode.getChildNodes();

        Root root = new Root();
        String stringTestName = null;
        Node arrayTestNode = null;

        for (int n = 0; n < rootChilds.getLength(); n++) {
            if (rootChilds.item(n).getNodeType() != Node.ELEMENT_NODE) {
//                System.out.println(rootList.item(n).getNodeName());
                continue;
            }
            switch (rootChilds.item(n).getNodeName()) {
                case "stringTest": {
                    stringTestName = rootChilds.item(n).getTextContent();
//                        System.out.println(stringTestName);
                    break;
                }
                case "arrayTest": {
                    arrayTestNode = rootChilds.item(n);
                    break;
                }
            }
        }

        if (arrayTestNode == null) {
            return;
        }

        List<Car> carsList = new ArrayList<>();
        NodeList arrayTestrChilds = arrayTestNode.getChildNodes();
        for (int i = 0; i < arrayTestrChilds.getLength(); i++) {
            if (arrayTestrChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!arrayTestrChilds.item(i).getNodeName().equals("car")) {
                continue;
            }
            String carName = null;
            String modelCar = null;
            NodeList elementCar = arrayTestrChilds.item(i).getChildNodes();
            for (int j = 0; j < elementCar.getLength(); j++) {
                if (rootChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (elementCar.item(j).getNodeName()) {
                    case "name": {
                        carName = elementCar.item(j).getTextContent();
                        break;
                    }
                    case "model": {
                        modelCar = elementCar.item(j).getTextContent();
                        break;
                    }
                }
            }
            Car car = new Car(carName, modelCar);
            carsList.add(car);
        }
        root.setStringTest(stringTestName);
        root.setArrayTest(carsList);
        System.out.println(root);
    }
}
