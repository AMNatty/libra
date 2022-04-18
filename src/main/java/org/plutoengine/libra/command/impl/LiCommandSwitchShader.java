package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;

public non-sealed class LiCommandSwitchShader<S> extends LiCommand
{
    private final S shader;

    public LiCommandSwitchShader(S shader)
    {
        super(EnumGUIPipelineCommand.SWITCH_SHADER);

        this.shader = shader;
    }

    public S getShader()
    {
        return this.shader;
    }
}
