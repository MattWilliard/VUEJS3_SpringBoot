# This is an example Dockerfile that would run just the VUE JS 3 application by itself.
FROM node:16
# make the 'app' folder the current working directory
WORKDIR /app
# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./
# install project dependencies
RUN npm install
# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY .. ./
# build app for production with minification
# only needed if we are serving the app as static content from the dist folder
#RUN npm run build
EXPOSE 8080
#CMD [ "http-server", "dist" ]
CMD npm run serve -- --mode=production