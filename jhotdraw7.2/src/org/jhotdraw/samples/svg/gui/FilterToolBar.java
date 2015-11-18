package org.jhotdraw.samples.svg.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.filters.BlackHoleFilterAction;
import org.jhotdraw.filters.GaussianBlurFilterAction;
import org.jhotdraw.filters.PixelFilterAction;
import org.jhotdraw.gui.plaf.palette.PaletteButtonUI;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 *
 * @author Dan/Kyzaq
 */
public class FilterToolBar extends AbstractToolBar{
    
    private SelectionComponentDisplayer displayer;
    
    public FilterToolBar(){
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        setName(labels.getString(getID() + ".toolbar"));
    }
    
    @Override
    public void setEditor(DrawingEditor newValue) {
        if (this.editor != null) {
            this.removePropertyChangeListener(getEventHandler());
        }
        this.editor = newValue;
        if (editor != null) {
            init();
            setDisclosureState(prefs.getInt(getID() + ".disclosureState", 1));
            this.addPropertyChangeListener(getEventHandler());
        }
    }
    
    @Override
    protected JComponent createDisclosedComponent(int state) {
        JPanel p = null;

        switch (state) {
            case 1:
                 {
                    p = createPanelLayout(p);
                    ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
                    
                    createGaussianBlurFilterButton(labels, p);
                    createPixelFilterActionButton(labels, p);
                    createBlackHoleFilterButton(labels, p);
                }
                break;
        }
        return p;
    }
    
    @Override
    protected int getDefaultDisclosureState() {
        return 1;
    }
    
    @Override
    protected String getID() {
        return "filter";
    }

        /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    // End of variables declaration  

    private void createGaussianBlurFilterButton(ResourceBundleUtil labels, JPanel p) {
        AbstractButton btn;
        GridBagConstraints gbc;
        btn = new JButton(new GaussianBlurFilterAction(editor));
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        btn.setText(null);
        labels.configureToolBarButton(btn, GaussianBlurFilterAction.ID);
        btn.putClientProperty("hideActionText", Boolean.TRUE);
        gbc = new GridBagConstraints();
        gbc.gridy = 2;
        gbc.insets = new Insets(3, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        p.add(btn, gbc);
    }

    private void createPixelFilterActionButton(ResourceBundleUtil labels, JPanel p) {
        AbstractButton btn;
        GridBagConstraints gbc;
        btn = new JButton(new PixelFilterAction(editor));
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        btn.setText(null);
        labels.configureToolBarButton(btn, PixelFilterAction.ID);
        btn.putClientProperty("hideActionText", Boolean.TRUE);
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.insets = new Insets(3, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        p.add(btn, gbc);
    }

    private void createBlackHoleFilterButton(ResourceBundleUtil labels, JPanel p) {
        AbstractButton btn;
        GridBagConstraints gbc;
        btn = new JButton(new BlackHoleFilterAction(editor));
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        btn.setText(null);
        labels.configureToolBarButton(btn, BlackHoleFilterAction.ID);
        btn.putClientProperty("hideActionText", Boolean.TRUE);
        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        p.add(btn, gbc);
    }

    private JPanel createPanelLayout(JPanel p) {
        p = new JPanel();
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(5, 5, 5, 8));
        GridBagLayout layout = new GridBagLayout();
        p.setLayout(layout);
        return p;
    }
}