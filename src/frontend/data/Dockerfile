FROM node:16
# make the 'app' folder the current working directory.  This is the directory on the container, not locally.
WORKDIR /app
# install project dependencies
RUN npm install -g json-server
EXPOSE 5000
CMD json-server --watch /data/db.json --host 0.0.0.0 --port 5000
