/**
 * @author ：Shanyue Wan
 * @date ：Created in 15/11/19 8:12 pm
 * @description：
 * @modified By：
 * @version: jdk
 */
package controller;

import database.ChartService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.*;

import java.util.Date;

public class ReportController {
    @FXML
    private PieChart reasonAttendancePie;


    @FXML
    private BarChart reasonAttendanceBar;

    private ChartService chartService = new ChartService();

    public void initialize(Date startDate, Date endDate) {
        initPieChart(startDate, endDate);
        initBarChart(startDate, endDate);
    }

    private void initPieChart(Date startDate, Date endDate){
        ObservableList<PieChart.Data> dataSet = chartService.getLogDataBetweenTimeAllStudent(startDate, endDate);
        reasonAttendancePie.setData(FXCollections.observableArrayList(dataSet));
        dataSet.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ":", data.pieValueProperty().asString("%.0f")
                        )
                )
        );
        reasonAttendancePie.setLegendVisible(false);
    }

    private void initBarChart(Date startDate, Date endDate){
        Series[] genderSeries = chartService.getBarChartSeries(startDate, endDate);
        reasonAttendanceBar.getData().addAll(genderSeries[0], genderSeries[1]);
    }
}
