FROM adoptopenjdk/openjdk11

EXPOSE 8080

ENV APP_HOME /usr/src/app

# Create the application home directory
RUN mkdir -p $APP_HOME

# Copy the specific JAR file to the application home directory
COPY target/database_service_project-0.0.4.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

CMD ["java", "-jar", "app.jar"]
