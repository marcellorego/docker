FROM mongo:latest

# Install some packages so the connectivity test can run
RUN apt-get update -q \
    && apt-get install -qy netcat-openbsd \
    && apt-get clean \
    && rm -rf /var/lib/apt

ARG MONGO_ROOT_USER="admin"
ARG MONGO_ROOT_PASS="4dmInP4ssw0rd"

ARG MONGO_APPLICATION_DATABASE="admin"
ARG MONGO_APPLICATION_USER="user"
ARG MONGO_APPLICATION_PASS="userp4ssw0rd"

# Admin User
ENV MONGO_INITDB_ROOT_USERNAME=${MONGO_ROOT_USER} \
    MONGO_INITDB_ROOT_PASSWORD=${MONGO_ROOT_PASS}

# Application Database User
ENV MONGO_INITDB_APPLICATION_DATABASE=${MONGO_APPLICATION_DATABASE} \
    MONGO_INITDB_APPLICATION_USERNAME=${MONGO_APPLICATION_USER} \
    MONGO_INITDB_APPLICATION_PASSWORD=${MONGO_APPLICATION_PASS}

EXPOSE 27017

ADD set_application_password.sh /docker-entrypoint-initdb.d/set_application_password.sh

RUN chmod +x /docker-entrypoint-initdb.d/set_application_password.sh
