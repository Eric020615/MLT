package com.example.mlt.Graph;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;

public class GraphView extends Pane {
    private UnweightedGraph<? extends Displayable> graph;

    public GraphView(UnweightedGraph<Location> graph) {
        this.graph = graph;

        // Draw vertices
        List<? extends Displayable> vertices = graph.getVertices();

        for (int i = 0; i < graph.getSize(); i++) {

            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            Location l = new Location();
            l = (Location) vertices.get(i);
            String name = l.getName();
            //System.out.println(showShortestPath.get(i));

                getChildren().add(new Circle(x, y, 5)); // Display a vertex
                getChildren().add(new Text(x-20 , y -10, name));


        }
        /*
        while (!showShortestPath.isEmpty()) {

            int loc = showShortestPath.removeLast();
            Location l = new Location();
            l = (Location) vertices.get(loc);
            String name = l.getName();
            int x = vertices.get(loc).getX();
            int y = vertices.get(loc).getY();
            getChildren().add(new Circle(x, y, 5, Paint.valueOf("red"))); // Display a vertex
            getChildren().add(new Text(x - 8, y - 18, name));

            List<Integer> neighbors = graph.getNeighbors(loc);
            for (int v : neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();
                Line line = new Line(x, y, x2, y2);
                line.setStroke(Color.RED);
                getChildren().add(line);
            }
        }
        */


        // Draw edges for pairs of vertices
/*
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v : neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                // Draw an edge for (i, v)
                if (i == 0 || i == 3 || i == 65) {
                    Line line = new Line(x1, y1, x2, y2);
                    line.setStroke(Color.RED);
                    getChildren().add(line);
                } //else {
                 //   getChildren().add(new Line(x1, y1, x2, y2));
               // }
            }
*/
    }
}





