package parser;

import java.util.List;

public class Section {
    private String numbers;
    private List<String> delimiters;

    public Section(String numbers, List<String> delimiters) {
        this.numbers = numbers;
        this.delimiters = delimiters;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public List<String> getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(List<String> delimiters) {
        this.delimiters = delimiters;
    }
}
