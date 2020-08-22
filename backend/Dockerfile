FROM openjdk:11.0.8-jdk

# Add a volume pointing to /tmp
# VOLUME /tmp

COPY pom.xml /opt/fleet-manager-service/build/
COPY mvnw /opt/fleet-manager-service/build/
ADD .mvn /opt/fleet-manager-service/build/.mvn/
ADD src /opt/fleet-manager-service/build/src/

WORKDIR /opt/fleet-manager-service/build/

RUN ./mvnw clean package

FROM openjdk:11.0.8-jre
# Maven build is mandatory before copying jar file : $ ./mvnw clean package

WORKDIR /opt/fleet-manager-service/

RUN ls -la /opt/fleet-manager-service/build/target

COPY --from=0 /opt/fleet-manager-service/build/target/fleet-manager-service-0.0.1-SNAPSHOT.jar fleet-manager-service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","review-service.jar"]
