package com.example.mlt.Graph;

import java.util.*;

public class UnweightedGraph<V> {
  protected List<V> vertices = new ArrayList<>(); // Store vertices
  protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacency lists
  protected ArrayList<ArrayList<Integer>> adjList;

  /**
   * Construct an empty graph
   */
  public UnweightedGraph() {
  }

  /**
   * Construct a graph from vertices and edges stored in arrays
   */
  public UnweightedGraph(V[] vertices, int[][] edges) {
    for (int i = 0; i < vertices.length; i++)
      addVertex(vertices[i]);

    createAdjacencyLists(edges, vertices.length);
  }

  /**
   * Construct a graph from vertices and edges stored in List
   */
  public UnweightedGraph(List<V> vertices, List<Edge> edges) {
    for (int i = 0; i < vertices.size(); i++)
      addVertex(vertices.get(i));

    createAdjacencyLists(edges, vertices.size());
  }

  /**
   * Construct a graph for integer vertices 0, 1, 2 and edge list
   */
  public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++)
      addVertex((V) (new Integer(i))); // vertices is {0, 1, ...}

    createAdjacencyLists(edges, numberOfVertices);
  }

  /**
   * Construct a graph from integer vertices 0, 1, and edge array
   */
  public UnweightedGraph(int[][] edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++)
      addVertex((V) (new Integer(i))); // vertices is {0, 1, ...}

    createAdjacencyLists(edges, numberOfVertices);
  }

  /**
   * Create adjacency lists for each vertex
   */
  private void createAdjacencyLists(
          int[][] edges, int numberOfVertices) {
    for (int i = 0; i < edges.length; i++) {
      addEdge(edges[i][0], edges[i][1]);
    }
  }

  /**
   * Create adjacency lists for each vertex
   */
  private void createAdjacencyLists(
          List<Edge> edges, int numberOfVertices) {
    for (Edge edge : edges) {
      addEdge(edge.u, edge.v);
    }
  }


  /** Return the number of vertices in the graph */
  public int getSize() {
    return vertices.size();
  }


  /** Return the vertices in the graph */
  public List<V> getVertices() {
    List<V> name = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i++) {
      Location l = new Location();
      l= (Location) vertices.get(i);
      String names = l.getName();

      name.add((V) names);
    }
    return vertices;
  }

  /** Return the object for the specified vertex */
  public V getVertex(int index) {
    return vertices.get(index);
  }


  /** Return the index for the specified vertex object */
  public int getIndex(V v) {
    return vertices.indexOf(v);
  }


  /** Return the neighbors of the specified vertex */
  public List<Integer> getNeighbors(int index) {
    List<Integer> result = new ArrayList<>();
    List<String> neighbour = new ArrayList<>();
    for (Edge e : neighbors.get(index))
      result.add(e.v);
    return result;
  }

  public String showNeighbours(int index){
    List<Integer> result = new ArrayList<>();
    List<String> neighbour = new ArrayList<>();
    for (Edge e : neighbors.get(index))
      result.add(e.v);

    for (Integer num : result) {
      neighbour.add((String) getVertex(num));
    }
    return "The neighbours of "+getVertex(index)+" are : "+ neighbour;
  }


  /** Return the degree for a specified vertex */
  public int getDegree(int v) {
    return neighbors.get(v).size();
  }


  /** Print the edges */
  public void printEdges() {
    for (int u = 0; u < neighbors.size(); u++) {
      System.out.print(getVertex(u) + " (" + u + "): ");
      for (Edge e : neighbors.get(u)) {
        System.out.print("(" + getVertex(e.u) + ", " +
                getVertex(e.v) + ") ");
      }
      System.out.println();
    }
  }


  /** Clear the graph */
  public void clear() {
    vertices.clear();
    neighbors.clear();
  }

  //@Override
  /** Add a vertex to the graph */
  /*
  public boolean addVertex(V vertex) {
      if (!vertices.contains(vertex)) {
        vertices.add(vertex);
        neighbors.add(new ArrayList<Edge>());
        return true;
      } else {
        return false;
      }
    }
    */

  public boolean addVertex(V vertex) {
    boolean add = true;
    for (int i = 0; i < getSize(); i++) {
      Location l = (Location) vertices.get(i);
      if (l.getName().equals(((Location) vertex).getName())) {
        // System.out.println("l :"+l.getName());
        //System.out.println("vertex :"+((Location) vertex).getName());
        add = false;
      }
    }
    if(add){
      vertices.add(vertex);
      neighbors.add(new ArrayList<Edge>());
    }

    return add;
  }





  /** Add an edge to the graph */
  public boolean addEdge(Edge e) {
    if (e.u < 0 || e.u > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.u);

    if (e.v < 0 || e.v > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.v);

    if (!neighbors.get(e.u).contains(e)) {
      neighbors.get(e.u).add(e);
      return true;
    } else {
      return false;
    }
  }

  /** Add an edge to the graph */
  public boolean addEdge(int u, int v) {
    return addEdge(new Edge(u, v)) && addEdge(new Edge(v, u));
  }


  /** Obtain a DFS tree starting from vertex u */
  /** To be discussed in Section 28.7 */
  public SearchTree dfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    // Mark visited vertices
    boolean[] isVisited = new boolean[vertices.size()];

    // Recursively search
    dfs(v, parent, searchOrder, isVisited);

    // Return a search tree
    return new SearchTree(v, parent, searchOrder);
  }

  /**
   * Recursive method for DFS search
   */
  private void dfs(int v, int[] parent, List<Integer> searchOrder,
                   boolean[] isVisited) {
    // Store the visited vertex
    searchOrder.add(v);
    isVisited[v] = true; // Vertex v visited

    for (Edge e : neighbors.get(v)) { // Note that e.u is v
      if (!isVisited[e.v]) { // e.v is w in Listing 28.8
        parent[e.v] = v; // The parent of w is v
        dfs(e.v, parent, searchOrder, isVisited); // Recursive search
      }
    }
  }

  /** Starting bfs search from vertex v */
  /** To be discussed in Section 28.9 */
  public SearchTree bfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    java.util.LinkedList<Integer> queue =
            new java.util.LinkedList<>(); // list used as a queue
    boolean[] isVisited = new boolean[vertices.size()];
    queue.offer(v); // Enqueue v
    isVisited[v] = true; // Mark it visited

    while (!queue.isEmpty()) {
      int u = queue.poll(); // Dequeue to u
      searchOrder.add(u); // u searched
      for (Edge e : neighbors.get(u)) { // Note that e.u is u
        if (!isVisited[e.v]) { // e.v is w in Listing 28.11
          queue.offer(e.v); // Enqueue w
          parent[e.v] = u; // The parent of w is u
          isVisited[e.v] = true; // Mark w visited
        }
      }
    }

    return new SearchTree(v, parent, searchOrder);
  }

  /** Tree inner class inside the AbstractGraph class */
  /**
   * To be discussed in Section 28.6
   */
  public class SearchTree {
    private int root; // The root of the tree
    private int[] parent; // Store the parent of each vertex
    private List<Integer> searchOrder; // Store the search order

    /**
     * Construct a tree with root, parent, and searchOrder
     */
    public SearchTree(int root, int[] parent,
                      List<Integer> searchOrder) {
      this.root = root;
      this.parent = parent;
      this.searchOrder = searchOrder;
    }

    /**
     * Return the root of the tree
     */
    public int getRoot() {
      return root;
    }

    /**
     * Return the parent of vertex v
     */
    public int getParent(int v) {
      return parent[v];
    }

    /**
     * Return an array representing search order
     */
    public List<Integer> getSearchOrder() {
      return searchOrder;
    }

    /**
     * Return number of vertices found
     */
    public int getNumberOfVerticesFound() {
      return searchOrder.size();
    }

    /**
     * Return the path of vertices from a vertex to the root
     */
    public List<V> getPath(int index) {
      ArrayList<V> path = new ArrayList<>();

      do {
        path.add(vertices.get(index));
        index = parent[index];
      }
      while (index != -1);

      return path;
    }

    /**
     * Print a path from the root to vertex v
     */
    public void printPath(int index) {
      List<V> path = getPath(index);
      System.out.print("A path from " + vertices.get(root) + " to " +
              vertices.get(index) + ": ");
      for (int i = path.size() - 1; i >= 0; i--) {
        if (i == 0) {
          System.out.print(path.get(i));
        } else {
          System.out.print(path.get(i) + " -> ");
        }
      }
    }

    /**
     * Print the whole tree
     */
    public void printTree() {
      System.out.println("Root is: " + vertices.get(root));
      System.out.print("Edges: ");
      for (int i = 0; i < parent.length; i++) {
        if (parent[i] != -1) {
          // Display an edge
          System.out.print("(" + vertices.get(parent[i]) + ", " +
                  vertices.get(i) + ") ");
        }
      }
      System.out.println();
    }
  }
  /*
    public void printShortestPaths() {
      this.adjList = new ArrayList<ArrayList<Integer>>(getSize());

      for (int i = 0; i < getSize(); i++) {
        adjList.add(new ArrayList<Integer>());
      }
      for (int i = 0; i < getSize(); i++) {
            for (Edge e : neighbors.get(i)) {
              adjList.get(i).add(e.v);
            }

      }
      System.out.println(adjList);
    }
  */
  public LinkedList<Integer> printShortestDistance(int s, int dest, int v) {
    this.adjList = new ArrayList<ArrayList<Integer>>(getSize());

    for (int i = 0; i < getSize(); i++) {
      adjList.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < getSize(); i++) {
      for (Edge e : neighbors.get(i)) {
        adjList.get(i).add(e.v);
      }

    }
    // predecessor[i] array stores predecessor of
    // i and distance array stores distance of i
    // from s
    int pred[] = new int[v];
    int dist[] = new int[v];

    if (BFS(adjList, s, dest, v, pred, dist) == false) {
      System.out.println("Given source and destination" +
              "are not connected");
      return null;
    }

    // LinkedList to store path
    LinkedList<Integer> path = new LinkedList<Integer>();
    int crawl = dest;
    path.add(crawl);
    while (pred[crawl] != -1) {
      path.add(pred[crawl]);
      crawl = pred[crawl];
    }

    // Print distance
    System.out.println("Shortest path length is: " + dist[dest]);

    // Print path
    System.out.print("Path is : ");
    for (int i = path.size() - 1; i >= 0; i--) {
      if(i==0)
        System.out.print(getVertex(path.get(i)));
      else
        System.out.print(getVertex(path.get(i))+" -> ");
    }

    return path   ;
  }

  // a modified version of BFS that stores predecessor
  // of each vertex in array pred
  // and its distance from source in array dist
  public boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[])
  {
    // a queue to maintain queue of vertices whose
    // adjacency list is to be scanned as per normal
    // BFS algorithm using LinkedList of Integer type
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // boolean array visited[] which stores the
    // information whether ith vertex is reached
    // at least once in the Breadth first search
    boolean visited[] = new boolean[v];

    // initially all vertices are unvisited
    // so v[i] for all i is false
    // and as no path is yet constructed
    // dist[i] for all i set to infinity
    for (int i = 0; i < v; i++) {
      visited[i] = false;
      dist[i] = Integer.MAX_VALUE;
      pred[i] = -1;
    }

    // now source is first to be visited and
    // distance from source to itself should be 0
    visited[src] = true;
    dist[src] = 0;
    queue.add(src);

    // bfs Algorithm
    while (!queue.isEmpty()) {
      int u = queue.remove();
      for (int i = 0; i < adj.get(u).size(); i++) {
        if (visited[adj.get(u).get(i)] == false) {
          visited[adj.get(u).get(i)] = true;
          dist[adj.get(u).get(i)] = dist[u] + 1;
          pred[adj.get(u).get(i)] = u;
          queue.add(adj.get(u).get(i));

          // stopping condition (when we find
          // our destination)
          if (adj.get(u).get(i) == dest)
            return true;
        }
      }
    }
    return false;
  }


  /** Remove vertex v and return true if successful */
  public boolean remove(V v) {
    return true; // Implementation left as an exercise
  }


  /** Remove edge (u, v) and return true if successful */
  public boolean remove(int u, int v) {
    return true; // Implementation left as an exercise
  }

}

