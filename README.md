[![Build Status](https://ec2-54-165-115-111.compute-1.amazonaws.com/buildStatus/icon?job=unikitty)](https://ec2-54-165-115-111.compute-1.amazonaws.com/job/unikitty/)

## Getting started
### Running the backend with the frontend
You need Java and Maven to be installed.
From the command line run
`mvn spring-boot:run`

This should start the application on port 8080. http://localhost:8080/

### Running the frontend only
Nodejs and NPM must be installed

1. Install all packages `npm install`
2. Run the server `npm start`
3. visit http://localhost:8000

And to run karma tests just run `npm test`


## How the code is organized
This is a really simple typical java web application. All the `java` code is
under `src/main/java` and all the web resources (`javascript`, `css`, `html`,
etc) are under `src/main/resources/static/`

### npm Packages
All `npm` packages are installed in their default location under `node_modules`.

### Bower Packages
`bower` packages are installed under `src/main/resources/static/lib` directory.
