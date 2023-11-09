package com.example.NamedEntityRecognizer_RestAPI.RestController;


import com.example.NamedEntityRecognizer_RestAPI.Services.PipeLine_Delegate;
import com.example.NamedEntityRecognizer_RestAPI.model.Type;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class NERController {


    @Autowired
    PipeLine_Delegate pipeLine_delegate;

    public PipeLine_Delegate getPipeLine_delegate() {
        return pipeLine_delegate;
    }

    public void setPipeLine_delegate(PipeLine_Delegate pipeLine_delegate) {
        this.pipeLine_delegate = pipeLine_delegate;
    }

    @PostMapping
@RequestMapping(value = "ner")
   public Set<String> ner(@RequestBody final String input, @RequestParam final Type type){

    CoreDocument coreDocument=new CoreDocument(input);
    standfordCoreNLP.annotate(coreDocument);

    List<CoreLabel> coreLabelList= coreDocument.tokens();


//    for(CoreLabel coreLabel: coreLabelList) {
//
//        String ner= coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//        //System.out.println(coreLabel.originalText() + " == "+ ner);
//
//    }
       return new HashSet<>(collectList(coreLabelList,type));
    }


    private List<String> collectList(List<CoreLabel> coreLabels, final Type type){
   return coreLabels
            .stream()
            .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
            .map(CoreLabel::originalText)
            .collect(Collectors.toList());
    }
}
