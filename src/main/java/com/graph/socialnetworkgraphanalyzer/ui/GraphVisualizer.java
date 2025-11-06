/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.ui;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Edge;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;

/**
 *
 * @author rafaelc3127
 */

/**
 * Utility class for converting custom Graph<String> to GraphStream Graph
 * and applying visual styling.
 */
public class GraphVisualizer {

    private static final String[] COMPONENT_COLORS = {
        "#E63946", // Bright red
        "#06FFA5", // Mint green
        "#4361EE", // Royal blue
        "#F77F00", // Vivid orange
        "#9D4EDD", // Purple
        "#06D6A0", // Teal
        "#EF476F", // Hot pink
        "#FFD60A", // Yellow
        "#118AB2", // Ocean blue
        "#FF6B35", // Coral
        "#7209B7", // Deep purple
        "#90E0EF", // Sky blue
        "#E76F51", // Terracotta
        "#06A77D", // Forest green
        "#D62828", // Dark red
        "#F4A261"  // Sandy orange
    };
    
    /**
     * Builds complete GraphStream graph from custom Graph structure.
     * Clears existing graph and rebuilds from scratch.
     * 
     * @param gsGraph The GraphStream graph to populate
     * @param customGraph The custom Graph source data
     */
    public static void buildCompleteGraph(org.graphstream.graph.Graph gsGraph, Graph<String> customGraph) {
        gsGraph.clear();
        
        // Apply base configuration
        gsGraph.setAttribute("ui.quality");
        gsGraph.setAttribute("ui.antialias");
        gsGraph.setAttribute("ui.stylesheet", getBaseStylesheet());
        
        // Add all nodes
        LinkedList<String> nodes = customGraph.getNodes();
        Node<String> currentNode = nodes.getHead();
        
        while (currentNode != null) {
            String username = currentNode.getData();
            gsGraph.addNode(username);
            
            // Set visible label
            org.graphstream.graph.Node gsNode = gsGraph.getNode(username);
            gsNode.setAttribute("ui.label", username);
            
            currentNode = currentNode.getNext();
        }
        
        // Add all edges
        LinkedList<Edge<String>> edges = customGraph.getEdges();
        Node<Edge<String>> currentEdge = edges.getHead();
        
        while (currentEdge != null) {
            Edge<String> edge = currentEdge.getData();
            String edgeId = edge.from + "->" + edge.to; 
            
            gsGraph.addEdge(edgeId, edge.from, edge.to, true);
            currentEdge = currentEdge.getNext();
        }
    }

    
    /**
     * Adds single node to visualization.
     * 
     * @param gsGraph The GraphStream graph
     * @param username The username to add
     */
    public static void addNode(org.graphstream.graph.Graph gsGraph, String username) {
        gsGraph.addNode(username);
        org.graphstream.graph.Node gsNode = gsGraph.getNode(username);
        gsNode.setAttribute("ui.label", username);
    }
    
    /**
     * Removes single node from visualization.
     * Also removes all connected edges automatically.
     * 
     * @param gsGraph The GraphStream graph
     * @param username The username to remove
     */
    public static void removeNode(org.graphstream.graph.Graph gsGraph, String username) {
        if (gsGraph.getNode(username) != null) {
            gsGraph.removeNode(username);
        }
    }
    
    /**
     * Adds single edge to visualization.
     * 
     * @param gsGraph The GraphStream graph
     * @param from Source node username
     * @param to Target node username
     */
    public static void addEdge(org.graphstream.graph.Graph gsGraph, String from, String to) {
        String edgeId = from + "->" + to; // Unique ID based on endpoints
        
        if (gsGraph.getEdge(edgeId) == null) {
            gsGraph.addEdge(edgeId, from, to, true); // true = directed
        }
    }
    
    /**
     * Removes single edge from visualization.
     * 
     * @param gsGraph The GraphStream graph
     * @param from Source node username
     * @param to Target node username
     */
    public static void removeEdge(org.graphstream.graph.Graph gsGraph, String from, String to) {
        String edgeId = from + "->" + to;
        
        if (gsGraph.getEdge(edgeId) != null) {
            gsGraph.removeEdge(edgeId);
        }
    }
    
    /**
     * Returns the base stylesheet for the graph visualization.
     */
    private static String getBaseStylesheet() {
        return "node { " +
           "    size: 35px; " +
           "    fill-color: #3498db; " +
           "    text-size: 14px; " +
           "    text-color: #2c3e50; " +
           "    text-offset: 0px, 25px; " +
           "    text-alignment: center; " +
           "    text-background-mode: none; " +
           "    stroke-mode: none; " +
           "} " +
           "edge { " +
           "    fill-color: #95a5a6; " +
           "    arrow-size: 12px, 6px; " +
           "    arrow-shape: arrow; " +
           "}";
    }
}
