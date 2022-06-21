package com.example.mlt.Graph;
//import all the libraries needed
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;

public class GraphView extends Pane {
    private UnweightedGraph<? extends Displayable> graph;

    public GraphView(UnweightedGraph<Location> graph) {
        this.graph = graph;
        Image lct = new Image("file:src/main/resources/image/Location Pin.png");
        Image background = new Image("file:src/main/resources/image/Map Background.png");

        // Draw location pin
        List<? extends Displayable> vertices = graph.getVertices();
        BackgroundImage back = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        setBackground(new Background(back));
        //A loop process to plot the station one by one on the graph
        for (int i = 0; i < graph.getSize(); i++) {
            //get position x and y of the station
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            Location l ;
            l = (Location) vertices.get(i);
            String name = l.getName();
            //use the represented location image to plot the station
            ImageView pin = new ImageView(lct);
            pin.setX(x);
            pin.setY(y);
            pin.setFitWidth(20);
            pin.setFitHeight(20);
            getChildren().add(pin);

            //set the station name beside the plotting station on the graph
            Text t= new Text(x-20 , y -4, name);
            t.setFont(Font.font("Times New Romen",12));
            t.setFill(Color.BLUE);
            getChildren().add(t);

        }
    }
}





