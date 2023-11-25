package hu.progmasters.finalexam.quidditch.exceptionhandling;

public class PlayernotFoundException extends  RuntimeException {

    private final long PLAYER_ID;

    public PlayernotFoundException(long PLAYER_ID) {
        this.PLAYER_ID = PLAYER_ID;
    }

    public long getPLAYER_ID() {
        return PLAYER_ID;
    }
}
