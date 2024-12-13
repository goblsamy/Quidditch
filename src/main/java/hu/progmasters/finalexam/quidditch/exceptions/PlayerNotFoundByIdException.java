package hu.progmasters.finalexam.quidditch.exceptions;

public class PlayerNotFoundByIdException extends RuntimeException {

    private long playerId;

    public PlayerNotFoundByIdException(Long playerId) {
        this.playerId = playerId;
    }

    public long getId() {
        return playerId;
    }
}

