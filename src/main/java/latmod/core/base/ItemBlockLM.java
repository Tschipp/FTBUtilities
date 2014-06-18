package latmod.core.base;
import java.util.List;

import latmod.core.FastList;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

public class ItemBlockLM extends ItemBlock
{
	public BlockLM blockLM;

	public ItemBlockLM(Block b)
	{
		super(b);
		setHasSubtypes(true);
		setMaxDamage(0);
		blockLM = (BlockLM)b;
	}

	public int getMetadata(int m)
	{ return m; }

	public String getUnlocalizedName(ItemStack is)
	{ return blockLM.getUnlocalizedName(is.getItemDamage()); }
	
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item j, CreativeTabs c, List l)
	{ blockLM.getSubBlocks(j, c, l); }
	
	private FastList<String> infoList = new FastList<String>();
	
	@SuppressWarnings("all") @SideOnly(Side.CLIENT)
    public final void addInformation(ItemStack is, EntityPlayer ep, List l, boolean b)
	{
		infoList.clear();
		blockLM.addInfo(is, ep, infoList);
		l.addAll(infoList);
	}
}