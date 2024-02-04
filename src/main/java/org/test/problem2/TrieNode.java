package org.test.problem2;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;
    int count;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.count = 0;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "children=" + children +
                ", isEndOfWord=" + isEndOfWord +
                ", count=" + count +
                '}';
    }
}
