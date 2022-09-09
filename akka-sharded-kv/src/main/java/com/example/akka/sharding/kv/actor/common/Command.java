package com.example.akka.sharding.kv.actor.common;

public interface Command extends CborSerializable {
    public String toString();
}
