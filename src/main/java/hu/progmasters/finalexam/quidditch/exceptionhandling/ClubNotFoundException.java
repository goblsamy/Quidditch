package hu.progmasters.finalexam.quidditch.exceptionhandling;

public class ClubNotFoundException extends RuntimeException {

    private final long CLUB_ID;

    public ClubNotFoundException(long CLUB_ID) {
        this.CLUB_ID = CLUB_ID;
    }

    public long getCLUB_ID() {
        return CLUB_ID;
    }
}
