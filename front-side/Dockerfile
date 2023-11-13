# Stage 1: Build the Next.js app
FROM node:16-alpine as builder

WORKDIR /edu/front-side

COPY package.json package-lock.json ./

RUN npm install --only=production


COPY . .

RUN npm run build

# Stage 2: Copy the built app to the final image
FROM node:alpine

WORKDIR /edu/front-side

COPY --from=builder --chown=1000:1000 /edu/front-side/ .

CMD ["npm","run", "dev"]