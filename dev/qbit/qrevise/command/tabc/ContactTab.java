package dev.qbit.qrevise.command.tabc;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ContactTab implements TabCompleter {
   public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      if (s == null) {
         $$$reportNull$$$0(2);
      }

      if (strings == null) {
         $$$reportNull$$$0(3);
      }

      return strings.length == 1 ? List.of("<сообщение>") : null;
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

      var10001[1] = "dev/qbit/qrevise/command/tabc/ContactTab";
      var10001[2] = "onTabComplete";
      throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
   }
}
