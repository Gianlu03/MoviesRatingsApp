module org.example.moviesratingsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    /*
        Added json.simple toolkit to decode the json http response
     */
    requires json.simple;
    /*
        Added java.net.http toolkit to make http requests
     */
    requires java.net.http;

    opens org.project.moviesratingsapp to javafx.fxml;
    exports org.project.moviesratingsapp;
    exports org.project.moviesratingsapp.controller;
    opens org.project.moviesratingsapp.controller to javafx.fxml;
    exports org.project.moviesratingsapp.model;
    opens org.project.moviesratingsapp.model to javafx.fxml;
}