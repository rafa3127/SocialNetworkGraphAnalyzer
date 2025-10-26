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
 * Represents a simple linked list with generic data type.
 * 
 * @param <T> The type of elements stored in the list
 */
public class LinkedList<T> {
    private Node<T> head, tail;
    private int size;

    /**
     * Constructor that initializes an empty linked list.
     */
    public LinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }
    
    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Adds an element to the end of the list.
     * 
     * @param data The element to add to the list
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.isEmpty()) {
           this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size ++;
    }
    
    /**
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param data The element to remove
     * @throws IllegalArgumentException if the list is empty or the element is not found
     */    
    public void remove(T data) {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Cannot remove from empty list");
        }
        
        if (this.head.data.equals(data)) {
            this.head = this.head.next;
            if (this.size == 1) {
                this.tail = null;
            }
            this.size --;
            return;
        } 
        
        Node<T> currentNode = this.head;
        while(currentNode.next != null) {
            if (currentNode.next.data.equals(data)) {
                Node<T> nodeToRemove = currentNode.next;
                currentNode.next = nodeToRemove.next;
                
                if (nodeToRemove == this.tail) {
                   this.tail = currentNode;
                }
                
                this.size --;
                return;
            }
            currentNode = currentNode.next;
        }
   
        throw new IllegalArgumentException("Element not found in list");
    }

    /**
     * Checks if the list contains the specified element.
     * 
     * @param data The element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains (T data) {
        Node<T> currentNode = this.head;
        while(currentNode != null) {
            if (currentNode.data.equals(data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the size of the list
     */
    public int getSize() {
        return this.size;
    }

    
    /**
     * Removes all elements from the list, leaving it empty.
     */
    public void clear() {
        this.tail = this.head = null;
        this.size = 0;
    }
}
