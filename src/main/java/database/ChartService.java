package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.*;

import models.LogEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a class used to generate chart data from database.
 *
 * @author Shanyue Wan
 * @date Created in 15/11/19 3:15 pm
 * @description This is a class used to generate chart data from database.
 * @version jdk 1.8
 */
public class ChartService {

    /**
     * Used to generate Pie Chart By StudentID
     *
     * @param stuID student id
     * @return ObservableList<PieChart.Data> for student with this id
     */
    public ObservableList<PieChart.Data> getPieChartDataByStudentID(String stuID) {
        ObservableList<PieChart.Data> reasonFrequencyData = FXCollections.observableArrayList();
        List<LogEntity> logs = DAO.selectStuLog(stuID);
        Map<String, Long> result = logs.stream()
                .collect(Collectors.groupingBy(LogEntity::getReason, Collectors.counting()));
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            reasonFrequencyData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return reasonFrequencyData;
    }

    /**
     * Used to generate log data within time range
     *
     * @param startDate
     * @param endDate
     * @return log data within time range (all stu)
     */
    public ObservableList<PieChart.Data> getLogDataBetweenTimeAllStudent(Date startDate, Date endDate) {
        ObservableList<PieChart.Data> reasonFrequencyData = FXCollections.observableArrayList();
        List<LogEntity> logs = DAO.selectAllLog();
        List<LogEntity> betweenTimeLogs = new ArrayList<>();
        for (LogEntity log : logs) {
            if (log.getVisitTime().before(endDate) & log.getVisitTime().after(startDate)) {
                betweenTimeLogs.add(log);
            }
        }

        // Sort log data
        Map<String, Long> result = betweenTimeLogs.stream()
                .collect(Collectors.groupingBy(LogEntity::getReason, Collectors.counting()));
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            reasonFrequencyData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return reasonFrequencyData;
    }

    /**
     * Used to generate log data by gender
     *
     * @param startDate
     * @param endDate
     * @param gender
     * @return log data by gender
     */
    public ObservableList<PieChart.Data> getPieChartByGender(Date startDate, Date endDate, String gender) {
        ObservableList<PieChart.Data> reasonFrequencyData = FXCollections.observableArrayList();
        List<LogEntity> logs = DAO.selectLogGender();
        List<LogEntity> betweenTimeLogs = new ArrayList<>();
        for (LogEntity log : logs) {
            if (log.getVisitTime().before(endDate) & log.getVisitTime().after(startDate)
                    & log.getStudentGender().equals(gender)) {
                betweenTimeLogs.add(log);
            }
        }
        Map<String, Long> result = betweenTimeLogs.stream()
                .collect(Collectors.groupingBy(LogEntity::getReason, Collectors.counting()));
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            reasonFrequencyData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return reasonFrequencyData;
    }

    /**
     * Generate Bar Chart Series: male, female
     *
     * @param startDate
     * @param endDate
     * @return Bar Chart Series: male, female
     * @throws ArrayIndexOutOfBoundsException throw when no data within time
     * range
     */
    public Series[] getBarChartSeries(Date startDate, Date endDate) throws ArrayIndexOutOfBoundsException {
        Series resultMale = new Series();
        Series resultFemale = new Series();
        List<LogEntity> logs = DAO.selectLogGender();
        List<LogEntity> betweenTimeLogs = new ArrayList<>();
        for (LogEntity log : logs) {
            if (log.getVisitTime().before(endDate) & log.getVisitTime().after(startDate)) {
                betweenTimeLogs.add(log);
            }
        }
        if (betweenTimeLogs.size() == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String currentReason = betweenTimeLogs.get(0).getReason();
        int maleCount = 0;
        int femaleCount = 0;
        for (LogEntity log : betweenTimeLogs) {
            if (!currentReason.equals(log.getReason())) {
                resultFemale.getData().add(new Data(currentReason, femaleCount));
                resultMale.getData().add(new Data(currentReason, maleCount));
                femaleCount = 0;
                maleCount = 0;
                currentReason = log.getReason();
            }
            if (log.getStudentGender().equals("Female")) {
                femaleCount += 1;
            } else {
                maleCount += 1;
            }
        }
        resultMale.setName("Male");
        resultFemale.setName("Female");
        return new Series[]{resultMale, resultFemale};
    }
}