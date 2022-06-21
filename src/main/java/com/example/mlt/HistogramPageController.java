package com.example.mlt;

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

public class HistogramPageController implements Initializable {



    @FXML
    private BarChart<String,Number> HistogramChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private ComboBox<String> Month;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Month.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    }

    public void showHistogram (){
        HistogramChart.getData().clear();


        MergeSort ms = new MergeSort();

        File dir = new File("C:\\Users\\HO KAI CHONG\\IdeaProjects\\MLT\\src\\main\\resources\\HistogramTestCases");
        File[] files = dir.listFiles();

        if (files[0].isFile()) {
            Scanner sc = null;
            try {
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
                    String str1 = sc.nextLine();
                    String[] num1 = str1.split(" ");
                    int N = Integer.parseInt(num1[0]);
                    int M = Integer.parseInt(num1[1]);
                    String str2 = sc.nextLine();
                    String[] num2 = str2.split(" ");
                    int[] data = new int[N];
                    for (int i = 0; i < N; i++) {
                        data[i] = Integer.parseInt(num2[i]);
                    }

                    ms.mergeSort(data);

                    int width = (data[N - 1] - data[0]) / M;
                    int [] interval = new int[M + 1];
                    interval[0] = data[0];
                    for (int i = 1; i < interval.length; i++) {
                        interval[i] = interval[i - 1] + width;
                    }
                    for (int i = 0; i < interval.length; i++) {
                        System.out.print(interval[i] + " ");
                    }
                    System.out.println();
                    int [] count = new int[M];
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
                    for (int i = 0; i < count.length; i++) {
                        System.out.print(count[i] + " ");
                    }
                    System.out.println();
                    XYChart.Series <String,Number>series =new XYChart.Series<String,Number>();
                    for (int i = 0; i < count.length; i++) {
                        series.getData().add(new XYChart.Data(String.valueOf(interval[i]) + "-" + String.valueOf(interval[i + 1]), count[i]));
                    }

                    HistogramChart.getData().add(series);
                    HistogramChart.setAnimated(false);
                    HistogramChart.setBarGap(0);
                    HistogramChart.setVerticalGridLinesVisible(false);
                    HistogramChart.setCategoryGap(0);
                    
                }
            } catch (IOException e) {
                System.out.println("File got some problems.");
            } finally {
                if (sc != null)
                    sc.close();
            }


        }


    }


    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void UserProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void NavigationButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }
}

