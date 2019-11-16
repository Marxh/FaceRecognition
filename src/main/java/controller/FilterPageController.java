package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import view.ErrorView;
import view.ReportView;

import java.time.ZoneId;
import java.util.Date;


public class FilterPageController {

    @FXML
    private DatePicker startTime;

    @FXML
    private DatePicker endTime;

    private ErrorView errorView = new ErrorView();

    @FXML
    public void doGenerate() {
        if(startTime.getValue() == null | endTime.getValue() == null){
            errorView.start("Date not entered");
            return;
        }

        if(startTime.getValue().isAfter(endTime.getValue())){
            errorView.start("EndDate is before startDate");
            return;
        }

        ReportView reportView = new ReportView();
        reportView.start(Date.from(startTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(endTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println();
    }
}
