package fr.cobnet.api.entity;

import com.google.common.annotations.Beta;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.AttributeModifier;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

@SuppressWarnings("unused")
@Beta
public class AttributesManager {

	private static final UUID MAX_HEALTH_UUID = UUID
			.fromString("f8b0a945-2d6a-4bdb-9a6f-59c285bf1e5d");
	private static final UUID FOLLOW_RANGE_UUID = UUID
			.fromString("1737400d-3c18-41ba-8314-49a158481e1e");
	private static final UUID KNOCKBACK_RESISTANCE_UUID = UUID
			.fromString("8742c557-fcd5-4079-a462-b58db99b0f2c");
	private static final UUID MOVEMENT_SPEED_UUID = UUID
			.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	private static final UUID ATTACK_DAMAGE_UUID = UUID
			.fromString("7bbe3bb1-079d-4150-ac6f-669e71550776");
	
	/**
	 * Permet de changer la vitesse de déplacement d'une entité
	 * @param nmsEntity
	 * @param value
	 */
	public static void setSpeed(EntityLiving nmsEntity, double value) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		attributes.setValue(value);
	}

	/**
	 * Permet de changer la vitesse de déplacement d'une entité
	 * @param livingEntity
	 * @param value
	 */
	public void setSpeed(LivingEntity livingEntity, double value) {
		CraftLivingEntity entity = (CraftLivingEntity) livingEntity;
		setSpeed(entity, value);
	}
	
	/**
	 * Permet de changer le multiplicateur de vitesse de déplacement d'une entité
	 * @param living
	 * @param multiplier
	 */
	public static void setSpeedMultiplier(EntityLiving living, double multiplier) {
		AttributeInstance attributes = living.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(MOVEMENT_SPEED_UUID, "speed multiplier", multiplier, 1);
		attributes.b(modifier);
		attributes.a(modifier);
	}
	
	/**
	 * Permet de reset le multiplicateur de vitesse de déplacement d'une entité
	 * @param living
	 */
	public static void resetSpeedMultiplier(EntityLiving living) {
		AttributeInstance attributes = living.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(MOVEMENT_SPEED_UUID, "speed multiplier", 1.0d, 1);
		attributes.b(modifier);
	}
	
	/**
	 * Permet de changer la distance de suivi (aggro)
	 * @param nmsEntity
	 * @param value
	 */
	public static void setFollowRange(EntityLiving nmsEntity, double value) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		attributes.setValue(value);
	}

	/**
	 * Permet de changer la distance de suivi (aggro)
	 * @param value
	 */
	public static void setFollowRangeMultiplier(EntityLiving nmsEntity, double value) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(FOLLOW_RANGE_UUID, "range multiplier", value, 1);
		attributes.b(modifier);
		attributes.a(modifier);
	}

	/**
	 * Permet de reset la distance de suivi (multiplicateur - aggro)
	 * @param nmsEntity
	 */
	public static void clearFollowRangeMultiplier(EntityLiving nmsEntity) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(FOLLOW_RANGE_UUID, "range multiplier", 1.0d, 1);
		attributes.b(modifier);
	}

	/**
	 * Permet de modifier la knockback resistance
	 * @param nmsEntity
	 * @param value
	 */
	public static void setKnockbackResistance(EntityLiving nmsEntity, double value) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		attributes.setValue(value);
	}

	/**
	 * Permet de modifier la knockback resistance (multiplicateur)
	 * @param nmsEntity
	 * @param multiplier
	 */
	public static void setKnockbackResistanceMultiplier(EntityLiving nmsEntity, double multiplier) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, "resistance multiplier", multiplier, 1);
		attributes.b(modifier);
		attributes.a(modifier);
	}

	/**
	 * Permet de reset la knockback resistance (multiplicateur)
	 * @param nmsEntity
	 */
	public static void clearKnockbackResistanceMultiplier(EntityLiving nmsEntity) {
		AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
		AttributeModifier modifier = new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, "resistance multiplier", 1.0d, 1);
		attributes.b(modifier);
	}
	
}