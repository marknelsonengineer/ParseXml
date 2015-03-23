/**
 * Created by mark on 3/22/15.
 */
public class Product {
  public String vendor = "";
  public String product = "";
  public String version = "";

  public Product(String inString) {

    String[] parsedStrings = inString.split(":");

    if (parsedStrings.length >= 3) {
      this.vendor = parsedStrings[2];
    }
    if (parsedStrings.length >= 4) {
      this.product = parsedStrings[3];
    }
    if (parsedStrings.length >= 5) {
      this.version = parsedStrings[4];
    }
  }
}
