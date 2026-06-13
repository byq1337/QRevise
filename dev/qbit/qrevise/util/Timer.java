package dev.qbit.qrevise.util;

public class Timer {
   public static String getTimer(int seconds) {
      int minutes = seconds / 60;
      int remainingSeconds = seconds % 60;
      return minutes == 0 ? remainingSeconds + " сек" : minutes + " мин " + remainingSeconds + " сек";
   }
}
