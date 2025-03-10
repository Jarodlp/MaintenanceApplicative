package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Questions {
    public static ArrayList<String> CATEGORIES = new ArrayList<String>(List.of( "Rock", "Pop", "Science", "Sports"));
    private final HashMap<String, LinkedList<String>> questions = new HashMap<>();

    public Questions() {
        for (String category : CATEGORIES) {
            questions.put(category, new LinkedList<>());
        }
        for (int i = 0; i < 50; i++) {
            for (String category : CATEGORIES) {
                questions.get(category).addLast(category + " Question " + i);
            }
        }
    }

    public void getNextQuestion(String category) {
        System.out.println(questions.get(category).removeFirst());
    }
}