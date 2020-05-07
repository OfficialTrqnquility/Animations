package me.trqnquility.animations.armorstands.commands;

import me.trqnquility.animations.Animations;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class RiseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Location spawnLoc = player.getLocation().subtract(0, 1.5, 0).add(player.getEyeLocation().getDirection().normalize().multiply(3));
        spawnLoc.setY(player.getLocation().getY() - 2);

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(spawnLoc, EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setHelmet(new ItemStack(Material.APPLE));
        stand.setHeadPose(new EulerAngle(0, Math.toRadians(-180), 0));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Animations.getInstance(), new BukkitRunnable() {
            int counter = 0;
            @Override
            public void run() {
                if (counter > 30) {
                    if (stand.isValid())
                        stand.remove();;
                    this.cancel();
                }
                stand.teleport(stand.getLocation().add(0, 0.05, 0));

                counter++;
            }
        }, 0l, 1l);




        return false;
    }
}
