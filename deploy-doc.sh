#!/bin/sh

mvn javadoc:javadoc

s3cmd sync -P --exclude '/.*' target/site/apidocs/* s3://docs.mushikago.org/JavaSDK/latest/

