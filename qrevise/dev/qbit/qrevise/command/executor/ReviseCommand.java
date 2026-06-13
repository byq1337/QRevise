package dev.qbit.qrevise.command.executor;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.manager.Manager;
import dev.qbit.qrevise.revise.Revise;
import dev.qbit.qrevise.revise.ReviseType;
import dev.qbit.qrevise.util.Colorize;
import dev.qbit.qrevise.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReviseCommand implements CommandExecutor {
   private Manager manager = new Manager();

   public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      if (s == null) {
         $$$reportNull$$$0(2);
      }

      if (strings == null) {
         $$$reportNull$$$0(3);
      }

      Player sender = (Player)commandSender;
      if (!sender.hasPermission("revise.use")) {
         return true;
      } else {
         if (strings.length == 1 && strings[0].equals("list") && sender.hasPermission("revise.list")) {
            if (Main.revises.isEmpty()) {
               sender.sendMessage(Colorize.s(Main.ins.getConfig().getString("list.empty")));
               return true;
            }

            int i = 0;
            sender.sendMessage(Colorize.s(Main.ins.getConfig().getString("list.header")));

            for(Player p : Main.revises.keySet()) {
               ++i;
               Revise revise = (Revise)Main.revises.get(p);
               sender.sendMessage(Colorize.s(Main.ins.getConfig().getString("list.line").replace("{i}", String.valueOf(i)).replace("{admin}", revise.getAdmin().getName()).replace("{player}", revise.getTarget().getName()).replace("{type}", revise.getType().toString()).replace("{time}", Timer.getTimer((int)((System.currentTimeMillis() - revise.getStartTime()) / 1000L)))));
               sender.sendMessage("");
            }
         }

         if (strings.length < 2) {
            return true;
         } else {
            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
               return true;
            } else {
               if (strings[1].equals("start") && !Main.admins.containsKey(sender)) {
                  if (strings.length < 3) {
                     return true;
                  }

                  ReviseType type = ReviseType.valueOf(strings[2].toUpperCase());
                  if (type.equals(ReviseType.ANYDESK) && !sender.hasPermission("revise.anydesk")) {
                     return true;
                  }

                  if (type.toString() == null) {
                     return true;
                  }

                  if (target.hasPermission("revise.protect") && !sender.hasPermission("revise.admin")) {
                     return true;
                  }

                  if (this.manager.inCheck(target)) {
                     return true;
                  }

                  Revise revise = new Revise(target, sender, type);
                  revise.start(revise);
                  Main.revises.put(target, revise);
               }

               if (strings[1].equals("finish") && this.manager.inCheck(target)) {
                  ((Revise)Main.revises.get(target)).setResult("отпущен");
                  this.manager.finish(target);
               }

               if (strings[1].equals("go") && this.manager.inCheck(target)) {
                  this.manager.addSeconds(target, Main.ins.getConfig().getInt("extra-time"));
               }

               if (strings[1].equals("rt") && this.manager.inCheck(target)) {
                  this.manager.removeTimer(target);
               }

               return true;
            }
         }
      }
   }

   // $FF: synthetic method
   private static void $$$reportNull$$$0(int var0) {
      Object[] var10001 = new Object[3];
      switch (var0) {
         case 0:
         default:
            var10001[0] = "commandSender";
            break;
         case 1:
            var10001[0] = "command";
            break;
         case 2:
            var10001[0] = "s";
            break;
         case 3:
            var10001[0] = "strings";
      }

      var10001[1] = "dev/qbit/qrevise/command/executor/ReviseCommand";
      var10001[2] = "onCommand";
      throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
   }
}
