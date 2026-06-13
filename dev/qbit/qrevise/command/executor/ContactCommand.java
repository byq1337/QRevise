package dev.qbit.qrevise.command.executor;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.manager.Manager;
import dev.qbit.qrevise.util.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ContactCommand implements CommandExecutor {
   private Manager manager = new Manager();

   public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      if (s == null) {
         $$$reportNull$$$0(2);
      }

      if (strings == null) {
         $$$reportNull$$$0(3);
      }

      Player sender = (Player)commandSender;
      if (this.manager.inCheck(sender)) {
         Player admin = (Player)Main.players.get(sender);
         if (strings.length == 0) {
            return true;
         }

         String join = String.join(" ", strings);
         String msg = Main.ins.getConfig().getString("contact-format.from-player");
         sender.sendMessage(Colorize.s(msg.replace("{message}", join).replace("{player}", sender.getName())));
         admin.sendMessage(Colorize.s(msg.replace("{message}", join).replace("{player}", sender.getName())));
      } else if (Main.admins.containsKey(sender)) {
         Player target = (Player)Main.admins.get(sender);
         if (!sender.hasPermission("revise.use")) {
            return true;
         }

         if (strings.length == 0) {
            return true;
         }

         String join = String.join(" ", strings);
         String msg = Main.ins.getConfig().getString("contact-format.from-admin");
         target.sendMessage(Colorize.s(msg.replace("{message}", join)));
         sender.sendMessage(Colorize.s(msg.replace("{message}", join)));
      }

      return true;
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

      var10001[1] = "dev/qbit/qrevise/command/executor/ContactCommand";
      var10001[2] = "onCommand";
      throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
   }
}
