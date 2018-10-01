FROM mariadb:latest

# Install some packages so the connectivity test can run
RUN apt-get update -q \
    && apt-get install -qy netcat-openbsd \
    && apt-get clean \
    && rm -rf /var/lib/apt

ENV MYSQL_ROOT_PASSWORD adminpwd
ENV MYSQL_DATABASE test
ENV MYSQL_USER user
ENV MYSQL_PASSWORD userpwd
