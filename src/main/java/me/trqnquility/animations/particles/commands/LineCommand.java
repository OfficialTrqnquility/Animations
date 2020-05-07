package me.trqnquility.animations.particles.commands;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Location point1 = player.getEyeLocation().clone();
        Location point2 = player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize().multiply(10));
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(0.1);
        double covered = 0;
        while(covered <= distance) {
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, false, (float) p1.getX(), (float) p1.getY(), (float) p1.getZ(), 0, 0, 0, 0, 1);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            covered += 0.1;
            p1.add(vector);
        }
        return false;
    }
}
