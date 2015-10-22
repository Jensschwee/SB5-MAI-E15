/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw.action;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;
import org.jhotdraw.draw.CompositeFigure;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.samples.svg.figures.SVGGroupFigure;
import org.jhotdraw.samples.svg.gui.SelectionComponentDisplayer;
import org.jhotdraw.util.ResourceBundleUtil;
import sun.java2d.loops.ScaledBlit;

/**
 *
 * @author Jens Schwee
 */
public class ApplyScaleButton extends AbstractSelectedAction {

    private ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels", Locale.getDefault());
    
    private Grouping group;
    private CompositeFigure prototype;

    
    public ApplyScaleButton(DrawingEditor editor, CompositeFigure prototype) {
        super(editor);
        labels.configureAction(this, "edit.applyScale");
        group = new Grouping();
        this.prototype = prototype;
        updateEnabledState();
    }
    
    @Override
    protected void updateEnabledState() {
        if (getView() != null) {
            setEnabled(getView().getSelectionCount() > 0 ? true : false);
        } else {
            setEnabled(false);
        }
    }
    
    protected boolean canGroup() {
        if(getView() != null)
        {
            //If the selected is only one group.
            if(getView().getSelectionCount() == 1 && getView().getSelectedFigures().iterator().next().getClass().equals(prototype.getClass()))
                return false;
            else if (getView().getSelectionCount() >= 1)
                return true;
        }
        return false;
    }
    
    protected boolean canUngroup() {
        return getView() != null &&
                getView().getSelectionCount() == 1 &&
                prototype != null && 
                getView().getSelectedFigures().iterator().next().getClass().equals(
                prototype.getClass());
    }

    public void actionPerformed(ActionEvent e) {
        if(canGroup())
        {
            fireUndoableEditHappened(group.groupAction(getView(), prototype));
        }
        else if(group.canUngroup(getView(), prototype))
        {
            fireUndoableEditHappened(group.ungroupAction(getView()));
        }
    }

    /*@SuppressWarnings("unchecked")
     public void applyAttributes() {
     DrawingEditor editor = getEditor();

     CompositeEdit edit = new CompositeEdit(labels.getString("edit.applyAttrbutes.text"));
     DrawingView view = getView();
     view.getDrawing().fireUndoableEditHappened(edit);

     for (Figure figure : view.getSelectedFigures()) {
     figure.willChange();
     for (Map.Entry<AttributeKey, Object> entry : editor.getDefaultAttributes().entrySet()) {
     if (!excludedAttributes.contains(entry.getKey())) {
     entry.getKey().basicSet(figure, entry.getValue());
     }
     }
     figure.changed();
     }
     view.getDrawing().fireUndoableEditHappened(edit);
     }

     public void selectionChanged(FigureSelectionEvent evt) {
     setEnabled(getView().getSelectionCount() == 1);
     }
     */
}
