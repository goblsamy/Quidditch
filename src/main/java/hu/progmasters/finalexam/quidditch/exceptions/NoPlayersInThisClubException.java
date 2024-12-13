package hu.progmasters.finalexam.quidditch.exceptions;

public class NoPlayersInThisClubException extends RuntimeException {

    private long coachId;

    public NoPlayersInThisClubException(long clubId) {
        this.coachId = clubId;
    }

    public long getClubId() {
        return coachId;
    }
}
