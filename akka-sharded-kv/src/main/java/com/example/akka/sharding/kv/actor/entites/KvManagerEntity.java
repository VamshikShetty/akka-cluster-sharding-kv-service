package com.example.akka.sharding.kv.actor.entites;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

import akka.pattern.StatusReply;
import akka.persistence.typed.PersistenceId;
import akka.persistence.typed.javadsl.CommandHandlerWithReply;
import akka.persistence.typed.javadsl.CommandHandlerWithReplyBuilder;
import akka.persistence.typed.javadsl.EventHandler;
import akka.persistence.typed.javadsl.EventSourcedBehaviorWithEnforcedReplies;
import akka.persistence.typed.javadsl.ReplyEffect;

import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.cluster.sharding.typed.ShardingEnvelope;

import com.example.akka.sharding.kv.actor.common.Event;
import com.example.akka.sharding.kv.actor.common.Command;


public class KvManagerEntity extends EventSourcedBehaviorWithEnforcedReplies<Command, Event, KVState> {

   public final static EntityTypeKey<Command> ENTITY_KEY = EntityTypeKey.create(Command.class, "kv-manager-entity");
   private final String entityId;
   private final ActorContext<Command> context;

   public KvManagerEntity(ActorContext<Command> context, String entityId) {
      super(PersistenceId.ofUniqueId(entityId));
      this.entityId = entityId;
      this.context = context;
      context.getLog().info(String.format("KvManagerEntity created : %s", entityId));
   }

   @Override
   public KVState emptyState() {
       return new KVState();
   }

   public static Behavior<Command> create(String entityId) {
      return Behaviors.setup(context -> new KvManagerEntity(context, entityId));
   }

   public static ActorRef<ShardingEnvelope<Command>> init(ActorSystem<Void> system) {
      return ClusterSharding.get(system).init(
          Entity.of(
              ENTITY_KEY,
              entityContext -> {
                  return KvManagerEntity.create(entityContext.getEntityId());
              }
          )
      );
   }

   private ReplyEffect<Event, KVState> onStoreValueCmd(KVState state, StoreValueCmd cmd) {
      return Effect()
               .persist(new StoreValueEvent(cmd.getKey(), cmd.getValue()))
               .thenReply(cmd.getReplyTo(), updatedState -> StatusReply.success(true));
   }

   private ReplyEffect<Event, KVState> onFetchValueCmd(KVState state, FetchValueCmd cmd) {
      return Effect()
               .none()
               .thenReply(cmd.getReplyTo(), updatedState -> StatusReply.success(updatedState.getValue(cmd.getKey())));
   }

   @Override
   public CommandHandlerWithReply<Command, Event, KVState> commandHandler() {
      CommandHandlerWithReplyBuilder<Command, Event, KVState> builder = newCommandHandlerWithReplyBuilder();
      builder.forAnyState()
         .onCommand(StoreValueCmd.class, this::onStoreValueCmd)
         .onCommand(FetchValueCmd.class, this::onFetchValueCmd);
      return builder.build();
   }
   
   @Override
   public EventHandler<KVState, Event> eventHandler() {
      return newEventHandlerBuilder()
               .forAnyState()
               .onEvent(StoreValueEvent.class, (state, event) -> {
                  state.addKeyValue(event.getKey(), event.getValue());
                  context.getLog().info(String.format("Storing kv : %s:%s", event.getKey(), event.getValue()));
                  return state;
               }).build();
   }
}
