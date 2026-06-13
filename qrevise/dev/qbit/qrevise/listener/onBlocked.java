package dev.qbit.qrevise.listener;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.manager.Manager;
import dev.qbit.qrevise.util.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class onBlocked implements Listener {
   private Manager manager = new Manager();

   @EventHandler
   public void onMove(PlayerMoveEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.move"));
      }
   }

   @EventHandler
   public void onItemDrop(PlayerDropItemEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.item-drop"));
      }
   }

   @EventHandler
   public void onConsume(PlayerItemConsumeEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.item-consume"));
      }
   }

   @EventHandler
   public void onClick(InventoryClickEvent e) {
      Player p = (Player)e.getWhoClicked();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.inventory-click"));
      }
   }

   @EventHandler
   public void onChat(AsyncPlayerChatEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.chat.cancel"));
         if (Main.ins.getConfig().getBoolean("event-cancel.chat.send-to-contact")) {
            Bukkit.getScheduler().runTaskLater(Main.ins, () -> p.chat("/contact " + e.getMessage()), 1L);
         }

      }
   }

   @EventHandler
   public void onCommand(PlayerCommandPreprocessEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         boolean find = false;

         for(String cmd : Main.ins.getConfig().getStringList("event-cancel.command.whitelist")) {
            if (e.getMessage().contains(cmd)) {
               find = true;
            }
         }

         if (!find) {
            e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.command.cancel"));
            p.sendMessage(Colorize.s(Main.ins.getConfig().getString("event-cancel.command.msg")));
         }

      }
   }

   @EventHandler
   public void onTeleport(PlayerTeleportEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         e.setCancelled(Main.ins.getConfig().getBoolean("event-cancel.teleport"));
      }
   }
}
