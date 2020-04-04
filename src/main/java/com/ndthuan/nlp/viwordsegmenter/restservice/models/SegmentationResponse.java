package com.ndthuan.nlp.viwordsegmenter.restservice.models;

import vn.pipeline.Sentence;
import vn.pipeline.Word;

import java.util.ArrayList;
import java.util.List;

public class SegmentationResponse {
    private static final String PUNCT_DEP = "punct";

    private List<String> sentences = new ArrayList<>();

    public SegmentationResponse() {
    }

    public SegmentationResponse(List<Sentence> sentences, boolean skipPunctuation) {
        for (Sentence sentence : sentences) {
            this.sentences.add(normalizeSentence(sentence, skipPunctuation));
        }
    }

    public List<String> getSentences() {
        return sentences;
    }

    private String normalizeSentence(Sentence sentence, boolean skipPunct) {
        List<String> words = new ArrayList<>();

        for (Word word : sentence.getWords()) {
            if (skipPunct && PUNCT_DEP.equals(word.getDepLabel())) {
                continue;
            }

            words.add(word.getForm());
        }

        return String.join(" ", words);
    }
}
