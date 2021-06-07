package org.spawny.dbflowtest;

import java.util.List;

/**
 * Created by iaindownie on 07/13/2016.
 */
public class User {

    private String name;
    private int age;
    private List<String> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    //getters and setters
}