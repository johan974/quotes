# Quotes

Service to provide random quote each time

## Execution
Start the service using `mvn spring-boot:run`

Now if you call the api using GET `localhost:8080/random-quote`

You should see something like below

```
{
    "result": "An obstacle may be either a stepping stone or a stumbling block.",
    "setOrExpired": true
}

```

Where `result` is the quote.

# Dit is een update om 20:00 uur 

## Tutorial
This repository has been used to demonstrate the dockerization of the spring boot service. Tutorial can be accessed at https://medium.com/@bhanuchaddha/containerise-spring-boot-application-with-docker-6d395c529f44
