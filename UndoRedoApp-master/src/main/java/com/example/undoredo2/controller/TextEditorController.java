package com.example.undoredo2.controller;

import com.example.undoredo2.service.TextEditor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TextEditorController {

    @Autowired
    private TextEditor textEditor;

    @GetMapping("/")
    public String index(@NotNull Model model) {
        model.addAttribute("currentText", textEditor.getCurrentText());
        model.addAttribute("canUndo", textEditor.canUndo());
        model.addAttribute("canRedo", textEditor.canRedo());
        return "editor";
    }

    // REST API endpoints for AJAX calls
    
    @PostMapping("/api/update")
    @ResponseBody
    public Map<String, Object> updateText(@NotNull @RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        textEditor.updateText(text);
        return getEditorState();
    }

    @PostMapping("/api/undo")
    @ResponseBody
    public Map<String, Object> undo() {
        textEditor.undo();
        return getEditorState();
    }

    @PostMapping("/api/redo")
    @ResponseBody
    public Map<String, Object> redo() {
        textEditor.redo();
        return getEditorState();
    }

    @GetMapping("/api/state")
    @ResponseBody
    public Map<String, Object> getState() {
        return getEditorState();
    }

    // Helper method to create response with editor state
    @NotNull
    private Map<String, Object> getEditorState() {
        Map<String, Object> state = new HashMap<>();
        state.put("currentText", textEditor.getCurrentText());
        state.put("canUndo", textEditor.canUndo());
        state.put("canRedo", textEditor.canRedo());
        return state;
    }
}