package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        // it takes fxml file and brings it to life
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        downcasting is possible for child element:
//        GridPane example = (GridPane) root;
//        example.setVgap(10);

        /*Group root = new Group(); // root element
        Text txt = new Text("Sup?");
        txt.setFont(new Font("Papyrus", 80));
        Label label = new Label("Name:");
        TextField nameFld = new TextField();
        GridPane grid = new GridPane();
        grid.add(label, 0, 0);
        grid.add(nameFld, 1, 0);
        grid.setHgap(20);
        grid.setVgap(5);
        Button btn = new Button();
        grid.add(btn, 1, 1);
        btn.setText("Say Sup!");
        btn.setOnAction(evt -> System.out.printf("Sup %s!%n", nameFld.getText())); // evt is argument
        VBox box = new VBox();
        box.getChildren().addAll(txt, grid);
        root.getChildren().add(box);*/
        primaryStage.setTitle("Sup");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
