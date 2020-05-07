package me.trqnquility.animations.particles.commands;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TunnelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Location point1 = player.getEyeLocation().clone();
        Location point2 = player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize().multiply(20));
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(0.1);
        double covered = 0;
        while(covered <= distance) {
            for (int i = 0; i < 30; i++) {
                double angle = 2 * Math.PI * i / 30;
                Location point = p1.toLocation(player.getWorld()).clone().add(2d * Math.sin(angle), 0.0d, 2d * Math.cos(angle));
                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true, (float) point.getX(), (float) point.getY(), (float) point.getZ(), 0, 0, 0, 0, 1);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
            covered += 0.1;
            p1.add(vector);
        }

        return false;
    }
}
