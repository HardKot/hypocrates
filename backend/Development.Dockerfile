FROM node:20

WORKDIR /app

RUN npm install -g yarn --force

COPY package.json yarn.lock ./

RUN yarn install --frozen-lockfile

RUN npm install -g @nestjs/cli

RUN npx prisma generate

RUN yarn start:dev
