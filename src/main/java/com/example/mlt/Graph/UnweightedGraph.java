package com.example.mlt.Graph;

import java.util.*;

public class UnweightedGraph<V> {
  protected List<V> vertices = new ArrayList<>(); // Store vertices
  protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacency lists
  protected ArrayList<ArrayList<Integer>> adjList;


  public UnweightedGraph() {
  }


  private void createAdjacencyLists(
          int[][] edges, int numberOfVertices) {
    for (int i = 0; i < edges.length; i++) {
      addEdge(edges[i][0], edges[i][1]);
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


  public boolean addVertex(V vertex) {
    boolean add = true;
    for (int i = 0; i < getSize(); i++) {
      Location l = (Location) vertices.get(i);
      if (l.getName().equals(((Location) vertex).getName())) {
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


  /** Tree inner class inside the AbstractGraph class */
  public class SearchTree {
    private int root; // The root of the tree
    private int[] parent; // Store the parent of each vertex
    private List<Integer> searchOrder; // Store the search order

    /**
     * Construct a tree with root, parent, and searchOrder
     */
    public SearchTree(int root, int[] parent,List<Integer> searchOrder) {
      this.root = root;
      this.parent = parent;
      this.searchOrder = searchOrder;
    }

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
  public boolean removeVertex(V v) {
    return true; // Implementation left as an exercise
  }


  /** Remove edge (u, v) and return true if successful */
  public boolean removeEdge(int u, int v) {
    return true; // Implementation left as an exercise
  }

}

