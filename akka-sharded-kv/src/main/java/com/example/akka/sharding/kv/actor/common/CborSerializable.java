package com.example.akka.sharding.kv.actor.common;

/**
 * Marker trait for serialization with Jackson CBOR. Enabled in serialization.conf
 * `akka.actor.serialization-bindings` (via application.conf).
 */
public interface CborSerializable {}
