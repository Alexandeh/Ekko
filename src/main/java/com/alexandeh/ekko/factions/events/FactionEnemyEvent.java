package com.alexandeh.ekko.factions.events;

import com.alexandeh.ekko.factions.type.PlayerFaction;
import lombok.Getter;

/**
 * Copyright 2016 Alexander Maxwell
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Alexander Maxwell
 */
@Getter
public class FactionEnemyEvent extends FactionEvent {

    private PlayerFaction[] factions;

    public FactionEnemyEvent(PlayerFaction[] factions) {
        this.factions = factions;
    }

}
