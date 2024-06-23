FROM node:20-bookworm

ARG MONGO_VERSION=7.0

EXPOSE 3000
EXPOSE 27017
EXPOSE 9229
EXPOSE 4000

RUN apt-get install gnupg curl

RUN curl -fsSL https://www.mongodb.org/static/pgp/server-7.0.asc | \
       gpg -o /usr/share/keyrings/mongodb-server-7.0.gpg \
       --dearmor

RUN touch /etc/apt/sources.list.d/mongodb-org-7.0.list
RUN echo "deb [ signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] http://repo.mongodb.org/apt/debian bookworm/mongodb-org/7.0 main" | tee /etc/apt/sources.list.d/mongodb-org-7.0.list

RUN apt-get update

RUN apt-get install -y mongodb-org

RUN wget https://dl.min.io/server/minio/release/linux-amd64/archive/minio_20240613225353.0.0_amd64.deb -O minio.deb

RUN dpkg -i minio.deb

WORKDIR /app

RUN npm install -g yarn --force
RUN npm install -g @nestjs/cli


ENV NODE_ENV=development

COPY prisma ./prisma
COPY package.json yarn.lock ./
COPY tsconfig.json ./

RUN yarn install

RUN yarn prisma generate
CMD [ "yarn", "start:debug" ]
