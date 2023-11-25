package hu.progmasters.finalexam.quidditch.exceptionhandling;

public class CoachNotFoundByIdExcepiton extends RuntimeException {

    private final long COACH_ID;

    public CoachNotFoundByIdExcepiton(long COACH_ID) {
        this.COACH_ID = COACH_ID;
    }

    public long getCOACH_ID() {
        return COACH_ID;
    }
}
