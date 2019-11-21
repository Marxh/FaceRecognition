package controller;

import database.ChartService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.*;
import view.ErrorView;

import java.util.Date;

/**
 * @author Group 6
 * @date：2019-11-14
 * @description This is a class used to control the page which display the chart
 * of the reports
 * @version jdk 1.8
 */
public class ReportController {

    /**
     * reason pie chart
     */
    @FXML
    private PieChart reasonAttendancePie;

    /**
     * reason bar chart
     */
    @FXML
    private BarChart reasonAttendanceBar;

    /**
     * female pie chart
     */
    @FXML
    private PieChart femalePiechart;

    /**
     * male pie chart
     */
    @FXML
    private PieChart malePiechart;

    /**
     * error view
     */
    private ErrorView errorView = new ErrorView();

    /**
     * chart service
     */
    private ChartService chartService = new ChartService();

    /**
     * @param startDate startDate: the start date of the report the user enter Date
     * @param endDate the end date of the report the user enter
     * @description This is the initial method for the class. The method gets
     * the start date and end date entered by the user
     */
    public void initialize(Date startDate, Date endDate) {
        startDate = toLastSecond(startDate);
        endDate = toLastSecond(endDate);
        initPieChart(startDate, endDate);
        //initBarChart(startDate, endDate);
    }

    /**
     * @description the method is to draw the two pie chart of the visiting
     * categories groupby gender
     * @param  startDate startDate: the start date of the report the user enter Date
     * @param endDate the end date of the report the user enter
     */
    private void initPieChart(Date startDate, Date endDate) {
        ObservableList<PieChart.Data> dataSet = chartService.getLogDataBetweenTimeAllStudent(startDate, endDate);
        reasonAttendancePie.setData(FXCollections.observableArrayList(dataSet));
        dataSet.forEach(data
                        -> data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), ":", data.pieValueProperty().asString("%.0f")
                )
                )
        );
        reasonAttendancePie.setLegendVisible(false);

        ObservableList<PieChart.Data> dataSetFemale = chartService.getPieChartByGender(startDate, endDate, "Female");
        femalePiechart.setData(FXCollections.observableArrayList(dataSetFemale));
        dataSetFemale.forEach(data
                        -> data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), ":", data.pieValueProperty().asString("%.0f")
                )
                )
        );
        femalePiechart.setTitle("Female attendance distribution");
        femalePiechart.setLegendVisible(false);

        ObservableList<PieChart.Data> dataSetMale = chartService.getPieChartByGender(startDate, endDate, "Male");
        malePiechart.setData(FXCollections.observableArrayList(dataSetMale));
        dataSetMale.forEach(data
                        -> data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), ":", data.pieValueProperty().asString("%.0f")
                )
                )
        );
        malePiechart.setTitle("Male attendance distribution");
        malePiechart.setLegendVisible(false);

    }

    /**
     * @description the method is to draw the pie chart of the visiting
     * categories
     * @param startDate: the start date of the report the user enter Date
     * @param endDate: the end date of the report the user enter
     */
    private void initBarChart(Date startDate, Date endDate) {
        try {
            Series[] genderSeries = chartService.getBarChartSeries(startDate, endDate);
            reasonAttendanceBar.getData().addAll(genderSeries[0], genderSeries[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            errorView.start("Selected date has no data.");
        }
    }

    /**
     * @description the method is to the date the user entered into the last
     * seconds of that day
     * @param  inputDate of the report the user enter
     * @return the date including the last second of that day
     */
    private Date toLastSecond(Date inputDate) {
        int dayMis = 1000 * 60 * 60 * 24;
        long curMillisecond = inputDate.getTime();
        long resultMis = curMillisecond + (dayMis - 1);
        Date resultDate = new Date(resultMis);
        return resultDate;
    }
}