package latmod.ftbu.mod;

import latmod.ftbu.core.*;
import latmod.ftbu.core.gui.ContainerEmpty;
import latmod.ftbu.core.tile.IGuiTile;
import latmod.ftbu.mod.client.gui.*;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.*;

public class FTBUGuiHandler implements ILMGuiHandler
{
	public static final FTBUGuiHandler instance = new FTBUGuiHandler();
	
	public static final String TILE = "lmc.tile";
	public static final String FRIENDS = "lmc.friends";
	public static final String SECURITY = "lmc.security";
	public static final String DISPLAY_ITEM = "lmc.displayitem";
	public static final String[] IDs = { TILE, FRIENDS, SECURITY, DISPLAY_ITEM };
	
	public Container getContainer(EntityPlayer ep, String id, NBTTagCompound data)
	{
		if(id.equals(TILE))
		{
			int[] xyz = data.getIntArray("XYZ");
			TileEntity te = ep.worldObj.getTileEntity(xyz[0], xyz[1], xyz[2]);
			if(te != null && !te.isInvalid() && te instanceof IGuiTile)
				return ((IGuiTile)te).getContainer(ep, data);
		}
		else return new ContainerEmpty(ep, null);
		
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public GuiScreen getGui(EntityPlayer ep, String id, NBTTagCompound data)
	{
		if(id.equals(TILE))
		{
			int[] xyz = data.getIntArray("XYZ");
			TileEntity te = ep.worldObj.getTileEntity(xyz[0], xyz[1], xyz[2]);
			if(te != null && !te.isInvalid() && te instanceof IGuiTile)
				return ((IGuiTile)te).getGui(ep, data);
		}
		else if(id.equals(FRIENDS)) return new GuiFriends(null);
		else if(id.equals(DISPLAY_ITEM))
			return new GuiDisplayItem(ItemDisplay.readFromNBT(data));
		
		return null;
	}
}