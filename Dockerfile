FROM adoptopenjdk/openjdk11

EXPOSE 8080

ENV APP_HOME /usr/src/app

# Create the application home directory
RUN mkdir -p $APP_HOME

# Define a build argument for the version
ARG JAR_VERSION=0.0.5  # Default value, can be overridden during build

# Copy the specific JAR file to the application home directory
COPY target/database_service_project-${JAR_VERSION}.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

CMD ["java", "-jar", "app.jar"]
