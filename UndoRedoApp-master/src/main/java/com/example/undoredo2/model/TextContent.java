package com.example.undoredo2.model;

import java.io.Serializable;

public class TextContent implements Serializable {
    
    private String content;
    
    public TextContent() {
        this.content = "";
    }
    
    public TextContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return content;
    }
    
    // Create a deep copy to ensure stack operations don't reference the same object
    public TextContent copy() {
        return new TextContent(this.content);
    }
}