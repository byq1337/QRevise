package dev.qbit.qrevise.revise;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.api.PlayerReviseStartEvent;
import dev.qbit.qrevise.manager.Manager;
import dev.qbit.qrevise.util.Colorize;
import dev.qbit.qrevise.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Revise {
   private Player target;
   private Player admin;
   private ReviseType type;
   private Revise revise;
   private long startTime;
   private String result = "отпущен";
   public int timeRemaining = this.getConfig().getInt("revise-time");
   private Manager manager = new Manager();

   public Revise(Player target, Player admin, ReviseType type) {
      this.target = target;
      this.admin = admin;
      this.type = type;
   }

   public void start(final Revise revise) {
      this.startTime = System.currentTimeMillis();
      PlayerReviseStartEvent event = new PlayerReviseStartEvent(this.target, this.admin, this.type);
      Bukkit.getServer().getPluginManager().callEvent(event);
      this.revise = revise;
      this.manager.removePvP(this.target);
      Location spawnLocation = new Location(Bukkit.getWorld(this.getConfig().getString("spawn-location").split(";")[0]), Double.valueOf(this.getConfig().getString("spawn-location").split(";")[1]), Double.valueOf(this.getConfig().getString("spawn-location").split(";")[2]), Double.valueOf(this.getConfig().getString("spawn-location").split(";")[3]), (float)Integer.valueOf(this.getConfig().getString("spawn-location").split(";")[4]), (float)Integer.valueOf(this.getConfig().getString("spawn-location").split(";")[5]));
      this.target.teleport(spawnLocation);
      Main.players.put(this.target, this.admin);
      Main.admins.put(this.admin, this.target);
      this.target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0, false, false));
      final BossBar bossBar = Bukkit.createBossBar("", BarColor.valueOf(this.getConfig().getString("bossbar.color")), BarStyle.valueOf(this.getConfig().getString("bossbar.style")), new BarFlag[0]);
      Main.bossbars.put(this.revise, bossBar);
      (new BukkitRunnable() {
         public void run() {
            if (Revise.this.manager.inCheck(Revise.this.target)) {
               for(String message : Revise.this.getConfig().getStringList("revise-messages." + Revise.this.type.toString().toLowerCase())) {
                  Revise.this.target.sendMessage(Colorize.s(message));
               }
            } else {
               this.cancel();
            }

         }
      }).runTaskTimer(Main.ins, 0L, (long)this.getConfig().getInt("revise-messages.delay"));
      (new BukkitRunnable() {
         public void run() {
            if (Revise.this.target == null) {
               this.cancel();
            }

            if (!Revise.this.manager.inCheck(Revise.this.target)) {
               this.cancel();
            }

            if (Revise.this.timeRemaining > 0) {
               if (Revise.this.timeRemaining < 1337) {
                  try {
                     --Revise.this.timeRemaining;
                     BossBar b = (BossBar)Main.bossbars.get(revise);
                     b.setTitle(Colorize.s(Revise.this.getConfig().getString("bossbar.text").replace("{seconds}", String.valueOf(Revise.this.timeRemaining)).replace("{timer}", Timer.getTimer(Revise.this.timeRemaining))));
                     bossBar.setProgress((double)Revise.this.timeRemaining / (double)Revise.this.getConfig().getInt("revise-time"));
                     b.removeAll();
                     b.addPlayer(Revise.this.target);
                     b.addPlayer(Revise.this.admin);
                     Revise.this.target.sendTitle(Colorize.s(Revise.this.getConfig().getString("title").split(";")[0]), Colorize.s(Revise.this.getConfig().getString("title").split(";")[1]), 10, 20, 10);
                  } catch (Exception var3) {
                  }
               } else {
                  try {
                     BossBar b = (BossBar)Main.bossbars.get(revise);
                     if (b == null) {
                        this.cancel();
                        return;
                     }

                     b.setTitle(Colorize.s(Revise.this.getConfig().getString("bossbar.rt-text")));
                     b.setProgress((double)1.0F);
                     b.removeAll();
                     b.addPlayer(Revise.this.admin);
                  } catch (Exception var2) {
                  }
               }
            } else {
               Revise.this.setResult("вышло время");
               Revise.this.manager.finish(Revise.this.target);
               Revise.this.admin.chat(Main.ins.getConfig().getString("time-leave.command").replace("{player}", Revise.this.target.getName()).replace("{admin}", Revise.this.admin.getName()));
               Main.ins.getLogger().info(Revise.this.getConfig().getString("time-leave.log").replace("{player}", Revise.this.target.getName()).replace("{admin}", Revise.this.admin.getName()));
               this.cancel();
            }

         }
      }).runTaskTimer(Main.ins, 0L, 20L);
   }

   private FileConfiguration getConfig() {
      return Main.ins.getConfig();
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

   public long getStartTime() {
      return this.startTime;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult(String set) {
      this.result = set;
   }
}
