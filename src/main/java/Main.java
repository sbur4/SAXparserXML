import entity.Root;

public class Main {
    public static void main(String[] args) {
        XMLParserSAX parser = new XMLParserSAX();
        Root root = parser.parse();
        System.out.println(root.toString());
    }
}

