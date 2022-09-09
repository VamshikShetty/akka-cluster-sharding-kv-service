package com.example.akka.sharding.kv.actor.entites;

import akka.actor.typed.ActorRef;
import akka.pattern.StatusReply;

import com.example.akka.sharding.kv.actor.common.Command;
import com.fasterxml.jackson.annotation.JsonCreator;

public class StoreValueCmd implements Command {
   private final ActorRef<StatusReply<Boolean>> replyTo;
   private String key;
   private String value;

   @JsonCreator
   public StoreValueCmd(String key, String value,  ActorRef<StatusReply<Boolean>> replyTo) {
      this.key = key;
      this.value = value;
      this.replyTo = replyTo;
   }

   @Override
   public String toString() {
       return "StoreValueCmd";
   }

   public ActorRef<StatusReply<Boolean>> getReplyTo() {
      return replyTo;
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
