package justmasashii.project.enchanticmk.enchantment;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AutoSmeltingEnchantment extends Enchantment implements Listener {


    public AutoSmeltingEnchantment() {
        super(NamespacedKey.minecraft("auto_smelting"));
    }

    public AutoSmeltingEnchantment(int id) {
        super(NamespacedKey.minecraft("auto_smelting"));
    }

    public static void register() {
        try {
            Enchantment.registerEnchantment(new AutoSmeltingEnchantment(101)); // Gán id tùy ý ở đây
        } catch (IllegalArgumentException ignored) {
            // Enchantment with this key is already registered, ignore
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isDropItems()) return;
        ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
        if (tool == null || !tool.hasItemMeta() || !tool.getItemMeta().hasEnchant(this)) return;

        if (e.getBlock().getType() == Material.IRON_ORE) {
            e.setDropItems(false);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
        }
    }

    @Override
    public String getName() {
        return "Auto Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }
}
