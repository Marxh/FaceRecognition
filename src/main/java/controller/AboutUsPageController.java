package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.net.URI;

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @description This is a class used to control the about us page to show the
 * specific information of our group members
 */
public class AboutUsPageController {

    /**
     * link murli
     */
    @FXML
    public Hyperlink murli;

    /**
     * link jianping
     */
    @FXML
    public Hyperlink jianping;

    /**
     * link shanyue
     */
    @FXML
    public Hyperlink shanyue;

    /**
     * link yufan
     */
    @FXML
    public Hyperlink yufan;

    /**
     * link xinrui
     */
    @FXML
    public Hyperlink rui;

    /**
     * link xu
     */
    @FXML
    public Hyperlink xuHyper;

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of murli, our instructor of this group project
     */
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

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of jianpin, one of our group members
     */
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

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of Shanyue, one of our group members
     */
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

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of Yufan, one of our group members
     */
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

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of Xinrui, one of our group members
     */
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

    /**
     * @param actionEvent action event
     * @description The method is to control the UI which is to display the
     * information of Xu Zheng, one of our group members
     */
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