#!/bin/bash

APP_TOKEN=$1
VERSION_CODE=$2
VERSION_NAME=$3
PATH_TO_MAPPING_FILE=$4

echo "Instabug mapping files uploader"

VERSION='{"code":"'"$VERSION_CODE"'","name":"'"$VERSION_NAME"'"}'

if [ ! -f $PATH_TO_MAPPING_FILE ]; then
    echo "File not found!"
    exit 0
fi

echo "Mapping file found! Uploading..."

ENDPOINT="https://api.instabug.com/api/sdk/v3/symbols_files"
STATUS=$(curl "${ENDPOINT}" --write-out %{http_code} --silent --output /dev/null -F os=android -F app_version="${VERSION}" -F symbols_file=@"${PATH_TO_MAPPING_FILE}" -F application_token="${APP_TOKEN}")
if [ $STATUS -ne 200 ]; then
  echo "Error while uploading mapping files"
  exit 0
fi


echo "Success! Your mapping files got uploaded successfully"
