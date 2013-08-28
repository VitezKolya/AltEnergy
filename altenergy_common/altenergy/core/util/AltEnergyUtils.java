
package altenergy.core.util;

import ic2.api.Direction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.server.FMLServerHandler;

public class AltEnergyUtils {

	/**
	 * Checks for a new version of Mekanism.
	 */
	public static boolean checkForUpdates(EntityPlayer player) {

		return true;
	}

	/**
	 * Gets the latest version using getHTML and returns it as a string.
	 * 
	 * @return latest version
	 */
	public void getLatestVersion() {

	}

	/**
	 * Checks if the mod doesn't need an update.
	 * 
	 * @return if mod doesn't need an update
	 */
	public static boolean noUpdates() {

		return true;
	}

	/**
	 * Checks if Minecraft is running in offline mode.
	 * 
	 * @return if mod is running in offline mode.
	 */
	public static boolean isOffline() {

		return false;
	}

	/**
	 * Sends a Packet3Chat packet to the defined player, with the defined
	 * message.
	 * 
	 * @param player
	 *            - Player to send packet to.
	 * @param msg
	 *            - message sent to player.
	 */
	public static void sendChatMessageToPlayer(String playerUsername, String msg) {

		EntityPlayer player = FMLServerHandler.instance().getServer().getConfigurationManager()
				.getPlayerForUsername(playerUsername);
		Packet3Chat chatPacket = new Packet3Chat(msg);

		if (player != null) {
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(chatPacket);
		}
	}

	/**
	 * Sends the defined message to all players.
	 * 
	 * @param msg
	 *            - message to send
	 */
	public static void sendChatMessageToAllPlayers(String msg) {

		PacketDispatcher.sendPacketToAllPlayers(new Packet3Chat(msg));
	}

	/**
	 * Sets the defined world's time to the defined time.
	 * 
	 * @param world
	 *            - world to set time
	 * @param paramInt
	 *            - hour to set time to
	 */
	public static void setHourForward(World world, int paramInt) {

		long l1 = world.getWorldTime() / 24000L * 24000L;
		long l2 = l1 + 24000L + paramInt * 1000;
		world.setWorldTime(l2);
	}

	/**
	 * Creates a fake explosion at the declared player, with only sounds and
	 * effects. No damage is caused to either blocks or the player.
	 * 
	 * @param entityplayer
	 *            - player to explode
	 */
	public static void doFakeEntityExplosion(EntityPlayer entityplayer) {

		World world = entityplayer.worldObj;
		world.spawnParticle("hugeexplosion", entityplayer.posX, entityplayer.posY, entityplayer.posZ, 0.0D, 0.0D, 0.0D);
		world.playSoundAtEntity(entityplayer, "random.explode", 1.0F, 1.0F);
	}

	/**
	 * Creates a fake explosion at the declared coords, with only sounds and
	 * effects. No damage is caused to either blocks or the player.
	 * 
	 * @param world
	 *            - world where the explosion will occur
	 * @param x
	 *            - x coord
	 * @param y
	 *            - y coord
	 * @param z
	 *            - z coord
	 */
	public static void doFakeBlockExplosion(World world, int x, int y, int z) {

		world.spawnParticle("hugeexplosion", x, y, z, 0.0D, 0.0D, 0.0D);
		world.playSound(x, y, z, "random.explode", 1.0F, 1.0F, true);
	}

	/**
	 * Copies an ItemStack and returns it with a defined stackSize.
	 * 
	 * @param itemstack
	 *            - stack to change size
	 * @param size
	 *            - size to change to
	 * @return resized ItemStack
	 */
	public static ItemStack size(ItemStack itemstack, int size) {

		ItemStack newStack = itemstack.copy();
		newStack.stackSize = size;
		return newStack;
	}

