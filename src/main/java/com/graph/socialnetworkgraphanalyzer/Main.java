/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Edge;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;
import com.graph.socialnetworkgraphanalyzer.algorithm.Kosaraju;

/**
 *
 * @author rafaelc3127
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing LinkedList ===");
        testLinkedList();
        
        System.out.println("\n=== Testing HashMap ===");
        testHashMap();
        
        System.out.println("\n=== Testing Graph ===");
        testGraph();
        
        System.out.println("\n=== Testing Edge cases ===");
        testEdgeCases();
        
        System.out.println("\n=== Testing Kosaraju ===");
        testKosaraju();
        
        System.out.println("\n=== All tests completed ===");
        
    }
    
    private static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        
        // Test add
        list.add("@pepe");
        list.add("@juan");
        list.add("@maria");
        System.out.println("Added 3 users. Size: " + list.getSize());
        
        // Test contains
        System.out.println("Contains @juan: " + list.contains("@juan"));
        System.out.println("Contains @pedro: " + list.contains("@pedro"));
        
        // Test remove
        list.remove("@juan");
        System.out.println("Removed @juan. Size: " + list.getSize());
        System.out.println("Contains @juan after removal: " + list.contains("@juan"));
        
        // Test isEmpty
        System.out.println("Is empty: " + list.isEmpty());
        
        // Test clear
        list.clear();
        System.out.println("After clear. Size: " + list.getSize());
        System.out.println("Is empty: " + list.isEmpty());
        
        // Test toString
        System.out.println("List toString: " + list.toString());
    }
    
    private static void testHashMap() {
        HashMap<String, String> map = new HashMap<>();
        
        // Test put
        map.put("@pepe", "Pepe Garcia");
        map.put("@juan", "Juan Lopez");
        map.put("@maria", "Maria Rodriguez");
        System.out.println("Added 3 entries. Size: " + map.size());
        
        // Test get
        System.out.println("Get @juan: " + map.get("@juan"));
        System.out.println("Get @pedro: " + map.get("@pedro"));
        
        // Test update
        map.put("@juan", "Juan Martinez");
        System.out.println("Updated @juan: " + map.get("@juan"));
        System.out.println("Size after update: " + map.size());
        
        // Test containsKey
        System.out.println("Contains @maria: " + map.containsKey("@maria"));
        System.out.println("Contains @pedro: " + map.containsKey("@pedro"));
        
        // Test remove
        map.remove("@juan");
        System.out.println("Removed @juan. Size: " + map.size());
        System.out.println("Contains @juan after removal: " + map.containsKey("@juan"));
        
        // Test resize (add many elements)
        System.out.println("\nTesting resize with 20 elements...");
        for (int i = 0; i < 20; i++) {
            map.put("@user" + i, "User " + i);
        }
        System.out.println("Size after adding 20 more: " + map.size());
        System.out.println("Get @user10: " + map.get("@user10"));
        
        // Test getKeys
        System.out.println("\nTesting getKeys");
        LinkedList<String> keys = map.getKeys();
        Node<String> current = keys.getHead();
        while ( current != null ) {
            System.out.println(current.getData());
            current = current.getNext();
        }
        
        System.out.println("All keys using toString: " + keys.toString());
    }
    
    private static void testEdgeCases() {
        // LinkedList: remove from empty list
        LinkedList<String> emptyList = new LinkedList<>();
        try {
            emptyList.remove("@test");
            System.out.println("ERROR: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty list remove throws exception correctly");
        }
        
        // HashMap: get from empty map
        HashMap<String, String> emptyMap = new HashMap<>();
        System.out.println("Get from empty map: " + emptyMap.get("@test"));
        System.out.println("Returns null correctly");
        
        // HashMap: isEmpty
        System.out.println("Empty map isEmpty: " + emptyMap.isEmpty());
        emptyMap.put("@key", "value");
        System.out.println("Map with 1 element isEmpty: " + emptyMap.isEmpty());
        
        // Graph: Add duplicate node
        System.out.println("\n--- Graph Edge Cases ---");
        Graph<String> graph = new Graph<>();
        graph.addNode("@test");
        try {
            graph.addNode("@test");
            System.out.println("ERROR: Should have thrown exception for duplicate node");
        } catch (IllegalArgumentException e) {
            System.out.println("Adding duplicate node throws exception correctly");
        }
        
        // Graph: Add edge with non-existent nodes
        try {
            graph.addEdge("@test", "@nonexistent");
            System.out.println("ERROR: Should have thrown exception for non-existent node");
        } catch (IllegalArgumentException e) {
            System.out.println("Adding edge with non-existent node throws exception correctly");
        }
        
        // Graph: Remove non-existent node
        try {
            graph.removeNode("@nonexistent");
            System.out.println("ERROR: Should have thrown exception for non-existent node");
        } catch (IllegalArgumentException e) {
            System.out.println("Removing non-existent node throws exception correctly");
        }
        
        // Graph: Remove non-existent edge
        graph.addNode("@node2");
        graph.addEdge("@test", "@node2");
        try {
            graph.removeEdge("@node2", "@test");  // Reverse direction doesn't exist
            System.out.println("ERROR: Should have thrown exception for non-existent edge");
        } catch (IllegalArgumentException e) {
            System.out.println("Removing non-existent edge throws exception correctly (directed graph)");
        }
        
        // Graph: isEmpty on empty graph
        Graph<String> emptyGraph = new Graph<>();
        System.out.println("Empty graph isEmpty: " + emptyGraph.isEmpty());
        emptyGraph.addNode("@single");
        System.out.println("Graph with 1 node (0 edges) isEmpty: " + emptyGraph.isEmpty());
    }
    
    private static void testGraph() {
        Graph<String> graph = new Graph<>();
        
        // Test addNode
        System.out.println("Adding nodes...");
        graph.addNode("@pepe");
        graph.addNode("@juan");
        graph.addNode("@maria");
        graph.addNode("@pedro");
        System.out.println("Node count: " + graph.getNodeCount());
        
        // Test containsNode
        System.out.println("Contains @juan: " + graph.containsNode("@juan"));
        System.out.println("Contains @ana: " + graph.containsNode("@ana"));
        
        // Test addEdge (directed)
        System.out.println("\nAdding directed edges...");
        graph.addEdge("@pepe", "@juan");
        graph.addEdge("@pepe", "@maria");
        graph.addEdge("@juan", "@pedro");
        graph.addEdge("@maria", "@pepe");  // Creates cycle
        System.out.println("Edge count: " + graph.getEdgeCount());
        
        // Test getNodes
        System.out.println("\nAll nodes:");
        LinkedList<String> nodes = graph.getNodes();
        System.out.println(nodes.toString());
        
        // Test getEdges
        System.out.println("\nAll edges:");
        LinkedList<Edge<String>> edges = graph.getEdges();
        Node<Edge<String>> currentEdge = edges.getHead();
        while (currentEdge != null) {
            System.out.println("  " + currentEdge.getData().toString());
            currentEdge = currentEdge.getNext();
        }
        
        // Test directed nature (verify @juan -> @pepe doesn't exist)
        System.out.println("\nVerifying directed graph:");
        System.out.println("Edge @pepe -> @juan exists");
        System.out.println("Checking if reverse edge exists...");
        try {
            graph.removeEdge("@juan", "@pepe");
            System.out.println("ERROR: Reverse edge should not exist!");
        } catch (IllegalArgumentException e) {
            System.out.println("Correct: Reverse edge does not exist (directed graph)");
        }
        
        // Test removeEdge
        System.out.println("\nRemoving edge @pepe -> @maria...");
        graph.removeEdge("@pepe", "@maria");
        System.out.println("Edge count after removal: " + graph.getEdgeCount());
        
        // Test removeNode (should remove all connected edges)
        System.out.println("\nRemoving node @pepe (should remove 2 edges)...");
        System.out.println("Edges before: " + graph.getEdgeCount());
        graph.removeNode("@pepe");
        System.out.println("Edges after: " + graph.getEdgeCount());
        System.out.println("Nodes after: " + graph.getNodeCount());
        
        // Test isEmpty
        System.out.println("\nIs empty: " + graph.isEmpty());
        
        // Create small cycle for visualization
        System.out.println("\nCreating cycle: @juan -> @pedro -> @maria -> @juan");
        graph.addEdge("@pedro", "@maria");
        graph.addEdge("@maria", "@juan");
        
        System.out.println("\nFinal graph edges:");
        edges = graph.getEdges();
        currentEdge = edges.getHead();
        while (currentEdge != null) {
            System.out.println("  " + currentEdge.getData().toString());
            currentEdge = currentEdge.getNext();
        }
    }
    
    private static void testKosaraju() {
        System.out.println("\n=== Testing Kosaraju: Project Data (13 users, 18 relations) ===");
        Graph<String> graph = new Graph<>();
        
        // Add all 13 users
        String[] users = {
            "@pepe", "@mazinger", "@juanc", "@xoxojaime", "@tuqui33",
            "@sancho23", "@terciopelo", "@caribedoble", "@africa",
            "@totalfree", "@radiogaga", "@cipriano", "@newageforever"
        };
        
        for (String user : users) {
            graph.addNode(user);
        }
        
        // Add all 18 relationships
        graph.addEdge("@pepe", "@mazinger");
        graph.addEdge("@mazinger", "@juanc");
        graph.addEdge("@mazinger", "@tuqui33");
        graph.addEdge("@tuqui33", "@xoxojaime");
        graph.addEdge("@xoxojaime", "@pepe");
        graph.addEdge("@juanc", "@sancho23");
        graph.addEdge("@sancho23", "@mazinger");
        graph.addEdge("@sancho23", "@terciopelo");
        graph.addEdge("@terciopelo", "@juanc");
        graph.addEdge("@terciopelo", "@newageforever");
        graph.addEdge("@terciopelo", "@caribedoble");
        graph.addEdge("@caribedoble", "@africa");
        graph.addEdge("@africa", "@cipriano");
        graph.addEdge("@cipriano", "@totalfree");
        graph.addEdge("@cipriano", "@radiogaga");
        graph.addEdge("@totalfree", "@africa");
        graph.addEdge("@totalfree", "@radiogaga");
        graph.addEdge("@radiogaga", "@caribedoble");
        
        System.out.println("Created graph with " + graph.getNodeCount() + " nodes and " + graph.getEdgeCount() + " edges");
        
        LinkedList<LinkedList<String>> components = Kosaraju.findSCC(graph);
        
        System.out.println("\nExpected: 3 components");
        System.out.println("  - Component 1: 7 nodes (pepe, mazinger, juanc, xoxojaime, tuqui33, sancho23, terciopelo)");
        System.out.println("  - Component 2: 5 nodes (africa, cipriano, totalfree, radiogaga, caribedoble)");
        System.out.println("  - Component 3: 1 node (newageforever)");
        
        System.out.println("\nFound: " + components.getSize() + " component(s)");
        
        // Mostrar componentes con detalle
        Node<LinkedList<String>> current = components.getHead();
        int componentNum = 1;
        while (current != null) {
            LinkedList<String> component = current.getData();
            System.out.println("\nComponent " + componentNum + " (" + component.getSize() + " nodes):");
            System.out.println("  " + component.toString());
            componentNum++;
            current = current.getNext();
        }
    }
}
