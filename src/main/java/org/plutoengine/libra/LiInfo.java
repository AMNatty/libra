package org.plutoengine.libra;

import org.plutoengine.libra.command.LiCommandBuffer;

public abstract class LiInfo
{
    protected LiCommandBuffer commandBuffer;

    protected LiInfo(LiCommandBuffer commandBuffer)
    {
        this.commandBuffer = commandBuffer;
    }

    public LiCommandBuffer getDrawCommandBuffer()
    {
        return this.commandBuffer;
    }
}
