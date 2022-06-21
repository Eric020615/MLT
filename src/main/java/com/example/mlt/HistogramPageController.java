package com.example.mlt;
//import all the libraries needed
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
//Histogram Page Controller
public class HistogramPageController implements Initializable {
    //Barchart field for our histogram chart
    @FXML
    private BarChart<String,Number> HistogramChart;
    //x-axis
    @FXML
    private CategoryAxis x;
    //y-axis
    @FXML
    private NumberAxis y;
    //Create the combo box
    @FXML
    private ComboBox<String> Month;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //list down all the elements of the combo box
        Month.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    }

    //Method name showHistogram
    public void showHistogram (){
        //initialise the data in the histogram chart
        HistogramChart.getData().clear();
        //Create an instance for the MergeSort
        MergeSort ms = new MergeSort();
        //access the text file for the histogram
        File dir = new File("C:\\Users\\HO KAI CHONG\\IdeaProjects\\MLT\\src\\main\\resources\\HistogramTestCases");
        File[] files = dir.listFiles();
        //open the first file
        if (files[0].isFile()) {
            Scanner sc = null;
            try {
                //Show the bar chart for each month when the user click the month
                sc = new Scanner(new FileInputStream(files[1]));
                if(Month.getValue()=="January")
                    sc = new Scanner(new FileInputStream(files[0]));
                else if(Month.getValue()=="February")
                    sc = new Scanner(new FileInputStream(files[1]));
                else if(Month.getValue()=="March")
                    sc = new Scanner(new FileInputStream(files[2]));
                else if(Month.getValue()=="April")
                    sc = new Scanner(new FileInputStream(files[3]));
                else if(Month.getValue()=="May")
                    sc = new Scanner(new FileInputStream(files[4]));
                else if(Month.getValue()=="June")
                    sc = new Scanner(new FileInputStream(files[5]));
                else if(Month.getValue()=="July")
                    sc = new Scanner(new FileInputStream(files[6]));
                else if(Month.getValue()=="August")
                    sc = new Scanner(new FileInputStream(files[7]));
                else if(Month.getValue()=="September")
                    sc = new Scanner(new FileInputStream(files[8]));
                else if(Month.getValue()=="October")
                    sc = new Scanner(new FileInputStream(files[9]));
                else if(Month.getValue()=="November")
                    CommonTask.showAlert(Alert.AlertType.ERROR,"No Available Data","No Available Data To Show Histogram.");
                else
                    CommonTask.showAlert(Alert.AlertType.ERROR,"No Available Data","No Available Data To Show Histogram.");
                while (sc.hasNextLine()) {
                    //get the String text from the text file
                    String str1 = sc.nextLine();
                    //Split the String into the array
                    String[] num1 = str1.split(" ");
                    //Convert it to the integer
                    int N = Integer.parseInt(num1[0]);
                    int M = Integer.parseInt(num1[1]);
                    //get the String text from the text file
                    String str2 = sc.nextLine();
                    //Convert it to the integer
                    String[] num2 = str2.split(" ");
                    int[] data = new int[N];
                    for (int i = 0; i < N; i++) {
                        //Convert it to the integer
                        data[i] = Integer.parseInt(num2[i]);
                    }
                    //Sort the data by using merge sort
                    ms.mergeSort(data);
                    //Get the width
                    int width = (data[N - 1] - data[0]) / M;
                    //Get Interval
                    int [] interval = new int[M + 1];
                    interval[0] = data[0];
                    for (int i = 1; i < interval.length; i++) {
                        interval[i] = interval[i - 1] + width;
                    }
                    for (int i = 0; i < interval.length; i++) {
                        System.out.print(interval[i] + " ");
                    }
                    System.out.println();
                    //Get the count
                    int [] count = new int[M];
                    //Perform the for loop operation to compute the count
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < data.length; j++) {
                            if (i == M - 1) {
                                if (data[j] >= interval[i] && data[j] <= interval[i + 1]) {
                                    count[i]++;
                                }
                            } else {
                                if (data[j] >= interval[i] && data[j] < interval[i + 1]) {
                                    count[i]++;
                                }
                            }
                        }
                    }
                    //Print the count one by one
                    for (int i = 0; i < count.length; i++) {
                        System.out.print(count[i] + " ");
                    }
                    System.out.println();
                    //Open object series by importing the XYChart.Series and types are String and Number
                    XYChart.Series <String,Number>series =new XYChart.Series<String,Number>();
                    for (int i = 0; i < count.length; i++) {
                        //add data to the XYChart accordingly
                        series.getData().add(new XYChart.Data(String.valueOf(interval[i]) + "-" + String.valueOf(interval[i + 1]), count[i]));
                    }
                    //send data to the XYChart
                    HistogramChart.getData().add(series);
                    //Set the Histogram
                    HistogramChart.setAnimated(false);
                    //Set the histogram without the gap
                    HistogramChart.setBarGap(0);
                    //make the vertical grid lines to be invisible
                    HistogramChart.setVerticalGridLinesVisible(false);
                    //Set the gap to be zero
                    HistogramChart.setCategoryGap(0);
                    
                }
            } catch (IOException e) {
                //Print the message when the errors found
                System.out.println("File got some problems.");
            } finally {
                if (sc != null);
                    sc.close();
            }


        }


    }

    //On Action Function to move to Home Page with title: MEOW RAPID TRANSIT
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }
    //On Action Function to move to Payment Page with title: MEOW RAPID TRANSIT
    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }
    //On Action Function to move to User Profile Page with title: MEOW RAPID TRANSIT
    @FXML
    void UserProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }
    //On Action Function to move to Navigation Page with title: MEOW RAPID TRANSIT
    @FXML
    void NavigationButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }
}

