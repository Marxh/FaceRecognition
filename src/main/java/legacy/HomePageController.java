package legacy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import view.CapturePage;
import view.FilterPage;
import view.SettingPage;

import java.awt.*;

import java.net.URI;


public class HomePageController {
    @FXML
    public Button homepage;
    @FXML
    public Hyperlink urlOpenCV;
    @FXML
    public Hyperlink urlCMU;
    @FXML
    public Hyperlink urlHelp;
    @FXML
    public Button capture;
    @FXML
    public ImageView heinzLogo;
    @FXML
    public ImageView originalFrame;
    @FXML
    public HBox topMenu;
    @FXML
    public VBox leftPane;
    @FXML
    public HBox captureHBox;
    @FXML
    public Pane urlPane;
    @FXML
    public Button logButton;
    @FXML
    public Button reportButton;
    @FXML
    public Button settingButton;
    @FXML
    public Button exitButton;

    @FXML
    public void goHome(ActionEvent actionEvent) {
        HomePage home = new HomePage();
        home.start(new Stage());

        Stage stage = (Stage) homepage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openUrlOpenCV(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://opencv.org/about/");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openUrlCMU(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.cmu.edu");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openUrlHelp(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://docs.opencv.org/4.1.2/");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goLog(ActionEvent actionEvent) {
    }

    public void goReport(ActionEvent actionEvent) {
        FilterPage filterPage = new FilterPage();
        filterPage.start(new Stage());
    }

    public void goSetting(ActionEvent actionEvent) {
        SettingPage settingPage = new SettingPage();
        settingPage.start(new Stage());
    }

    public void doExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void goCapture(ActionEvent actionEvent) {
        CapturePage capturePage = new CapturePage();
        capturePage.start(new Stage());
    }
}
