package com.example.NamedEntityRecognizer_RestAPI.Services;


import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class PipeLine_Delegate {



    private static StanfordCoreNLP stanfordCoreNLP;
    private static String propertiesName="tokenize, ssplit, pos, lemma, ner , parse, sentiment";

    private static Properties properties;

    public PipeLine_Delegate(){

    }

    static {
        properties= new Properties();
        properties.setProperty("annotators",propertiesName);
    }
@Bean(name = "standfordCoreNLP")
    public static StanfordCoreNLP getPipeline(){
        if(stanfordCoreNLP==null){
            stanfordCoreNLP= new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }

}
