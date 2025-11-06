/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.ui;

/**
 *
 * @author rafaelc3127
 */
public interface GraphUpdateListener {
    void onGraphUpdated();
    void onNodeAdded(String username);
    void onNodeRemoved(String username);
    void onEdgeAdded(String from, String to);
    void onEdgeRemoved(String from, String to);
    void onLoadFileRequested();
    void onSaveFileRequested();
}
