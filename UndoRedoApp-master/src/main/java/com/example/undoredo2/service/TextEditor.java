package com.example.undoredo2.service;

import org.springframework.stereotype.Service;
import java.util.Stack;

@Service
public class TextEditor {
    private String currentText = "";
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    /**
     * Get the current text in the editor
     * @return current text
     */
    public String getCurrentText() {
        return currentText;
    }

    /**
     * Update the text in the editor
     * @param newText the new text to set
     */
    public void updateText(String newText) {
        if (newText.equals(currentText)) {
            return; // No change, no need to add to undo stack
        }
        
        undoStack.push(currentText);
        currentText = newText;
        redoStack.clear(); // Clear redo stack when a new change is made
    }

    /**
     * Check if undo operation is available
     * @return true if undo is available
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Check if redo operation is available
     * @return true if redo is available
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Perform undo operation
     * @return the text after undo
     */
    public String undo() {
        if (canUndo()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
        }
        return currentText;
    }

    /**
     * Perform redo operation
     * @return the text after redo
     */
    public String redo() {
        if (canRedo()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
        }
        return currentText;
    }
}