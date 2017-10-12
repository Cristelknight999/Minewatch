package twopiradians.minewatch.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import twopiradians.minewatch.common.CommonProxy.EnumParticle;
import twopiradians.minewatch.common.Minewatch;
import twopiradians.minewatch.common.util.EntityHelper;

public class EntitySoldier76HelixRocket extends EntityMW {

	private static final DataParameter<Integer> NUMBER = EntityDataManager.<Integer>createKey(EntitySoldier76HelixRocket.class, DataSerializers.VARINT);

	public EntitySoldier76HelixRocket(World worldIn) {
		this(worldIn, null, -1);
	}

	public EntitySoldier76HelixRocket(World worldIn, EntityLivingBase throwerIn, int number) {
		super(worldIn, throwerIn);
		this.setSize(0.1f, 0.1f);
		if (!worldIn.isRemote && number != -1)
			this.getDataManager().set(NUMBER, number);
		this.setNoGravity(true);
		this.lifetime = 60;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(NUMBER, Integer.valueOf(0));
	}

	@Override
	public void onUpdate() {		
		super.onUpdate();

		int num = this.getDataManager().get(NUMBER);
		double speed = 100d;
		double size = 0.3d;

		// TODO improve
		double separateThree = (num - 2) * 360 / 3;
		double toRadians = Math.PI / 180;
		double ticks = this.ticksExisted * speed;
		double yaw = this.rotationYaw;
		double pitch = this.rotationPitch;

		if (this.ticksExisted == 1) {
			if (Math.abs(pitch) >= 30 && Math.abs(pitch) <= 70) {
				this.posX += size * (Math.sin(Math.abs(pitch) * toRadians + Math.PI/4) * Math.cos((yaw + separateThree) * toRadians));                
				this.posY += size * Math.cos((pitch + separateThree) * toRadians) * Math.cos(pitch * toRadians);
				this.posZ += size * (Math.sin(Math.abs(pitch) * toRadians + Math.PI/4) * Math.sin((yaw + separateThree) * toRadians));
			}
			else {
				this.posX += size * (Math.sin(pitch * toRadians) * Math.sin((yaw + separateThree) * toRadians) + Math.cos(pitch * toRadians) * Math.cos(yaw * toRadians) * Math.signum(num - 2));                
				this.posY += size * Math.cos((pitch + separateThree) * toRadians) * Math.cos(pitch * toRadians);
				this.posZ += size * (Math.sin(pitch * toRadians) * Math.cos((yaw + separateThree) * toRadians) - Math.cos(pitch * toRadians) * Math.sin(yaw * toRadians) * Math.signum(num - 2));
			}
		}

		this.posX += this.motionX + Math.cos((yaw + separateThree + ticks) * toRadians) * Math.cos((pitch + separateThree + ticks) * toRadians) * size - Math.cos(yaw * toRadians) * size/2;                
		this.posY += this.motionY + Math.sin((yaw + separateThree + this.ticksExisted * speed) * Math.PI / 180) * size;
		this.posZ += this.motionZ + Math.cos((yaw + separateThree + ticks) * toRadians) * Math.sin((pitch + separateThree + ticks) * toRadians) * size + Math.sin(yaw * toRadians) * size/2;

		if (this.world.isRemote) 
			EntityHelper.spawnTrailParticles(this, 10, 0.05d, 0x5EDCE5, 0x007acc, 1, 4, 1);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);

		if (!(result.entityHit instanceof EntitySoldier76HelixRocket)) {
			// direct hit damage (explosions do plenty of damage - direct can't be much)
			if (EntityHelper.attemptImpact(this, result.entityHit, 1, false)) 
				result.entityHit.hurtResistantTime = 10;

			// explosion
			if (this.world.isRemote) 
				Minewatch.proxy.spawnParticlesCustom(EnumParticle.SMOKE, world, posX, posY, posZ, 
						0, 0, 0, 0x62E2FC, 0x203B7E, 1, 10, 25, 20, 0, 0);
			else {
				Minewatch.proxy.createExplosion(world, this.getThrower(), posX, posY, posZ, 
						1.6f, 40f, 80/3, 80/3, result.entityHit, 120/3, true);
				this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
			}
			this.setDead();
		}
	}
}
