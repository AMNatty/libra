package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;

public non-sealed class LiCommandSwitchTexture<T> extends LiCommand
{
    private final T texture;

    public LiCommandSwitchTexture(T texture)
    {
        super(EnumGUIPipelineCommand.SWITCH_TEXTURE);

        this.texture = texture;
    }

    public T getTexture()
    {
        return this.texture;
    }
}
