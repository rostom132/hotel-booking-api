#!/bin/bash
set -e

# Wait for Elasticsearch to be up and running
until curl -s http://localhost:9200 -o /dev/null; do
    sleep 1
done

# Create index
curl -X PUT "http://localhost:9200/hotel" -H 'Content-Type: application/json' -d @mapping.json

# Load data
curl -X POST "http://localhost:9200/_bulk" -H 'Content-Type: application/json' --data-binary @es.json
