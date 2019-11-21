package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import view.ErrorView;
import view.ReportView;

import java.time.ZoneId;
import java.util.Date;

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @descriptionThis is a class used to control the page which filters the date
 */
public class FilterPageController {

    /**
     * start time
     */
    @FXML
    private DatePicker startTime;

    /**
     * end time
     */
    @FXML
    private DatePicker endTime;

    /**
     * error view
     */
    private ErrorView errorView = new ErrorView();

    /**
     * @description This is a method used to generate report view
     */
    @FXML
    public void doGenerate() {
        if (startTime.getValue() == null | endTime.getValue() == null) {
            errorView.start("Date not entered");
            return;
        }

        if (startTime.getValue().isAfter(endTime.getValue())) {
            errorView.start("EndDate is before startDate");
            return;
        }

        ReportView reportView = new ReportView();
        reportView.start(Date.from(startTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(endTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println();
    }
}