package dev.qbit.qrevise.listener;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.manager.Manager;
import dev.qbit.qrevise.revise.Revise;
import dev.qbit.qrevise.util.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onLeave implements Listener {
   private Manager manager = new Manager();

   @EventHandler
   public void onLeave(PlayerQuitEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p)) {
         ((Revise)Main.revises.get(p)).setResult(e.getReason().toString());
         if (Main.ins.getConfig().getBoolean("player-leave.disconnect")) {
            if (((Revise)Main.revises.get(p)).timeRemaining > 1000) {
               for(String s : Main.ins.getConfig().getStringList("player-leave.broadcast.rt")) {
                  for(Player ap : Bukkit.getOnlinePlayers()) {
                     if (ap.hasPermission("revise.notify")) {
                        ap.sendMessage(Colorize.s(s.replace("{player}", p.getName()).replace("{reason}", e.getReason().toString())));
                     }
                  }
               }

               this.manager.finish(p);
               return;
            }

            Player admin = ((Revise)Main.revises.get(p)).getAdmin();
            this.manager.finish(p);
            admin.chat(Main.ins.getConfig().getString("player-leave.command").replace("{player}", p.getName()).replace("{admin}", admin.getName()));
            Main.ins.getLogger().info(Main.ins.getConfig().getString("player-leave.log").replace("{player}", p.getName()).replace("{admin}", admin.getName()));

            for(String s : Main.ins.getConfig().getStringList("player-leave.broadcast.ban")) {
               for(Player ap : Bukkit.getOnlinePlayers()) {
                  if (ap.hasPermission("revise.notify")) {
                     ap.sendMessage(Colorize.s(s.replace("{player}", p.getName()).replace("{reason}", e.getReason().toString())));
                  }
               }
            }
         }
      }

   }

   @EventHandler
   public void onKick(PlayerKickEvent e) {
      Player p = e.getPlayer();
      if (this.manager.inCheck(p) && Main.ins.getConfig().getBoolean("player-leave.kick")) {
         ((Revise)Main.revises.get(p)).setResult("KICKED");
         if (e.getReason().contains("бан")) {
            ((Revise)Main.revises.get(p)).setResult("BANNED");
         }

         if (((Revise)Main.revises.get(p)).timeRemaining > 1000) {
            for(String s : Main.ins.getConfig().getStringList("player-leave.broadcast.rt")) {
               for(Player ap : Bukkit.getOnlinePlayers()) {
                  if (ap.hasPermission("revise.notify")) {
                     ap.sendMessage(Colorize.s(s.replace("{player}", p.getName()).replace("{reason}", e.getReason().toString())));
                  }
               }
            }

            this.manager.finish(p);
            return;
         }

         Player admin = ((Revise)Main.revises.get(p)).getAdmin();
         this.manager.finish(p);
         admin.chat(Main.ins.getConfig().getString("player-leave.command").replace("{player}", p.getName()).replace("{admin}", admin.getName()));
         Main.ins.getLogger().info(Main.ins.getConfig().getString("player-leave.log").replace("{player}", p.getName()).replace("{admin}", admin.getName()));

         for(String s : Main.ins.getConfig().getStringList("player-leave.broadcast.ban")) {
            for(Player ap : Bukkit.getOnlinePlayers()) {
               if (ap.hasPermission("revise.notify")) {
                  ap.sendMessage(Colorize.s(s.replace("{player}", p.getName()).replace("{reason}", e.getReason().toString())));
               }
            }
         }
      }

   }

   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      for(PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
         if (effect.getType().equals(PotionEffectType.BLINDNESS)) {
            e.getPlayer().removePotionEffect(effect.getType());
         }
      }

   }
}
