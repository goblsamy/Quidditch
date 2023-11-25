package hu.progmasters.finalexam.quidditch.domain;

import javax.persistence.criteria.CriteriaBuilder;

public enum PlayerType {

    CHASER(3),
    BEATER(2),
    KEEPER(1),
    SEEKER(1);

    private Integer maxPlayerFromType;

    PlayerType(Integer maxPlayerFromType) {
        this.maxPlayerFromType = maxPlayerFromType;
    }

    public Integer getMaxPlayerFromType() {
        return maxPlayerFromType;
    }
}
