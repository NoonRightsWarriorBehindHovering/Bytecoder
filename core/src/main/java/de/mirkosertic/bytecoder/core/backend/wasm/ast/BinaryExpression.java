/*
 * Copyright 2018 Mirko Sertic
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

public abstract class BinaryExpression implements WasmExpression {

    private final WasmValue leftValue;
    private final WasmValue rightValue;
    private final String textCode;
    private final byte binaryCode;

    protected BinaryExpression(final WasmValue leftValue, final WasmValue rightValue, final String textCode, final byte binaryCode) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.textCode = textCode;
        this.binaryCode = binaryCode;
    }

    @Override
    public final void writeTo(final TextWriter textWriter, final ExportContext context) throws IOException {
        textWriter.opening();
        textWriter.write(textCode);
        textWriter.space();
        leftValue.writeTo(textWriter, context);
        textWriter.space();
        rightValue.writeTo(textWriter, context);
        textWriter.closing();
    }

    @Override
    public final void writeTo(final BinaryWriter.Writer codeWriter, final ExportContext context) throws IOException {
        leftValue.writeTo(codeWriter, context);
        rightValue.writeTo(codeWriter, context);
        codeWriter.writeByte(binaryCode);
    }
}
