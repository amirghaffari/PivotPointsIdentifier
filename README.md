# Pivot Points Identifier

This example shows how to develop a REST API using Spring Boot to provide pivot points for a selected date from [a dataset](https://github.com/plotly/datasets/blame/master/tesla-stock-price.csv). The endpoint accepts two parameters, i.e. `Date` and `Days`, and calculates the pivot high and pivot low values for the `Days` before and after the `Date`. To run the app locally, the below command can be used:

- `mvn spring-boot:run`

After the app is run succefully, the endpoint should be available at [http://localhost:8080/api/points](http://localhost:8080/api/points?date=2018-10-10&days=4)
Mockito framework is used to verify the differnet test cases for calculating the pivot points.
