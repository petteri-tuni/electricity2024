# Pörssisähkö Java Practice

This project is a Java application that fetches and stores electricity prices from an external API. It uses Spring Boot for the backend services and Maven for dependency management.

The application fetches electricity prices from the following API:
https://porssisahko.net/api

Current electricity price is available at:
https://vare.fi/sahkosopimus/porssisahko/hinta/

## Project Structure

- `src/main/java/fi/sade24/copilot/`
  - `ElectricityPrice.java`: Represents the electricity price with a timestamp.
  - `ElectricityPriceService.java`: Service class to fetch electricity prices from an external API.
  - `ElectricityPriceController.java`: REST controller to expose endpoints for fetching electricity prices.
  - `ElPriceStorage.java`: Storage class to store and retrieve electricity prices.
  - `ElTimes.java`: Utility class to handle time-related operations.

## Dependencies

- Spring Boot
- Spring Web
- Maven

## Getting Started

### Prerequisites

- Java 11 or higher (17 recommended)
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/petteri-tuni/porssisahko-java-practice.git
   cd porssisahko-java-practice
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

### Running the Application

To run the application, use the following command:
```sh
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### Endpoints

- `GET /info`: Returns information about the application.
- `GET /raw-price`: Fetches raw electricity price data from the external API.
- `GET /electricity-price`: Fetches the processed electricity price data.
- `GET /current-date`: Returns the current date and time in the Helsinki timezone.

## Usage

### Fetching Electricity Prices

To fetch the latest electricity prices, you can use the `/electricity-price` endpoint. The prices are stored in the `ElPriceStorage` class and can be retrieved based on timestamps.

### Timezone Handling

The application uses the `Europe/Helsinki` timezone for all date and time operations. The `ElTimes` class provides utility methods to handle time-related operations.

## License

This project is licensed under the MIT License.


## Examples

The following examples demonstrate how to use the application.

First, start the application using the following command:
```sh
$curl localhost:8080/info
{"message":"Welcome to the CoPilot application!"}
```
To fetch the current date and time in the Helsinki timezone, use the `/time-info` endpoint:

```sh
$ curl localhost:8080/time-info
- Helsinki: 2024-10-24T19:06:13.857715500+03:00[Europe/Helsinki]
- UTC time: 2024-10-24T16:00Z[UTC]
- Instant: 2024-10-24T16:00:00Z
```

At this point the application has not fetched the electricity prices yet, so the response will be empty.
This will fetch the electricity prices from the external API and store them in the `ElPriceStorage` class. The response will contain the latest electricity price data.

```sh

$ curl localhost:8080/electricity-price
{"prices":[{"startDate":"2024-10-25T21:00:00Z","endDate":"2024-10-25T22:00:00Z","price":0.379},{"startDate":"2024-10-25T20:00:00Z","endDate":"2024-10-25T21:00:00Z","price":0.383},{"startDate":"2024-10-25T19:00:00Z","endDate":"2024-10-25T20:00:00Z","price":0.369},{"startDate":"2024-10-25T18:00:00Z","e
```
Now the electricity prices have been fetched and stored in the `ElPriceStorage` class. The response will contain the latest electricity price data.
Current price is fetched from the storage and returned as a response:

```sh
o$ curl localhost:8080/current-price
{"startDate":"2024-10-24T16:00:00Z","endDate":"2024-10-24T17:00:00Z","price":19.317}

```

