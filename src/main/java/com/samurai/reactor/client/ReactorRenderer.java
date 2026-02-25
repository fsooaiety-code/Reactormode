package com.samurai.reactor.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.samurai.reactor.tileentity.ReactorCoreTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;

public class ReactorRenderer extends TileEntityRenderer<ReactorCoreTile> {
    public ReactorRenderer(TileEntityRendererDispatcher d) { super(d); }

    @Override
    public void render(ReactorCoreTile te, float partialTicks, MatrixStack ms, IRenderTypeBuffer buffer, int light, int overlay) {
        ms.push();
        ms.translate(0.5, 0.5, 0.5); // Центр блока
        
        float time = (System.currentTimeMillis() % 360000) / 10.0f;
        float scale = 4.0f; // Размер черной дыры (4 блока)
        
        ms.scale(scale, scale, scale);
        ms.rotate(Vector3f.YP.rotationDegrees(time)); // Вращение
        
        // Рисуем сферу (упрощенно через бокс, закрашенный в черный)
        IVertexBuilder builder = buffer.getBuffer(RenderType.getLeash()); // Используем темный тип рендера
        
        // Тут вызывается отрисовка геометрии. 
        // Для телефона проще всего использовать готовую модель сферы в .obj
        
        ms.pop();
    }
}