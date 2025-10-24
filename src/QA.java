public class QA {
    private final String question;
    private final String answer;

    public QA(String question, String answer) {
        this.question = question == null ? "" : question.trim();
        this.answer   = answer   == null ? "" : answer.trim();
    }

    public String getQuestion() { return question; }
    public String getAnswer()   { return answer; }
}

