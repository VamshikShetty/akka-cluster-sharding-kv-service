package com.example.akka.sharding.kv.actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.cluster.sharding.typed.ShardingEnvelope;

import com.example.akka.sharding.kv.actor.common.Command;
import com.example.akka.sharding.kv.actor.entites.KvManagerEntity;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ShardedRegionsFactory {
   private static final Logger log = LoggerFactory.getLogger(ShardedRegionsFactory.class);
   private ActorRef<ShardingEnvelope<Command>> KvShardRegion;

   public ShardedRegionsFactory(ActorSystemProducer actorSystemProducer) {
      ActorSystem system = actorSystemProducer.getActorSystem();
      this.KvShardRegion = KvManagerEntity.init(system);
      log.info("Sharded regions initialized.");
   }
}
