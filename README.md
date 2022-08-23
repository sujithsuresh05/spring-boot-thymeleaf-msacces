# spring-boot-thymeleaf-msaccess
Sample project which uses springboot + msaccess database + thymeleaf

<p> Project have a good implementation to provide searching on date and integer 
fields that stored in ms access DB in text format .</p>
<p> The implementation is following filter chaining model so that it can be 
extended easily when new Type search field come </p> 
Build and Run Commands

![alt text](https://drive.google.com/file/d/1oFH2faI-sFIk3v_WuPnniM0K0_MNubFW/view?usp=sharing)
![alt text](https://drive.google.com/file/d/1KNJhtlCaduwDU4SNiSsa5mEjfFij5qvl/view?usp=sharing)
![alt text](https://drive.google.com/file/d/1xM2F-L8G10lpxgFrC9BvHABIPjFDYilD/view?usp=sharing)
## Jar building mvn package
#### Run Test mvn test
#### Build  mvn package
## Run application 
#### mvn spring-boot:run -Dspring.datasource.url=jdbc:ucanaccess: database path

##### Example: mvn spring-boot:run -Dspring.datasource.url=jdbc:ucanaccess://\/Users\/sujiths\/Downloads\/JavaTest\/accountsdb.accdb 


External Libraries

1. Mockito -> to do some complex tests
2. Jupitor Junit 5 -> to do Junit test

Build Tool : Maven