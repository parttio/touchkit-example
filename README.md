# TouchKitMapAnt - A TouchKit for V8 example project

This is a small example app using TouchKit and Vaadin 8. You can use it as a basis for your own mobile Vaadin 8 app.

The project is pretty standard Maven web app project and should thus
be compatible with practically all IDE's. The application stub
contains usage examples of some basic components and also provides a
method to serve different UI for desktop browsers.

## Packaging/installing the project

Run maven command:

    $ mvn package

Running the default package target generates war file. Also
widgetset compilation is automatically done at this point.

## Running the app in development server

The project has by default jetty plugin configure as a web server. It
can be started with maven command:

    $ mvn vaadin:compile jetty:run
