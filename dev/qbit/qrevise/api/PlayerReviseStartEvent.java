package dev.qbit.qrevise.api;

import dev.qbit.qrevise.revise.ReviseType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReviseStartEvent extends Event {
   private static final HandlerList handlers = new HandlerList();
   private final Player target;
   private final Player admin;
   private final ReviseType type;

   public PlayerReviseStartEvent(Player player, Player admin, ReviseType type) {
      this.target = player;
      this.admin = admin;
      this.type = type;
   }

   public Player getTarget() {
      return this.target;
   }

   public Player getAdmin() {
      return this.admin;
   }

   public ReviseType getType() {
      return this.type;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   public HandlerList getHandlers() {
      return handlers;
   }
}