	/**
	 * Adds a recipe directly to the CraftingManager that works with the Forge
	 * Ore Dictionary.
	 * 
	 * @param output
	 *            the ItemStack produced by this recipe
	 * @param params
	 *            the items/blocks/itemstacks required to create the output
	 *            ItemStack
	 */
	public static void addRecipe(ItemStack output, Object[] params) {

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, params));
	}

	/**
	 * Gets the left side of a certain orientation.
	 * 
	 * @param orientation
	 * @return left side
	 */
	public static ForgeDirection getLeft(int orientation) {

		switch (orientation) {
			case 2:
				return ForgeDirection.EAST;
			case 3:
				return ForgeDirection.WEST;
			case 4:
				return ForgeDirection.SOUTH;
			default:
				return ForgeDirection.NORTH;
		}
	}

	/**
	 * Gets the right side of a certain orientation.
	 * 
	 * @param orientation
	 * @return right side
	 */
	public static ForgeDirection getRight(int orientation) {

		return getLeft(orientation).getOpposite();
	}

	/**
	 * Checks to see if a specified ItemStack is stored in the Ore Dictionary
	 * with the specified name.
	 * 
	 * @param check
	 *            - ItemStack to check
	 * @param oreDict
	 *            - name to check with
	 * @return if the ItemStack has the Ore Dictionary key
	 */
	public static boolean oreDictCheck(ItemStack check, String oreDict) {

		boolean hasResource = false;

		for (ItemStack ore : OreDictionary.getOres(oreDict)) {
			if (ore.isItemEqual(check)) {
				hasResource = true;
			}
		}

		return hasResource;
	}

	/**
	 * Returns an integer facing that converts a world-based orientation to a
	 * machine-based oriention.
	 * 
	 * @param side
	 *            - world based
	 * @param blockFacing
	 *            - what orientation the block is facing
	 * @return machine orientation
	 */
	public static int getBaseOrientation(int side, int blockFacing) {

		if (blockFacing == 3 || side == 1 || side == 0) {
			if (side == 2 || side == 3) {
				return ForgeDirection.getOrientation(side).getOpposite().ordinal();
			}

			return side;
		} else if (blockFacing == 2) {
			if (side == 2 || side == 3) {
				return side;
			}

			return ForgeDirection.getOrientation(side).getOpposite().ordinal();
		} else if (blockFacing == 4) {
			return getRight(side).ordinal();
		} else if (blockFacing == 5) {
			return getLeft(side).ordinal();
		}

		return side;
	}

	/**
	 * Gets the operating ticks required for a machine via it's upgrades.
	 * 
	 * @param speedUpgrade
	 *            - number of speed upgrades
	 * @param def
	 *            - the original, default ticks required
	 * @return max operating ticks
	 */
	public static int getTicks(int speedUpgrade, int def) {

		return (int) (def * Math.pow(10, -speedUpgrade / 9.0));
	}

	/**
	 * Gets the energy required per tick for a machine via it's upgrades.
	 * 
	 * @param speedUpgrade
	 *            - number of speed upgrades
	 * @param energyUpgrade
	 *            - number of energy upgrades
	 * @param def
	 *            - the original, default energy required
	 * @return max energy per tick
	 */
	public static int getEnergyPerTick(int speedUpgrade, int energyUpgrade, int def) {

		return (int) (def * Math.pow(10, (speedUpgrade - energyUpgrade) / 9.0));
	}

	/**
	 * Gets the energy required per tick for a machine via it's upgrades.
	 * 
	 * @param speedUpgrade
	 *            - number of speed upgrades
	 * @param energyUpgrade
	 *            - number of energy upgrades
	 * @param def
	 *            - the original, default energy required
	 * @return max energy per tick
	 */
	public static double getEnergyPerTick(int speedUpgrade, int energyUpgrade, double def) {

		return def * Math.pow(10, (speedUpgrade - energyUpgrade) / 9.0);
	}

	/**
	 * Gets the maximum energy for a machine via it's upgrades.
	 * 
	 * @param energyUpgrade
	 *            - number of energy upgrades
	 * @param def
	 *            - original, default max energy
	 * @return max energy
	 */
	public static double getEnergy(int energyUpgrade, double def) {

		return (int) (def * Math.pow(10, energyUpgrade / 9.0));
	}

	/**
	 * Converts a ForgeDirection enum value to it's corresponding value in
	 * IndustrialCraft's 'Direction.' Using values()[ordinal()] will not work in
	 * this situation,
	 * as IC2 uses different values from base MC direction theory.
	 * 
	 * @param side
	 *            - ForgeDirection value
	 * @return Direction value
	 */
	public static Direction toIC2Direction(ForgeDirection side) {

		switch (side) {
			case DOWN:
				return Direction.YN;
			case UP:
				return Direction.YP;
			case NORTH:
				return Direction.ZN;
			case SOUTH:
				return Direction.ZP;
			case WEST:
				return Direction.XN;
			default:
				return Direction.XP;
		}
	}

	/**
	 * Gets the OreDictionary-registered name of an ItemStack.
	 * 
	 * @param itemStack
	 *            - ItemStack to check
	 * @return name of the ItemStack
	 */
	public static String getName(ItemStack itemStack) {

		return OreDictionary.getOreName(OreDictionary.getOreID(itemStack));
	}

}
