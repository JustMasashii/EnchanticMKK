package justmasashii.project.enchanticmk;

import justmasashii.project.enchanticmk.enchantment.AutoSmeltingEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchanticMK extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        registerEnchantment(new AutoSmeltingEnchantment());

    }

    private void registerEnchantment(AutoSmeltingEnchantment enchantment) {
        try {
            Bukkit.getScheduler().runTask(this, () -> {
                try {
                    enchantment.register();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("enchantmine")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command!");
                return true;
            }

            Player player = (Player) sender;
            if (args.length < 2) {
                player.sendMessage("Usage: /enchantmine <enchantment> <level>");
                return true;
            }

            NamespacedKey autoSmeltingKey = new NamespacedKey(this, "auto_smelting");
            Enchantment enchantment = Enchantment.getByKey(autoSmeltingKey);
            if (enchantment == null || !(enchantment instanceof AutoSmeltingEnchantment)) {
                player.sendMessage("Invalid or unregistered enchantment!");
                return true;
            }

            int level;
            try {
                level = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Invalid level!");
                return true;
            }

            if (level < 1 || level > enchantment.getMaxLevel()) {
                player.sendMessage("Invalid level! Must be between 1 and " + enchantment.getMaxLevel());
                return true;
            }

            // Apply the enchantment to the item in hand
            player.getInventory().getItemInMainHand().addUnsafeEnchantment(enchantment, level);
            player.sendMessage("Enchantment applied!");
            return true;
        }
        return false;
    }
}
