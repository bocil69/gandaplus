package org.commonmark.parser.block;

import org.commonmark.parser.SourceLine;
/* loaded from: classes2.dex */
public interface ParserState {
    BlockParser getActiveBlockParser();

    int getColumn();

    int getIndent();

    int getIndex();

    SourceLine getLine();

    int getNextNonSpaceIndex();

    boolean isBlank();
}
