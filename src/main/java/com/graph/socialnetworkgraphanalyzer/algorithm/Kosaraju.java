package com.graph.socialnetworkgraphanalyzer.algorithm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Edge;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;
/**
 *
 * @author rafaelc3127
 */
public class Kosaraju {
    /**
     * Apply depth-first search starting from a given node.
     * Nodes are added to the result list in post-order (after visiting all descendants).
     * 
     * @param <T> The type of nodes in the graph
     * @param node The starting node for DFS
     * @param graph The graph to traverse
     * @param visited HashMap tracking which nodes have been visited
     * @param result List where nodes are added in post-order (finish time order)
     */
    private static <T> void dfs(
        T node,
        Graph<T> graph,
        HashMap<T, Boolean> visited,
        LinkedList<T> result
    ) {
        // set node as visited
        visited.put(node, true);
        
        // get adjancet nodes
        LinkedList<T> adjacentNodes = graph.getOutgoingNodes(node);
        
        Node<T> current = adjacentNodes.getHead();
        // visit each adjacent node
        while (current != null) {
            T adjacentNode = current.getData();
            // if not visited, recusive dfs
            if (visited.get(adjacentNode) == null) {
                dfs(adjacentNode, graph, visited, result);
            }
            current = current.getNext();
        }
        // result to save finished order
        result.add(node);
    }
    
    /**
     * Creates a transposed graph where all edges are reversed.
     * If the original graph has an edge A -> B, the transposed graph will have B -> A.
     * 
     * @param <T> The type of nodes in the graph
     * @param graph The original graph to transpose
     * @return A new graph with all edges reversed
     */
    private static <T> Graph<T> transpose(Graph<T> graph) {
        Graph<T> transposed = new Graph<>();
        
        // copy all nodes
        LinkedList<T> nodes = graph.getNodes();
        Node<T> currentNode = nodes.getHead();
        while (currentNode != null) {
            transposed.addNode(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        
        // invert edges using Edge objects
        LinkedList<Edge<T>> edges = graph.getEdges();
        Node<Edge<T>> currentEdge = edges.getHead();
        while (currentEdge != null) {
            Edge<T> edge = currentEdge.getData();
            transposed.addEdge(edge.to, edge.from);
            currentEdge = currentEdge.getNext();
        }
        
        return transposed;
    }
    
    public static <T> LinkedList<LinkedList<T>> findSCC(Graph<T> graph) {
        // Step 1: First DFS in prder to get finish order
        HashMap<T, Boolean> visited = new HashMap<>();
        LinkedList<T> finishOrder = new LinkedList<>();
        
        LinkedList<T> nodes = graph.getNodes();
        Node<T> current = nodes.getHead();
        while (current != null) {
            T node = current.getData();
            if (visited.get(node) == null) {
                dfs(node, graph, visited, finishOrder);
            }
            current = current.getNext();
        }
        
        // Step 2: Transpose graph
        Graph<T> transposed = transpose(graph);
        
        // Step 3: Second DFS to find components
        visited = new HashMap<>();  // reset visited
        LinkedList<LinkedList<T>> components = new LinkedList<>();
        
        current = finishOrder.getHead();
        while (current != null) {
            T node = current.getData();
            if (visited.get(node) == null) {
                LinkedList<T> component = new LinkedList<>();
                dfs(node, transposed, visited, component);
                components.add(component);
            }
            current = current.getNext();
        }
        
        return components;
    }
}
