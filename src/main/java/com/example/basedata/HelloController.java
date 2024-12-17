package com.example.basedata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {

    @FXML
    private TableView<File> tableView;
    @FXML
    private TableColumn<File, Integer> idColumn;
    @FXML
    private TableColumn<File, String> nameColumn;
    @FXML
    private TableColumn<File, Integer> ageColumn;
    @FXML
    private TableColumn<File, Double> salaryColumn;
    @FXML
    private Button sortButton;

    private final ObservableList<File> data = FXCollections.observableArrayList();

    // Настройки базы данных
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static Connection connection;
    public static Statement statement;
    static {
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try{
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }


    @FXML
    public void initialize() {
        // Настраиваем столбцы таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Загружаем данные из базы данных
        loadDataFromDatabase();

        // Устанавливаем данные в таблицу
        tableView.setItems(data);
    }

    private void loadDataFromDatabase() {
        data.clear();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double salary = resultSet.getDouble("salary");

                data.add(new File(id, name, age, salary));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSortButtonClick(ActionEvent actionEvent) {
        data.sort((f1, f2) -> Double.compare(f2.getSalary(), f1.getSalary()));
    }
}