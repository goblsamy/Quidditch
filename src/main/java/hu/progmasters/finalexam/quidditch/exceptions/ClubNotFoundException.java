package hu.progmasters.finalexam.quidditch.exceptions;

public class ClubNotFoundException extends RuntimeException {

    private long clubId;

    public ClubNotFoundException(long clubId) {
        this.clubId = clubId;
    }

    public long getClubId() {
        return clubId;
    }
}
