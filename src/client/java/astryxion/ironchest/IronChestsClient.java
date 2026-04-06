package astryxion.ironchest;

import astryxion.ironchest.registry.ModBlockEntityRenderer;
import astryxion.ironchest.registry.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class IronChestsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModScreenHandlers.registerScreenHandlers();
        ModBlockEntityRenderer.registerBlockEntityRenderer();
    }
}
