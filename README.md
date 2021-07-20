## OAuth Sample Project

### Getting Started


### Projects

- OAuth Server (KeyCloak Server)
- Resource Server (library-service)
- OAuth Client (college-web-client-app)

#### KeyClock Server

- Download standalone server from https://keycloak.org
- Unzip the downloaded file
- Go to the bin folder of unzipped file
- Execute following command to run the keycloak server
    - ./standalone.bat (windows)
    - ./standalone.sh (linux)
- Server will be running on port 8080. Access the server on http://localhost:8080
- Login as admin with username: admin & password: admin
- After login a dashboard will appear.
- Create a realm with name ***appsdeveloperblog***
- Create a user
- Create a client by going to the clients tab with following required information.
    - app name: college-app
    - redirect_url: http://localhost:8082/login/oauth2/code/mywebclient
    - Standard Flow Enabled: ON
    
- Get the client secret key. It will be used by the college-web-client-app.
    
### library-service

- Import project in IDE (intellij)
- Run the project. Service will be running on port 8081

### college-web-client-app

- Import project in IDE
- Open application.properties file and update client-secret value with the one you get while creating Client on keycloak server.
- Run the project and application will be accessible on port 8082

### Demo instructions

- Open home page. Home page is accessible without any authentication/authorization
- Go to secret books page by clicking on the ***secret books*** button.
- These book's information detail is fetched from the resource server which demands access token and this access tokekn can be accessed from the Keyclock server
- When you click on secret books button it will redirect you to the keyclaok authentication page where user provide username & password
- After successful authentication, app internally get access token from keycloak server and this token is used to get books detail from resource server.


