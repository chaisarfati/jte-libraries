FROM golang:1.17-alpine as builder

# Arguments of this Dockerfile
ARG MONGODB_USER
ARG MONGODB_PASSWORD
ARG EXECUTABLE_NAME

# Set destination for COPY
WORKDIR /app

# Retrieve application dependencies.
# This allows the container build to reuse cached dependencies.
# Expecting to copy go.mod and if present go.sum.
COPY go.* ./
RUN go mod download

# Copy local code to the container image.
COPY . ./

# Build
RUN go mod tidy
RUN go build -o ${EXECUTABLE_NAME}


# Use the official Debian slim image for a lean production container
FROM debian:buster-slim
RUN set -x && apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y \
    ca-certificates && \
    rm -rf /var/lib/apt/lists/*

# Copy the binary to the production image from the builder stage.
COPY --from=builder /app/${EXECUTABLE_NAME} /app/${EXECUTABLE_NAME}

# Run the web service on container startup.
CMD ["/app/${EXECUTABLE_NAME}"]

