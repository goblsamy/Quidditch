package hu.progmasters.finalexam.quidditch.exceptions;

import hu.progmasters.finalexam.quidditch.domain.PlayerType;

public class NoFreePlaceOnPositionException extends RuntimeException {

    private PlayerType playerType;

    private int clubId;

    public NoFreePlaceOnPositionException(PlayerType playerType, int clubId) {
        this.playerType = playerType;
        this.clubId = clubId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
    public int getClubId() {
        return clubId;
    }
}
