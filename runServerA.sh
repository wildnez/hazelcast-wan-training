#!/bin/sh
java -classpath target/hazelcast-wan-training-1.0-SNAPSHOT.jar:target/lib/*  -Dhazelcast.config=hazelcast_A.xml com.hazelcast.training.wan.Server
