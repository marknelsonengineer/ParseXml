import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entry {
  private String cveId = "";
  private List<String> classifications = new ArrayList<String>();  // TODO:  Refactor classifications
  private String summary = "";
  private float score = 0;
  private String accessVector = "";
  private String accessComplexity = "";
  private String authentication = "";
  private String confidentialityImpact = "";
  private String integrityImpact = "";
  private String availabilityImpact = "";
  private List<Product> products = new ArrayList<Product>();
  private String publishedDateTime = "";

  private static Counter entryCount = new Counter("Entry");
  private static Counter accessVectorCount = new Counter("Access Vector");
  private static Counter accessComplexityCount = new Counter("Access Complexity");
  private static Counter authenticationCount = new Counter("Authentication");
  private static Counter confidentialityImpactCount = new Counter("Confidentiality Impact");
  private static Counter integrityImpactCount = new Counter("Integrity Impact");
  private static Counter availabilityImpactCount = new Counter("Availability Impact");
  private static Counter classificationCount = new Counter("Classification Count - many/entry");
  private static Counter scoreCount = new Counter("Score");
  private static Counter vendorCount = new Counter("Vendor - many/entry");
  private static Counter productCount = new Counter("Product - many/entry");


  public Entry() {
  }

  /**
   * Print contents of the Entry object.
   */
  public void dump() {
    System.out.printf("cve-id = [%s]\n", cveId);
    //for (String s : classifications) {
    //  System.out.printf("  cwe = [%s]\n", s);
    //}
    System.out.printf("  summary = [%s]\n", summary);
    //System.out.printf("  score = [%f]\n", score);
    //System.out.printf("  access vector = [%s]\n", accessVector);
    //System.out.printf("  access complexity = [%s]\n", accessComplexity);
    //System.out.printf("  authentication = [%s]\n", authentication);
    //System.out.printf("  confidentiality impact = [%s]\n", confidentialityImpact);
    //System.out.printf("  integrity impact = [%s]\n", integrityImpact);
    //System.out.printf("  availability impact = [%s]\n", availabilityImpact);
    //for (Product p : products) {
//      System.out.printf("  Product = [%s] [%s] [%s]\n", p.vendor, p.product, p.version);
  //  }
    //System.out.printf("  published datetime = [%s]\n", publishedDateTime);
  }

  public void summarizeCounters() {
    /*
    private static Counter classificationCount = new Counter("Classification Count - many/entry");
     */
    entryCount.countItem("Entry");
    availabilityImpactCount.countItem(availabilityImpact);
    integrityImpactCount.countItem(integrityImpact);
    confidentialityImpactCount.countItem(confidentialityImpact);
    accessVectorCount.countItem(accessVector);
    scoreCount.countItem(String.valueOf(this.score));
    accessComplexityCount.countItem(accessComplexity);
    authenticationCount.countItem(authentication);

    for(Object c : classifications.toArray()) {
      classificationCount.countItem(c.toString());
    }

    Map<String,String> uniqueVendors = new HashMap<String,String>();

    for (Product p : products) {
      uniqueVendors.put(p.vendor, p.vendor);
    }

    for (String v : uniqueVendors.keySet()) {
      vendorCount.countItem(v);
    }

    Map<String,String> uniqueProducts = new HashMap<String,String>();

    for (Product p : products) {
      uniqueProducts.put(p.product, p.product);
    }

    for (String v : uniqueProducts.keySet()) {
      productCount.countItem(v);
    }
  }

  public static void dumpCounters() {
    System.out.printf("\n");
    System.out.printf("\n");
    System.out.printf("Summary\n");
    System.out.printf("=============\n");
    entryCount.dump();
    accessVectorCount.dump();
    accessComplexityCount.dump();
    authenticationCount.dump();
    confidentialityImpactCount.dump();
    integrityImpactCount.dump();
    availabilityImpactCount.dump();
    classificationCount.dump();
    scoreCount.dump();
    vendorCount.dump();
    productCount.dump();
  }

  public boolean isInteresting() {
    if (classifications.contains("CWE-94")) {
      return true;
    }
    else {
      return false;
    }
  }

  public void setCveId(String cveId) {
    if ((this.cveId != null) && !(this.cveId.isEmpty())) {
      throw new RuntimeException( "Attempt to set CVE ID when it's already set.  Old value: [" + this.cveId
          + "]  New value: [" + cveId + "]");
    }
    this.cveId = cveId;
  }

  public void setClassification(String classification) {
    classifications.add(classification);
    //classificationCount.countItem(classification);
  }

  public void setProduct(String productInfo) {
    this.products.add(new Product(productInfo));
  }

  public void setPublishedDateTime(String publishedDateTime) {
    if ((this.publishedDateTime != null) && !(this.publishedDateTime.isEmpty())) {
      throw new RuntimeException( "Attempt to set published date-time when it's already set.  Old value: [" + this.publishedDateTime
          + "]  New value: [" + publishedDateTime + "]");
    }
    this.publishedDateTime = publishedDateTime;
  }

  public void setAvailabilityImpact(String availabilityImpact) {
    if ((this.availabilityImpact != null) && !(this.availabilityImpact.isEmpty())) {
      throw new RuntimeException( "Attempt to set availabilityImpact when it's already set.  Old value: [" + this.availabilityImpact
          + "]  New value: [" + availabilityImpact + "]");
    }
    this.availabilityImpact = availabilityImpact;
  }

  public void setIntegrityImpact(String integrityImpact) {
    if ((this.integrityImpact != null) && !(this.integrityImpact.isEmpty())) {
      throw new RuntimeException( "Attempt to set integrityImpact when it's already set.  Old value: [" + this.integrityImpact
          + "]  New value: [" + integrityImpact + "]");
    }
    this.integrityImpact = integrityImpact;
  }

  public void setConfidentialityImpact(String confidentialityImpact) {
    if ((this.confidentialityImpact != null) && !(this.confidentialityImpact.isEmpty())) {
      throw new RuntimeException( "Attempt to set confidentialityImpact when it's already set.  Old value: [" + this.confidentialityImpact
          + "]  New value: [" + confidentialityImpact + "]");
    }
    this.confidentialityImpact = confidentialityImpact;
  }

  public void setAuthentication(String authentication) {
    if ((this.authentication != null) && !(this.authentication.isEmpty())) {
      throw new RuntimeException( "Attempt to set authentication when it's already set.  Old value: [" + this.authentication
          + "]  New value: [" + authentication + "]");
    }
    this.authentication = authentication;
  }

  /** Set access complexity field. */
  public void setAccessComplexity(String accessComplexity) {
    if ((this.accessComplexity != null) && !(this.accessComplexity.isEmpty())) {
      throw new RuntimeException( "Attempt to set accessComplexity when it's already set.  Old value: [" + this.accessComplexity
          + "]  New value: [" + accessComplexity + "]");
    }
    this.accessComplexity = accessComplexity;
  }

  /** Set access vector field. */
  public void setAccessVector(String accessVector) {
    if ((this.accessVector != null) && !(this.accessVector.isEmpty())) {
      throw new RuntimeException( "Attempt to set accessVector when it's already set.  Old value: [" + this.accessVector
          + "]  New value: [" + accessVector + "]");
    }
    this.accessVector = accessVector;
  }

  /** Set score field. */
  public void setScore(String score) {
    if (this.score != 0) {
      throw new RuntimeException("Attempt to set score when it's already set.  Old score: [" + this.score
      + "]  New score: [" + score + "]");
    }
    this.score = new Float (score);
  }

  /** Set summary field. */
  public void setSummary(String summary) {

    if ((this.summary != null) && !(this.summary.isEmpty())) {
      throw new RuntimeException( "Attempt to set summary when it's already set.  Old summary: [" + this.summary
          + "]  New summary: [" + summary + "]");
    }
    this.summary = summary;
  }
}

