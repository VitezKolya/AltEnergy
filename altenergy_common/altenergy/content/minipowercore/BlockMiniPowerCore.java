
package altenergy.content.minipowercore;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockAdvanced;
import altenergy.AltEnergy;
import altenergy.lib.GuiIds;
import altenergy.lib.RenderIds;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMiniPowerCore extends BlockAdvanced {

	public BlockMiniPowerCore(int id) {

		super(id, Material.iron);
		this.setUnlocalizedName(Strings.BLOCK_MINI_POWER_CORE);
		this.setCreativeTab(AltEnergy.tabsAE);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileMiniPowerCore();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.mini_powercore;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(player.isSneaking()) {
			return false;
		} else {
			if(!world.isRemote) {
				TileMiniPowerCore tileEntity = (TileMiniPowerCore) world.getBlockTileEntity(x, y, z);
				tileEntity.findController();
				if(tileEntity != null) {
					
					player.openGui(AltEnergy.instance, GuiIds.MINI_POWERCORE, world, x, y, z);
				}
			}
			return true;
		}
	}
}