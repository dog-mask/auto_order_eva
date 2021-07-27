FROM node:lts-alpine
RUN npm install -g http-server
RUN npm install --production
RUN npm install chalk
RUN npm install each-async
RUN npm install indent-string
COPY . .
RUN npm run build

EXPOSE 8081
CMD [ "http-server", "dist" ]