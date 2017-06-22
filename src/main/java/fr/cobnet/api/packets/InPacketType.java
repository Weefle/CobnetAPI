package fr.cobnet.api.packets;

import fr.cobnet.api.packets.in.*;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Créé par Creart le 25/01/2016.
 */
public enum InPacketType {

    // IN
    ABILITIES("Abilities", PacketPlayInAbilities.class, net.minecraft.server.v1_8_R3.PacketPlayInAbilities.class),
    ARM_ANIMATION("ArmAnimation", PacketPlayInArmAnimation.class, net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation.class),
    BLOCK_DIG("BlockDig", PacketPlayInBlockDig.class, net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.class),
    BLOCK_PLACE("BlockPlace", PacketPlayInBlockPlace.class, net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace.class),
    CHAT("Chat", PacketPlayInChat.class, net.minecraft.server.v1_8_R3.PacketPlayInChat.class),
    CLIENT_COMMAND("ClientCommand", PacketPlayInClientCommand.class, net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.class),
    CLOSE_WINDOW("CloseWindow", PacketPlayInCloseWindow.class, net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow.class),
    CUSTOM_PAYLOAD("CustomPayload", PacketPlayInCustomPayload.class, net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload.class),
    ENCHANT_ITEM("EnchantItem", PacketPlayInEnchantItem.class, net.minecraft.server.v1_8_R3.PacketPlayInEnchantItem.class),
    ENTITY_ACTION("EntityAction", PacketPlayInEntityAction.class, net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.class),
    FLYING("Flying", PacketPlayInFlying.class, net.minecraft.server.v1_8_R3.PacketPlayInFlying.class),
    HELD_ITEM_SLOT("HeldItemSlot", PacketPlayInHeldItemSlot.class, net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot.class),
    KEEP_ALIVE("KeepAlive", PacketPlayInKeepAlive.class, net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive.class),
    LOOK("Look", PacketPlayInLook.class, net.minecraft.server.v1_8_R3.PacketPlayInFlying.PacketPlayInLook.class),
    POSITION("Position", PacketPlayInPosition.class, net.minecraft.server.v1_8_R3.PacketPlayInFlying.PacketPlayInPosition.class),
    POSITION_LOOK("PositionLook", PacketPlayInPositionLook.class, net.minecraft.server.v1_8_R3.PacketPlayInFlying.PacketPlayInPositionLook.class),
    RESOURCE_PACK_STATUS("ResourcePackStatus", PacketPlayInResourcePackStatus.class, net.minecraft.server.v1_8_R3.PacketPlayInResourcePackStatus.class),
    SET_CREATIVE_SLOT("SetCreativeSlot", PacketPlayInSetCreativeSlot.class, net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot.class),
    SETTINGS("Settings", PacketPlayInSettings.class, net.minecraft.server.v1_8_R3.PacketPlayInSettings.class),
    SPECTATE("Spectate", PacketPlayInSpectate.class, net.minecraft.server.v1_8_R3.PacketPlayInSpectate.class),
    STEER_VEHICLE("SteerVehicle", PacketPlayInSteerVehicle.class, net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle.class),
    TAB_COMPLETE("TabComplete", PacketPlayInTabComplete.class, net.minecraft.server.v1_8_R3.PacketPlayInTabComplete.class),
    TRANSACTION("Transaction", PacketPlayInTransaction.class, net.minecraft.server.v1_8_R3.PacketPlayInTransaction.class),
    UPDATE_SIGN("UpdateSign", PacketPlayInUpdateSign.class, net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign.class),
    USE_ENTITY("UseEntity", PacketPlayInUseEntity.class, net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.class),
    WINDOW_CLICK("WindowClick", PacketPlayInWindowClick.class, net.minecraft.server.v1_8_R3.PacketPlayInWindowClick.class);

    private final String rawName;
    private final Class<? extends Packet<?>> clazz;
    private final Class<? extends ReceivedPacket> softPacket;
    private Constructor<? extends ReceivedPacket> constructor;
    private Way way;

    InPacketType(String rawName, Class<? extends ReceivedPacket> softPacket, Class<? extends Packet<?>> clazz) {
        this.rawName = rawName;
        this.clazz = clazz;
        this.softPacket = softPacket;
        this.setWay(Way.IN);

        try {
            this.constructor = this.softPacket.getConstructor(Packet.class, Player.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRawName() {
        return rawName;
    }

    public Class<? extends Packet<?>> getClazz() {
        return clazz;
    }

    public Class<? extends ReceivedPacket> getSoftPacket() {
        return softPacket;
    }

    public Constructor<? extends ReceivedPacket> getConstructor() {
        return constructor;
    }

    public boolean isIncoming() {
        return true;
    }

    public boolean isOutgoing() {
        return false;
    }

	public Way getWay() {
		return way;
	}

	public void setWay(Way way) {
		this.way = way;
	}

}
