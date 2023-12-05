FROM gradle:7.2-jdk8 AS BUILD_STAGE
# Copy everything (Java Code) from the current directory on the local machine to the /home/gradle directory in the container.
COPY --chown=gradle:gradle . /home/gradle
# Run the gradle build command or exit if there is an error.
RUN gradle build || return 1

FROM openjdk:8
EXPOSE 8081
MAINTAINER "Matt Williard <mwilliard@oreillyauto.com>"
WORKDIR /app
# In the first stage above, the BUILD_STAGE created the jar in the /home/gradle/build/libs
# We will copy the jar from the BUILD_STAGE location to the /app location and name it app.jar
COPY --from=BUILD_STAGE /home/gradle/build/libs/*.jar /app/app.jar
#COPY /build/libs/VueAndJava-0.0.1-SNAPSHOT.jar  /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]