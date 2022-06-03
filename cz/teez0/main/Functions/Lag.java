package cz.teez0.main.Functions;

import java.text.DecimalFormat;

public class Lag implements Runnable {
   public static int TICK_COUNT = 0;
   public static long[] TICKS = new long[600];
   public static long LAST_TICK = 0L;

   public static String getTPS() {
      double TPS = getTPS(100);
      String stringXP = (new DecimalFormat("#.00")).format(TPS);
      return stringXP;
   }

   public static double getTPS(int ticks) {
      if (TICK_COUNT < ticks) {
         return 20.0D;
      } else {
         int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
         if (target != -1) {
            long elapsed = System.currentTimeMillis() - TICKS[target];
            return (double)ticks / ((double)elapsed / 1000.0D);
         } else {
            return 0.0D;
         }
      }
   }

   public static long getElapsed(int tickID) {
      if (TICK_COUNT - tickID >= TICKS.length) {
      }

      long time = TICKS[tickID % TICKS.length];
      return System.currentTimeMillis() - time;
   }

   public void run() {
      TICKS[TICK_COUNT % TICKS.length] = System.currentTimeMillis();
      ++TICK_COUNT;
   }
}
