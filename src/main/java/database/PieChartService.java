/**
 * @author ：Shanyue Wan
 * @date ：Created in 15/11/19 3:15 pm
 * @description：
 * @modified By：
 * @version: jdk
 */
package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import models.LogEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieChartService {
    public ObservableList<PieChart.Data> getPieChartData(String stuID){
        ObservableList<PieChart.Data> reasonFrequencyData = FXCollections.observableArrayList();
        List<LogEntity> logs = DAO.selectStuLog(stuID);
        Map<String, Long> result = logs.stream()
                .collect(Collectors.groupingBy(LogEntity::getReason, Collectors.counting()));
        for (Map.Entry<String, Long> entry : result.entrySet()){
            reasonFrequencyData.add(new PieChart.Data(entry.getKey(),entry.getValue()));
        }

        return reasonFrequencyData;
    }

}
