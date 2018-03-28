package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInAbilities extends ReceivedPacket {

    private float flySpeed, walkSpeed;

    public PacketPlayInAbilities(Packet<?> packet, Player player) {
        super(packet, player);

        this.flySpeed = -1;
        this.walkSpeed = -1;
    }

    public PacketPlayInAbilities setWalkSpeed(float value) {
        reflection().set("e", value);
        return this;
    }

    public PacketPlayInAbilities setFlySpeed(float value) {
        reflection().set("f", value);
        return this;
    }

    public float getWalkSpeed() {
        return walkSpeed == -1 ? walkSpeed = reflection().get("f") : walkSpeed;
    }

    public float getFlySpeed() {
        return flySpeed == -1 ? flySpeed = reflection().get("e") : flySpeed;
    }

    public boolean isAbility(Ability ability) {
        return (boolean) reflection().get(ability.fieldName);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.ABILITIES;
    }

    public enum Ability {
        INVULNERABLE("a"),
        FLYING("b"),
        CAN_FLY("c"),
        CAN_INSTANT_BUILD("d");

        private final String fieldName;

        Ability(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

}
