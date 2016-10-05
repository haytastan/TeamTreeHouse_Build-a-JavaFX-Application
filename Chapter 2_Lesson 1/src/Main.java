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
    //    main is an application for inheritance of application class

    public void start(Stage primaryStage) throws Exception{
        // Stage is where the performance is happening (swing'deki jframe'e benzer)
        Group root = new Group(); // root element
        Text txt = new Text("Sup?");
        txt.setFont(new Font("Papyrus", 80));
        Label label = new Label("Name:");
        TextField nameFld = new TextField();
        GridPane grid = new GridPane();
        grid.add(label, 0, 0);
        grid.add(nameFld, 1, 0); // array'in aksine 1 columnu 0 ise rowu niteler
        grid.setHgap(20);
        grid.setVgap(5);
        Button btn = new Button();
        grid.add(btn, 1, 1);
        // grid.setGridLinesVisible(true); // to help understanding to see what current grid looks like
        btn.setText("Say Sup!");
        btn.setOnAction(evt -> System.out.printf("Sup %s!%n", nameFld.getText())); // evt is argument
        // *setOnAction metodu, (EventHandler interface'ine ait)
        // evt argumentin bulunduğu handle metodunu kullanır*
        VBox box = new VBox(); // layout eklendi
        box.getChildren().addAll(txt, grid); // vbox a eklenme sırası önemli
        // gridpane, vbox a eklenmiş
        root.getChildren().add(box);
        primaryStage.setTitle("Sup");
        primaryStage.setScene(new Scene(root, 300, 275));
        // root is passed to the scene
        // new scene is passed to the setscene method of the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        // threadde main method'da start metodu çağırılırken run metodu override ediliyordu
    }
}
