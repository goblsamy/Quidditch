package hu.progmasters.finalexam.quidditch.exceptions;

public class CurrentIdAndGivenMatchesException extends RuntimeException {

    private long id;

    public CurrentIdAndGivenMatchesException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
