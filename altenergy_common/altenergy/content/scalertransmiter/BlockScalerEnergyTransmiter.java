package altenergy.content.scalertransmiter;

import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import altenergy.AltEnergy;
import altenergy.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.block.BlockAdvanced;
import universalelectricity.prefab.tile.TileEntityAdvanced;


public class BlockScalerEnergyTransmiter extends BlockAdvanced{

	private HashMap<String, Icon> icons = new HashMap<String, Icon>();
	
	public BlockScalerEnergyTransmiter(int id) {

		super(id, Material.iron);
		this.setUnlocalizedName(Strings.BLOCK_SCALER_ENERGY_TRANSMITER);
		this.setStepSound(soundMetalFootstep);
		this.setHardness(1.5f);
		this.setResistance(10.0f);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		if(side == metadata + 2) {
			return this.icons.get("output");
		} else if(side == ForgeDirection.getOrientation(metadata + 2).getOpposite().ordinal()) {
			return this.icons.get("input");
		} else {
			return this.icons.get("default");
		}
	} 
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg) {
		this.icons.put("output", iconReg.registerIcon(Strings.BLOCK_SCALER_ENERGY_TRANSMITER + "_Output"));
		this.icons.put("input", iconReg.registerIcon(Strings.BLOCK_SCALER_ENERGY_TRANSMITER + "_Input"));
		this.icons.put("default", iconReg.registerIcon(Strings.BLOCK_SCALER_ENERGY_TRANSMITER + "_Top"));
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileScalerEnergyTransmiter();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote) {
			boolean isPlayerOP = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().areCommandsAllowed(player.getCommandSenderName());
			
			if(player.username == ((TileScalerEnergyTransmiter) world.getBlockTileEntity(x, y, z)).getOwningPlayer() || isPlayerOP) {
				player.openGui(AltEnergy.instance, 4, world, x, y, z);
				return true;
			}
			return true;
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {
		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
		switch(angle) {
			case 0:
				world.setBlock(x, y, z, this.blockID, 3, 0);
				break;
			case 1:
				world.setBlock(x, y, z, this.blockID, 1, 0);
				break;
			case 2:
				world.setBlock(x, y, z, this.blockID, 2, 0);
				break;
			case 3:
				world.setBlock(x, y, z, this.blockID, 0, 0);
				break;
		}
		
		if(entityLiving instanceof EntityPlayer && (TileEntityAdvanced) world.getBlockTileEntity(x, y, z) instanceof TileScalerEnergyTransmiter) {
			((TileScalerEnergyTransmiter) world.getBlockTileEntity(x, y, z)).setPlayer((EntityPlayer) entityLiving);
		}
		
		((TileEntityAdvanced) world.getBlockTileEntity(x, y, z)).initiate();
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		int change = 0;
		
		//Re-orient the block
		switch(metadata) {
			case 0:
				change = 3;
				break;
			case 3:
				change = 1;
				break;
			case 1:
				change = 2;
				break;
			case 2:
				change = 0;
				break;
		}
		
		world.setBlock(x, y, z, this.blockID, change, 0);
		world.markBlockForUpdate(x, y, z);
		((TileEntityAdvanced) world.getBlockTileEntity(x, y, z)).initiate();
		return true;
		
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		
		int id = this.idPicked(world, x, y, z);
		
		if(id == 0)
			return null;
		
		Item item = Item.itemsList[id];
		if(item == null)
			return null;
		
		return new ItemStack(id, 1, 0);
	}
}
