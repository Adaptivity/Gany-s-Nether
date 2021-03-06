package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class QuarzBerry extends Item implements IConfigurable {

	public QuarzBerry() {
		setTextureName(Utils.getItemTexture(Strings.Items.QUARZ_BERRY_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.QUARZ_BERRY_NAME));
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateQuarzBerryBush;
	}
}