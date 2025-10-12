package org.example;

import java.awt.*;
import java.io.IOException;

public class FontPixelada {

    public static Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Frame.class.getResourceAsStream("/fonte.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
