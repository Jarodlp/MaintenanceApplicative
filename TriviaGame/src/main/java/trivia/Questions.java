package trivia;

import java.util.LinkedList;

public class Questions {
    private LinkedList<String> popQuestions = new LinkedList<>();
    private LinkedList<String> scienceQuestions = new LinkedList<>();
    private LinkedList<String> sportsQuestions = new LinkedList<>();
    private LinkedList<String> rockQuestions = new LinkedList<>();

    public Questions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void getNextQuestion(String category) {
        switch (category) {
            case "Pop" :
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
            default:
                throw new IllegalArgumentException(category + " non valide");
        }
    }
}