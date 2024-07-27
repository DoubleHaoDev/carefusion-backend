#!/usr/bin/env bash



SOURCE="${BASH_SOURCE[0]}"
while [ -h "SOURCE" ]; do
    DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
done
DIR="$( cd -P "$( dirname "$SOURCE")" && pwd)"

cd "${DIR}/.."


set -e



./gradlew --console=plain -Dorg.gradle.daemon=false checkstyleMain spotlesscheck
