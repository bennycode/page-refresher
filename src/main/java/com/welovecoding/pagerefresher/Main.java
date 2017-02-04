package com.welovecoding.pagerefresher;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

  private RefreshRunner runner = null;

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    String applicatioName = "Page Refresher";
    primaryStage.setTitle(applicatioName);
    primaryStage.setScene(new Scene(new StackPane(), 300, 250));
    primaryStage.show();

    String placeholder = "http://isitfridayyet.net/";
    TextInputDialog confirmation = new TextInputDialog(placeholder);
    Optional<String> dialogResult = confirmation.showAndWait();
    if (dialogResult.isPresent()) {
      String pageUrl = dialogResult.get();
      ExecutorService executor = Executors.newSingleThreadExecutor();
      runner = new RefreshRunner(pageUrl);
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
}
