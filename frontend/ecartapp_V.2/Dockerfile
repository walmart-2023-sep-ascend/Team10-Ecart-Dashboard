FROM node:17-alpine as builder
WORKDIR /app
COPY package.json .
COPY package-lock.json .
#COPY npm.lock .
RUN npm install
COPY . .
RUN npm run build


FROM nginx

#COPY container /
WORKDIR /app
#COPY nginx.conf /etc/nginx/nginx.conf
COPY ./build/ /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

RUN rm -rf ./*
COPY --from=builder /app/build .
ENTRYPOINT ["nginx", "-g", "daemon off;"]