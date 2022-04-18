package org.plutoengine.libra.command.impl;

import org.joml.Matrix3fc;
import org.plutoengine.libra.command.EnumGUIPipelineCommand;

public final class LiCommandSetTransform extends LiCommand
{
    private final Matrix3fc transform;

    public LiCommandSetTransform(Matrix3fc transform)
    {
        super(EnumGUIPipelineCommand.SET_TRANSFORM);

        this.transform = transform;
    }

    public Matrix3fc getTransform()
    {
        return this.transform;
    }
}
