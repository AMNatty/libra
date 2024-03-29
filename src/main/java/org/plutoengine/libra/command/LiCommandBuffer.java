/*
 * MIT License
 *
 * Copyright (c) 2022 493msi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.plutoengine.libra.command;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.plutoengine.libra.command.impl.*;
import org.plutoengine.libra.paint.LiPaint;
import org.plutoengine.util.color.Color;

import java.util.Deque;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Stream;

public final class LiCommandBuffer implements Iterable<LiCommand>
{
    private final Deque<LiCommand> commands;

    private Object texture;
    private Object shader;
    private Matrix3fc transform;
    private LiPaint paint;

    private LiCommandBuffer()
    {
        this.commands = new LinkedList<>();
    }

    public static LiCommandBuffer cleared()
    {
        var cb = new LiCommandBuffer();
        cb.commands.push(new LiCommandSetPaint(cb.paint = LiPaint.solidColor(Color.WHITE)));
        cb.commands.push(new LiCommandSetTransform(cb.transform = new Matrix3f()));
        return cb;
    }

    public static LiCommandBuffer uncleared()
    {
        return new LiCommandBuffer();
    }

    public LiCommandBuffer pushAll(LiCommandBuffer commandBuffer)
    {
        commandBuffer.forEach(this::push);

        return this;
    }

    public LiCommandBuffer push(LiCommand command)
    {
        switch (command.getType())
        {
            case SET_TRANSFORM -> {
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SET_TRANSFORM), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));

                var trans = ((LiCommandSetTransform) command).getTransform();

                if (trans.equals(this.transform))
                    break;

                this.commands.add(command);
                this.transform = trans;
            }

            case SET_PAINT -> {
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SET_PAINT), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));

                var paint = ((LiCommandSetPaint) command).getPaint();

                if (paint.equals(this.paint))
                    break;

                this.commands.add(command);
                this.paint = paint;
            }

            case SWITCH_SHADER -> {
                // This does have side effects, however in practice it is unwanted to switch shaders like that
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SWITCH_SHADER, EnumGUIPipelineCommand.SET_TRANSFORM, EnumGUIPipelineCommand.SET_PAINT),
                    EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));

                var shd = ((LiCommandSwitchShader<?>) command).getShader();

                if (shd.equals(this.shader))
                    break;

                this.commands.add(command);
                this.shader = shd;

                if (this.transform != null)
                    this.commands.add(new LiCommandSetTransform(this.transform));
            }

            case SWITCH_TEXTURE -> {
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SWITCH_TEXTURE), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));
                var tex = ((LiCommandSwitchTexture<?>) command).getTexture();

                if (tex.equals(this.texture))
                    break;

                this.commands.add(command);
                this.texture = tex;
            }

            case SPECIAL -> {
                this.commands.add(command);
            }

            case DRAW_MESH -> mergeInto(command);
        }

        return this;
    }

    private void removeAllUntil(EnumSet<EnumGUIPipelineCommand> remove, EnumSet<EnumGUIPipelineCommand> until)
    {
        var it = this.commands.descendingIterator();
        while (it.hasNext())
        {
            var val = it.next();
            var type = val.getType();

            if (until.contains(type))
                return;

            if (remove.contains(type))
                it.remove();
        }
    }

    private void mergeInto(LiCommand command)
    {
        LiCommand prev;

        while ((prev = this.commands.peekLast()) != null)
        {
            if (prev.getType() != command.getType() || !prev.supportsMerge(command))
                break;

            command = prev.merge(command);

            this.commands.removeLast();
        }

        this.commands.add(command);
    }

    public <T> T withCurrentTransform(Function<Matrix3fc, T> transform)
    {
        return transform.apply(this.transform);
    }

    public Stream<LiCommand> stream()
    {
        return this.commands.stream();
    }

    @NotNull
    @Override
    public Iterator<LiCommand> iterator()
    {
        return this.commands.iterator();
    }
}
