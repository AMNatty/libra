package org.plutoengine.libra;

import org.joml.primitives.Rectanglef;
import org.plutoengine.libra.command.LiCommandBuffer;

public abstract class LiInfo
{
    protected LiCommandBuffer commandBuffer;
    protected Rectanglef boundingBox;

    protected LiInfo(LiCommandBuffer commandBuffer, Rectanglef aabb)
    {
        this.commandBuffer = commandBuffer;
        this.boundingBox = aabb;
    }

    public LiCommandBuffer getDrawCommandBuffer()
    {
        return this.commandBuffer;
    }

    public Rectanglef getBoundingBox()
    {
        return this.boundingBox;
    }
}
