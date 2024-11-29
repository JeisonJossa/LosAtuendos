module com.losatuendos {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.losatuendos.app to spring.core, spring.beans, javafx.fxml;
    opens com.losatuendos.controller to spring.core, spring.beans, javafx.fxml;
    opens com.losatuendos.entity to org.hibernate.orm.core, spring.core;
    opens com.losatuendos.view to javafx.fxml;
}
