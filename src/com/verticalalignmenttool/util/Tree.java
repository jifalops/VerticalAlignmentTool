//package com.verticalalignmenttool.util;
//
//import java.util.LinkedList;
//
// Basic tree:
// http://stackoverflow.com/questions/3522454/java-tree-data-structure
//public class Tree<T> {
//    private Node<T> root;
//
//    public Tree(T rootData) {
//        root = new Node<T>();
//        root.data = rootData;
//        root.children = new LinkedList<Node<T>>();
//    }
//
//    public Node<T> getRootNode() {
//    	return root;
//    }
//
//    public static class Node<T> {
//        private T data;
//        private Node<T> parent;
//        private LinkedList<Node<T>> children;
//
//        public Node(T data) {
//        	this.data = data;
//        }
//
//        public boolean addChild(T child) {
//        	return children.add(child);
//        }
//
//        public void addChild(int index, T child) {
//        	children.add(index, child);
//        }
//
//        public Node<T> getChild(int index) {
//        	return children.get(index);
//        }
//
//        public boolean removeChild(T child) {
//        	return children.remove(child);
//        }
//
//        public Node<T> removeChild(int index) {
//        	return children.remove(index);
//        }
//    }
//}