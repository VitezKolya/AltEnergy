
package altenergy.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FooRoo extends EntityFlying implements IMob {

	public int courseChangeCooldown = 0;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity = null;
	private double speed = 0.1;
	private int minDistanceToTarget = 1;

	/**
	 * Time when this creeper was last in an active state (Messed up code here,
	 * probably causes creeper animation to go
	 * weird)
	 */
	private int lastActiveTime;

	/**
	 * The amount of time since the FooRoo was close enough to the player to
	 * ignite
	 */
	private int timeSinceIgnited;
	private int fuseTime = 30;

	/** Explosion radius for this FooRoo. */
	private int explosionRadius = 3;

	public FooRoo(World par1World) {

		super(par1World);
		setSize(1.0F, 1.0F);
		isImmuneToFire = true;
		experienceValue = 5;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		if (isEntityInvulnerable()) {
			return false;
		} else {
			setFooRooState(1);
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	public void setFuse() {

	}

	@Override
	public int getMaxHealth() {

		// TODO Auto-generated method stub
		return 6;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setShort("Fuse", (short) fuseTime);
		par1NBTTagCompound.setByte("ExplosionRadius", (byte) explosionRadius);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Fuse")) {
			fuseTime = par1NBTTagCompound.getShort("Fuse");
		}

		if (par1NBTTagCompound.hasKey("ExplosionRadius")) {
			explosionRadius = par1NBTTagCompound.getByte("ExplosionRadius");
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {

		if (!worldObj.isRemote && worldObj.difficultySetting == 0) {
			setDead();
		}

		despawnEntity();
		double d0 = waypointX - posX;
		double d1 = waypointY - posY;
		double d2 = waypointZ - posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		// Move to target if there is a target
		if (targetedEntity != null) {
			faceEntity(targetedEntity, 100F, 100F);
			waypointX = Math.min(targetedEntity.posX, 5);
			waypointY = Math.min(targetedEntity.posY, 5);
			waypointZ = Math.min(targetedEntity.posZ, 5);
		}

		if (targetedEntity == null) {

			if (d3 < 1.0D || d3 > 3600.0D) {
				waypointX = posX + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
				waypointY = posY + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
				waypointZ = posZ + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			}

			if (courseChangeCooldown-- <= 0) {
				courseChangeCooldown += rand.nextInt(5) + 2;
				d3 = MathHelper.sqrt_double(d3);

				if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
					motionX += d0 / d3 * speed;
					motionY += d1 / d3 * speed;
					motionZ += d2 / d3 * speed;
				} else {
					waypointX = posX;
					waypointY = posY;
					waypointZ = posZ;
				}
			}
		}

		if (targetedEntity != null && targetedEntity.isDead) {
			targetedEntity = null;
		}

		if (targetedEntity == null) {
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 60.0D);
		}

		if (targetedEntity != null
				&& targetedEntity.getDistanceSqToEntity(this) < minDistanceToTarget * minDistanceToTarget) {

		}

		if (isEntityAlive()) {
			lastActiveTime = timeSinceIgnited;
			int i = getFooRooState();

			timeSinceIgnited += i;

			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;

				if (!worldObj.isRemote) {
					boolean flag = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

					worldObj.createExplosion(this, posX, posY, posZ, explosionRadius, flag);

					setDead();
				}
			}
		}

		super.onUpdate();
	}

	/**
	 * True if the FooRoo has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {

		double d4 = (waypointX - posX) / par7;
		double d5 = (waypointY - posY) / par7;
		double d6 = (waypointZ - posZ) / par7;
		AxisAlignedBB axisalignedbb = boundingBox.copy();

		for (int i = 1; i < par7; ++i) {
			axisalignedbb.offset(d4, d5, d6);

			if (!worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the current state of FooRoo, -1 is idle, 1 is 'in fuse'
	 */
	public int getFooRooState() {

		return dataWatcher.getWatchableObjectByte(16);
	}

	/**
	 * Sets the state of FooRoo, -1 to idle and 1 to be 'in fuse'
	 */
	public void setFooRooState(int par1) {

		dataWatcher.updateObject(16, Byte.valueOf((byte) par1));
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Params: (Float)Render tick. Returns the intensity of the FooRoo's flash when it is ignited.
	 */
	public float getFooRooFlashIntensity(float par1) {

		return (lastActiveTime + (timeSinceIgnited - lastActiveTime) * par1) / (fuseTime - 2);
	}

}
