module com.example.fx2048 {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires AnimateFX;

  opens com.example.fx2048 to javafx.fxml;
  exports com.example.fx2048;
  exports com.example.fx2048.model;
  opens com.example.fx2048.model to javafx.fxml;
  exports com.example.fx2048.game;
  opens com.example.fx2048.game to javafx.fxml;
}