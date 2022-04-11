package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;

public non-sealed abstract class LiCommandDrawMesh extends LiCommand
{
    protected LiCommandDrawMesh()
    {
        super(EnumGUIPipelineCommand.DRAW_MESH);
    }
}
