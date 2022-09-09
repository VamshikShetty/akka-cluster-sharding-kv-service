package com.example.akka.sharding.kv.actor.entites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.akka.sharding.kv.actor.common.CborSerializable;

import java.util.HashMap;

public class KVState implements CborSerializable {

   private HashMap<String, String> kvValueMap;

   public KVState() {
      this.kvValueMap = new HashMap<>();
   }

   public void addKeyValue(String key, String value) {
      this.kvValueMap.put(key, value);
   }

   public String getValue(String key) {
      return this.kvValueMap.get(key);
   }

   @JsonProperty("kvValueMap")
   public HashMap<String, String> getKvValueMap() {
      return kvValueMap;
   }

   @JsonProperty("kvValueMap")
   public void setKvValueMap(HashMap<String, String> kvValueMap) {
      this.kvValueMap = kvValueMap;
   }
}
