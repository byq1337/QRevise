package dev.qbit.qrevise;

import dev.qbit.qrevise.command.executor.ContactCommand;
import dev.qbit.qrevise.command.executor.ReviseCommand;
import dev.qbit.qrevise.command.tabc.ContactTab;
import dev.qbit.qrevise.command.tabc.ReviseTab;
import dev.qbit.qrevise.listener.onBlocked;
import dev.qbit.qrevise.listener.onLeave;
import dev.qbit.qrevise.revise.Revise;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
   public static Main ins;
   public static Map<Player, Player> players = new HashMap();
   public static Map<Player, Player> admins = new HashMap();
   public static Map<Player, Revise> revises = new HashMap();
   public static Map<Revise, BossBar> bossbars = new HashMap();

   public void onEnable() {
      this.saveDefaultConfig();
      ins = this;
      this.getCommand("revise").setExecutor(new ReviseCommand());
      this.getCommand("revise").setTabCompleter(new ReviseTab());
      this.getCommand("contact").setExecutor(new ContactCommand());
      this.getCommand("contact").setTabCompleter(new ContactTab());
      Bukkit.getPluginManager().registerEvents(new onBlocked(), this);
      Bukkit.getPluginManager().registerEvents(new onLeave(), this);
   }

   public void onDisable() {
   }
}
