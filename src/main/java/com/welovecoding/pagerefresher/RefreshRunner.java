package com.welovecoding.pagerefresher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RefreshRunner implements Callable {

  private final RefreshConfiguration config;
  private final WebDriver driver;

  public RefreshRunner(RefreshConfiguration config) throws Exception {
    String baseName = "chromedriver";
    String extension = ".exe";

    File extractedDriver = File.createTempFile(baseName, extension);
    extractedDriver.deleteOnExit();
    
    Files.copy(this.getClass().getResourceAsStream(baseName + extension), extractedDriver.toPath(), StandardCopyOption.REPLACE_EXISTING);
    System.setProperty("webdriver.chrome.driver", extractedDriver.getAbsolutePath());

    playSound(false);

    this.driver = new ChromeDriver();
    this.config = config;
  }

  public final void playSound(boolean inLoop) throws Exception {
    String fileName = "alert.mp3";
    Media media = new Media(this.getClass().getResource(fileName).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    if (inLoop) {
      mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
    }
    mediaPlayer.play();
  }

  @Override
  public Object call() throws Exception {
    driver.get(config.getPageUrl());

    String uninterestingText = config.getUninterestingText();
    boolean hasStarted = false;

    while (!hasStarted) {
      WebElement body = driver.findElement(By.tagName("body"));

      if (body.getText().contains(uninterestingText)) {
        driver.navigate().refresh();
        Thread.sleep(config.getWaitInMillis());
      } else {
        hasStarted = true;
        playSound(true);
      }
    }

    return null;
  }

  public void stop() {
    driver.close();
    driver.quit();
  }
}
