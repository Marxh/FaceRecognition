package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.AboutUsPage;
import view.ErrorView;
import view.OpenCVView;

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

    private ErrorView errorView = new ErrorView();


    @FXML
    private void doLogin() {
        String _user = usernameField.getText();
        String _pass = passwordField.getText();

        if ( !_user.equals("jianpind") || !_pass.equals("123456")) {
            errorView.start("Wrong User Password.");
        } else {
            OpenCVView openCVView = new OpenCVView();
            openCVView.start();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void doExit() {
        System.exit(0);
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
