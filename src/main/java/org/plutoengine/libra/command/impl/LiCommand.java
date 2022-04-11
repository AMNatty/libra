package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;

public abstract sealed class LiCommand permits LiCommandDrawMesh, LiCommandSetTransform, LiCommandSwitchShader, LiCommandSwitchTexture
{
    private final EnumGUIPipelineCommand type;

    protected LiCommand(EnumGUIPipelineCommand type)
    {
        this.type = type;
    }

    public EnumGUIPipelineCommand getType()
    {
        return this.type;
    }

    public boolean supportsMerge(LiCommand other)
    {
        return false;
    }

    public LiCommand merge(LiCommand other)
    {
        throw new UnsupportedOperationException();
    }
}
