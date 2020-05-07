package me.trqnquility.animations;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PacketCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        WorldServer s = ((CraftWorld)player.getWorld()).getHandle();
        EntityArmorStand stand = new EntityArmorStand(s);

        stand.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 0, 0);
        stand.setCustomNameVisible(true);
        stand.setGravity(true);
        stand.setCustomName("TEST");
        stand.setInvisible(false);
        stand.setEquipment(1, CraftItemStack.asNMSCopy(new ItemStack(Material.DIRT)));
        stand.setEquipment(2, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
        stand.setEquipment(3, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
        stand.setEquipment(4, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
        PacketPlayOutEntityEquipment EquipP = new PacketPlayOutEntityEquipment(stand.getId(), 4, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_BLOCK)));
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(EquipP);




        return false;
    }
}
