package rach.android_swzom.framework;

import rach.android_swzom.framework.Graphics.PixmapFormat;


public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
