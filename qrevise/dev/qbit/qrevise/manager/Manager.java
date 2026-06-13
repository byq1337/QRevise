package dev.qbit.qrevise.manager;

import dev.qbit.qrevise.Main;
import dev.qbit.qrevise.revise.Revise;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import ru.leymooo.antirelog.Antirelog;
import ru.leymooo.antirelog.manager.PvPManager;

public class Manager {
   public boolean inCheck(Player p) {
      Main var10000 = Main.ins;
      return Main.players.containsKey(p);
   }

   public void addSeconds(Player p, int seconds) {
      ((Revise)Main.revises.get(p)).timeRemaining += seconds;
   }

   public void removeTimer(Player p) {
      ((Revise)Main.revises.get(p)).timeRemaining = 1337;
   }

   public void removePvP(Player p) {
      Antirelog plug = (Antirelog)Bukkit.getPluginManager().getPlugin("Antirelog");
      PvPManager pvpManager = plug.getPvpManager();
      if (pvpManager.isInPvP(p)) {
         pvpManager.stopPvP(p);
      }

   }

   public void finish(Player target) {
      Revise revise = (Revise)Main.revises.get(target);

      for(PotionEffect effect : revise.getTarget().getActivePotionEffects()) {
         revise.getTarget().removePotionEffect(effect.getType());
      }

      try {
         ((BossBar)Main.bossbars.get(revise)).removeAll();
         Main.bossbars.remove(revise);
         Main.revises.remove(revise.getTarget());
         Main.players.remove(revise.getTarget());
         Main.admins.remove(revise.getAdmin());
      } catch (Exception var5) {
      }

   }
}
