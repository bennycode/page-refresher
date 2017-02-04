package com.welovecoding.pagerefresher;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

  private RefreshRunner runner = null;

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Page Refresher");
    primaryStage.setScene(new Scene(new StackPane(), 300, 250));
    primaryStage.show();

    Dialog<RefreshConfiguration> dialog = constructConfigurationDialog();
    Optional<RefreshConfiguration> result = dialog.showAndWait();
    if (result.isPresent()) {
      RefreshConfiguration config = result.get();
      ExecutorService executor = Executors.newSingleThreadExecutor();
      runner = new RefreshRunner(config);
      executor.submit(runner);
    }
  }

  @Override
  public void stop() throws Exception {
    if (runner != null) {
      runner.stop();
    }
    super.stop();
  }

  private Dialog<RefreshConfiguration> constructConfigurationDialog() {
    Dialog<RefreshConfiguration> dialog = new Dialog<>();
    dialog.setTitle("Configuration");
    dialog.setHeaderText("Please enter your refresh settings and the URL you want to refresh.");
    dialog.setResizable(true);

    Label urlLabel = new Label("Web page URL to be refreshed: ");
    TextField urlInput = new TextField("http://isitfridayyet.net/");

    Label refreshTextLabel = new Label("Refresh when the following text is displayed: ");
    TextField refreshTextInput = new TextField("You just missed it.");

    Label intervalLabel = new Label("Refresh interval in milliseconds: ");
    TextField intervalInput = new TextField("1024");

    GridPane grid = new GridPane();

    grid.add(urlLabel, 1, 1);
    grid.add(urlInput, 2, 1);

    grid.add(refreshTextLabel, 1, 2);
    grid.add(refreshTextInput, 2, 2);

    grid.add(intervalLabel, 1, 3);
    grid.add(intervalInput, 2, 3);

    dialog.getDialogPane().setContent(grid);

    ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().add(okButton);

    dialog.setResultConverter((ButtonType button) -> {
      if (button == okButton) {
        RefreshConfiguration config = new RefreshConfiguration(urlInput.getText(), refreshTextInput.getText());
        config.setWaitInMillis(Long.valueOf(intervalInput.getText()));
        return config;
      } else {
        return null;
      }
    });

    return dialog;
  }
}
