#!/usr/bin/env sh
#   Use this script to test if a given TCP host/port are available

WAITFORIT_host="$1"
WAITFORIT_port="$2"
shift 2

WAITFORIT_cmd="$@"

while ! nc -z "$WAITFORIT_host" "$WAITFORIT_port"; do
  echo "Waiting for $WAITFORIT_host:$WAITFORIT_port..."
  sleep 1
done

echo "$WAITFORIT_host:$WAITFORIT_port is available, running command: $WAITFORIT_cmd"
exec "$@"
