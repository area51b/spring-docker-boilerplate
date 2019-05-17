FROM maven:3.6.1-jdk-11 as BUILD

COPY m2 /root/.m2/repository/

COPY . /usr/src/app
RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package -o

FROM openjdk:11.0.3-jre-stretch
EXPOSE 7080 5005
COPY --from=BUILD /usr/src/app/target /opt/target
WORKDIR /opt/target
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

CMD ["java", "-jar", "app.war"]
