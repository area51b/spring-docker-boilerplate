FROM maven:3.9-amazoncorretto-17 as BUILD

COPY . /usr/src/app

RUN mvn -f /usr/src/app/pom.xml dependency:go-offline

RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17
EXPOSE 7080 5005
COPY --from=BUILD /usr/src/app/target /opt/target
WORKDIR /opt/target
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

CMD ["java", "-jar", "app.war"]
