
package altenergy.content.conductors;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockEnergyBusway extends ItemBlock {

	public ItemBlockEnergyBusway(int id) {

		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {

		list.add("Resistance: " + ElectricityDisplay.getDisplay(0, ElectricUnit.RESISTANCE));
		list.add("Max Amps: " + ElectricityDisplay.getDisplay(5000000, ElectricUnit.AMPERE));

		list.add("Normal Conductor, does not shock you");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg) {

		// iconReg.registerIcon(Reference.MOD_ID + ":" +
		// this.getUnlocalizedName());
	}
}
