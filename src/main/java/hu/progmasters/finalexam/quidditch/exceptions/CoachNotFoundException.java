package hu.progmasters.finalexam.quidditch.exceptions;

public class CoachNotFoundException extends RuntimeException {

    private long coachId;

    public CoachNotFoundException(long coachId) {
        this.coachId = coachId;
    }

    public long getCoachId() {
        return coachId;
    }
}
