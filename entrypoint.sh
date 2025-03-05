#!/bin/bash
echo "Parsing secrets..."

export $(jq -r 'to_entries | .[] | .key + "=" + .value' /var/secrets/image-persistent-secrets.json)

echo "Starting spring boot application..."

exec java -jar app.jar