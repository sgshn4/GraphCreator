module com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx to javafx.fxml;
    exports com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;
}