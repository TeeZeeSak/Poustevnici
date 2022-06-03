package cz.teez0.main.Files;

import cz.teez0.main.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerBlocks {
   private static File customConfigFile;
   private static FileConfiguration customConfig;

   public static FileConfiguration getCustomConfig() {
      return customConfig;
   }

   public static void save() {
      if (customConfigFile.length() / 1024L >= 500L && !main.commitingRestart) {
         main.commitingRestart = true;
         ((main)main.getPlugin(main.class)).getLogger().info("File size: " + customConfigFile.length());
         ((main)main.getPlugin(main.class)).getServer().broadcastMessage("§9§l[§r§cRESTART§9§l] §r§aServer will restart in 10 minutes!");
         Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), new Runnable() {
            public void run() {
               PrintWriter writer = null;

               try {
                  writer = new PrintWriter(PlayerBlocks.customConfigFile);
               } catch (FileNotFoundException var4) {
                  var4.printStackTrace();
               }

               writer.print("");
               writer.close();

               try {
                  PlayerBlocks.customConfig.save(PlayerBlocks.customConfigFile);
               } catch (IOException var3) {
                  var3.printStackTrace();
               }

               ((main)main.getPlugin(main.class)).getLogger().info("Clearing player-placed-blocks.yml");
               ((main)main.getPlugin(main.class)).getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
            }
         }, 12000L);
      } else {
         try {
            customConfig.save(customConfigFile);
         } catch (IOException var1) {
            var1.printStackTrace();
         }
      }

   }

   public static void createCustomConfig() {
      customConfigFile = new File(((main)main.getPlugin(main.class)).getDataFolder(), "player-placed-blocks.yml");
      if (!customConfigFile.exists()) {
         customConfigFile.getParentFile().mkdirs();
         ((main)main.getPlugin(main.class)).saveResource("player-placed-blocks.yml", false);
      }

      customConfig = new YamlConfiguration();

      try {
         customConfig.load(customConfigFile);
      } catch (InvalidConfigurationException | IOException var1) {
         var1.printStackTrace();
      }

   }
}
