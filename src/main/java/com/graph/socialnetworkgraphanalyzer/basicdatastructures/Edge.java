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
 * Represents a directed edge in a graph.
 * An edge connects two nodes with a direction from source to destination.
 * 
 * @param <T> The type of nodes that this edge connects
 */
public class Edge<T> {
    public T from, to;
    
        /**
     * Constructor that creates a directed edge from one node to another.
     * 
     * @param from The source node of the edge
     * @param to The destination node of the edge
     */
    public Edge(T from, T to) {
        this.from = from;
        this.to = to;
    }
    
    /**
     * Returns a string representation of the edge.
     * Format: "from -> to"
     * 
     * @return A string showing the direction of the edge
     */
    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
