# CareFusion-Backend
Springboot backend for CareFusion Web App
This applications uses JDK 17

## IntelliJ Code Formatting
Go to `IntelliJ Settings` -> `Editor` -> `Code Style` -> `Java` -> `Scheme` -> `Import Scheme` 
-> `IntelliJ Idea Code Scheme XML` -> find the XML under 
carefusion-backend/.formatting/intellij-java-google-style.xml and import setting.

## Run Formatter and Style Checker before pushing the changes
1. Run `./gradlew spotlessApply` to automatically clean up spaces
2. Run `./gradlew --console=plain checkstyleMain` to check if there are any formatting issues
3. Run `./gradlew --console=plain checkstyleMain spotlessCheck` for a final check.

## User Signup
Most of the endpoints in CareFusion are protected by user login. <br/>
To create a new user, call `/v1/authentication/signup` endpoint with POST method and a JSON formatted request body.
For instance,
```json
{
  "firstname": "John",
  "lastname": "Doe",
  "username": "a@c.com",
  "password": "!aA123456789"
}
```
## User Login
Once an user is created, call `/v1/authentication/login` endpoint with POST method to login with an user. <br/>
Attach username and password as request body when calling the endpoint
```json
{
  "username": "a@c.com",
  "password": "!aA123456789"
}
```
Once Logged in, a token is attached in the response body. Use this token to call other endpoints.
```json
{
  "token": "JWT TOKEN",
  "expiresIn": 3600000
}
```
