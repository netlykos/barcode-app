# barcode-app
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=coverage)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)

A barcode generating application. The backend services are written in Java. The frontend/user interface is developing in @Angular

## Versioning support using __nebula.release__

This project makes use of the __nebula.release__ plugin, which requires one of the following arguments to set the version attribute. Please see the [nebular-release-plugins](https://github.com/nebula-plugins/nebula-release-plugin) web site for more details about this plugin.

* snapshot
* devSnapshot
* candidate
* final

## Application components

The application is divided into two components - the __frontend__ and the __backend__. It is possible to run and test both in isolation. 

### Application component - frontend

To launch the Angular Web application where node has not been installed on the local system run the gradle task ``./gradlew :frontend:web-app:serve``. This task will download the node and npm version specified in ``buildSrc/src/main/groovy/org.netlykos.barcode.npm-build-conventions.gradle`` and launch the application using this. Users can also make use of the system available node and npm install and run ``npm run start`` from the ``frontend/web-app`` directory.

### Application component - backend

The backend module is broken up into different modules (a __barcode_utilities__ and __app__ component). The __app__ component packages the __frontend:web-app__ and __backend:barcode-utilities__ component into a single jar which can then be used to serve the application.

## Application endpoints

https://<host>:<port>/swagger-ui.html

## Launching the backend service


## Launching the web application


## External links

[SonarQube](https://sonarcloud.io/project/overview?id=netlykos_barcode-app)
https://careydevelopment.us/blog/how-to-add-a-responsive-sidebar-navigation-menu-to-your-angular-app

