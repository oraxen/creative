/*
 * This file is part of uracle, licensed under the MIT license
 *
 * Copyright (c) 2021-2022 Unnamed Team
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
package team.unnamed.uracle.model;

import team.unnamed.uracle.file.AssetWriter;

import java.util.Locale;
import java.util.Map;

abstract class AbstractModel
        implements Model {

    protected abstract void serializeOwnProperties(AssetWriter writer);

    @Override
    public void serialize(AssetWriter writer) {

        writer.startObject();

        // parent
        writer.key("parent").value(parent());

        // display
        writer.key("display").startObject();
        for (Map.Entry<ModelDisplay.Type, ModelDisplay> entry : display().entrySet()) {
            ModelDisplay.Type type = entry.getKey();
            ModelDisplay display = entry.getValue();

            writer.key(type.name().toLowerCase(Locale.ROOT));
            display.serialize(writer);
        }
        writer.endObject();

        // elements
        writer.key("elements").startArray();
        for (Element element : elements()) {
            element.serialize(writer);
        }
        writer.endArray();

        serializeOwnProperties(writer);
        writer.endObject();
    }

}
