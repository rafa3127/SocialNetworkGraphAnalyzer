/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.basicdatastructures;

/**
 *
 * @author rafaelc3127
 */
/**
 * Represents a directed generic graph implemented using an adjacency list.
 * Uses a HashMap to store nodes and their corresponding adjacency lists.
 * Maintains a count of edges for efficient edge counting operations.
 * 
 * @param <T> The type of data stored in the graph nodes
 */
public class Graph<T> {
    private HashMap<T, LinkedList<T>> adjacencyList;
    private int edgeCount;
    
    /**
     * Constructor that initializes an empty graph.
     * Creates an empty adjacency list with no nodes or edges.
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
    }
    
    /**
     * Adds a new node to the graph with no initial edges.
     * 
     * @param node The node to add to the graph
     * @throws IllegalArgumentException if the node already exists in the graph
     */
    public void addNode(T node) {
        if (this.adjacencyList.containsKey(node)) {
            throw new IllegalArgumentException("Node already exists in graph");
        }
        this.adjacencyList.put(node, new LinkedList<>());
    }
    
    /**
     * Removes a node from the graph along with all its associated edges.
     * This includes both outgoing edges (from this node) and incoming edges 
     * (to this node from other nodes).
     * 
     * @param node The node to remove from the graph
     * @throws IllegalArgumentException if the node does not exist in the graph
     */
    public void removeNode(T node) {
        if (!this.adjacencyList.containsKey(node)) {
            throw new IllegalArgumentException("Node does not exist in graph");
        }
        
        int outgoingEdges = this.adjacencyList.get(node).getSize();
        this.edgeCount -= outgoingEdges;
        
        this.adjacencyList.remove(node);
        
        LinkedList<T> allNodes = this.adjacencyList.getKeys();
        Node<T> current = allNodes.getHead();
        
        while (current != null) {
            LinkedList<T> relations = this.adjacencyList.get(current.getData());
            if (relations.contains(node)) {
                relations.remove(node);
                this.edgeCount--;
            }
            current = current.getNext();
        }
    }
    
    /**
     * Checks if a node exists in the graph.
     * 
     * @param node The node to check for existence
     * @return true if the node exists in the graph, false otherwise
     */
    public boolean containsNode(T node) {
        return this.adjacencyList.containsKey(node);
    }
    
    /**
     * Returns all nodes present in the graph.
     * 
     * @return A LinkedList containing all nodes in the graph
     */
    public LinkedList<T> getNodes() {
        return this.adjacencyList.getKeys();
    }
    
    /**
     * Returns the number of nodes in the graph.
     * 
     * @return The total count of nodes in the graph
     */
    public int getNodeCount() {
        return this.adjacencyList.size();
    }
    
   /**
    * Adds a directed edge from one node to another.
    * Both nodes must exist in the graph before adding the edge.
    * 
    * @param from The source node of the edge
    * @param to The destination node of the edge
    * @throws IllegalArgumentException if either node does not exist in the graph
    */
    public void addEdge(T from, T to) {
        if (!this.adjacencyList.containsKey(from) || !this.adjacencyList.containsKey(to)) {
            throw new IllegalArgumentException("One or more nodes does not exist in graph");
        }
        this.adjacencyList.get(from).add(to);
        this.edgeCount++;
    }
    
    /**
     * Removes a directed edge between two nodes.
     * Both nodes must exist in the graph.
     * 
     * @param from The source node of the edge
     * @param to The destination node of the edge
     * @throws IllegalArgumentException if either node does not exist or the edge does not exist
     */
    public void removeEdge(T from, T to) {
        if (!this.adjacencyList.containsKey(from) || !this.adjacencyList.containsKey(to)) {
            throw new IllegalArgumentException("One or more nodes does not exist in graph");
        }
        try {
            this.adjacencyList.get(from).remove(to);
            this.edgeCount--;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("This edge does not exist");
        }
    }

    /**
     * Returns all edges in the graph as a list of Edge objects.
     * Each Edge contains the source and destination nodes.
     * 
     * @return A LinkedList containing all edges in the graph
     */
    public LinkedList<Edge<T>> getEdges() {
        LinkedList<Edge<T>> list = new LinkedList<>();
        LinkedList<T> nodes = this.adjacencyList.getKeys();
        Node<T> currentNode = nodes.getHead();
        while ( currentNode != null ){
            LinkedList<T> edgesFromNode = this.adjacencyList.get(currentNode.getData());
            Node<T> currentEdge = edgesFromNode.getHead();
            while ( currentEdge != null) {
                Edge<T> edgeToAdd = new Edge(currentNode.getData(), currentEdge.getData());
                list.add(edgeToAdd);
                currentEdge = currentEdge.getNext();
            }
            currentNode = currentNode.getNext();
        }
        return list;
    }
    
    /**
     * Returns all adjacent nodes of a given node.
     * In a directed graph, these are the nodes that can be reached 
     * directly from the given node via an outgoing edge.
     * 
     * @param node The node whose adjacent nodes to retrieve
     * @return A LinkedList containing all adjacent nodes
     * @throws IllegalArgumentException if the node does not exist in the graph
     */
    public LinkedList<T> getOutgoingNodes(T node) {
        if (!this.adjacencyList.containsKey(node)) {
            throw new IllegalArgumentException("Node does not exist in graph");
        }
        return this.adjacencyList.get(node);
    }
    
    /**
     * Returns the total number of edges in the graph.
     * 
     * @return The count of directed edges in the graph
     */
    public int getEdgeCount() {
        return this.edgeCount;
    }
    
    /**
     * Checks if the graph is empty (contains no nodes).
     * 
     * @return true if the graph has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return this.adjacencyList.isEmpty();
    }
    
}
