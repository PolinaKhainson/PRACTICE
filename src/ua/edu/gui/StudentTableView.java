package ua.edu.gui;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.templates.messages.ErrorMonitor;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import ua.edu.ReviewStudentsList;
import ua.edu.dataBase.data.Person;
import ua.edu.dataBase.data.Student;
import ua.edu.dataBase.dao.StudentTableOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by Oxana on 7/13/15.
 */
public class StudentTableView {
    public HBox container;
    ObservableList<Student> listStudents;
    public static List<Student> students = new ArrayList<Student>();
    public static TableView<Student> tableViewStudents;
    HBox separator;

    public StudentTableView() {
        this.container = new HBox();

        container.setMaxSize(ReviewStudentsList.weight,
                        ReviewStudentsList.height-
                        ReviewStudentsList.header.heightHeader-
                        ReviewStudentsList.footer.heightFooter-150);
        container.setMinSize(ReviewStudentsList.weight,
                ReviewStudentsList.height -
                        ReviewStudentsList.header.heightHeader -
                        ReviewStudentsList.footer.heightFooter - 150);
        container.setAlignment(Pos.CENTER);

        separator=new HBox();
        separator.setMinSize(ReviewStudentsList.weight,50);
        final VBox part1=new VBox();
        part1.setMaxSize(ReviewStudentsList.weight-400,ReviewStudentsList.height-
                ReviewStudentsList.header.heightHeader-ReviewStudentsList.footer.heightFooter-150);
        part1.setMinSize(ReviewStudentsList.weight - 400, ReviewStudentsList.height -
                ReviewStudentsList.header.heightHeader - ReviewStudentsList.footer.heightFooter - 150);
        try {
            students = new StudentTableOperator().listStudents();
        }catch(Throwable ex){
            System.out.println(ex.getMessage());
            new ErrorMonitor().showTerminalError(ReviewStudentsList.root,"Can't read dataBase!");
        }
        listStudents= FXCollections.observableArrayList();
        if(students!=null) {
            for (Student st : students) {
                listStudents.add(st);
            }
        }

        buildTable(part1);
        container.getChildren().add(part1);

    }
    private void buildTable(VBox partDisplay){

        partDisplay.getChildren().clear();

        partDisplay.getChildren().add(separator);
        Label name=new Label("Students");
        name.setStyle("-fx-background-color: brown;-fx-font-size: 25");
        partDisplay.getChildren().add(name);
        partDisplay.setAlignment(Pos.CENTER_LEFT);

        tableViewStudents = new TableView<Student>(listStudents);
        tableViewStudents.setTableMenuButtonVisible(true);
        tableViewStudents.setCursor(Cursor.TEXT);
        tableViewStudents.setPrefHeight(ReviewStudentsList
                .height - ReviewStudentsList.header.heightHeader - ReviewStudentsList.footer.heightFooter - 150);
        tableViewStudents.setMaxHeight(ReviewStudentsList.height - ReviewStudentsList.header.heightHeader - ReviewStudentsList.footer.heightFooter - 150);;
        tableViewStudents.setPrefWidth(ReviewStudentsList.weight - 400);
        tableViewStudents.setMaxWidth(ReviewStudentsList.weight - 400);
        tableViewStudents.setTooltip(new Tooltip("horses table"));
        tableViewStudents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewStudents.setStyle("-fx-background-color: yellow; -fx-padding: 10; -fx-font-size: 16;-fx-font-weight: bold;");
        tableViewStudents.setEditable(true);

        TableColumn nameColumn1=new TableColumn("LastName");
        nameColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        nameColumn1.setSortable(true);
        nameColumn1.setPrefWidth(80);
        nameColumn1.setResizable(true);
        nameColumn1.setEditable(true);
        nameColumn1.setCellFactory(TextFieldTableCell.<ObservableList<StringProperty>>forTableColumn());
        nameColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Student, String> event) {
                                            StudentTableOperator operator = new StudentTableOperator();
                                            operator.saveLastName((event.getTableView().getItems().get(event.getTablePosition().getRow())), event.getNewValue()
                                            );


                                        }
                                    }
        );
        TableColumn nameColumn2=new TableColumn("FirstName");
        nameColumn2.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        nameColumn2.setSortable(true);
        nameColumn2.setPrefWidth((ReviewStudentsList.weight - 400) - 240);
        nameColumn2.setResizable(true);
        nameColumn2.setEditable(true);
        nameColumn2.setCellFactory(TextFieldTableCell.<ObservableList<StringProperty>>forTableColumn());
        nameColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Student, String> event) {
                                            StudentTableOperator operator = new StudentTableOperator();
                                            operator.saveFirstName((event.getTableView().getItems().get(event.getTablePosition().getRow())),
                                                    event.getNewValue()
                                            );


                                        }
                                    }
        );

        TableColumn nameColumn3=new TableColumn("Department");
        nameColumn3.setCellValueFactory(new PropertyValueFactory<Student, String>("department"));
        nameColumn3.setSortable(true);
        nameColumn3.setPrefWidth(80);
        nameColumn3.setResizable(true);
        nameColumn3.setEditable(true);
        nameColumn3.setCellFactory(TextFieldTableCell.<ObservableList<StringProperty>>forTableColumn());
        nameColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Student, String> event) {
                                            StudentTableOperator operator = new StudentTableOperator();
                                            operator.saveDepartment((event.getTableView().getItems().get(event.getTablePosition().getRow())),
                                                    event.getNewValue()
                                            );


                                        }
                                    }
        );


        TableColumn nameColumn4=new TableColumn("Course");
        nameColumn4.setCellValueFactory(new PropertyValueFactory<Student, Integer>("course"));
        nameColumn4.setSortable(true);
        nameColumn4.setPrefWidth(80);
        nameColumn4.setResizable(true);
        nameColumn4.setEditable(true);
        nameColumn4.setCellFactory(TextFieldTableCell.<Student, Integer>forTableColumn(new IntegerStringConverter()));  //
        nameColumn4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, Integer>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Student, Integer> event) {
                                            StudentTableOperator operator = new StudentTableOperator();
                                            operator.saveCourse((event.getTableView().getItems().get(event.getTablePosition().getRow())),
                                                    event.getNewValue()
                                            );


                                        }
                                    }
        );

        TableColumn nameColumn5=new TableColumn("BirthDate");
        nameColumn5.setCellValueFactory(new PropertyValueFactory<Student, Date>("birthDate"));
        nameColumn5.setSortable(true);
        nameColumn5.setPrefWidth(80);
        nameColumn5.setResizable(true);
        nameColumn5.setEditable(true);
        nameColumn5.setCellFactory(TextFieldTableCell.<Student, Date>forTableColumn(new DateStringConverter()));
        nameColumn5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, Date>>() {

                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Student, Date> event) {
                                            StudentTableOperator operator = new StudentTableOperator();
                                            operator.saveBirthDate((event.getTableView().getItems().get(event.getTablePosition().getRow())),
                                                    event.getNewValue()
                                            );
                                        }
                                    }
        );

        tableViewStudents.setItems(listStudents);
        tableViewStudents.getColumns().addAll(nameColumn1, nameColumn2, nameColumn3, nameColumn4, nameColumn5);//

        partDisplay.getChildren().add(tableViewStudents);




    }

}
