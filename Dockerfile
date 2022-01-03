#FROM specifies a base image to build from, see hub.docker.com
#Also see the docker plugin
FROM openjdk:8-jdk-alpine

#Specify a location of the local machine where information from the container can be stored
VOLUME /tmp

#Allows us to provide some value at image build time using --build-arg flag
#For instance: docker build --build-arg profile=dev
#Defaults to local
ARG profile=local

#Creates and sets the environment variable for the resulting container
ENV PROFILE=${profile}

#Add the created jar file into the image so it can be ran as a container
COPY target/*.jar ReverbApplication.jar

#ADD vs COPY
#Effectively the same, but technically copy is used when moving local files into the image
#Add is for external files and move them into the image

#Allows us to expose ports from the container to the host machine
#               host:container
#docker run -p 8080:5000
EXPOSE 5000

#Command ran during image building
RUN echo 'Hello from inside Docker'
RUN ls

#Command ran when a container starts
CMD echo 'H'

#Allows us to specify the primary command(s) that should be ran to initialize the container
#Array of string args
#Canno use ARGs vars in this, only ENV vars
ENTRYPOINT ["sh","-c", "java -jar -Dspring.profiles.active=${PROFILE} ReverbApplication.jar"]

#docker build -t reverb-api .
#docker run -p 8080:8080 reverb-api:latest
#TODO get H2 console working from container