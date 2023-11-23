package justmasashii.project.enchanticmk;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JoinListener implements Listener {
    private  NamespacedKey key;
    public  JoinListener(NamespacedKey key) {
        this.key = key;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack pick = new ItemStack(Material.IRON_PICKAXE);
        pick.addUnsafeEnchantment(Enchantment.getByKey(key), 1);
        ItemMeta meta = pick.getItemMeta();
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Auto Smelting + 1"));
        pick.setItemMeta(meta);

        e.getPlayer().getInventory().addItem(pick);




    }
}
