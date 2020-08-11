FROM openjdk:14-alpine
COPY target/covid19-aggregator-*.jar covid19-aggregator.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "covid19-aggregator.jar"]