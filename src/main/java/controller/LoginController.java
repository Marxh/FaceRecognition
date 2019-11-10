package controller;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.AboutUsPage;
import legacy.HomePage;

public class LoginController {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Button exitButton;
    @FXML
    public ImageView cmuImage;


    @FXML
    private void doLogin() {
        String _user = usernameField.getText();
        String _pass = passwordField.getText();

        if ( !_user.equals("jianpind") || !_pass.equals("123456")) {

        } else {
            HomePage homePage = new HomePage();
            homePage.start(new Stage());

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void doExit() {
        Platform.exit();
    }

    @FXML
    public void onAboutUs() {
        AboutUsPage aboutUsPage = new AboutUsPage();
        aboutUsPage.start(new Stage());
    }

    @FXML
    public void onChangePassword() {

    }

    @FXML
    public void onForgetPassword() {

    }
}
