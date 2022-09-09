package com.example.akka.sharding.kv.actor.entites;

import com.example.akka.sharding.kv.actor.common.Event;
import com.fasterxml.jackson.annotation.JsonCreator;

public class StoreValueEvent implements Event {
   private String key;
   private String value;

   @JsonCreator
   public StoreValueEvent(String key, String value) {
      this.key = key;
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }
}
