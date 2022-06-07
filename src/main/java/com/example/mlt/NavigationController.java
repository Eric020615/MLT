package com.example.mlt;

import com.example.mlt.Graph.Displayable;
import com.example.mlt.Graph.GraphView;
import com.example.mlt.Graph.Location;
import com.example.mlt.Graph.UnweightedGraph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class NavigationController {

    UnweightedGraph<Location> graph;

    HashMap<String, Integer> place;

    @FXML
    AnchorPane map;

    @FXML
    Text price;

    @FXML
    Text totalStation;

    @FXML
    TextField source;

    @FXML
    TextField destination;

    @FXML
    Text information;

    @FXML
    Pane shortestpathMap;

    @FXML
    Button findpath;

    @FXML
    public void Map(ActionEvent event) {

        place = new HashMap<String, Integer>();
        Scanner input = new Scanner(System.in);

        graph = new UnweightedGraph();
        File file = new File("src/main/resources/com/example/mlt/NavigationMap.txt");
        if (file.exists()) {
            System.out.println("file exists");
        }
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(new FileInputStream(file));
                int index = 0;

                while (sc.hasNextLine()) {

                    String word = sc.nextLine();
                    if (word.equals("END"))
                        break;

                    String[] places = word.split(" => ");
                    String place_name1 = places[0];
                    String place_name2 = places[1];
                    if (!place.containsKey(place_name1)) {
                        place.put(place_name1, index++);
                    }
                    if (!place.containsKey(place_name2)) {
                        place.put(place_name2, index++);
                    }

                    graph.addVertex(new Location(place_name1, 0, 0));
                    graph.addVertex(new Location(place_name2, 0, 0));
                    graph.addEdge(place.get(place_name1), place.get(place_name2));

                }

                int positionX = 30;
                int positionY = 50;
                int i = 0;
                int j = 0;

                for (i = 0; i < graph.getSize(); i++) {

                    if (i % 10 == 0) {
                        if (i == 0) {
                            positionX += 0;
                        } else {
                            positionX += 80; //width
                        }
                        if (j % 2 == 0) {
                            positionY = 50;
                        } else {
                            positionY = 90;
                        }
                        j++;
                    }


                    graph.getVertex(i).setX(positionX);
                    graph.getVertex(i).setY(positionY);

                    positionY += 58;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        /*
        List<? extends Displayable> vertices = graph.getVertices();
        for (int i = 0; i < graph.getSize(); i++) {

            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            Location l = new Location();
            l = (Location) vertices.get(i);
            String name = l.getName();
            //System.out.println(showShortestPath.get(i));

            paneMap.getChildren().add(new Circle(x, y, 4)); // Display a vertex
            Text t = new Text(x -15, y-6 ,name);
            t.setFont(Font.font(10));
            paneMap.getChildren().add(t);




        }
        */
        }
            Scene scene = new Scene(new GraphView(graph), 1300, 700);
            Stage mapStage = new Stage();
            mapStage.setTitle("DisplayMRTMap"); // Set the stage title
            mapStage.setScene(scene); // Place the scene in the stage
            mapStage.show();


    }

    public void findShortestPath(ActionEvent event) {
        //
        // paneMap.getChildren().clear();
        shortestpathMap.getChildren().clear();
        String s = source.getText();
        String d = destination.getText();
        System.out.println(s + " " + d);

        LinkedList<Integer> shortpath = null;
        try {
            shortpath = graph.printShortestDistance(place.get(s), place.get(d), graph.getSize());
        } catch (NullPointerException e) {
            //information.setText("Sorry, the source or destination are not exists.");
            System.out.println("Sorry, the source or destination are not exists. ");
        }

        List<? extends Displayable> vertices = graph.getVertices();
        Integer [] n=new Integer[shortpath.size()];
        int j=shortpath.size()-1;
        for (int num : shortpath) {
            n[j]=num;
            j--;
        }
        int incx=5;

        for(int i=0;i<n.length;i++){

            int x = vertices.get(n[i]).getX();
            int y = vertices.get(n[i]).getY();
            Location l = new Location();
            l = (Location) vertices.get(n[i]);
            String name = l.getName();
            //paneMap.getChildren().add(new Circle(x, y, 5, Paint.valueOf("red"))); // Display a vertex
            shortestpathMap.getChildren().add(new Circle(105+incx, 80, 15, Paint.valueOf("blue")));
            Text t = new Text(x -15, y-6 ,name);
            t.setFont(Font.font(10));
            //paneMap.getChildren().add(t);
            shortestpathMap.getChildren().add(new Text(105+incx-15,80+25,name));

            if(i<n.length-1) {
                int x2 = graph.getVertex(n[i + 1]).getX();
                int y2 = graph.getVertex(n[i + 1]).getY();
                Line line = new Line(x, y, x2, y2);
                line.setStroke(Color.RED);
                Line line2 = new Line(120+incx, 80, 105+incx+100, 80);
                line2.setStroke(Color.GREEN);

                //paneMap.getChildren().add(line);
                shortestpathMap.getChildren().add(line2);
            }

            incx+=100;
        }

        double Price=shortpath.size()-1 * 0.80;
        System.out.println("\nRM "+price);
        price.setText("The Estimated Price : RM "+Price);

        int station=shortpath.size()-1;
        totalStation.setText("Total Station To Travel : "+station);
        information.setText("The shortest path from "+s+" to "+d );

    }
/*
    public void initialize(URL location, ResourceBundle resources) {
        Map(new ActionEvent());
    }*/

    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void HistogramButton(ActionEvent event) {

    }


    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void UserProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

}
