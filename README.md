# Login-Module
This is web application development project. It is based on J2EE MVC (Model VIew Controller) architecture. It includes the following:
1. The WelcomePage.jsp - This is the default page. If you copy any other web page URL while logged in and paste it after logging out, 
the server will redirect you to WelcomePage.jsp. You enter your email in the textbox and server verifies if it exist in the database.
If you are registered user, you goto PasswordPage.jsp else to the NewUserRegistrationPage.jsp

2. PasswordPage.jsp - If you are here, you are a registered user and you need to type in your password. The server validates it and
creates a ServletContext if the password is correct else give error message on PasswordPage.jsp

3. NewUserFormPage.jsp - If you are here, you need to register. All the values on this page are validated for format. E.g. Write first name
as 1234, the javascript will validate it and ask you to enter only text. If the JavaScript fails, the server is the second checkpoint. The
class of the respective flow, will validate all the fields again and then the data to database and register. If server validation fails, 
appropriate error messages are displayed on the NewUserRegistrationPage.jsp

4. UserWelcomePage.pjsp - This is the user application page.

 
