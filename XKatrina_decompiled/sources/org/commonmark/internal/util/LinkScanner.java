package org.commonmark.internal.util;

import org.commonmark.internal.inline.Scanner;
/* loaded from: classes2.dex */
public class LinkScanner {
    public static boolean scanLinkLabelContent(Scanner scanner) {
        while (scanner.hasNext()) {
            switch (scanner.peek()) {
                case '[':
                    return false;
                case '\\':
                    scanner.next();
                    if (!Parsing.isEscapable(scanner.peek())) {
                        break;
                    } else {
                        scanner.next();
                        break;
                    }
                case ']':
                    return true;
                default:
                    scanner.next();
                    break;
            }
        }
        return true;
    }

    public static boolean scanLinkDestination(Scanner scanner) {
        if (scanner.hasNext()) {
            if (scanner.next('<')) {
                while (scanner.hasNext()) {
                    switch (scanner.peek()) {
                        case '\n':
                        case '<':
                            return false;
                        case '>':
                            scanner.next();
                            return true;
                        case '\\':
                            scanner.next();
                            if (!Parsing.isEscapable(scanner.peek())) {
                                break;
                            } else {
                                scanner.next();
                                break;
                            }
                        default:
                            scanner.next();
                            break;
                    }
                }
                return false;
            }
            return scanLinkDestinationWithBalancedParens(scanner);
        }
        return false;
    }

    public static boolean scanLinkTitle(Scanner scanner) {
        char endDelimiter;
        if (scanner.hasNext()) {
            switch (scanner.peek()) {
                case '\"':
                    endDelimiter = '\"';
                    break;
                case '\'':
                    endDelimiter = '\'';
                    break;
                case '(':
                    endDelimiter = ')';
                    break;
                default:
                    return false;
            }
            scanner.next();
            if (scanLinkTitleContent(scanner, endDelimiter) && scanner.hasNext()) {
                scanner.next();
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean scanLinkTitleContent(Scanner scanner, char endDelimiter) {
        while (scanner.hasNext()) {
            char c = scanner.peek();
            if (c == '\\') {
                scanner.next();
                if (Parsing.isEscapable(scanner.peek())) {
                    scanner.next();
                }
            } else if (c == endDelimiter) {
                return true;
            } else {
                if (endDelimiter == ')' && c == '(') {
                    return false;
                }
                scanner.next();
            }
        }
        return true;
    }

    private static boolean scanLinkDestinationWithBalancedParens(Scanner scanner) {
        int parens = 0;
        boolean empty = true;
        while (scanner.hasNext()) {
            char c = scanner.peek();
            switch (c) {
                case ' ':
                    return !empty;
                case '(':
                    parens++;
                    if (parens <= 32) {
                        scanner.next();
                        break;
                    } else {
                        return false;
                    }
                case ')':
                    if (parens != 0) {
                        parens--;
                        scanner.next();
                        break;
                    } else {
                        return true;
                    }
                case '\\':
                    scanner.next();
                    if (!Parsing.isEscapable(scanner.peek())) {
                        break;
                    } else {
                        scanner.next();
                        break;
                    }
                default:
                    if (!Character.isISOControl(c)) {
                        scanner.next();
                        break;
                    } else {
                        return !empty;
                    }
            }
            empty = false;
        }
        return true;
    }
}
