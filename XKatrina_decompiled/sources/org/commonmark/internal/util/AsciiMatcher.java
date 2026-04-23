package org.commonmark.internal.util;

import java.util.BitSet;
/* loaded from: classes2.dex */
public class AsciiMatcher implements CharMatcher {
    private final BitSet set;

    private AsciiMatcher(Builder builder) {
        this.set = builder.set;
    }

    @Override // org.commonmark.internal.util.CharMatcher
    public boolean matches(char c) {
        return this.set.get(c);
    }

    public Builder newBuilder() {
        return new Builder((BitSet) this.set.clone());
    }

    public static Builder builder() {
        return new Builder(new BitSet());
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private final BitSet set;

        private Builder(BitSet set) {
            this.set = set;
        }

        public Builder c(char c) {
            if (c > 127) {
                throw new IllegalArgumentException("Can only match ASCII characters");
            }
            this.set.set(c);
            return this;
        }

        public Builder range(char from, char toInclusive) {
            for (char c = from; c <= toInclusive; c = (char) (c + 1)) {
                c(c);
            }
            return this;
        }

        public AsciiMatcher build() {
            return new AsciiMatcher(this);
        }
    }
}
