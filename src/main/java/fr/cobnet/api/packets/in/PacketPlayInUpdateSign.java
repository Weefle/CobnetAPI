package fr.cobnet.api.packets.in;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInUpdateSign extends ReceivedPacket {

    private Gson gson;

    public PacketPlayInUpdateSign(Packet<?> packet, Player player) {
        super(packet, player);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BaseComponent.class, new ComponentSerializer());
        builder.registerTypeAdapter(IChatBaseComponent.class, new IChatBaseComponent.ChatSerializer());
        this.gson = builder.create();
    }

    private BaseComponent cast(IChatBaseComponent comp) {
        return gson.fromJson(gson.toJson(comp), BaseComponent.class);
    }

    public BaseComponent[] getLines() {
        IChatBaseComponent[] packet = reflection().get("b");
        BaseComponent[] ret = new BaseComponent[packet.length];
        for(int i = 0; i < packet.length; i++) {
            ret[i] = cast(packet[i]);
        }
        return ret;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.UPDATE_SIGN;
    }

}
