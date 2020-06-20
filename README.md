# GoogleApiPOC
_______
[![<ORG_NAME>](https://circleci.com/gh/renatoferreira656/GoogleApiPOC.svg?style=svg)](https://app.circleci.com/pipelines/github/renatoferreira656/GoogleApiPOC)

### Setup

This application use SpringFramework stack with JAVA `1.8.0_251`.

With Java configured to start the web server just execute:

#### Linux:
```
./mvnw spring-boot:run
```

#### Windows:

```
mvnw.cmd spring-boot:run
```

### Environment variable 

To Access the Google API an `Api-Key` is needed, to pass this variable to the application you export 
`GOOGLE_API_KEY` as an Environment Variable. Also is possible to pass as follows:

#### Linux
```
GOOGLE_API_KEY="Api-Key from google" ./mvnw spring-boot:run 
```

#### Windows (Prompt Comando)
```
set GOOGLE_API_KEY=APIKEYFROMGOOGLE
mvnw.cmd spring-boot:run
```

#### Windows (GitBash)
```
GOOGLE_API_KEY="Api-Key from google" ./mvnw spring-boot:run 
./mvnw spring-boot:run
```


Now the port `8080` is open for request.

### Web Page:

Accessing the root of `8080` will be a webpage available to help test the endpoints.

###### Default URL:
``` 
http://localhost:8080
```

### API Description:

#### `GET` /api/location/coordinates?location={TEXT}
Request with `location` parameter as an open text.

#### Example
###### Request:
`/api/location/coordinates?location=Campinas`

###### Response:
```
[
   {
      "formattedAddress" : "Campinas - State of São Paulo, Brazil",
      "geoPoint" : {
         "lat" : -22.9329252,
         "lng" : -47.073845
      },
      "locationQuery" : "Campinas",
      "status" : "FOUND"
   }
]
```

#### `POST` /api/location/coordinates/batch
Multipart request to upload an UTF-8 file with one address per line, example:

##### Example
###### Request file:

```
Barcelona, Barcelona
C. Vilamari, 50, Barcelona
Pl. Jar Jar Binks, 42, Barcelona
Felip II, 323, Barcelona
08015 Barcelona
calle de las doncellas, 10, Barcelona
Plaza España
Gran Vía de la Corte Catalana, 423, 08015 Barcelona
inAtlas
Fuente de Montjuic
nowhere
Melbourne
Universidade de Campinas, Brasil
```

###### Response:
```
   [{
      "formattedAddress" : "201 Lewis St, Dennis, KS 67341, USA",
      "geoPoint" : {
         "lat" : 37.3456569,
         "lng" : -95.4112203
      },
      "locationQuery" : "nowhere",
      "status" : "FOUND"
   },
   {
      "formattedAddress" : "08015 Barcelona, Spain",
      "geoPoint" : {
         "lat" : 41.378633,
         "lng" : 2.1523914
      },
      "locationQuery" : "08015 Barcelona",
      "status" : "FOUND"
   },
   {
      "formattedAddress" : "Carrer de Felip II, 323, 08016 Barcelona, Spain",
      "geoPoint" : {
         "lat" : 41.4292193,
         "lng" : 2.1751336
      },
      "locationQuery" : "Felip II, 323, Barcelona",
      "status" : "FOUND"
   }]
```
