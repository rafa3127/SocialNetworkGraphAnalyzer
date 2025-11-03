/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;

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
    }
}
