package me.trqnquility.animations.armorstands.commands;

import me.trqnquility.animations.Animations;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
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
import org.bukkit.util.Vector;

public class SpinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;


        Location spawnLoc = player.getLocation().add(player.getEyeLocation().getDirection().normalize().multiply(5));
        spawnLoc.setY(player.getLocation().getY() + 1);

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(spawnLoc, EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setHelmet(new ItemStack(Material.DIRT));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Animations.getInstance(), new BukkitRunnable() {
            int counter = 0;
            @Override
            public void run() {
                if (counter > 100) {
                    if (stand.isValid())
                            stand.remove();
                    this.cancel();
                }
                double angle = 2 * Math.PI * counter / 100;
                Location point = spawnLoc.clone().add(2d * Math.sin(angle), 0.0d, 2d * Math.cos(angle));

                stand.teleport(point);

                counter++;
            }
        }, 0l, 1l);

        return false;
    }

    public static EulerAngle genVec(Location a, Location b) {
        double dX = a.getX() - b.getX();
        double dY = a.getY() - b.getY();
        double dZ = a.getZ() - b.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        Vector vector = new Vector(x, z, y);

        return new EulerAngle(vector.getX(), vector.getY(), vector.getZ());
    }
}
