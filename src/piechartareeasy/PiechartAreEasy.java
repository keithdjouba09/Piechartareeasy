/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiechartAreEasy;

import static java.awt.SystemColor.text;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author sblythe
 */
public class PiechartAreEasy extends Application {
    
    public PieChart circleChart;
     private String input;
     private Text text;
     Random gen;
    
    @Override
    public void start(Stage primaryStage) {
        
        circleChart = new PieChart();
        gen= new Random();
      
     
        
        Pane pieChartHolder = new Pane();
        pieChartHolder.setBackground(
            new Background(  new BackgroundFill(Color.BISQUE, null, null)  )
        );
        pieChartHolder.setMinHeight(250);
        pieChartHolder.setMaxWidth(900);
        
        
        pieChartHolder.getChildren().add(circleChart);      
        
        HBox controlHolder = new HBox();
        
        
        Button opt1 = new Button("addword");
        Button opt2 = new Button("Clear All");
        Button quitter = new Button("Quit");
        
        TextField Field = new TextField();
    controlHolder.getChildren().addAll(Field,opt1, opt2, quitter);
    
        
        
        
    
        VBox animHolder = new VBox();
        
        animHolder.setMinWidth(400);
        animHolder.setMaxWidth(400);
        animHolder.setMinHeight(400);
        animHolder.setMaxHeight(400);
        
        animHolder.setBackground(
                new Background( new BackgroundFill(Color.WHEAT, null, null) )
        );
        
        Rectangle myClipper = new Rectangle(400,400);
        
        animHolder.setClip(myClipper);
      
        
        /*root.getChildren().addAll(animHolder, pieChartHolder);*/
         BorderPane root = new BorderPane();
    root.setPadding(new Insets(20)); // space between elements and window border
    root.setLeft(animHolder);
    root.setRight(pieChartHolder);
    root.setBottom(controlHolder);

         
        opt1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
               input = Field.getText();
                 text = new Text(input);
                for(int i=1; i<=1; i++)
        {
            
             
            animHolder.getChildren().add(text);
        
                PieChart.Data slice1 = new PieChart.Data(input , 1);
                /*double currVal = slice1.getPieValue();
                slice1.setPieValue(currVal);*/
                circleChart.getData().add(slice1);
         
        EventHandler sliceHandler = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent ev)
                  
                    {
                            PieChart.Data foundSlice = null;
                            for (PieChart.Data slice : circleChart.getData() )
                            {
                                if (slice.getNode() == ev.getSource())
                                    foundSlice = slice;
                            }                            
                            foundSlice.setPieValue(foundSlice.getPieValue()+1);
                        }
                };
            slice1.getNode().setOnMouseClicked(sliceHandler); 
                
            }
            }
            
        });
        
         quitter.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                  Alert quit = new Alert(Alert.AlertType.CONFIRMATION);
        
                //set up message to show in the box
        quit.setHeaderText("Are you sure you want to quit?");
        
                //wait the user to response
        Optional<ButtonType> result = quit.showAndWait();
        
                //exit from the whole program platform for the case "OK"
        if(result.get() == ButtonType.OK)
            Platform.exit();        //this exits from the program
            }
         });
         
         opt2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                  //check if there is any candidates to reset vote count
        
            
                //Pop up and alert box and confirm the reset
        Alert reset = new Alert(Alert.AlertType.CONFIRMATION);
            
                //set up header and content text for the pop up box
        reset.setHeaderText("Are you sure you want to reset?");
        
        
                //show the box and wait till user click something
        Optional<ButtonType> result = reset.showAndWait();
             if(result.get() == ButtonType.OK){
            circleChart.getData().clear();
            animHolder.getChildren().remove(text); 
            
             }
             
                //set up for the case user clicked "OK"
       
            
               //this exits from the program
            
                    //show no data message
            /*noDataPie.setVisible(true);*/
        
                
            }
         });
        
        /********
         * The following two lines force everything in  pieChart to be updated
         *   and re-layed out. 
         */
        pieChartHolder.applyCss();
        pieChartHolder.layout();
        
        /****** want to know rectangular bounds for something??? *****/
        Bounds buttonBoundary1 = opt1.getBoundsInParent();
        Bounds buttonBoundary2 = opt2.getBoundsInParent();
        
        /***** want to know if two bounds intersect? *****/
        if (buttonBoundary1.intersects(buttonBoundary2))
        {
            // code here
        }
        
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pie Chart Fun!");
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        
        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setMaxHeight(primaryStage.getHeight());
        
     
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
