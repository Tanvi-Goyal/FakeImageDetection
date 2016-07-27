package com.qburst.fakeimagedetection.ui.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.qburst.fakeimagedetection.ui.alert.Calert;
import com.qburst.fakeimagedetection.core.errorlevelanalysis.ImageStandardizer;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

public class BatchImageProcessorController implements Initializable {

    @FXML
    private JFXCheckBox srcIndicator;
    @FXML
    private JFXTextField sWidth;
    @FXML
    private JFXTextField sHeight;
    @FXML
    private JFXTextField outputBatchName;
    @FXML
    private JFXCheckBox destIndicator;
    @FXML
    private ProgressBar progressBar;

    File srcDir = null;
    File destDir = null;
    @FXML
    private AnchorPane rootPane;

    public static ProgressBar progress;
    public static Text statusOfProgress;

    private String bName = "real_";
    @FXML
    private Text status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progress = progressBar;
        statusOfProgress = status;
    }

    @FXML
    private void loadSourceFolder(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Source Folder");
        srcDir = chooser.showDialog(rootPane.getScene().getWindow());
        if (srcDir == null) {
            Calert.showAlert("Error", "Not a valid folder", Alert.AlertType.ERROR);
            return;
        }
        srcIndicator.setSelected(true);
    }

    @FXML
    private void loadDestinationFolder(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Source Folder");
        destDir = chooser.showDialog(rootPane.getScene().getWindow());
        if (destDir == null) {
            Calert.showAlert("Error", "Not a valid folder", Alert.AlertType.ERROR);
            return;
        }
        destIndicator.setSelected(true);
    }

    @FXML
    private void startProcessing(ActionEvent event) {
        int width = Integer.parseInt(sWidth.getText());
        int height = Integer.parseInt(sHeight.getText());
        bName = outputBatchName.getText();
        if (srcDir != null && destDir != null) {
            ImageStandardizer ims = new ImageStandardizer(srcDir, destDir, new Dimension(width, height),bName);
        } else {
            System.out.println("Source Not selected");
        }
    }

}
