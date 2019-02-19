/**
   A class that represents a vertex.
   Used for dijkstra's algorithm function in Digraph.java.
   @author Minwoo Soh
*/
public class Vertex implements Comparable<Vertex>
{
  private double distance;
  private int cityNumber;
  private boolean visited;

  /** Create an unoptimized vertex with no location or distance. */
  public Vertex()
  {
    distance = Double.POSITIVE_INFINITY;
    cityNumber = -1;
    visited = false;
  } // end default constructor

  /** Create a vertex with given distance and location values.
      @param distance  The weight of the path to new vertex.
      @param cityNumber  The location value of new vertex. */
  public Vertex(double distance, int cityNumber)
  {
    this.distance = distance;
    this.cityNumber = cityNumber;
    visited = false;
  } // end constructor

  /** Set a new distance for the vertex.
      @param distance  Weight of new distance. */
  public void setDistance(double distance)
  {
    this.distance = distance;
  } // end setDistance

  /** Set a new city location for the vertex.
      @param cityNumber  Index of another city. */
  public void setCityNumber(int cityNumber)
  {
    this.cityNumber = cityNumber;
  } // end setCityNumber

  /** Set vertex as visited or unvisited.
      @param visited  True if vertex is to be set as visited, false if unvisited. */
  public void setVisited(boolean visited)
  {
    this.visited = visited;
  } // end setVisited

  /** Get the distance weight to this vertex.
      @return  The weight of the distance of the vertex. */
  public double getDistance()
  {
    return distance;
  } // end getDistance

  /** Get the city's index of this vertex.
      @return  The index of the city. */
  public int getCityNumber()
  {
    return cityNumber;
  } // end getCityNumber

  public boolean visitStatus()
  {
    return visited;
  } // end visitStatus

  /** Vertices are compared with their distance weights.
      @param another  Second vertex to compare with.*/
  public int compareTo(Vertex another)
  {
    if(distance > another.getDistance())
    {
      return 1;
    }
    else if(distance < another.getDistance())
    {
      return -1;
    }
    return 0;
  } // end compareTo
} // end of Vertex class
