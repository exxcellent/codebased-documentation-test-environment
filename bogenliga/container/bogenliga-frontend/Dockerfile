#  Create a new image from the base nodejs image
FROM node:10

# Instal http server
RUN npm i -g http-server

# Create the target directory in the image
RUN mkdir -p /usr/src/app

# Copy the client application source files. You can use .dockerignore to exlcude files. Works just as .gitignore does.
COPY ./staging /usr/src/app

# Set the created directory as the working directory
WORKDIR /usr/src/app/web

# Open 80 port. This is the port that our development server uses
EXPOSE 80

# Start the http server without caching
CMD ["http-server", "/usr/src/app/web", "-c-1", "-p80"]
