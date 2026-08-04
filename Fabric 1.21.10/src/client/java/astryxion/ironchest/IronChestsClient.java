package astryxion.ironchest;

import astryxion.ironchest.registry.ModBlockEntityRenderer;
import astryxion.ironchest.registry.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;

public class IronChestsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModScreenHandlers.registerScreenHandlers();
        ModBlockEntityRenderer.registerBlockEntityRenderer();
    }
}
