# MasterDevelTest

### Technology Involved

For this test, I have used the spring-boot java framework to build the backend service and the ReactJS framework for the client.

### Installation

Java version required: JDK 11
Node version: 10.19.0

Go to the backend project location and install using maven.
```sh
$ cd backendserver
$ mvn install
```

Go to the generated jar file location and execute it.
```sh
$ cd target
$ java -jar backendserver-0.0.1-SNAPSHOT.jar
```

After the backend server is successfully turned on. Go to the client project and start it.
```sh
$ cd ../../Client
$ npm start
```

After client is initialized. You can go to http://localhost:3000/ and start testing.

#### NOTE:

Input fields in PUT /credential form, should remain populated. Key field is going to be sent in the x-Key header and the Shared Secret is going to be used to sign the request.

