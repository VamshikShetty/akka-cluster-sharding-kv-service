package com.example.akka.sharding.kv.actor.entites;

import akka.actor.typed.ActorRef;
import akka.pattern.StatusReply;

import com.example.akka.sharding.kv.actor.common.Command;
import com.fasterxml.jackson.annotation.JsonCreator;

public class FetchValueCmd implements Command {
   private final ActorRef<StatusReply<String>> replyTo;
   private String key;

   @JsonCreator
   public FetchValueCmd(String key, ActorRef<StatusReply<String>> replyTo) {
      this.key = key;
      this.replyTo = replyTo;
   }

   @Override
   public String toString() {
       return "FetchValueCmd";
   }

   public ActorRef<StatusReply<String>> getReplyTo() {
      return replyTo;
   }

   public String getKey() {
      return key;
   }
}
