package hu.progmasters.finalexam.quidditch.exceptionhandling;

public class ClubNotFoundException extends RuntimeException {

    private final int CLUB_ID;

    public ClubNotFoundException(int CLUB_ID) {
        this.CLUB_ID = CLUB_ID;
    }

    public int getCLUB_ID() {
        return CLUB_ID;
    }
}
