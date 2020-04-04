package com.ndthuan.nlp.viwordsegmenter.restservice;

import com.ndthuan.nlp.viwordsegmenter.restservice.models.SegmentationRequest;
import com.ndthuan.nlp.viwordsegmenter.restservice.models.TaggingResponse;
import com.ndthuan.nlp.viwordsegmenter.restservice.models.SegmentationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.pipeline.Annotation;
import vn.pipeline.VnCoreNLP;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class WordSegmentationController {
    private VnCoreNLP tagger;

    public WordSegmentationController() throws IOException {
        tagger = new VnCoreNLP();
    }

    @PostMapping(value = "/v1/tagging")
    public TaggingResponse tagging(@RequestBody SegmentationRequest request) throws IOException {
        if (request.getText().length() > 0) {
            Annotation annotation = annotate(request.getText());

            return new TaggingResponse(annotation.getSentences());
        }

        return new TaggingResponse(new ArrayList<>());
    }

    @PostMapping(value = "/v1/segmenting")
    public SegmentationResponse segment(
            @RequestBody SegmentationRequest request,
            @RequestParam(name = "skipPunct", defaultValue = "0") boolean skipPunct
    ) throws IOException {
        if (request.getText().length() > 0) {
            Annotation annotation = annotate(request.getText());

            return new SegmentationResponse(annotation.getSentences(), skipPunct);
        }

        return new SegmentationResponse();
    }

    private Annotation annotate(String text) throws IOException {
        Annotation annotation = new Annotation(text);
        tagger.annotate(annotation);

        return annotation;
    }
}
