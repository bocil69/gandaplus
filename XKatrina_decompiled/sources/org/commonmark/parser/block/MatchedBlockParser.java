package org.commonmark.parser.block;

import org.commonmark.parser.SourceLines;
/* loaded from: classes2.dex */
public interface MatchedBlockParser {
    BlockParser getMatchedBlockParser();

    SourceLines getParagraphLines();
}
