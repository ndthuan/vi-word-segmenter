package com.ndthuan.nlp.viwordsegmenter.restservice.models;

public class SegmentedWord {
    private String form;
    private String pos;
    private String ner;
    private String dep;

    public SegmentedWord(String form, String pos, String ner, String dep) {
        this.form = form;
        this.pos = pos;
        this.ner = ner;
        this.dep = dep;
    }

    public String getForm() {
        return form;
    }

    public String getPos() {
        return pos;
    }

    public String getNer() {
        return ner;
    }

    public String getDep() {
        return dep;
    }
}
