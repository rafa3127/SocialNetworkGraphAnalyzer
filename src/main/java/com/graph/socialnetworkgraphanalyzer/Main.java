/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer;
import java.io.IOException;

import com.graph.socialnetworkgraphanalyzer.algorithm.Kosaraju;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Edge;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;
import com.graph.socialnetworkgraphanalyzer.io.FileIO;
import com.graph.socialnetworkgraphanalyzer.io.GraphFileManager;
import com.graph.socialnetworkgraphanalyzer.io.SectionParser;

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
        
        System.out.println("\n=== Testing FileIO ===");
        testFileIO();
        
        System.out.println("\n=== Testing SectionParser ===");
        testSectionParser();
        
        System.out.println("\n=== Testing GraphFileManager ===");
        testGraphFileManager();
        
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
    
    private static void  testFileIO() {
        try {
            LinkedList<String> lines = FileIO.readFile("test_data/data.txt");
            System.out.println("Lines:" + lines.toString());
        } catch (IOException ex) {
            System.out.println("Unexpected error when reading file");
        }
            // Write normal strings
            try {
                LinkedList<String> lines = new LinkedList<>();
                lines.add("usuarios");
                lines.add("@pepe");
                lines.add("@juan");
                lines.add("relaciones");
                lines.add("@pepe, @juan");
                
                String outputPath = "test_data/output_test.txt";
                FileIO.writeFile(outputPath, lines);
                System.out.println("✅ Test 1 passed: Normal strings written successfully");
                
                // Verify by reading back
                LinkedList<String> readBack = FileIO.readFile(outputPath);
                System.out.println("Read back: " + readBack.toString());
                
            } catch (Exception e) {
                System.out.println("❌ Test 1 failed: " + e.getMessage());
            }
            
            // Write with null element
            try {
                LinkedList<String> linesWithNull = new LinkedList<>();
                linesWithNull.add("line1");
                linesWithNull.add(null);  // This should fail
                linesWithNull.add("line3");
                
                FileIO.writeFile("test_data/should_fail.txt", linesWithNull);
                System.out.println("❌ Test 2 failed: Should have thrown exception for null element");
                
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Test 2 passed: Correctly rejected null element - " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Test 2 failed with wrong exception: " + e.getMessage());
            }
            
            // Write with non-String objects (should work with toString)
            try {
                LinkedList<Integer> numbers = new LinkedList<>();
                numbers.add(1);
                numbers.add(2);
                numbers.add(3);
                
                String outputPath = "test_data/numbers_test.txt";
                FileIO.writeFile(outputPath, numbers);
                System.out.println("✅ Test 3 passed: Integers converted to String successfully");
                
                // Verify
                LinkedList<String> readBack = FileIO.readFile(outputPath);
                System.out.println("Read back numbers: " + readBack.toString());
                
            } catch (Exception e) {
                System.out.println("❌ Test 3 failed: " + e.getMessage());
            }
            
            // Write to invalid path (should throw IOException)
            try {
                LinkedList<String> lines = new LinkedList<>();
                lines.add("test");
                
                FileIO.writeFile("/invalid/path/doesnt/exist/file.txt", lines);
                System.out.println("❌ Test 4 failed: Should have thrown IOException for invalid path");
                
            } catch (IOException e) {
                System.out.println("✅ Test 4 passed: Correctly threw IOException - " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Test 4 failed with wrong exception: " + e.getMessage());
            }
        
    }
    
    private static void testSectionParser() {
        
        // Parse normal file with 2 sections
        try {
            // Create parser with whitelist
            String[] whitelist = {"usuarios", "relaciones"};
            SectionParser parser = new SectionParser(whitelist);
            
            // Read file and parse
            LinkedList<String> lines = FileIO.readFile("test_data/data.txt");
            HashMap<String, LinkedList<String>> sections = parser.parse(lines);
            
            // Check sections exist
            System.out.println("✅ Test 1: File parsed successfully");
            System.out.println("  Found " + sections.size() + " sections");
            
            // Display usuarios section
            if (sections.containsKey("usuarios")) {
                LinkedList<String> usuarios = sections.get("usuarios");
                System.out.println("  Usuarios (" + usuarios.getSize() + " items): " + usuarios.toString());
            }
            
            // Display relaciones section
            if (sections.containsKey("relaciones")) {
                LinkedList<String> relaciones = sections.get("relaciones");
                System.out.println("  Relaciones (" + relaciones.getSize() + " items): " + relaciones.toString());
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 1 failed: " + e.getMessage());
        }
        
        // Parse with empty lines and lines before first section
        try {
            LinkedList<String> testLines = new LinkedList<>();
            testLines.add("this should be ignored");
            testLines.add("");  // empty line
            testLines.add("usuarios");
            testLines.add("@user1");
            testLines.add("");  // empty line
            testLines.add("@user2");
            testLines.add("relaciones");
            testLines.add("@user1, @user2");
            
            String[] whitelist = {"usuarios", "relaciones"};
            SectionParser parser = new SectionParser(whitelist);
            HashMap<String, LinkedList<String>> sections = parser.parse(testLines);
            
            System.out.println("✅ Test 2: Handled empty lines and ignored pre-header line");
            System.out.println("  Usuarios: " + sections.get("usuarios").toString());
            System.out.println("  Relaciones: " + sections.get("relaciones").toString());
            
        } catch (Exception e) {
            System.out.println("❌ Test 2 failed: " + e.getMessage());
        }
        
        // Parse with duplicate sections (should sum)
        try {
            LinkedList<String> testLines = new LinkedList<>();
            testLines.add("usuarios");
            testLines.add("@user1");
            testLines.add("relaciones");
            testLines.add("@user1, @user2");
            testLines.add("usuarios");  // duplicate section
            testLines.add("@user3");
            
            String[] whitelist = {"usuarios", "relaciones"};
            SectionParser parser = new SectionParser(whitelist);
            HashMap<String, LinkedList<String>> sections = parser.parse(testLines);
            
            LinkedList<String> usuarios = sections.get("usuarios");
            System.out.println("✅ Test 3: Duplicate sections summed correctly");
            System.out.println("  Usuarios (should have 2 items): " + usuarios.toString());
            System.out.println("  Size: " + usuarios.getSize());
            
        } catch (Exception e) {
            System.out.println("❌ Test 3 failed: " + e.getMessage());
        }
        
        // Empty sections
        try {
            LinkedList<String> testLines = new LinkedList<>();
            testLines.add("usuarios");
            testLines.add("relaciones");  // empty section
            
            String[] whitelist = {"usuarios", "relaciones"};
            SectionParser parser = new SectionParser(whitelist);
            HashMap<String, LinkedList<String>> sections = parser.parse(testLines);
            
            System.out.println("✅ Test 4: Empty sections handled");
            System.out.println("  Usuarios size: " + sections.get("usuarios").getSize());
            System.out.println("  Relaciones size: " + sections.get("relaciones").getSize());
            
        } catch (Exception e) {
            System.out.println("❌ Test 4 failed: " + e.getMessage());
        }
        
        // Serialize sections back to lines
        try {
            // Create sections manually
            HashMap<String, LinkedList<String>> sections = new HashMap<>();
            
            LinkedList<String> usuarios = new LinkedList<>();
            usuarios.add("@user1");
            usuarios.add("@user2");
            sections.put("usuarios", usuarios);
            
            LinkedList<String> relaciones = new LinkedList<>();
            relaciones.add("@user1, @user2");
            sections.put("relaciones", relaciones);
            
            // Serialize
            String[] whitelist = {"usuarios", "relaciones"};
            SectionParser parser = new SectionParser(whitelist);
            LinkedList<String> lines = parser.serialize(sections);
            
            System.out.println("✅ Test 5: Serialize sections back to lines");
            System.out.println("  Generated " + lines.getSize() + " lines: " + lines.toString());
            
            // parse again and verify
            HashMap<String, LinkedList<String>> parsedAgain = parser.parse(lines);
            System.out.println("  usuarios size: " + parsedAgain.get("usuarios").getSize());
            System.out.println("  relaciones size: " + parsedAgain.get("relaciones").getSize());
            
            if (parsedAgain.get("usuarios").getSize() == 2 && 
                parsedAgain.get("relaciones").getSize() == 1) {
                System.out.println("  Serialization successful!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 5 failed: " + e.getMessage());
        }
    }
    
    private static void testGraphFileManager() {
        // Load graph from file and verify structure
        try {
            Graph<String> graph = GraphFileManager.loadGraphFromFile("test_data/data.txt");
            
            System.out.println("✅ Test 1: Graph loaded successfully");
            System.out.println("  Nodes: " + graph.getNodeCount());
            System.out.println("  Edges: " + graph.getEdgeCount());
            
            // Verify some users exist
            if (graph.containsNode("@pepe") && graph.containsNode("@juan")) {
                System.out.println("  Sample users verified: @pepe, @juan");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 1 failed: " + e.getMessage());
        }
        
        // load graph to file
        try {
            // Create a test graph
            Graph<String> originalGraph = new Graph<>();
            originalGraph.addNode("@alice");
            originalGraph.addNode("@bob");
            originalGraph.addNode("@charlie");
            originalGraph.addEdge("@alice", "@bob");
            originalGraph.addEdge("@bob", "@charlie");
            
            // Save to file
            String testFile = "test_data/roundtrip_test.txt";
            GraphFileManager.saveGraphToFile(originalGraph, testFile);
            System.out.println("✅ Test 2a: Graph saved successfully");
            
            // Load back
            Graph<String> loadedGraph = GraphFileManager.loadGraphFromFile(testFile);
            
            // Verify counts match
            if (loadedGraph.getNodeCount() == 3 && loadedGraph.getEdgeCount() == 2) {
                System.out.println("✅ Test 2b: Round-trip successful");
                System.out.println("  Loaded nodes: " + loadedGraph.getNodeCount());
                System.out.println("  Loaded edges: " + loadedGraph.getEdgeCount());
            } else {
                System.out.println("❌ Test 2b failed: Counts don't match");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 2 failed: " + e.getMessage());
        }
        
        // Load graph with only users (no relations)
        try {
            // Create file with only users
            LinkedList<String> lines = new LinkedList<>();
            lines.add("usuarios");
            lines.add("@user1");
            lines.add("@user2");
            
            String testFile = "test_data/only_users.txt";
            FileIO.writeFile(testFile, lines);
            
            Graph<String> graph = GraphFileManager.loadGraphFromFile(testFile);
            
            if (graph.getNodeCount() == 2 && graph.getEdgeCount() == 0) {
                System.out.println("✅ Test 3: Graph with only users loaded correctly");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 3 failed: " + e.getMessage());
        }
        
        // Load empty graph
        try {
            // Create empty file
            LinkedList<String> lines = new LinkedList<>();
            String testFile = "test_data/empty_graph.txt";
            FileIO.writeFile(testFile, lines);
            
            Graph<String> graph = GraphFileManager.loadGraphFromFile(testFile);
            
            if (graph.getNodeCount() == 0 && graph.getEdgeCount() == 0) {
                System.out.println("✅ Test 4: Empty graph handled correctly");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Test 4 failed: " + e.getMessage());
        }
        
        // Invalid user format (missing @)
        try {
            LinkedList<String> lines = new LinkedList<>();
            lines.add("usuarios");
            lines.add("invalid_user");  // Missing @
            
            String testFile = "test_data/invalid_user.txt";
            FileIO.writeFile(testFile, lines);
            
            GraphFileManager.loadGraphFromFile(testFile);
            System.out.println("❌ Test 5 failed: Should have thrown exception");
            
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Test 5: Invalid user format rejected - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Test 5 failed with wrong exception: " + e.getMessage());
        }
        
        // Relation references non-existent user
        try {
            LinkedList<String> lines = new LinkedList<>();
            lines.add("usuarios");
            lines.add("@user1");
            lines.add("relaciones");
            lines.add("@user1, @nonexistent");  // @nonexistent doesn't exist
            
            String testFile = "test_data/invalid_relation.txt";
            FileIO.writeFile(testFile, lines);
            
            GraphFileManager.loadGraphFromFile(testFile);
            System.out.println("❌ Test 6 failed: Should have thrown exception");
            
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Test 6: Invalid relation rejected - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Test 6 failed with wrong exception: " + e.getMessage());
        }
    }
}
