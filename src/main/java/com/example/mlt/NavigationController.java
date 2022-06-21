package com.example.mlt;
//import all the libraries needed
import com.example.mlt.Graph.Displayable;
import com.example.mlt.Graph.GraphView;
import com.example.mlt.Graph.Location;
import com.example.mlt.Graph.UnweightedGraph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


public class NavigationController implements Initializable {

    UnweightedGraph<Location> graph;

    HashMap<String, Integer> place;

    @FXML
    AnchorPane map;

    @FXML
    Label price;

    @FXML
    Label totalStation;

    @FXML
    TextField source;

    @FXML
    TextField destination;

    @FXML
    Label information;

    @FXML
    Pane shortestpathMap;

    @FXML
    Button findpath;

    @FXML
    //A method to plot the map of station navigation
    public void Map(ActionEvent event) {
            //Create a scene and stage for the map
            Scene scene = new Scene(new GraphView(graph), 1300, 700);
            Stage mapStage = new Stage();
            mapStage.setTitle("DisplayMRTMap"); // Set the stage title
            mapStage.setScene(scene); // Place the scene in the stage
            mapStage.show();


    }
    //A method to find the shortest path from a location to destination
    public void findShortestPath(ActionEvent event) {
        //Clean the pane before process
        shortestpathMap.getChildren().clear();
        //Get the source location and destination information
        String s = source.getText();
        String d = destination.getText();
        System.out.println(s + " " + d);

        LinkedList<Integer> shortpath = null;
        //Store the corresponding index of station along the shortest path in a list
        try {
            shortpath = graph.printShortestDistance(place.get(s), place.get(d), graph.getSize());
        //Print error message when cannot find the source or destination
        } catch (NullPointerException e) {
            System.out.println("Sorry, the source or destination are not exists. ");
        }

        //Get the station name along the shortest path and store in a list
        List<? extends Displayable> vertices = graph.getVertices();
        try {
            //Create a array n to store the index of station
            Integer[] n = new Integer[shortpath.size()];
            int j = shortpath.size() - 1;
            for (int num : shortpath) {
                n[j] = num;
                j--;
            }
            int incx = 5;
            //Plot out the station name using the circle icon on the pane
            for (int i = 0; i < n.length; i++) {
                //Get position x and y of station
                int x = vertices.get(n[i]).getX();
                int y = vertices.get(n[i]).getY();
                Location l = new Location();
                l = (Location) vertices.get(n[i]);
                String name = l.getName();
                //Plot the circle on the map
                shortestpathMap.getChildren().add(new Circle(105 + incx, 80, 15, Paint.valueOf("blue")));
                //Set the station name beside the circle plot
                Text t = new Text(105 + incx - 15, 80 + 30, name);
                t.setFont(Font.font("Times New Romen",15));
                shortestpathMap.getChildren().add(t);
                //Draw the line between the station
                if (i < n.length - 1) {
                    int x2 = graph.getVertex(n[i + 1]).getX();
                    int y2 = graph.getVertex(n[i + 1]).getY();
                    Line line = new Line(x, y, x2, y2);
                    line.setStroke(Color.RED);
                    Line line2 = new Line(120 + incx, 80, 105 + incx + 100, 80);
                    line2.setStroke(Color.GREEN);

                    //Show the line between the stations on the map
                    shortestpathMap.getChildren().add(line2);
                }

                incx += 100;
            }
            //Calculate the estimated price of the travelling station
            double Price = shortpath.size() - 1 * 0.80;
            System.out.println("\nRM " + price);
            price.setText("The Estimated Price : RM " + Price);

            //Calculate the total station travelling
            int station = shortpath.size() - 1;
            totalStation.setText("Total Station To Travel : " + station);
            information.setText("The shortest path from " + s + " to " + d);
            //Show out all the information by set visible to true
            price.setVisible(true);
            information.setVisible(true);
            totalStation.setVisible(true);
            shortestpathMap.setVisible(true);
        //Pop out error message when the source or destination is not exist
        } catch (NullPointerException e) {
            CommonTask.showAlert(Alert.AlertType.ERROR, "Failed to find", "Sorry, the source or destination are not exists.");
        }

    }

    //Get all station from txt file and construct a graph according to the station information in txt file
    public void initialize(URL location, ResourceBundle resources) {
        //Use hash map to store every station with a particular number
        place = new HashMap<String, Integer>();
        Scanner input = new Scanner(System.in);
        //Create a graph
        graph = new UnweightedGraph();
        File file = new File("src/main/resources/com/example/mlt/NavigationMap.txt");
        if (file.exists()) {
            System.out.println("file exists");
        }

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(new FileInputStream(file));
                int index = 0;
                //Read the txt file
                while (sc.hasNextLine()) {
                    //Stop reading when END is detect
                    String word = sc.nextLine();
                    if (word.equals("END"))
                        break;
                    //Split the text in file to get information of two linked station
                    String[] places = word.split(" => ");
                    String place_name1 = places[0];
                    String place_name2 = places[1];
                    //Store them in hash map
                    if (!place.containsKey(place_name1)) {
                        place.put(place_name1, index++);
                    }
                    if (!place.containsKey(place_name2)) {
                        place.put(place_name2, index++);
                    }

                    //Add the vertices (stations) on the graph
                    graph.addVertex(new Location(place_name1, 0, 0));
                    graph.addVertex(new Location(place_name2, 0, 0));
                    //Add the edge between the vertices
                    graph.addEdge(place.get(place_name1), place.get(place_name2));

                }
                //This process is setting each station with different location (use different position x and y ) for plotting later
                int positionX = 30;
                int positionY = 50;
                int i = 0;
                int j = 0;
                //This loop process create the difference location for each station by changing position x and y
                for (i = 0; i < graph.getSize(); i++) {
                    //Change position x and y when 10 station are plotting out
                    if (i % 10 == 0) {
                        if (i == 0) {
                            positionX += 0;
                        } else {
                            positionX += 80;
                        }
                        if (j % 2 == 0) {
                            positionY = 50;
                        } else {
                            positionY = 90;
                        }
                        j++;
                    }

                    //set position x and y to station
                    graph.getVertex(i).setX(positionX);
                    graph.getVertex(i).setY(positionY);

                    positionY += 60
                    ;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //Set the label of information, price, total station and map to false before the process is running
        price.setVisible(false);
        information.setVisible(false);
        totalStation.setVisible(false);
        shortestpathMap.setVisible(false);
    }

    //Action on button to witch to Home Page Action
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }
    //Action on button to switch to Histogram Page
    @FXML
    void HistogramButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Histogram Page.fxml","MEOW RAPID TRANSIT");
    }
    //Action on button to switch to Payment Page
    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }
    //Action on button to switch to User Profile Page
    @FXML
    void UserProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

}
