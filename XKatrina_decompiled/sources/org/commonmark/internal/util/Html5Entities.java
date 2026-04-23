package org.commonmark.internal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class Html5Entities {
    private static final String ENTITY_PATH = "/org/commonmark/internal/util/entities.properties";
    private static final Map<String, String> NAMED_CHARACTER_REFERENCES = readEntities();

    public static String entityToString(String input) {
        if (input.startsWith("&") && input.endsWith(";")) {
            String value = input.substring(1, input.length() - 1);
            if (value.startsWith("#")) {
                String value2 = value.substring(1);
                int base = 10;
                if (value2.startsWith("x") || value2.startsWith("X")) {
                    value2 = value2.substring(1);
                    base = 16;
                }
                try {
                    int codePoint = Integer.parseInt(value2, base);
                    if (codePoint == 0) {
                        return "�";
                    }
                    return new String(Character.toChars(codePoint));
                } catch (IllegalArgumentException e) {
                    return "�";
                }
            }
            String s = NAMED_CHARACTER_REFERENCES.get(value);
            return s != null ? s : input;
        }
        return input;
    }

    private static Map<String, String> readEntities() {
        Map<String, String> entities = new HashMap<>();
        InputStream stream = Html5Entities.class.getResourceAsStream(ENTITY_PATH);
        Charset charset = StandardCharsets.UTF_8;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, charset));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else if (line.length() != 0) {
                    int equal = line.indexOf("=");
                    String key = line.substring(0, equal);
                    String value = line.substring(equal + 1);
                    entities.put(key, value);
                }
            }
            if (bufferedReader != null) {
                if (0 != 0) {
                    bufferedReader.close();
                } else {
                    bufferedReader.close();
                }
            }
            entities.put("NewLine", "\n");
            return entities;
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading data for HTML named character references", e);
        }
    }
}
