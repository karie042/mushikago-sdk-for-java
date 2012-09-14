#!/bin/sh

mvn package

# mavenリポジトリにデプロイ
mvn deploy

echo 'mushikago java sdk deploy to maven repository, complete.'

# mvn packageで作成された最新のjarファイルを割り出す
TARGET_FILE="target/`ls target | grep 'jar-with-dependencies'`"

# 成果物を格納したzipファイルを作成する
LATEST_DIR='latest'
ZIP_FILE='mushikago-java-sdk-2.4.2.zip'
rm -rf $LATEST_DIR
rm -f $ZIP_FILE

mkdir $LATEST_DIR

cp $TARGET_FILE "$LATEST_DIR/${TARGET_FILE#*\/}"
zip -r $ZIP_FILE $LATEST_DIR

# zipファイルをS3にアップロードします
S3_DEST_PATH='s3://mushikago.org/developer/sdk/java'
S3_DEST_URL="$S3_DEST_PATH/$ZIP_FILE"
echo "$ZIP_FILE -> $S3_DEST_URL"
s3cmd put -P $ZIP_FILE $S3_DEST_URL

echo 'mushikago java sdk upload, complete.'

# mitsubachiのサンプルコードをデプロイします
#TARGET_FILE='src/main/java/org/mushikago/sdk/services/mitsubachi/samples/MitsubachiApiSample.java'
#S3_DEST_URL="$S3_DEST_PATH/${TARGET_FILE#*samples\/}"
#echo "$S3_DEST_URL -> $S3_DEST_URL"
#s3cmd put -P $TARGET_FILE $S3_DEST_URL

# hotaruのサンプルコードをデプロイします
#TARGET_FILE='src/main/java/org/mushikago/sdk/services/hotaru/samples/HotaruApiSample.java'
#S3_DEST_URL="$S3_DEST_PATH/${TARGET_FILE#*samples\/}"
#echo "$S3_DEST_URL -> $S3_DEST_URL"
#s3cmd put -P $TARGET_FILE $S3_DEST_URL


