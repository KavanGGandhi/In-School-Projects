/**
 * This class models an artwork piece
 *
 * @author Kavan Gandhi
 */
public class Artwork implements Comparable<Artwork> {

  private String name; // name of the artwork piece, artist
  private int year; // year in which the artwork was created
  private double cost; // cost of the artwork

  /**
   * Creates a new art piece with given attributes
   * 
   * @param name name of the artwork piece, artist
   * @param year year of creation of this artwork piece
   * @param cost the cost of this artwork piece in dollars
   * @throws an IllegalArgumentException if year is less than 1000, if cost is negative or zero or
   *            if name is null or an empty string
   */
  public Artwork(String name, int year, double cost) {
    // check the validity of the input parameters
    if (year < 1000)
      throw new IllegalArgumentException("Invalid year of creation. Too old.");
    if (cost <= 0.0)
      throw new IllegalArgumentException("Invalid cost. The cost must be positive");
    if (name == null || name.isBlank())
      throw new IllegalArgumentException("Invalid artwork name");
    // assign the artwork attributes
    this.year = year;
    this.cost = cost;
    this.name = name;
  }

  /**
   * Gets the cost of this artwork
   * 
   * @return the cost of artwork
   */
  public double getCost() {
    return cost;
  }

  /**
   * Gets the year when this artwork was created
   *
   * @return the year of creation of this artwork
   */
  public int getYear() {
    return year;
  }

  /**
   * Gets the name of the artwork piece, artist
   * 
   * @return the name of the artwork piece, artist
   */
  public String getName() {
    return name;
  }

  /**
   * Compares two artwork pieces for ordering with respect to their years, costs, and names
   * 
   * @return 0 if otherArtwork has the same year of creation, same name, and same cost as this
   *         artwork; an integer less then 0 if this artwork is older than the otherArtwork, or if
   *         they were created in the same year but this artwork is less expensive than the
   *         otherArtwork, or if they have the same year and the same cost and this artwork has a
   *         lower lexical order name than otherArtwork name; String comparisons are case-sensitive.
   *         otherwise returns an integer greater than 0.
   * @author Kavan Gandhi
   * @author Chinmay Koimuttum
   */
  @Override
  public int compareTo(Artwork otherArtwork) {
    // first, compares the years
    if (this.year < otherArtwork.getYear()) {
      return this.year - otherArtwork.getYear();
    } else if (this.year > otherArtwork.getYear()) {
      return this.year - otherArtwork.getYear();
      // if the years are the same, compares the cost
    } else if (this.year == otherArtwork.getYear()) {
      if (this.cost < otherArtwork.getCost()) {
        return (int) (this.cost - otherArtwork.getCost());
      } else if (this.cost > otherArtwork.getCost()) {
        return (int) (this.cost - otherArtwork.getCost());
        // if the cost is the same, compares the name
      } else if (this.cost == otherArtwork.getCost()) {
        return (this.name.compareTo(otherArtwork.getName()));
      }
    }
    return 0;
  }

  /**
   * Checks whether this artwork equals to another object passed as input
   * 
   * @param obj other object to compare
   * @return true if obj refers to an Artwork object with the same name and year of creation
   * @author Kavan Gandhi
   * @author Chinmay Koimuttum
   * 
   */
  @Override
  public boolean equals(Object obj) {
    // obj must be an Artwork for this method to return true
    if (!(obj instanceof Artwork)) {
      return false;
    }
    return (this.name.equals(((Artwork) obj).getName()) && this.year == ((Artwork) obj).getYear());
  }

  /**
   * Returns a String representation of the artwork attributes
   * 
   * @return the artwork as a String in the format
   */
  public String toString() {
    return "[(Name: " + name + ") (Year: " + year + ") (Cost: $" + cost + ")]";
  }

}
