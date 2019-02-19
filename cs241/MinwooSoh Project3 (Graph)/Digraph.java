import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
   A class for directed graphs using Dijkstra's Algorithm to search for shortest path.
   @author Minwoo Soh
*/
public class Digraph
{
  private double[][] graph;
  private String[][] data;
  private Vertex[] distance;
  private LinkedPriorityQueue<Vertex> priorityQueue;
  private boolean initialized;

  /** Create a Digraph with given values from city.dat and road.dat. */
  public Digraph()
  {
    try
    {
      // Default city amount is 20.
      graph = new double[20][20];
      data = new String[20][5];

      // Store city information in 2D array.
      // Column 0 = city number.
      // Column 1 = city code.
      // Column 2 = city name.
      // Column 3 = population.
      // Column 4 = elevation.
      File file = new File("city.dat");
      Scanner inputFile = new Scanner(file);
      int row = 0;
      while(row < 20)
      {
        String cityString = inputFile.nextLine().trim();
        String cityData[] = cityString.split("  +");
        for(int col = 0; col < 5; col++)
        {
          data[row][col] = cityData[col];
        }
        row++;
      }

      // Set default values for graph.
      for(int i = 0; i < graph.length; i++)
      {
        for(int j = 0; j < graph[i].length; j++)
        {
          if(i == j)
          {
            graph[i][j] = 0;
          }
          else
          {
            graph[i][j] = Double.POSITIVE_INFINITY;
          }
        }
      }

      // Store road information.
      file = new File("road.dat");
      inputFile = new Scanner(file);
      while(inputFile.hasNextLine())
      {
        String roadString = inputFile.nextLine().trim();
        String roadData[] = roadString.split("\\s+");
        int from = Integer.parseInt(roadData[0])-1;
        int to = Integer.parseInt(roadData[1])-1;
        int distance = Integer.parseInt(roadData[2]);
        graph[from][to] = distance;
      }

      initialized = true;
    } // end try
    catch(FileNotFoundException e)
    {
      initialized = false;
    } // end catch
  } // end constructor

  /** Find the shortest path from one city to another city using Dijkstra's Algorithm.
      @param code1  The starting point city's code.
      @param code2  The target destination city's code. */
  public void dijkstra(String code1, String code2)
  {
    int from = getIndex(code1);
    int to = getIndex(code2);
    if (from == -1 || to == -1)
    {
      System.out.println("City does not exist.");
      return;
    }

    priorityQueue = new LinkedPriorityQueue<>();
    distance = new Vertex[graph.length];
    int[] previous = new int[graph.length]; // To store cities passed through.

    // Initialization: all distances to each city set as infinity.
    for(int v = 0; v < graph.length; v++)
    {
      distance[v] = new Vertex(Double.POSITIVE_INFINITY, v);
      previous[v] = -1;
    }
    distance[from].setDistance(0); // Distance from source to source.
    // Add distances to priority queue.
    for(int v = 0; v < graph.length; v++)
    {
      priorityQueue.add(distance[v]);
    }

    // Main loop.
    while(!priorityQueue.isEmpty())
    {
      int u = priorityQueue.remove().getCityNumber(); // Vertex with shortest path.
      distance[u].setVisited(true); // Set vertex as visited, no longer included in queue.
      if(distance[u].getDistance() == Double.POSITIVE_INFINITY)
      {
        break; // All remaining vertices are inaccessible.
      }
      for(int v = 0; v < graph[u].length; v++)
      {
        double alt = distance[u].getDistance() + graph[u][v];
        if (alt < distance[v].getDistance()) // Relaxation.
        {
          distance[v].setDistance(alt);
          previous[v] = u;
        }
      }
      // Reload priorityQueue with new distance values.
      priorityQueue.clear();
      for(int v = 0; v < distance.length; v++)
      {
        if(distance[v].visitStatus() == false) // Only unvisted vertices added to queue.
        {
          priorityQueue.add(distance[v]);
        }
      }
    } // end while

    System.out.print("The minimum distance between " + data[from][2] + " and " + data[to][2]
                        + " is " + (int)distance[to].getDistance() + " through the route: ");
    String route = code2 + ".";
    int index = previous[to];
    while(index != from)
    {
      route = data[index][1] + ", " + route;
      index = previous[index];
    }
    route = data[index][1] + ", " + route;
    System.out.println(route);
  } // end dijkstra

  /** Add a new weighted path from one city to another.
      @param code1  The code of the city where the road will start from.
      @param code2  The code of the city where the road will end at.
      @param distance   The weight/length of the road. */
  public void addEdge(String code1, String code2, int distance)
  {
    int city1 = getIndex(code1);
    int city2 = getIndex(code2);
    if((city1 == -1) || (city2 == -1))
    {
      System.out.println("City does not exist.");
    }
    else if(city1 == city2)
    {
      System.out.println("Cannot create a road to itself.");
    }
    else if(distance <= 0)
    {
      System.out.println("Distance must be a positive value.");
    }
    else if(graph[city1][city2] != Double.POSITIVE_INFINITY)
    {
      System.out.println("Delete the existing road to add a new road.");
    }
    else // If graph[city1][city2] == Double.POSITIVE_INFINITY.
    {
      graph[city1][city2] = distance;
      System.out.println("You have inserted a road from " + data[city1][2] + " to " + data[city2][2] + " with a distance of " + distance + ".");
    }
  } // end addEdge

  /** Remove a path from one city to another.
      @param code1  The code of the city where the road starts from.
      @param code2  The code of the city where the road ends at. */
  public void removeEdge(String code1, String code2)
  {
    int city1 = getIndex(code1);
    int city2 = getIndex(code2);
    if((city1 == -1) || (city2 == -1))
    {
      System.out.println("City does not exist.");
      return;
    }
    if((graph[city1][city2] != Double.POSITIVE_INFINITY) && (graph[city1][city2] != 0))
    {
      graph[city1][city2] = Double.POSITIVE_INFINITY;
      System.out.println("You have removed a road from " + data[city1][2] + " to " + data[city2][2] + ".");
    }
    else
    {
      System.out.println("The road from " + data[city1][2] + " to " + data[city2][2] + " does not exist.");
    }
  } // end removeEdge

  /**  Get the location/index of the city in the graph. Returns -1 if not found.
       @param code  City code. */
  public int getIndex(String code)
  {
    for(int i = 0; i < data.length; i++)
    {
      if(code.equals(data[i][1]))
      {
        return i;
      }
    }
    return -1;
  } // end getIndex

  /** Get the full information of the city.
      @param code  City's code. */
  public String getCityInfo(String code)
  {
    String result = "";
    int index = getIndex(code);
    if(index == -1)
    {
      result = "City with that code does not exist.";
    }
    else
    {
      for(int col = 0; col < 5; col++)
      {
        result += data[index][col] + " ";
      }
    }
    return result;
  } // end getCityInfo

  /** Check if the digraph is initialized.
      @return  True if the digraph is properly made, false if not.*/
  public boolean checkInitialization()
  {
    return initialized;
  } // end checkInitialization
} // end of Diagraph class
