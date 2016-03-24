package fr.cobnet.api.packets;

import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutEntityLook;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutRelEntityMove;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Créé par Creart le 15/02/2016.
 */
public enum OutPacketType {

    ABILITIES("Abilities", fr.cobnet.api.packets.out.PacketPlayOutAbilities.class, PacketPlayOutAbilities.class),
    ANIMATION("Animation", fr.cobnet.api.packets.out.PacketPlayOutAnimation.class, PacketPlayOutAnimation.class),
    ATTACH_ENTITY("AttachEntity", fr.cobnet.api.packets.out.PacketPlayOutAttachEntity.class, PacketPlayOutAttachEntity.class),
    BED("Bed", fr.cobnet.api.packets.out.PacketPlayOutBed.class, PacketPlayOutBed.class),
    BLOCK_ACTION("BlockAction", fr.cobnet.api.packets.out.PacketPlayOutBlockAction.class, PacketPlayOutBlockAction.class),
    BLOCK_BREAK_ANIMATION("BlockBreakAnimation", fr.cobnet.api.packets.out.PacketPlayOutBlockBreakAnimation.class, PacketPlayOutBlockBreakAnimation.class),
    BLOCK_CHANGE("BlockChange", fr.cobnet.api.packets.out.PacketPlayOutBlockChange.class, PacketPlayOutBlockChange.class),
    CAMERA("Camera", fr.cobnet.api.packets.out.PacketPlayOutCamera.class, PacketPlayOutCamera.class),
    CHAT("Chat", fr.cobnet.api.packets.out.PacketPlayOutChat.class, PacketPlayOutChat.class),
    CLOSE_WINDOW("CloseWindow", fr.cobnet.api.packets.out.PacketPlayOutCloseWindow.class, PacketPlayOutCloseWindow.class),
    COLLECT("Collect", fr.cobnet.api.packets.out.PacketPlayOutCollect.class, PacketPlayOutCollect.class),
    COMBAT_EVENT("CombatEvent", fr.cobnet.api.packets.out.PacketPlayOutCombatEvent.class, PacketPlayOutCombatEvent.class),
    CUSTOM_PAYLOAD("CustomPayload", fr.cobnet.api.packets.out.PacketPlayOutCustomPayload.class, PacketPlayOutCustomPayload.class),
    ENTITY("Entity", fr.cobnet.api.packets.out.PacketPlayOutEntity.class, PacketPlayOutEntity.class),
    ENTITY_DESTROY("EntityDestroy", fr.cobnet.api.packets.out.PacketPlayOutEntityDestroy.class, PacketPlayOutEntityDestroy.class),
    ENTITY_EFFECT("EntityEffect", fr.cobnet.api.packets.out.PacketPlayOutEntityEffect.class, PacketPlayOutEntityEffect.class),
    ENTITY_EQUIPMENT("EntityEquipment", fr.cobnet.api.packets.out.PacketPlayOutEntityEquipment.class, PacketPlayOutEntityEquipment.class),
    ENTITY_HEAD_ROTATION("EntityHeadRotation", fr.cobnet.api.packets.out.PacketPlayOutEntityHeadRotation.class, PacketPlayOutEntityHeadRotation.class),
    ENTITY_LOOK("EntityLook", fr.cobnet.api.packets.out.PacketPlayOutEntityLook.class, PacketPlayOutEntityLook.class),
    ENTITY_METADATA("EntityMetadata", fr.cobnet.api.packets.out.PacketPlayOutEntityMetadata.class, PacketPlayOutEntityMetadata.class),
    ENTITY_STATUS("EntityStatus", fr.cobnet.api.packets.out.PacketPlayOutEntityStatus.class, PacketPlayOutEntityStatus.class),
    ENTITY_TELEPORT("EntityTeleport", fr.cobnet.api.packets.out.PacketPlayOutEntityTeleport.class, PacketPlayOutEntityTeleport.class),
    ENTITY_VELOCITY("EntityVelocity", fr.cobnet.api.packets.out.PacketPlayOutEntityVelocity.class, PacketPlayOutEntityVelocity.class),
    EXPERIENCE("Experience", fr.cobnet.api.packets.out.PacketPlayOutExperience.class, PacketPlayOutExperience.class),
    EXPLOSION("Explosion", fr.cobnet.api.packets.out.PacketPlayOutExplosion.class, PacketPlayOutExplosion.class),
    GAME_STATE_CHANGE("GameStateChange", fr.cobnet.api.packets.out.PacketPlayOutGameStateChange.class, PacketPlayOutGameStateChange.class),
    HELD_ITEM_SLOT("HeldItemSlot", fr.cobnet.api.packets.out.PacketPlayOutHeldItemSlot.class, PacketPlayOutHeldItemSlot.class),
    KEEP_ALIVE("KeepAlive", fr.cobnet.api.packets.out.PacketPlayOutKeepAlive.class, PacketPlayOutKeepAlive.class),
    KICK_DISCONNECT("KickDisconnect", fr.cobnet.api.packets.out.PacketPlayOutKickDisconnect.class, PacketPlayOutKickDisconnect.class),
    LOGIN("Login", fr.cobnet.api.packets.out.PacketPlayOutLogin.class, PacketPlayOutLogin.class),
    MAP("Map", fr.cobnet.api.packets.out.PacketPlayOutMap.class, PacketPlayOutMap.class),
    MAP_CHUNK("MapChunk", fr.cobnet.api.packets.out.PacketPlayOutMapChunk.class, PacketPlayOutMapChunk.class),
    MAP_CHUNK_BULK("MapChunkBulk", fr.cobnet.api.packets.out.PacketPlayOutMapChunkBulk.class, PacketPlayOutMapChunkBulk.class),
    MULTI_BLOCK_CHANGE("MultiBlockChange", fr.cobnet.api.packets.out.PacketPlayOutMultiBlockChange.class, PacketPlayOutMultiBlockChange.class),
    NAMED_ENTITY_SPAWN("NamedEntitySpawn", fr.cobnet.api.packets.out.PacketPlayOutNamedEntitySpawn.class, PacketPlayOutNamedEntitySpawn.class),
    NAMED_SOUND_EFFECT("NamedSoundEffect", fr.cobnet.api.packets.out.PacketPlayOutNamedSoundEffect.class, PacketPlayOutNamedSoundEffect.class),
    OPEN_SIGN_EDITOR("OpenSignEditor", fr.cobnet.api.packets.out.PacketPlayOutOpenSignEditor.class, PacketPlayOutOpenSignEditor.class),
    OPEN_WINDOW("OpenWindow", fr.cobnet.api.packets.out.PacketPlayOutOpenWindow.class, PacketPlayOutOpenWindow.class),
    PLAYER_INFO("PlayerInfo", fr.cobnet.api.packets.out.PacketPlayOutPlayerInfo.class, PacketPlayOutPlayerInfo.class),
    PLAYER_LIST_HEADER_FOOTER("PlayerListHeaderFooter", fr.cobnet.api.packets.out.PacketPlayOutPlayerListHeaderFooter.class, PacketPlayOutPlayerListHeaderFooter.class),
    POSITION("Position", fr.cobnet.api.packets.out.PacketPlayOutPosition.class, PacketPlayOutPosition.class),
    REL_ENTITY_MOVE("RelEntityMove", fr.cobnet.api.packets.out.PacketPlayOutRelEntityMove.class, PacketPlayOutRelEntityMove.class),
    REL_ENTITY_MOVE_LOOK("RelEntityMoveLook", fr.cobnet.api.packets.out.PacketPlayOutRelEntityMoveLook.class, PacketPlayOutRelEntityMoveLook.class),
    REMOVE_ENTITY_EFFECT("RemoveEntityEffect", fr.cobnet.api.packets.out.PacketPlayOutRemoveEntityEffect.class, PacketPlayOutRemoveEntityEffect.class),
    RESOURCE_PACK_SEND("ResourcePackSend", fr.cobnet.api.packets.out.PacketPlayOutResourcePackSend.class, PacketPlayOutResourcePackSend.class),
    RESPAWN("Respawn", fr.cobnet.api.packets.out.PacketPlayOutRespawn.class, PacketPlayOutRespawn.class),
    SCOREBOARD_DISPLAY_OBJECTIVE("ScoreboardDisplayObjective", fr.cobnet.api.packets.out.PacketPlayOutScoreboardDisplayObjective.class, PacketPlayOutScoreboardDisplayObjective.class),
    SCOREBOARD_OBJECTIVE("ScoreboardObjective", fr.cobnet.api.packets.out.PacketPlayOutScoreboardObjective.class, PacketPlayOutScoreboardObjective.class),
    SCOREBOARD_SCORE("ScoreboardScore", fr.cobnet.api.packets.out.PacketPlayOutScoreboardScore.class, PacketPlayOutScoreboardScore.class),
    SCOREBOARD_TEAM("ScoreboardTeam", fr.cobnet.api.packets.out.PacketPlayOutScoreboardTeam.class, PacketPlayOutScoreboardTeam.class),
    SERVER_DIFFICULTY("ServerDifficulty", fr.cobnet.api.packets.out.PacketPlayOutServerDifficulty.class, PacketPlayOutServerDifficulty.class),
    SET_COMPRESSION("SetCompression", fr.cobnet.api.packets.out.PacketPlayOutSetCompression.class, PacketPlayOutSetCompression.class),
    SET_SLOT("SetSlot", fr.cobnet.api.packets.out.PacketPlayOutSetSlot.class, PacketPlayOutSetSlot.class),
    SPAWN_ENTITY("SpawnEntity", fr.cobnet.api.packets.out.PacketPlayOutSpawnEntity.class, PacketPlayOutSpawnEntity.class),
    SPAWN_ENTITY_EXPERIENCE_ORB("SpawnEntityExperienceOrb", fr.cobnet.api.packets.out.PacketPlayOutSpawnEntityExperienceOrb.class, PacketPlayOutSpawnEntityExperienceOrb.class),
    SPAWN_ENTITY_LIVING("SpawnEntityLiving", fr.cobnet.api.packets.out.PacketPlayOutSpawnEntityLiving.class, PacketPlayOutSpawnEntityLiving.class),
    SPAWN_ENTITY_PAINTING("SpawnEntityPainting", fr.cobnet.api.packets.out.PacketPlayOutSpawnEntityPainting.class, PacketPlayOutSpawnEntityPainting.class),
    SPAWN_ENTITY_WEATHER("SpawnEntityWeather", fr.cobnet.api.packets.out.PacketPlayOutSpawnEntityWeather.class, PacketPlayOutSpawnEntityWeather.class),
    SPAWN_POSITION("SpawnPosition", fr.cobnet.api.packets.out.PacketPlayOutSpawnPosition.class, PacketPlayOutSpawnPosition.class),
    STATISTIC("Statistic", fr.cobnet.api.packets.out.PacketPlayOutStatistic.class, PacketPlayOutStatistic.class),
    TAB_COMPLETE("TabComplete", fr.cobnet.api.packets.out.PacketPlayOutTabComplete.class, PacketPlayOutTabComplete.class),
    TILE_ENTITY_DATA("TileEntityData", fr.cobnet.api.packets.out.PacketPlayOutTileEntityData.class, PacketPlayOutTileEntityData.class),
    TITLE("Title", fr.cobnet.api.packets.out.PacketPlayOutTitle.class, PacketPlayOutTitle.class),
    TRANSACTION("Transaction", fr.cobnet.api.packets.out.PacketPlayOutTransaction.class, PacketPlayOutTransaction.class),
    UPDATE_ATTRIBUTES("UpdateAttributes", fr.cobnet.api.packets.out.PacketPlayOutUpdateAttributes.class, PacketPlayOutUpdateAttributes.class),
    UPDATE_ENTITY_NBT("UpdateEntityNBT", fr.cobnet.api.packets.out.PacketPlayOutUpdateEntityNBT.class, PacketPlayOutUpdateEntityNBT.class),
    UPDATE_HEALTH("UpdateHealth", fr.cobnet.api.packets.out.PacketPlayOutUpdateHealth.class, PacketPlayOutUpdateHealth.class),
    UPDATE_SIGN("UpdateSign", fr.cobnet.api.packets.out.PacketPlayOutUpdateSign.class, PacketPlayOutUpdateSign.class),
    UPDATE_TIME("UpdateTime", fr.cobnet.api.packets.out.PacketPlayOutUpdateTime.class, PacketPlayOutUpdateTime.class),
    WINDOW_DATA("WindowData", fr.cobnet.api.packets.out.PacketPlayOutWindowData.class, PacketPlayOutWindowData.class),
    WINDOW_ITEMS("WindowItems", fr.cobnet.api.packets.out.PacketPlayOutWindowItems.class, PacketPlayOutWindowItems.class),
    WORLD_BORDER("WorldBorder", fr.cobnet.api.packets.out.PacketPlayOutWorldBorder.class, PacketPlayOutWorldBorder.class),
    WORLD_EVENT("WorldEvent", fr.cobnet.api.packets.out.PacketPlayOutWorldEvent.class, PacketPlayOutWorldEvent.class),
    WORLD_PARTICLES("WorldParticles", fr.cobnet.api.packets.out.PacketPlayOutWorldParticles.class, PacketPlayOutWorldParticles.class);

    private final String rawName;
    private final Class<? extends Packet<?>> clazz;
    private final Class<? extends SentPacket> softPacket;
    private Constructor<? extends SentPacket> constructor;
    private Way way;

    OutPacketType(String rawName, Class<? extends SentPacket> softPacket, Class<? extends Packet<?>> clazz) {
        this.rawName = rawName;
        this.clazz = clazz;
        this.softPacket = softPacket;
        this.way = Way.OUT;

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

    public Class<? extends SentPacket> getSoftPacket() {
        return softPacket;
    }

    public Constructor<? extends SentPacket> getConstructor() {
        return constructor;
    }

    public boolean isIncoming() {
        return false;
    }

    public boolean isOutgoing() {
        return true;
    }
}
