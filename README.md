# Email API
Simple REST API for sending and retrieving emails. 

## Available endpoints 

| HTTP Method | URL                                      | Description               |
|-------------|------------------------------------------|---------------------------|
| GET         | http://localhost:8080/api/v1/emails      | Retrieve list of emails   |
| GET         | http://localhost:8080/api/v1/emails/{id} | Retrieve single email     |
| POST        | http://localhost:8080/api/v1/emails      | Create draft email        |
| PUT         | http://localhost:8080/api/v1/emails/{id} | Update draft email        |
| PATCH       | http://localhost:8080/api/v1/emails/{id} | Send single email         |

## Assumptions and limitations
1. At present application is running with in memory. Hence, all saved data will lose if restart application. 
2. Access token validation is only a placeholder. Proper authentication and authorization layer yet to implement. 
3. Minimal validations added to validate request parameters. 
4. Data has not been encrypted in-transit or at rest. Need to enhance token based data exchange (JWT) with proper PKI signing. 
5. Send email functionality will only update status. There will be no email server connection.

## Details and instructions
1. This Spring boot microservice locally runs with Tomcat server at port 8080. 
2. Apache Maven has been used as the building tool and below commands will build and run the application. 
   1. 'mvn clean package'   - Generate stubs, build application and run unit tests.
   2. 'mvn spring-boot:run' - Run the spring-boot application. 
