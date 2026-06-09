# Stage 1 — Build
FROM node:18-alpine AS builder
WORKDIR /app
COPY package*.json .
RUN npm ci --quiet
COPY . .
RUN npm run build

# Stage 2 — Nginx
FROM nginx:1.25-alpine
RUN addgroup -S nginxgroup && \
    adduser -S nginxuser -G nginxgroup
COPY --from=builder /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN chown -R nginxuser:nginxgroup /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
