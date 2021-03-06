package sereneseasons.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sereneseasons.core.SereneSeasons;

/**
 * Configuration Handler for seasonal crop fertility
 */
@Config(modid = SereneSeasons.MOD_ID, name = SereneSeasons.MOD_ID+"/cropfertility", category = "")
@Mod.EventBusSubscriber
public class FertilityConfig
{

	public static General general_category = new General();
	public static SeasonFertility seasonal_fertility = new SeasonFertility();

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equals(SereneSeasons.MOD_ID)){
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); //resync config
		}
	}

	public static class General
	{
		@Config.Comment({"Whether crops break if out of season. If false, they simply don't grow"})
		public boolean crops_break = false;
		@Config.Comment({"Whether unlisted seeds are fertile every season but winter. False means they're infertile every season," +
				"and true means they're fertile every season except Winter"})
		public boolean ignore_unlisted_crops = true;
		@Config.Comment({"Whether to include tooltips on crops listing which seasons they're fertile in. " +
				"Note: only applies to listed seeds. Currently not implemented."})
		public boolean seed_tooltips = true;
		@Config.Comment({"Whether to ignore sapling growth. True means it will always grow"})
		public boolean ignore_saplings = false;
		@Config.Comment({"Maximum height greenhouse glass can be above a crop for it to be fertile out of season"})
		public int greenhouse_glass_max_height = 7;
	}

	public static class SeasonFertility
	{
		@Config.Comment({"Seeds that do the opposite of what cropsBreak is set to." +
				"Seeds *only* listed here are considered \"unlisted\" for ignore_unlisted_crops"})
		public String [] crops_break_opposite = new String[]{};
		@Config.Comment({"Seeds growable in the spring"})
		public String [] spring_seeds = new String[]{"minecraft:potato", "minecraft:carrot"};
		@Config.Comment({"Seeds growable in the summer"})
		public String [] summer_seeds = new String[]{"minecraft:potato", "minecraft:carrot", "minecraft:wheat_seeds"};
		@Config.Comment({"Seeds growable in the fall"})
		public String [] fall_seeds = new String[]{"minecraft:potato", "minecraft:wheat_seeds"};
		@Config.Comment({"Seeds growable in the winter"})
		public String [] winter_seeds = new String[]{"minecraft:potato"};
	}
}
