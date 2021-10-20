import entity.Root;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLParserSAX {

    public Root parse() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParserHandler handler = new SAXParserHandler();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (Exception e) {
            System.out.println("Open file error");
            return null;
        }
        File file = new File("test.xml");
        try {
            saxParser.parse(file, handler);
        } catch (SAXException e) {
            System.out.println("Parsing error");
            return null;
        } catch (IOException e) {
            System.out.println("IO parsing error");
            return null;
        }
        return handler.getRoot();
    }
}


