package ua.edu;

import com.utils.encoding.UTF8Control;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.templates.footer.Footer;
import javafx.templates.header.Header;
import javafx.templates.lang.LanguageLocal;
import ua.edu.gui.StudentTableView;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by Oxana on 7/13/15.
 */
public class ReviewStudentsList extends Application{
    LanguageLocal local = new LanguageLocal();
    ResourceBundle bundle;
    public static int height;
    public static int weight;
    public static Header header;
    public static Footer footer;
    int ret;
    Stage primaryStage;
    public static Group root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        local.initLanguage(local.getResourceBundle("resources.LangLocale"));
        bundle = ResourceBundle.getBundle("resources.LangLocale", new UTF8Control());

        primaryStage.setTitle(bundle.getString("greetings"));
        this.primaryStage = primaryStage;
        root = new Group();

        height = (int) Screen.getPrimary().getVisualBounds().getMaxY();
        weight = (int) Screen.getPrimary().getVisualBounds().getMaxX();
        Scene scene = new Scene(root, height, weight);
        header = new Header("/resources/img/Onu_logo.png", "/resources/img/phon.jpeg", 100, weight);
        header.getNode().setLayoutX(0);
        header.getNode().setLayoutY(0);

        root.getChildren().add(header.getNode());
        HBox hBox=new HBox();
        hBox.setMinHeight(height - header.heightHeader - footer.heightFooter);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        StudentTableView tableView=new StudentTableView();
        hBox.getChildren().add(tableView.container);
        root.getChildren().add(hBox);
        footer = new Footer(false, false, true, 150, weight,"resources.Locale1");

        footer.getNode().setLayoutX(0);
        footer.getNode().setLayoutY(height - 150);
        for(int i=0;i<3;i++) {
            Node node=footer.getLangPanel().getChildren().get(i);
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bundle=footer.getBundle();
                    System.out.println(footer.getLocale());
//                    label.setText(bundle.getString("begin"));
   //                 btn.setText(bundle.getString("load"));
                }
            });
        }
        Button btnExit=new Button("Exit");
        btnExit.setStyle("-fx-background-color: green;-fx-font-weight: bold");

        btnExit.setMinHeight(50);
        btnExit.setMinWidth(100);
        btnExit.setLayoutY(height - 200);
        btnExit.setLayoutX(weight/2-500);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        root.getChildren().add(btnExit);


        root.getChildren().add(footer.getNode());
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main (String [] args){
        launch(args);
    }
}
