#Covid 19 Aggregator

A backend service which fetches Covid-19 data from different sources, consolidates the result
and provides most accurate response in terms of total number cases by country

Sources from
* WorldOMeter
* Math Droid (~~this will possibly be removed~~)
* John Hopkins University


<u> User Interface </u>

[Covid19 App](https://covid19-tracker-nullptr7.web.app/) deployed on [Firebase](https://firebase.google.com/)

[Source on bitbucket](https://bitbucket.org/ishan-shah/covid-19-tracker-ui/)

Currently, UI is fetching the data only from WorldOMeter.

<u><b>Tech Stack</b></u>:

<u> Backend </u>

1. Micronaut Framework (creating rest services)
2. Swagger (exposing documentation)
3. Mapstruct (converting data from different sources to one source)
4. Lombok (removing boilerplate)
5. RxJava (implementing reactive response instead vanilla response)
6. Java 14

<u> Frontend </u>

1. React
2. Material UI
3. Leaflet
4. LineGraph
5. Numeral
6. ChartJS