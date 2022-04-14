package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;
import org.plutoengine.libra.command.LiCommandBuffer;

import java.util.function.Consumer;

public final class LiCommandSpecial extends LiCommand
{
    private final Consumer<LiCommandBuffer> consumer;

    public LiCommandSpecial(Consumer<LiCommandBuffer> action)
    {
        super(EnumGUIPipelineCommand.SPECIAL);
        this.consumer = action;
    }

    public Consumer<LiCommandBuffer> getAction()
    {
        return this.consumer;
    }
}
