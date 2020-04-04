package com.ndthuan.nlp.viwordsegmenter.restservice.models;

import vn.pipeline.Sentence;
import vn.pipeline.Word;

import java.util.ArrayList;
import java.util.List;

public class TaggingResponse {
    private List<List<SegmentedWord>> sentences = new ArrayList<>();

    public TaggingResponse(List<Sentence> sentences) {
        for (Sentence sentence : sentences) {
            List<SegmentedWord> segmentedWords = new ArrayList<>();

            for (Word word : sentence.getWords()) {
                segmentedWords.add(new SegmentedWord(word.getForm(), word.getPosTag(), word.getNerLabel(), word.getDepLabel()));
            }

            this.sentences.add(segmentedWords);
        }
    }

    public List<List<SegmentedWord>> getSentences() {
        return sentences;
    }
}
