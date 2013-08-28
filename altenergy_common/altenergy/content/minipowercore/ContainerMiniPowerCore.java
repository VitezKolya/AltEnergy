
package altenergy.content.minipowercore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import altenergy.lib.ItemIds;

public class ContainerMiniPowerCore extends Container {

	private TileMiniPowerCore tileEntity;

	private final int PLAYER_INVENTORY_ROWS = 3;
	private final int PLAYER_INVENTORY_COLUMNS = 9;

	public ContainerMiniPowerCore(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {

		tileEntity = (TileMiniPowerCore) world.getBlockTileEntity(x, y, z);
		addSlotToContainer(new Slot(tileEntity, 0, 15, 20));

		int inventoryRowIndex;
		for (inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
				addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex
						* PLAYER_INVENTORY_COLUMNS + PLAYER_INVENTORY_COLUMNS, 8 + inventoryColumnIndex * 18,
						84 + inventoryRowIndex * 18));
			}
		}

		for (inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryRowIndex) {
			addSlotToContainer(new Slot(inventoryPlayer, inventoryRowIndex, 8 + inventoryRowIndex * 18, 142));
		}
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer player) {

		super.onCraftGuiClosed(player);
		tileEntity.playerUsing.remove(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {

		return tileEntity.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {

		ItemStack var2 = null;
		Slot var3 = (Slot) inventorySlots.get(slotIndex);

		if (var3 != null && var3.getHasStack()) {
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (slotIndex != 0) {
				if (var4.itemID == ItemIds.ENERGY_CRYSTAL_TINY || var4.itemID == ItemIds.ENERGY_CRYSTAL_SMALL) {
					if (!mergeItemStack(var4, 0, 1, false)) {
						return null;
					}
				} else if (slotIndex >= 30 && slotIndex < 37 && !mergeItemStack(var4, 3, 30, false)) {
					return null;
				}
			} else if (!mergeItemStack(var4, 3, 37, false)) {
				return null;
			}

			if (var4.stackSize == 0) {
				var3.putStack((ItemStack) null);
			} else {
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize) {
				return null;
			}

			var3.onPickupFromSlot(player, var4);
		}

		return var2;
	}
}
