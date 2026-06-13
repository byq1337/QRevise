package dev.qbit.qrevise.command.tabc;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReviseTab implements TabCompleter {
   public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      if (s == null) {
         $$$reportNull$$$0(2);
      }

      if (strings == null) {
         $$$reportNull$$$0(3);
      }

      if (strings.length == 2) {
         return List.of("start", "finish", "go", "rt", "history");
      } else if (strings.length == 1) {
         List<String> list = new ArrayList();

         for(Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().startsWith(strings[0])) {
               list.add(p.getName());
            }

            if (strings[0].isEmpty()) {
               list.add(p.getName());
            }
         }

         return list;
      } else if (strings[1].equals("start") && strings.length == 3) {
         return commandSender.hasPermission("revise.anydesk") ? List.of("discord", "anydesk") : List.of("discord");
      } else {
         return null;
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

      var10001[1] = "dev/qbit/qrevise/command/tabc/ReviseTab";
      var10001[2] = "onTabComplete";
      throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
   }
}
