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

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @description This is a class used to control the page which the administrator
 * uses to login the system
 */
public class LoginController {

    /**
     * username
     */
    @FXML
    public TextField usernameField;

    /**
     * password
     */
    @FXML
    public PasswordField passwordField;

    /**
     * login
     */
    @FXML
    public Button loginButton;

    /**
     * exit
     */
    @FXML
    public Button exitButton;

    /**
     * cmu image
     */
    @FXML
    public ImageView cmuImage;

    /**
     * error view
     */
    private ErrorView errorView = new ErrorView();

    /**
     * @description This is a method used to handle login event
     */
    @FXML
    private void doLogin() {
        String _user = usernameField.getText();
        String _pass = passwordField.getText();

        if (!_user.equals("jianpind") || !_pass.equals("123456")) {
            errorView.start("Wrong User Password.");
        } else {
            OpenCVView openCVView = new OpenCVView();
            openCVView.start();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * @description This is a method used to handle exit event
     */
    @FXML
    private void doExit() {
        System.exit(0);
    }

    /**
     * @description This is a method used to handle about us event
     */
    @FXML
    public void onAboutUs() {
        AboutUsPage aboutUsPage = new AboutUsPage();
        aboutUsPage.start(new Stage());
    }

    /**
     * @description This is a method used to handle change password event
     */
    @FXML
    public void onChangePassword() {

    }

    /**
     * @description This is a method used to handle forget password event
     */
    @FXML
    public void onForgetPassword() {

    }
}