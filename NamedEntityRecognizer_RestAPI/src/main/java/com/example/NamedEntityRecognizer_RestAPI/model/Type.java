package com.example.NamedEntityRecognizer_RestAPI.model;

public enum Type {

    PERSON("Person"),
    CITY("City"),
    STATE_OR_PROVINCE("State_Or_Provience"),
    COUNTRY("Country"),
    EMAIL("Email"),
    TITLE("Title");

    private String type;
    Type(String type){
        this.type=type;
    }

    public String getName() {

        return type;
    }


}
