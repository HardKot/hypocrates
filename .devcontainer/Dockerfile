FROM library/node:lts-bullseye-slim

ARG DEBIAN_FRONTEND=noninteractive
RUN apt update \
    && apt install -y --no-install-recommends sudo \
    && apt autoremove -y \
    && rm -rf /var/lib/apt/lists/* \
    && echo "node ALL=(ALL) NOPASSWD: ALL" >/etc/sudoers.d/node \
    && chmod 0440 /etc/sudoers.d/node

WORKDIR /workspace

COPY package.json yarn.lock ./

RUN yarn install --frozen-lockfile

RUN npm install -g @nestjs/cli

RUN npx prisma generate

RUN yarn start:dev
