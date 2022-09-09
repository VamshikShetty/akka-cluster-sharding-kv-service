package com.example.akka.sharding.kv.actor;

import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.EntityRef;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

import com.example.akka.sharding.kv.actor.common.Command;
import com.example.akka.sharding.kv.actor.entites.KvManagerEntity;
import com.example.akka.sharding.kv.actor.entites.StoreValueCmd;
import com.example.akka.sharding.kv.actor.entites.FetchValueCmd;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletionStage;
import java.time.Duration;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KvService {
	private static final Logger log = LoggerFactory.getLogger(KvService.class);

	private static MessageDigest md;
	private ActorSystem<Void> actorSystem;
	private final ClusterSharding clusterSharding;
	private final int entityIdLen = 5;
	public static final int ASK_WAIT_TIME = 45;

   static {
      try {
         md = MessageDigest.getInstance("SHA-256");
      } catch(NoSuchAlgorithmException e) {
         // TODO: Error handling
      }
   }

	public KvService(ActorSystemProducer actorSystemProducer) {
		this.actorSystem = actorSystemProducer.getActorSystem();
		this.clusterSharding = ClusterSharding.get(actorSystemProducer.getActorSystem());
		log.info("KvService created.");
	}

	public CompletionStage<Boolean> putKeyValue(String key, String value) {
		EntityRef<Command> entityRef = this.clusterSharding.entityRefFor(KvManagerEntity.ENTITY_KEY, kvManagerEntityId(key));
		return entityRef.askWithStatus(
			replyTo -> new StoreValueCmd(key, value, replyTo),
			Duration.ofSeconds(ASK_WAIT_TIME));
	}

	public CompletionStage<String> getValue(String key) {
		EntityRef<Command> entityRef = this.clusterSharding.entityRefFor(KvManagerEntity.ENTITY_KEY, kvManagerEntityId(key));
		return entityRef.askWithStatus(
			replyTo -> new FetchValueCmd(key, replyTo),
			Duration.ofSeconds(ASK_WAIT_TIME));
	}

	private String kvManagerEntityId(String key) {
      String id = sha256Hash(key).substring(0, entityIdLen);
      return String.format("kv-manager-entity-store-%s", id);
   }

	private static String sha256Hash(String input) {
      byte[] bytesHash = md.digest(input.getBytes(StandardCharsets.UTF_8));

      // Convert byte array into signum representation
      BigInteger number = new BigInteger(1, bytesHash);
 
      // Convert message digest into hex value
      StringBuilder hexString = new StringBuilder(number.toString(16));

      // Pad with leading zeros
      while (hexString.length() < 64) {
          hexString.insert(0, '0');
      }

      return hexString.toString();
   }
}