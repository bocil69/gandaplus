package org.commonmark.internal;

import org.commonmark.node.Block;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class ListItemParser extends AbstractBlockParser {
    private final ListItem block = new ListItem();
    private int contentIndent;
    private boolean hadBlankLine;

    public ListItemParser(int contentIndent) {
        this.contentIndent = contentIndent;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canContain(Block childBlock) {
        if (this.hadBlankLine) {
            Block parent = this.block.getParent();
            if (parent instanceof ListBlock) {
                ((ListBlock) parent).setTight(false);
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState state) {
        if (state.isBlank()) {
            if (this.block.getFirstChild() == null) {
                return BlockContinue.none();
            }
            Block activeBlock = state.getActiveBlockParser().getBlock();
            this.hadBlankLine = (activeBlock instanceof Paragraph) || (activeBlock instanceof ListItem);
            return BlockContinue.atIndex(state.getNextNonSpaceIndex());
        } else if (state.getIndent() >= this.contentIndent) {
            return BlockContinue.atColumn(state.getColumn() + this.contentIndent);
        } else {
            return BlockContinue.none();
        }
    }
}
