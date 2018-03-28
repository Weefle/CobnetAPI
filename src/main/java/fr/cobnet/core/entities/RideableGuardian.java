package fr.cobnet.core.entities;

import net.minecraft.server.v1_12_R1.*;

import java.lang.reflect.Field;

public class RideableGuardian extends EntityGuardian implements RideableEntity {

	public RideableGuardian(World world) {
		super(world);
	}
	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
    public float g(float sideMot, float forMot) {
        if (this.passengers != null) {
            EntityLiving passenger = (EntityLiving) this.passengers.get(0);
            this.yaw = passenger.yaw;
            this.lastYaw = this.yaw;
            this.pitch = (passenger.pitch * 0.5F);
            setYawPitch(this.yaw, this.pitch);
            this.aN = this.yaw;
            this.aP = this.aN;
         
            Float speedmultiplicator = 3F;//Here you can set the speed
            sideMot = passenger.be * speedmultiplicator;
            forMot = passenger.bf * speedmultiplicator; 
            if(forMot <= 0.0F) {
                    forMot *= 0.25F;// Make backwards slower
            }
             Field jump = null; //Jumping
                try {
                    jump = EntityLiving.class.getDeclaredField("bd");
                } catch (NoSuchFieldException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SecurityException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                jump.setAccessible(true);

                if (jump != null && this.onGround) {     // Wouldn't want it jumping while on the ground would we?
                    try {
                        if (jump.getBoolean(passenger)) {
                            double jumpHeight = 0.5D;     //Here you can set the jumpHeight
                            this.motY = jumpHeight;        // Used all the time in NMS for entity jumping
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            super.g(sideMot, forMot);
        }
		return forMot;
    }

}
