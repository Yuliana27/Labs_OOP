package lab8;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Main extends Application {
    public Hotel hotel;
    private Desktop desktop = Desktop.getDesktop();
    private ObservableList<Hotel> hotels = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("LAB_8");
        primaryStage.getIcons().add(new Image("file:resources/images/nomer.png"));

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        TableView<Hotel> table = new TableView<Hotel>(hotels);
        table.prefHeightProperty().bind(primaryStage.heightProperty());
        TableColumn<Hotel, Integer> idColumn = new TableColumn<Hotel, Integer>("INDEX");
        idColumn.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("index"));
        idColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(idColumn);
        TableColumn<Hotel, String> nameColumn = new TableColumn<Hotel, String>("Number of passport");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hotel, String>("passport"));
        nameColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(nameColumn);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(10));
        Button add_btn = new Button("Add_person");
        Button delete_btn = new Button("Delete_person");
        Button get_btn = new Button("Get from file(XML)");
        Button put_btn = new Button("Put to file(XML)");
        HBox buttons = new HBox(add_btn,delete_btn,get_btn,put_btn);
        VBox hb = new VBox();
        get_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(new FileInputStream(file.toString())))){
                        Integer size_ = (Integer) decoder_.readObject();
                        for (int i = 0; i < size_; i++) {
                            hotels.add((Hotel) decoder_.readObject());
                        }
                    } catch (FileNotFoundException ex) {
                        System.err.println("FileNotFound");
                    }
                }
            }
        });
        put_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.toString())))){
                        encoder.writeObject(hotels.size());
                        for (int i = 0; i < hotels.size(); i++) {
                            encoder.writeObject(hotels.get(i));
                        }
                        encoder.close();
                        System.out.println("Успішно записано!");
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                    }

                }
            }
        });
        delete_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(hotel != null){
                    hotels.remove(hotel);
                    hb.getChildren().clear();
                    table.refresh();
                    hb.getChildren().addAll(buttons);

                }
            }
        });
        add_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label error_l = new Label();
                TextField passport_add = new TextField();
                TextField data_p_add = new TextField();
                TextField data_v_add = new TextField();
                TextField reason_add = new TextField();
                Button save = new Button("Save");
                HBox passport_g = new HBox(new Label("NumPassport: "),passport_add);
                HBox data_p_g = new HBox(new Label("Data_p: "),data_p_add);
                HBox data_v_g = new HBox(new Label("Data_v: "),data_v_add);
                HBox reason_g = new HBox(new Label("Reason: "),reason_add);
                hb.getChildren().clear();
                hb.getChildren().addAll(passport_g,data_p_g,data_v_g,reason_g,save,error_l);

                save.setOnMouseClicked(e -> {
                    error_l.setText("");
                    if(passport_add.getText().length() > 0){
                        Hotel add_vac = new Hotel();
                        add_vac.setPassport(passport_add.getText());
                        add_vac.setData_p(data_p_add.getText());
                        add_vac.setData_v(data_v_add.getText());
                        add_vac.setReason((reason_add.getText()));
                        hotels.add(add_vac);
                        hb.getChildren().clear();
                        table.refresh();
                        hb.getChildren().addAll(buttons);
                    }
                    else{
                        error_l.setText("NumPassport must be not empty!");
                    }
                });
            }
        });
        getHotel(hb,table);
        hb.getChildren().addAll(buttons);
        TableView.TableViewSelectionModel<Hotel> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Hotel>(){

            public void changed(ObservableValue<? extends Hotel> val, Hotel oldVal, Hotel newVal){
                if(newVal != null){
                    hotel = newVal;
                    hb.getChildren().clear();
                    hb.getChildren().addAll(buttons);
                    getHotel(hb,table);

                }
            }
        });
        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column2);
        root.setColumnIndex(table, 0);
        sp.setContent(hb);
        root.setColumnIndex(sp, 1);

        root.getChildren().addAll(table, sp);

        Scene scene =  new Scene(root, 1000, 500);

        scene.getStylesheets().add((getClass().getResource("main.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void getHotel(VBox hb,TableView<Hotel> table){
        if(hotels.size() > 0) {
            if (hotel == null) {
                hotel = hotels.get(0);
            }
            Label lbl1 = new Label("ID:");
            TextField passport = new TextField();
            TextField data_p = new TextField();
            TextField data_v  = new TextField();
            TextField reason = new TextField();
            lbl1.setText("ID: " + hotel.getIndex());
            passport.setText(hotel.getPassport());
            data_p.setText(hotel.getData_p());
            data_v.setText(hotel.getData_v());
            reason.setText("" + ((hotel.getReason() == null) ? "" : hotel.getReason()));
            HBox passport_g = new HBox(new Label("NumPassport: "), passport);
            HBox data_p_g = new HBox(new Label("Data_p:"), data_p);
            HBox data_v_g = new HBox(new Label("Data_v: "), data_v);
            HBox reason_g = new HBox(new Label("Reason: "), reason);

            passport.textProperty().addListener((observable, oldValue, newValue) -> {
                if (hotel != null) {
                    hotel.setPassport(newValue);
                    table.refresh();
                }
            });
            data_p.textProperty().addListener((observable, oldValue, newValue) -> {
                if (hotel != null) {
                    hotel.setData_p(newValue);
                }
            });
            data_v.textProperty().addListener((observable, oldValue, newValue) -> {
                if (hotel != null) {
                    hotel.setData_v(newValue);
                }
            });

            reason.textProperty().addListener((observable, oldValue, newValue) -> {
                if (hotel != null) {
                            hotel.setReason((newValue));
                        }
            });
            hb.getChildren().addAll(lbl1, passport_g, data_p_g, data_v_g, reason_g);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
