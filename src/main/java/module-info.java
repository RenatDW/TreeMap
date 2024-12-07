module comp.graph.treemap {
    requires javafx.controls;
    requires javafx.fxml;


    opens comp.graph.treemap to javafx.fxml;
    exports comp.graph.treemap;
}