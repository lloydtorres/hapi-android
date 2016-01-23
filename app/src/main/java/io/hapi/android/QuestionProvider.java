package io.hapi.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.hapi.android.models.Question;

import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;

public class QuestionProvider {
    private static final List<Question> QUESTION_LIST = new ArrayList<Question>() {{
        add(new Question("How was your day?", false));
        add(new Question("What was the highlight of your day?", false));
        add(new Question("What was the lowpoint of your day?", false));
        add(new Question("Did something new happen today?", false));
        add(new Question("Did you eat well today?", true));
        add(new Question("Did you drink coffee today?", true));
        add(new Question("Did you have breakfast today?", true));
        add(new Question("Are you at work right now?", true));
        add(new Question("Would you say you're happy right now?", true));
    }};

    public static List<Question> getQuestionList() {
        return QUESTION_LIST;
    }

    public static List<Question> threeRandomQuestions() {
        return new ArrayList<Question>() {{
            List<Integer> indices = Arrays.asList(0, 1, 2);
            Collections.shuffle(indices);
            for (int index : indices) add(QUESTION_LIST.get(index));
        }};
    }
}
