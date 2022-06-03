package cz.teez0.main.Files;

import cz.teez0.main.main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MobEggs {
   private static File customConfigFile;
   private static FileConfiguration customConfig;

   public static FileConfiguration getCustomConfig() {
      return customConfig;
   }

   public static void save() throws IOException {
      customConfig.save(customConfigFile);
   }

   public static void createCustomConfig() {
      customConfigFile = new File(((main)main.getPlugin(main.class)).getDataFolder(), "mob_eggs.yml");
      if (!customConfigFile.exists()) {
         customConfigFile.getParentFile().mkdirs();
         ((main)main.getPlugin(main.class)).saveResource("mob_eggs.yml", false);
      }

      customConfig = new YamlConfiguration();

      try {
         customConfig.load(customConfigFile);
      } catch (InvalidConfigurationException | IOException var1) {
         var1.printStackTrace();
      }

   }
}
