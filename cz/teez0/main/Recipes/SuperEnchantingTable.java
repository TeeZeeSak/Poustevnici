package cz.teez0.main.Recipes;

import cz.teez0.main.main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class SuperEnchantingTable {
   public static void Initialize() {
      ItemStack table = new ItemStack(Material.ENCHANTING_TABLE, 1);
      NamespacedKey key = new NamespacedKey(main.getPlugin(main.class), "super_enchantingtable");
      ItemMeta itemMeta = table.getItemMeta();
      itemMeta.setDisplayName("§l§cSuper Enchanting Table");
      List<String> lore = new ArrayList();
      lore.add("§dUmoznuje ziskat");
      lore.add("§dvyssi urovne ocarovani");
      itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
      itemMeta.setLore(lore);
      table.setItemMeta(itemMeta);
      ShapedRecipe CustomEnchantingTable = new ShapedRecipe(key, table);
      CustomEnchantingTable.shape(new String[]{"*%*", "*B*", "***"});
      CustomEnchantingTable.setIngredient('*', Material.BOOKSHELF);
      CustomEnchantingTable.setIngredient('B', Material.ENCHANTING_TABLE);
      CustomEnchantingTable.setIngredient('%', Material.BEACON);
      ((main)main.getPlugin(main.class)).getServer().addRecipe(CustomEnchantingTable);
   }
}
