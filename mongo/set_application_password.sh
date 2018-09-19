#!/bin/bash
set -m

# Wait for MongoDB to boot
# RET=1
# while [[ RET -ne 0 ]]; do
#     echo "=> Waiting for confirmation of MongoDB service startup..."
#     sleep 5
#     mongo admin --eval "help" >/dev/null 2>&1
#     RET=$?
# done

if [ ! -f /tmp/application_password_set ]; then
    
if [ "$MONGO_INITDB_APPLICATION_DATABASE" != "admin" ]; then
    echo "=> Creating a ${MONGO_INITDB_APPLICATION_DATABASE} database user with a password in MongoDB"
    mongo admin -u $MONGO_INITDB_ROOT_USERNAME -p $MONGO_INITDB_ROOT_PASSWORD << EOF
echo "Using $MONGO_INITDB_APPLICATION_DATABASE database";
use $MONGO_INITDB_APPLICATION_DATABASE;
db.createUser({user: '$MONGO_INITDB_APPLICATION_USERNAME', pwd: '$MONGO_INITDB_APPLICATION_PASSWORD', roles:[{role:'dbOwner', db:'$MONGO_INITDB_APPLICATION_DATABASE'}]});
EOF
fi

touch /tmp/application_password_set

fi

echo "MongoDB configured successfully. You may now connect to the DB."