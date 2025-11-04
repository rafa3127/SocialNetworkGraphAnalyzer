/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.io;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.HashMap;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;

/**
 *
 * @author rafaelc3127
 */
public class SectionParser {
    
    private HashMap<String, Boolean> sectionNames;
    
    /**
     * Creates a new SectionParser with a specific whitelist of section names.
     * 
     * @param sectionNames Array of valid section names
     */
    public SectionParser(String[] sectionNames) {
        this.sectionNames = new HashMap<>();
        for (String name : sectionNames) {
            this.sectionNames.put(name, true);  // Value es dummy, solo usamos keys
        }
    }
    
    /**
     * Checks if a line is a section header (exists in whitelist).
     * 
     * @param line Line to check (will be trimmed)
     * @return true if line is in whitelist, false otherwise
     */
    private boolean isSectionHeader(String line) {
        return this.sectionNames.containsKey(line.trim());
    }
    
    /**
     * Parses lines into sections based on the configured whitelist.
     * Lines before any section header are ignored.
     * Empty lines are ignored.
     * If a section appears multiple times, lines are added to the existing section.
     * 
     * @param lines All lines from the file
     * @return HashMap where key=section name, value=LinkedList of lines in that section
     */
    public HashMap<String, LinkedList<String>> parse(LinkedList<String> lines) {
        HashMap<String, LinkedList<String>> sections = new HashMap<>();
        String currentSection = null;
        
        Node<String> current = lines.getHead();
        while (current != null) {
            String line = current.getData().trim();
            
            // Ignore empty lines
            if (line.isEmpty()) {
                current = current.getNext();
                continue;
            }
            
            // Check if this line is a section header
            if (isSectionHeader(line)) {
                currentSection = line;
                
                // If section doesn't exist yet, create it
                if (!sections.containsKey(currentSection)) {
                    sections.put(currentSection, new LinkedList<>());
                }
                // If section already exists, we'll just add to it (handles duplicates)
                
            } else {
                // It's a data line - add to current section if we have one
                if (currentSection != null) {
                    LinkedList<String> sectionData = sections.get(currentSection);
                    sectionData.add(line);
                }
                // If currentSection is null, ignore the line (before first header)
            }
            
            current = current.getNext();
        }
        
        return sections;
    }
    
    /**
     * Builds file lines from sections HashMap.
     * Creates lines in the format: section header followed by section data.
     * Sections are written in the order they appear when iterating the HashMap.
     * 
     * @param sections HashMap where key=section name, value=LinkedList of lines
     * @return LinkedList of lines ready to write to file
     */
    public LinkedList<String> serialize(HashMap<String, LinkedList<String>> sections) {
        LinkedList<String> lines = new LinkedList<>();
        
        // Get all section names
        LinkedList<String> sectionNames = sections.getKeys();
        Node<String> currentSection = sectionNames.getHead();
        
        while (currentSection != null) {
            String sectionName = currentSection.getData();
            
            // Add section header
            lines.add(sectionName);
            
            // Add section data
            LinkedList<String> sectionData = sections.get(sectionName);
            Node<String> currentLine = sectionData.getHead();
            
            while (currentLine != null) {
                lines.add(currentLine.getData());
                currentLine = currentLine.getNext();
            }
            
            currentSection = currentSection.getNext();
        }
        
        return lines;
    }
}
