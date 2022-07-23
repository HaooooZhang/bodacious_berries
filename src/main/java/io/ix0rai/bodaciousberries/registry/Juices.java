package io.ix0rai.bodaciousberries.registry;

import com.google.gson.JsonObject;
import io.ix0rai.bodaciousberries.BodaciousBerries;
import io.ix0rai.bodaciousberries.block.entity.JuicerRecipe;
import io.ix0rai.bodaciousberries.block.entity.JuicerRecipes;
import io.ix0rai.bodaciousberries.item.Blend;
import io.ix0rai.bodaciousberries.item.ChorusBerryJuice;
import io.ix0rai.bodaciousberries.item.EndBlend;
import io.ix0rai.bodaciousberries.item.GojiBerryBlend;
import io.ix0rai.bodaciousberries.item.Juice;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.biome.BiomeKeys.*;

public class Juices {
    public static final Item JUICE_RECEPTACLE = Items.GLASS_BOTTLE;
    public static final Item.Settings JUICE_SETTINGS = new Item.Settings().recipeRemainder(JUICE_RECEPTACLE).group(ItemGroup.FOOD).maxCount(16);
    public static final List<JsonObject> RECIPES = new ArrayList<>();
    public static final Juice DUBIOUS_JUICE = new Juice(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(2).saturationModifier(3F).build()));

    public static void registerJuice() {
        register(BodaciousBerries.id("dubious_juice"), DUBIOUS_JUICE);
        register("saskatoon_berry_juice", new Juice(Berries.saskatoonBerries));
        register("strawberry_juice", new Juice(Berries.strawberry));
        register("raspberry_juice", new Juice(Berries.raspberries));
        register("blackberry_juice", new Juice(Berries.blackberries));
        register("rainberry_juice", new Juice(Berries.rainberry));
        register("lingonberry_juice", new Juice(Berries.lingonberries));
        register("grape_juice", new Juice(Berries.grapes));
        register("goji_berry_juice", new Juice(Berries.gojiBerries));
        register("gooseberry_juice", new Juice(Berries.gooseberries));
        register("glow_berry_juice", new Juice(Items.GLOW_BERRIES, new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 90, 1), 1.0F)));
        register("sweet_berry_juice", new Juice(Items.SWEET_BERRIES, new FoodComponent.Builder()));
        register("chorus_berry_juice", new ChorusBerryJuice(Berries.chorusBerries, null));
        register("cloudberry_juice", new Juice(Berries.cloudberries, new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1200, 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 1), 1.0f)));

        createBiomeChorusJuice();
        registerBlends();
    }

    private static void createBiomeChorusJuice() {
        RegistryKey<?>[] biomes = new RegistryKey<?>[]{
                PLAINS, SNOWY_SLOPES, SWAMP,
                DESERT, TAIGA, BIRCH_FOREST,
                OCEAN, MUSHROOM_FIELDS, SUNFLOWER_PLAINS,
                FOREST, FLOWER_FOREST, DARK_FOREST,
                SAVANNA, BADLANDS, MEADOW,
                LUSH_CAVES, DRIPSTONE_CAVES, JUNGLE
        };

        Item[] biomeItems = new Item[]{
                Items.CORNFLOWER, Items.SNOWBALL, Items.CLAY_BALL,
                Items.SAND, Items.SWEET_BERRIES, Items.BIRCH_SAPLING,
                Items.COD, Items.RED_MUSHROOM, Items.SUNFLOWER,
                Items.OAK_SAPLING, Items.PEONY, Items.DARK_OAK_SAPLING,
                Items.ACACIA_SAPLING, Items.RED_SAND, Items.POPPY,
                Items.GLOW_BERRIES, Items.POINTED_DRIPSTONE, Items.COCOA_BEANS
        };

        for (int i = 0; i < biomes.length; i++) {
            RegistryKey<?> key = biomes[i];
            ChorusBerryJuice juice = new ChorusBerryJuice(Berries.chorusBerries, key.getValue());
            Identifier id = BodaciousBerries.id("chorus_berry_juice_" + key.getValue().getPath());

            RECIPES.add(JuicerRecipes.createShapelessJson(Registry.ITEM.getId(biomeItems[i]), id));

            Registry.register(Registry.ITEM, id, juice);
        }
    }

    private static void registerBlends() {
        Blend gojiBerryBlend = new GojiBerryBlend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(6).saturationModifier(6.0F).statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 800, 1), 1.0F).build()), Berries.gojiBerries, Berries.gojiBerries, Items.SUGAR_CANE);
        registerBlend("goji_berry_blend", gojiBerryBlend);
        Blend oppositeJuice = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(5).saturationModifier(5.0F).build()), Berries.raspberries, Berries.blackberries, Items.SUGAR_CANE);
        registerBlend("opposite_juice", oppositeJuice);
        Blend rainberryBlend = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(8).saturationModifier(10.0F).build()), Berries.rainberry, Berries.gojiBerries, Items.GOLDEN_APPLE);
        registerBlend("rainberry_blend", rainberryBlend);
        Blend gooseberryRum = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(7).saturationModifier(4.0f).build()), Berries.gooseberries, Berries.gooseberries, Items.WHEAT);
        registerBlend("gooseberry_rum", gooseberryRum);
        Blend redJuice = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(6).saturationModifier(6.0F).build()), Berries.strawberry, Items.SWEET_BERRIES, Berries.lingonberries);
        registerBlend("red_juice", redJuice);
        Blend endBlend = new EndBlend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(3).saturationModifier(8.0F).build()), Berries.chorusBerries, Berries.rainberry, Items.CHORUS_FRUIT);
        registerBlend("end_blend", endBlend);
        Blend purpleDelight = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(7).saturationModifier(6.0F).build()), Berries.chorusBerries, Berries.grapes, Berries.saskatoonBerries);
        registerBlend("purple_delight", purpleDelight);
        Blend trafficLightJuice = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(6).saturationModifier(8.0F).build()), Berries.gooseberries, Items.GLOW_BERRIES, Berries.raspberries);
        registerBlend("traffic_light_juice", trafficLightJuice);
        Blend vanillaDelight = new Blend(JUICE_SETTINGS.food(new FoodComponent.Builder().hunger(5).saturationModifier(5.0F).build()), Items.GLOW_BERRIES, Items.SWEET_BERRIES, Items.APPLE);
        registerBlend("vanilla_delight", vanillaDelight);
    }

    private static void register(String name, Juice juice) {
        Identifier id = BodaciousBerries.id(name);
        JuicerRecipes.addJuiceRecipe(new Identifier("c", Registry.ITEM.getId(juice.getBerry()).getPath()), id);
        register(id, juice);
    }

    private static void registerBlend(String name, Blend blend) {
        Identifier id = BodaciousBerries.id(name);
        register(id, blend);
        JuicerRecipes.addJuiceRecipe(Registry.ITEM.getId(blend.getIngredient0()), Registry.ITEM.getId(blend.getIngredient1()), Registry.ITEM.getId(blend.getIngredient2()), id);
    }

    private static void register(Identifier id, Juice juice) {
        Registry.register(Registry.ITEM, id, juice);
    }
}
