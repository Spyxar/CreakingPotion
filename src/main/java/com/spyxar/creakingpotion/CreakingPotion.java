package com.spyxar.creakingpotion;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreakingPotion implements ModInitializer
{
    public static final String MOD_ID = "creakingpotion";

    public static final Logger LOGGER = LoggerFactory.getLogger("Creaking Potion");

    @Override
    public void onInitialize()
    {
        ModEffects.init();
    }
}