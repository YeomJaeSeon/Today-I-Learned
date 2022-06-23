FROM node:16-alpine

COPY . /usr/src/app

WORKDIR /usr/src/app

RUN yarn install
RUN yarn build

EXPOSE 3000
CMD ["yarn", "start:prod"]
