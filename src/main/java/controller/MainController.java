package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import view.OpenCVView;


/**
 * 		
 */
public class MainController {
	@FXML
	private Button exit;
	@FXML
	private Button dashboard;
	@FXML
	private Button openCamera;

	private void closeButtonAction(){

	}

	/**
	 * The action triggered by pushing the button on the GUI
	 */
	@FXML
	protected void openCamera() {
		OpenCVView openCVView = new OpenCVView();
		openCVView.start();
		// get a handle to the stage
		Stage stage = (Stage) openCamera.getScene().getWindow();
		// do what you have to do
		stage.close();
	}
	
	/**
	 * The action triggered by pushing the button on the GUI
	 */
	@FXML
	protected void openDashboard() {

	}

	/**
	 * The action triggered by pushing the button on the GUI
	 */
	@FXML
	protected void exit() {
		System.exit(0);
	}
}
