FROM node:lts-alpine
RUN npm install -g http-server
RUN npm install --production
COPY . .
RUN npm run build

EXPOSE 8081
CMD [ "http-server", "dist" ]