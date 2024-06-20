FROM node:20 as builder

WORKDIR /app

RUN npm install -g yarn --force

COPY package.json yarn.lock ./

RUN yarn install --frozen-lockfile --production

RUN npm install -g @nestjs/cli

COPY --chown=node:node . .

RUN npx prisma generate
RUN nest build


FROM node:20

ENV NODE_ENV=production

USER node
EXPOSE 3000
WORKDIR /home/node

COPY --from=builder --chown=node:node /app/package*.json ./
COPY --from=builder --chown=node:node /app/node_modules/ ./node_modules/
COPY --from=builder --chown=node:node /app/dist/ ./dist/

CMD ["node", "dist/main.js"]

