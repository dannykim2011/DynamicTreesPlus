package com.dtteam.dynamictreesplus.systems.featuregen;

import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictreesplus.DynamicTreesPlus;

public class DynamicTreesPlusGenFeatures {

    public static final GenFeature CACTUS_CLONES = new CactusClonesGenFeature(DynamicTreesPlus.location("cactus_clones"));
    public static final GenFeature CACTUS_FRUIT = new CactusFruitGenFeature(DynamicTreesPlus.location("cactus_fruit"));
    public static final GenFeature CACTUS_FLOWER = new CactusFlowerGenFeature(DynamicTreesPlus.location("cactus_flower"), false, false);
    public static final GenFeature PILLAR_CACTUS_FLOWER = new CactusFlowerGenFeature(DynamicTreesPlus.location("pillar_cactus_flower"), true, false);
    public static final GenFeature PIPE_CACTUS_FLOWER = new CactusFlowerGenFeature(DynamicTreesPlus.location("pipe_cactus_flower"), false, true);

    public static void registerGenFeatures(final RegistryEvent<GenFeature> event){
        event.getRegistry().registerAll(CACTUS_CLONES, CACTUS_FRUIT, CACTUS_FLOWER, PILLAR_CACTUS_FLOWER, PIPE_CACTUS_FLOWER);
    }

}
