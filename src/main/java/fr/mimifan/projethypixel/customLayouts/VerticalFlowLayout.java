package fr.mimifan.projethypixel.customLayouts;

import java.awt.*;

public class VerticalFlowLayout extends FlowLayout {
    public VerticalFlowLayout() {
        super(FlowLayout.CENTER, 0, 0);
    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = super.preferredLayoutSize(target);
            int nmembers = target.getComponentCount();
            int x = 0;
            int y = 0;
            int rowHeight = 0;

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    if (x != 0) {
                        x += getHgap();
                    }
                    x += d.width;
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }
            dim.width = x;
            dim.height = y + rowHeight;

            return dim;
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = super.minimumLayoutSize(target);
            int nmembers = target.getComponentCount();
            int x = 0;
            int y = 0;
            int rowHeight = 0;

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getMinimumSize();
                    if (x != 0) {
                        x += getHgap();
                    }
                    x += d.width;
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }
            dim.width = x;
            dim.height = y + rowHeight;

            return dim;
        }
    }

    @Override
    public void layoutContainer(Container target) {
        synchronized (target.getTreeLock()) {
            int hgap = getHgap();
            int vgap = getVgap();
            Insets insets = target.getInsets();
            int maxwidth = target.getWidth()
                    - (insets.left + insets.right + hgap * 2);
            int nmembers = target.getComponentCount();
            int x = insets.left + hgap;
            int y = 0;
            int rowHeight = 0;

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    if ((x == insets.left + hgap) || ((x + d.width) <= maxwidth)) {
                        if (x > insets.left + hgap) {
                            x += hgap;
                        }
                        m.setBounds(x, y, d.width, d.height);
                        x += d.width;
                        rowHeight = Math.max(rowHeight, d.height);
                    } else {
                        x = insets.left + hgap;
                        y += vgap + rowHeight;
                        m.setBounds(x, y, d.width, d.height);
                        x += d.width;
                        rowHeight = d.height;
                    }
                }
            }
        }
    }
}
