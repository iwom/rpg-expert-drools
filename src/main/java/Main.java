import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sample.Department;
import sample.Employee;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample class to launch a rule.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        primaryStage.setTitle("RPG Expert");
        Insets insets = new Insets(10);

        List<String> choices = new ArrayList<String>();
        choices.add("Yes");
        choices.add("No");
        choices.add("Not sure");
        final ToggleGroup toggleGroup = new ToggleGroup();
        final VBox vBoxBottom = new VBox(10.0);
        final VBox vBoxMiddle = new VBox(10.0);
        final VBox vBoxTop = new VBox(10.0);
        final Button button = new Button("Go!");
        button.setFont(new Font(30));
        button.setOnAction(System.out::println);
        vBoxTop.setPadding(insets);
        vBoxMiddle.setPadding(insets);
        vBoxBottom.setPadding(insets);
        toggleGroup.selectedToggleProperty().addListener(System.out::println);
        Text messageText = new Text("DummyText");
        messageText.setFont(new Font(40));
        vBoxTop.getChildren().add(messageText);
        vBoxBottom.getChildren().add(button);
        choices.forEach(choice -> {
            RadioButton rb = new RadioButton(choice);
            rb.setToggleGroup(toggleGroup);
            rb.setFont(new Font(20));
            vBoxMiddle.getChildren().add(rb);
        });
        root.add(vBoxTop, 0, 0, 1, 1);
        root.add(vBoxMiddle, 0, 1, 1, 1);
        root.add(vBoxBottom, 0, 2, 1, 1);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            // load up the knowledge base
            launch(args);
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            Department civil = Department.builder().name("Civil").build();
            Department it = Department.builder().name("IT").build();
            Department adm = Department.builder().name("Administation").build();
            Employee john = Employee.builder().department(civil).name("John").manager(true).message("Hi, Im John").build();
            Employee george = Employee.builder().department(civil).name("George").manager(false).message("Hi Im Geo").build();
            Employee stephanie = Employee.builder().department(it).name("Stephanie").manager(true).message("Jo Stephanie").build();
            Employee ian = Employee.builder().department(it).name("Ian").manager(false).message("I'AN").build();


            kSession.insert(adm);
            kSession.insert(civil);
            kSession.insert(it);
            kSession.insert(john);
            kSession.insert(george);
            kSession.insert(stephanie);
            kSession.insert(ian);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
