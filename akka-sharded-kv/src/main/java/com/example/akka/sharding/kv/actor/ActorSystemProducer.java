package com.example.akka.sharding.kv.actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class ActorSystemProducer {
	private static final Logger log = LoggerFactory.getLogger(ActorSystemProducer.class);
	private ActorSystem<Void> actorSystem;

	public ActorSystemProducer() {
		this.actorSystem = ActorSystem.create(ActorSystemProducer.create(), "kv-inmem-actor-system");
		log.info("Actor system created.");
	}

	public ActorSystem<Void> getActorSystem() {
		return actorSystem;
	}

	private static Behavior<Void> create() {
		return Behaviors.setup(ctx -> {
			return Behaviors.empty();
		});
	}
}