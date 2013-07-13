
package altenergy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import altenergy.common.ItemAE;

public class ItemTETest extends ItemAE {

	public ItemTETest(int id) {

		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
			float xOffset, float yOffset, float zOffset) {

		int blockID = 12;

		if (world.getBlockId(x, y + 1, z) == 0) {
			world.setBlock(x, y + 1, z, blockID);
			// TETestEntity te = (TETestEntity) world.getBlockTileEntity(x, y +
			// 1, z);
			return true;
		}

		return false;
	}

}
