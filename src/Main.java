import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

/**
 * Mine data from the National Vulnerability Database.
 */
public class Main {

  /** Filename of XML test file to parse. */
  public static final String FILENAME_TEST =
      "/Users/mark/Desktop/InstructionSetRandomization/National Vulnerability Database/nvdcve-2.0-2015 test.xml";

  /** Filename of XML production file to parse. */
  public static final String FILENAME_FULL =
      "/Users/mark/Desktop/InstructionSetRandomization/National Vulnerability Database/nvdcve-2.0-2013-2014-2015.xml";

  /** True if printing debug statements. */
  public static final Boolean DEBUG = false;

  /**
   * Program entrypoint.
   *
   * @param args Unused.
   * @throws Exception Everything.
   */
  public static void main(String[] args) throws Exception {
    System.out.printf("Start parsing\n");

    XMLInputFactory factory = XMLInputFactory.newInstance();
    FileInputStream fis = new FileInputStream(FILENAME_FULL);

    XMLStreamReader reader = factory.createXMLStreamReader(fis);
    Entry entry = null;

    while (reader.hasNext()) {
      int event = reader.next();
      String element = "";

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          element = reader.getLocalName();
          switch (element) {
            case "entry":
              entry = new Entry();
              entry.setCveId(reader.getAttributeValue("", "id"));
              break;
            case "cwe":
              entry.setClassification(reader.getAttributeValue("", "id"));
              if (DEBUG) {
                System.out.printf("cwe: [%s]\n", reader.getAttributeValue("", "id"));
              }
              break;
            case "summary":
              reader.next();
              element = reader.getText().trim();
              entry.setSummary(element);
              break;
            case "score":
              reader.next();
              entry.setScore(reader.getText().trim());
              break;
            case "access-vector":
              reader.next();
              entry.setAccessVector(reader.getText().trim());
              break;
            case "access-complexity":
              reader.next();
              entry.setAccessComplexity(reader.getText().trim());
              break;
            case "authentication":
              reader.next();
              entry.setAuthentication(reader.getText().trim());
              break;
            case "confidentiality-impact":
              reader.next();
              entry.setConfidentialityImpact(reader.getText().trim());
              break;
            case "integrity-impact":
              reader.next();
              entry.setIntegrityImpact(reader.getText().trim());
              break;
            case "availability-impact":
              reader.next();
              entry.setAvailabilityImpact(reader.getText().trim());
              break;
            case "product":
              reader.next();
              entry.setProduct(reader.getText().trim());
              break;
            case "published-datetime":
              reader.next();
              entry.setPublishedDateTime(reader.getText().trim());
              break;
            default:
              if (DEBUG) {
                System.out.printf("Start element: [%s]\n", element);
              }
              break;
          }
          break;
        case XMLStreamConstants.CHARACTERS:
          element = reader.getText().trim();
          if (element.length() > 0 && DEBUG) {
            System.out.printf("Characters: [%s]\n", element);
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          element = reader.getLocalName();
          switch (element) {
            case "entry":
              if (entry.isInteresting()) {
                entry.summarizeCounters();
                entry.dump();
              }

              break;
            default:
              if (DEBUG) {
                System.out.printf("End element: [%s]\n", element);
              }
              break;
          }
          break;
        case XMLStreamConstants.START_DOCUMENT:
//          System.out.printf("Start document\n");
          break;
        default:
//          System.out.printf("Something else\n");
          break;
      }

    }

    Entry.dumpCounters();

    System.out.printf("Done parsing entries\n");
  }
}
