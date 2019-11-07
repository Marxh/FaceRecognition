package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.net.URI;

public class AboutUsPageController {
    @FXML
    public Hyperlink murli;
    @FXML
    public Hyperlink jianping;
    @FXML
    public Hyperlink shanyue;
    @FXML
    public Hyperlink yufan;
    @FXML
    public Hyperlink rui;
    @FXML
    public Hyperlink xuHyper;

    public void goMurli(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goJianping(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goShanyue(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goYufan(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goRui(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goXu(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.australia.cmu.edu/profile/murlikrishna-viswanathan");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
