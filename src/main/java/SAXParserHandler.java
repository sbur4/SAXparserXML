
import entity.Car;
import entity.Root;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {
    private static final String TAG_STRING = "stringTest";
    private static final String TAG_ARRAY = "arrayTest";
    private static final String TAG_CAR = "car";
    private static final String TAG_CAR_NAME = "name";
    private static final String TAG_CAR_MODEL = "model";

    Root root = new Root();
    private String currentTagName;
    private String name;
    private String model;
    private List<Car> carList = new ArrayList<>();
    private boolean isArray = false;
    private boolean isCar = false;

    public Root getRoot() {
        return root;
    }

    @Override
    public void startDocument() throws SAXException {
//        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
//        super.endDocument();
        root.setArrayTest(carList);
    }

    @Override
    public void startElement(String uri, String nameLocal, String qName, Attributes attributes) throws SAXException {
//        System.out.println(qName);
        currentTagName = qName;
        if (currentTagName.equals(TAG_ARRAY)) {
            isArray = true;
        } else if (currentTagName.equals(TAG_CAR)) {
            isCar = true;
        }
    }

    @Override
    public void endElement(String uri, String nameLocal, String qName) throws SAXException {
//        System.out.println(qName);

        if (qName != null) {
            if (qName.equals(TAG_ARRAY)) {
                isArray = false;
            }
            if (qName.equals(TAG_CAR)) {
                isCar = false;

            Car car = new Car(name, model);
            carList.add(car);
            }
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        super.characters(ch, start, length);
//        System.out.println("..." + new String(ch, start, length));
        if (currentTagName == null) {
            return;
        }
        if (!isArray && currentTagName.equals(TAG_STRING)) {
            root.setStringTest(new String(ch, start, length));
        }
        if (isArray && isCar) {
            if (currentTagName.equals(TAG_CAR_NAME)) {
                name = new String(ch, start, length);
            } else if (currentTagName.equals(TAG_CAR_MODEL)) {
                model = new String(ch, start, length);
            }
        }
    }
}
