/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.ui;

/**
 *
 * @author rafaelc3127
 */

/**
 * Listener interface for graph modification events.
 * 
 */
public interface GraphUpdateListener {
    /**
     * Called when the graph has been modified in any way.
     * Generic notification for any graph change.
     */
    void onGraphUpdated();
    
    /**
     * Called when a new node has been added to the graph.
     * 
     * @param username The username of the added node
     */
    void onNodeAdded(String username);
    
    /**
     * Called when a node has been removed from the graph.
     * 
     * @param username The username of the removed node
     */
    void onNodeRemoved(String username);
    
    /**
     * Called when a new edge has been added to the graph.
     * 
     * @param from The source node username
     * @param to The target node username
     */
    void onEdgeAdded(String from, String to);
    
    /**
     * Called when an edge has been removed from the graph.
     * 
     * @param from The source node username
     * @param to The target node username
     */
    void onEdgeRemoved(String from, String to);
    
    /**
     * Called when the user requests to load a file.
     * Delegates file loading logic to the parent component.
     */
    void onLoadFileRequested();
    
    /**
     * Called when the user requests to save the current graph to a file.
     * Delegates file saving logic to the parent component.
     */
    void onSaveFileRequested();
}
