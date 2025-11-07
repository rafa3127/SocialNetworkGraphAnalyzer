/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.io;
import java.io.IOException;

import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Edge;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;

/**
 *
 * @author rafaelc3127
 */

/**
 * Manages loading and saving of Graph objects from/to text files.
 * 
 */
public class GraphFileManager {
    
    /**
     * Loads a Graph from a text file with sections "usuarios" and "relaciones".
     * 
     * @param filepath Path to the file
     * @return Graph loaded from file
     * @throws IOException if file cannot be read
     * @throws IllegalArgumentException if format is invalid
     */
    public static Graph<String> loadGraphFromFile(String filepath) throws IOException, IllegalArgumentException {
        // read file
        LinkedList<String> lines = FileIO.readFile(filepath);
        
        // parse sections
        String[] sectionNames = {"usuarios", "relaciones"};
        SectionParser parser = new SectionParser(sectionNames);
        HashMap<String, LinkedList<String>> sections = parser.parse(lines);
        
        // get sections
        LinkedList<String> users, relations;
        users = sections.get("usuarios");
        relations = sections.get("relaciones");
        
        // create empty graph
        Graph<String> graph = new Graph<>();
        
        if ( users != null ) {
            Node<String> currentUser = users.getHead();
            while (currentUser != null) {
                String user = currentUser.getData();
                
                // Validate user format (must start with @)
                if (!user.startsWith("@")) {
                    throw new IllegalArgumentException("Invalid user format (must start with @): " + user);
                }
                
                graph.addNode(user);
                currentUser = currentUser.getNext();
            }
        }
        
        // add relations if section exists
        if (relations != null) {
            Node<String> currentRelation = relations.getHead();
            
            while (currentRelation != null) {
                String relation = currentRelation.getData();
                
                // parse relation: "@from, @to"
                String[] parts = relation.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid relation format (expected '@from, @to'): " + relation);
                }
                
                String from = parts[0].trim();
                String to = parts[1].trim();
                
                // validate both users start with @
                if (!from.startsWith("@") || !to.startsWith("@")) {
                    throw new IllegalArgumentException("Invalid relation format (users must start with @): " + relation);
                }
                
                // validate both users exist in graph
                if (!graph.containsNode(from)) {
                    throw new IllegalArgumentException("Relation references non-existent user: " + from);
                }
                if (!graph.containsNode(to)) {
                    throw new IllegalArgumentException("Relation references non-existent user: " + to);
                }
                
                graph.addEdge(from, to);
                currentRelation = currentRelation.getNext();
            }
        }
     
        return graph;
                
    }
    
    /**
     * Saves a Graph to a text file with sections "usuarios" and "relaciones".
     * 
     * @param graph Graph to save
     * @param filepath Path where to save
     * @throws IOException if file cannot be written
     */
    public static void saveGraphToFile(Graph<String> graph, String filepath) throws IOException {
        // create sections HashMap
        HashMap<String, LinkedList<String>> sections = new HashMap<>();
        
        // add usuarios section
        LinkedList<String> users = new LinkedList<>();
        LinkedList<String> nodes = graph.getNodes();
        Node<String> currentNode = nodes.getHead();
        
        while (currentNode != null) {
            users.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        
        sections.put("usuarios", users);
        
        // add relaciones section
        LinkedList<String> relations = new LinkedList<>();
        LinkedList<Edge<String>> edges = graph.getEdges();
        Node<Edge<String>> currentEdge = edges.getHead();
        
        while (currentEdge != null) {
            Edge<String> edge = currentEdge.getData();
            String relation = edge.from + ", " + edge.to;
            relations.add(relation);
            currentEdge = currentEdge.getNext();
        }
        
        sections.put("relaciones", relations);
        
        // serialize sections to lines
        String[] sectionNames = {"usuarios", "relaciones"};
        SectionParser parser = new SectionParser(sectionNames);
        LinkedList<String> lines = parser.serialize(sections);
        
        // write to file
        FileIO.writeFile(filepath, lines);
    }
    
}
