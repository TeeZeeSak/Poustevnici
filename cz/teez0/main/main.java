package cz.teez0.main;

import cz.teez0.main.Commands.CommandBan;
import cz.teez0.main.Commands.CommandHelp;
import cz.teez0.main.Commands.CommandHome;
import cz.teez0.main.Commands.CommandKick;
import cz.teez0.main.Commands.CommandSetHome;
import cz.teez0.main.Commands.CommandSetSpawn;
import cz.teez0.main.Commands.CommandSpawn;
import cz.teez0.main.Commands.CommandTpAccept;
import cz.teez0.main.Commands.CommandTpDeny;
import cz.teez0.main.Commands.CommandTpa;
import cz.teez0.main.Commands.CommandUnban;
import cz.teez0.main.Events.InventoryOpen;
import cz.teez0.main.Events.PlayerBreak;
import cz.teez0.main.Events.PlayerChat;
import cz.teez0.main.Events.PlayerConnect;
import cz.teez0.main.Events.PlayerDamageByEntityEvent;
import cz.teez0.main.Events.PlayerDeath;
import cz.teez0.main.Events.PlayerDisconnect;
import cz.teez0.main.Events.PlayerEnterBed;
import cz.teez0.main.Events.PlayerInteract;
import cz.teez0.main.Events.PlayerItemDamage;
import cz.teez0.main.Events.PlayerKillEntity;
import cz.teez0.main.Events.PlayerPlace;
import cz.teez0.main.Files.Classes;
import cz.teez0.main.Files.CustomBlocks;
import cz.teez0.main.Files.MobEggs;
import cz.teez0.main.Files.PlayerBlocks;
import cz.teez0.main.Files.Players;
import cz.teez0.main.Functions.Hrac;
import cz.teez0.main.Functions.Lag;
import cz.teez0.main.Recipes.SuperEnchantingTable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
   public static String JoinMessage;
   public static String LeaveMessage;
   public static String DefaultPrefix;
   public static HashMap<Player, Integer> actionBar = new HashMap();
   public static HashMap<Player, Player> tpaRequests = new HashMap();
   public static boolean commitingRestart = false;

   public void onEnable() {
      Iterator i$ = this.getServer().getOnlinePlayers().iterator();

      while(i$.hasNext()) {
         Player p = (Player)i$.next();
         Hrac.addUserToActionBarTask(p);
      }

      this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
      this.getCommand("help").setExecutor(new CommandHelp());
      this.getCommand("ban").setExecutor(new CommandBan());
      this.getCommand("unban").setExecutor(new CommandUnban());
      this.getCommand("kick").setExecutor(new CommandKick());
      this.getCommand("spawn").setExecutor(new CommandSpawn());
      this.getCommand("setspawn").setExecutor(new CommandSetSpawn());
      this.getCommand("tpa").setExecutor(new CommandTpa());
      this.getCommand("tpaccept").setExecutor(new CommandTpAccept());
      this.getCommand("tpdeny").setExecutor(new CommandTpDeny());
      this.getCommand("sethome").setExecutor(new CommandSetHome());
      this.getCommand("home").setExecutor(new CommandHome());
      this.getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerConnect(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDisconnect(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerPlace(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerBreak(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerKillEntity(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDamageByEntityEvent(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerEnterBed(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
      SuperEnchantingTable.Initialize();
      CustomBlocks.createCustomConfig();
      Players.createCustomConfig();
      Classes.createCustomConfig();
      PlayerBlocks.createCustomConfig();
      MobEggs.createCustomConfig();
      String timeStamp = (new SimpleDateFormat("HH:mm:ss dd-MM-yyyy")).format(Calendar.getInstance().getTime());
      this.setupConfiguration();
      this.getLogger().info("Core started at " + timeStamp);
   }

   public void setupConfiguration() {
      FileConfiguration config = this.getConfig();
      DefaultPrefix = config.getString("groups.default.prefix");
      JoinMessage = config.getString("configuration.joinMessage");
      LeaveMessage = config.getString("configuration.leaveMessage");
      if (JoinMessage == null) {
         config.set("configuration.joinMessage", "§6§l{player} §r§ahas joined the server!");
         this.saveConfig();
         JoinMessage = config.getString("configuration.joinMessage");
      }

      if (LeaveMessage == null) {
         config.set("configuration.leaveMessage", "§6§l{player} §r§4has disconnected!");
         this.saveConfig();
         LeaveMessage = config.getString("configuration.leaveMessage");
      }

      if (DefaultPrefix == null) {
         config.set("groups.default.prefix", "§7[§fPlayer§7]§r");
         this.saveConfig();
         DefaultPrefix = config.getString("groups.default.prefix");
      }

   }

   public void onDisable() {
   }
}
