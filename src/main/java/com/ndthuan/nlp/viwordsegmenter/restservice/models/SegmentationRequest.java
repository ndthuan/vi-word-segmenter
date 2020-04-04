package com.ndthuan.nlp.viwordsegmenter.restservice.models;

public class SegmentationRequest {
    private String text;

    public SegmentationRequest() {
    }

    public SegmentationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
