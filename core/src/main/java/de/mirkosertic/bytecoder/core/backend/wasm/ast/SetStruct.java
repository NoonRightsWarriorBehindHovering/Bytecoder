/*
 * Copyright 2023 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.core.backend.wasm.ast;

import java.io.IOException;

public class SetStruct implements WasmExpression {

    private final StructType structType;

    private final WasmValue target;

    private final String fieldName;

    private final WasmValue value;

    SetStruct(final StructType structType, final WasmValue target, final String fieldName, final WasmValue value) {
        this.structType = structType;
        this.target = target;
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public void writeTo(final TextWriter writer, final ExportContext context) throws IOException {
        writer.opening();
        writer.write("struct.set $");
        writer.write(structType.getName());
        writer.write(" $");
        writer.write(fieldName);
        writer.space();
        target.writeTo(writer, context);
        writer.space();
        value.writeTo(writer, context);
        writer.closing();
        writer.newLine();
    }

    @Override
    public void writeTo(final BinaryWriter.Writer binaryWriter, final ExportContext context) throws IOException {
        target.writeTo(binaryWriter, context);
        value.writeTo(binaryWriter, context);
        binaryWriter.writeByte((byte) 0xfb);
        binaryWriter.writeByte((byte) 0x06);
        binaryWriter.writeUnsignedLeb128(structType.index());
        binaryWriter.writeUnsignedLeb128(structType.indexOfField(fieldName));
    }
}
