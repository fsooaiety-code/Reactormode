package com.samurai.reactor;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Значение должно точно совпадать с modId в mods.toml!
@Mod("samuraireactor")
public class ReactorMod {

    public static final String MOD_ID = "samuraireactor";
    private static final Logger LOGGER = LogManager.getLogger();

    public ReactorMod() {
        // Регистрация событий настройки мода
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Регистрация мода на шине событий Forge
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT! РЕАКТОР ГОТОВИТСЯ К ЗАПУСКУ.");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Настройка клиента завершена.");
    }
}