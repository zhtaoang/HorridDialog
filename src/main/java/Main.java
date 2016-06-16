import Logger.EmailLogger;
import Logger.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;

interface StageAdapter {

    void setTitle(String s);

    void setScene(Scene scene);

    void show();
}

class JfxStageAdapter implements StageAdapter {
    private final Stage primaryStage;

    public JfxStageAdapter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void setTitle(String s) {
        this.primaryStage.setTitle(s);
    }

    @Override
    public void setScene(Scene scene) {
        this.primaryStage.setScene(scene);
    }

    @Override
    public void show() {
        this.primaryStage.show();
    }
}

interface ButtonAdapter {
    void setOnAction(EventHandler<ActionEvent> eventHandler);
}

class JfxButtonAdapter implements ButtonAdapter {
    private final Button button;

    public JfxButtonAdapter(Button btn) {
        this.button = btn;
    }

    @Override
    public void setOnAction(EventHandler<ActionEvent> eventHandler) {
        this.button.setOnAction(eventHandler);
    }
}

public class Main extends Application {

    private GridPane gridPane;
    private TextField userTextField;
    private Label firstName;
    private Label lastName;
    private Button btn;
    private Text error;
    private TextField lastNameTextField;
    private TextField firstNameTextField;
    private ButtonAdapter adaptedButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startWithStageAdapter(new JfxStageAdapter(primaryStage));
    }

    protected void startWithStageAdapter(StageAdapter primaryStage) {
        primaryStage.setTitle("Testing a Form");
        gridPane = new GridPane();

        setupDialog();

        adaptedButton = getButtonAdapter();

        adaptedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = userTextField.getText();

                try {
                    BufferedWriter out = null;
                    try {
                        FileWriter fstream = new FileWriter("userdata.csv", true);

                        out = new BufferedWriter(fstream);
                        if (EmailValidator.isValid(email) || firstName.isEmpty() || lastName.isEmpty()){
                            error.setFill(Color.FIREBRICK);
                            error.setText("Invalid Data");
                            UserDataWriter.write("continue");
                            throw new IllegalArgumentException(String.format("Email %s is not valid!", email));
                        }
                        out.write(String.format("\n%s, %s, %s", firstName, lastName, email));
                    } catch (IllegalArgumentException iae) {
                        Logger logger = new EmailLogger();
                        logger.log(iae.getMessage());
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(gridPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected ButtonAdapter getButtonAdapter() {
        return new JfxButtonAdapter(btn);
    }

    protected void setupDialog() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle =  new Text("Create User");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Email:");
        gridPane.add(userName, 0, 1);

        userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        firstName = new Label("First Name:");
        gridPane.add(firstName, 0, 2);

        firstNameTextField = new TextField();
        gridPane.add(firstNameTextField, 1, 2);

        lastName = new Label("Last Name:");
        gridPane.add(lastName, 0, 3);

        lastNameTextField = new TextField();
        gridPane.add(lastNameTextField, 1, 3);

        btn = new Button("Create");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        gridPane.add(hbBtn, 1, 4);

        error = new Text();
        gridPane.add(error, 1, 6);
    }
}