# barcode-app

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=coverage)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=netlykos_barcode-app&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=netlykos_barcode-app)

A barcode technology related application. The backend services are written in Java. The frontend/user interface is developing in @Angular. This application consists of multiple backend services. This is a PoC type of application that includes some business logic and allows for developing with Spring Boot/Cloud technologies.

## Versioning support using __nebula.release__

This project makes use of the __nebula.release__ plugin, which requires one of the following arguments to set the version attribute. Please see the [nebular-release-plugins](https://github.com/nebula-plugins/nebula-release-plugin) web site for more details about this plugin. One of the below arguments should be passed in to the build process. These arguments are configured in the CI pipeline of the project which are in the directory ``.github/workflows/``.

## Application sections

The application is divided into two main section(s) - the __frontend__ and the __backend__. It is possible to run and test both in isolation. The backend component includes multiple spring boot/cloud related technologies that are considered best practices for enterprise applications. These components are:

* barcode-config-server
* barcode-config-server
* barcode-registry-server
* barcode-utilities
* barcode-service

### Application section - frontend

To launch the Angular Web application where node has not been installed on the local system run the gradle task ``./gradlew :frontend:web-app:serve``. This task will download the node and npm version specified in ``buildSrc/src/main/groovy/org.netlykos.barcode.npm-build-conventions.gradle`` and launch the application using this. Users can also make use of the system available node and npm install and run ``npm run start`` from the ``frontend/web-app`` directory.

### Application section - backend

The backend module is broken up into different modules (a __barcode_utilities__ and __app__ component). The __app__ component packages the __frontend:web-app__ and __backend:barcode-utilities__ component into a single jar which can then be used to serve the application.

## Application endpoints


## Launching the backend service

Users can test the backend service independent of the complete application by launching main from __``backend/barcode-service/src/test/java/org/netlykos/barcode/LocalApp.java``__. In this case, the process will use the spring boot application configuration from the file __``backend/test-support/src/main/resources/application.yml``__ to launch the process. The welcome page will be hosted at [https://localhost:28080/](https://localhost:28080/).

## Launching the web application

## Upgrading/Dependencies version management

### Spring Boot

``/buildSrc/build.gradle``
``/gradle.properties``

## External links

[SonarQube](https://sonarcloud.io/project/overview?id=netlykos_barcode-app)
[Menu Generation](https://careydevelopment.us/blog/how-to-add-a-responsive-sidebar-navigation-menu-to-your-angular-app)
[Theme Selector](https://indepth.dev/posts/1218/lets-implement-a-theme-switch-like-the-angular-material-site)
