package com.bizarrealex.ekko;

import com.bizarrealex.ekko.factions.claims.ClaimListeners;
import com.bizarrealex.ekko.factions.commands.*;
import com.bizarrealex.ekko.factions.commands.FactionDepositCommand;
import com.bizarrealex.ekko.factions.commands.officer.FactionWithdrawCommand;
import com.bizarrealex.ekko.factions.commands.leader.FactionDemoteCommand;
import com.bizarrealex.ekko.factions.commands.leader.FactionDisbandCommand;
import com.bizarrealex.ekko.factions.commands.leader.FactionLeaderCommand;
import com.bizarrealex.ekko.factions.commands.leader.FactionPromoteCommand;
import com.bizarrealex.ekko.factions.commands.officer.*;
import com.bizarrealex.ekko.factions.type.PlayerFaction;
import com.bizarrealex.ekko.files.ConfigFile;
import com.bizarrealex.ekko.listeners.ChatListeners;
import com.bizarrealex.ekko.listeners.ScoreboardListeners;
import com.bizarrealex.ekko.profiles.Profile;
import com.bizarrealex.ekko.profiles.ProfileListeners;
import com.bizarrealex.ekko.utils.command.CommandFramework;
import com.bizarrealex.ekko.utils.player.PlayerUtility;
import com.bizarrealex.ekko.utils.player.SimpleOfflinePlayer;
import com.bizarrealex.ekko.factions.commands.*;
import com.bizarrealex.ekko.factions.commands.officer.*;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/*
 * Copyright (c) 2016, Alexander Maxwell. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - The name of Alexander Maxwell may not be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
@Getter
public class Ekko extends JavaPlugin {

    private static Ekko instance;

    private CommandFramework framework;
    private ConfigFile mainConfig;
    private ConfigFile langConfig;
    private Economy economy;

    public void onEnable() {
        instance = this;

        mainConfig = new ConfigFile(this, "config");
        langConfig = new ConfigFile(this, "lang");

        framework = new CommandFramework(this);
        economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        SimpleOfflinePlayer.load(this);
        PlayerFaction.runTasks(this);
        Profile.sendTabUpdate();

        registerListeners();
        registerCommands();
    }

    public void onDisable() {

        for (Player player : PlayerUtility.getOnlinePlayers()) {
            player.setScoreboard(getServer().getScoreboardManager().getNewScoreboard());
        }

        try {
            SimpleOfflinePlayer.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        new FactionHelpCommand();
        new FactionDisbandCommand();
        new FactionCreateCommand();
        new FactionVersionCommand();
        new FactionInviteCommand();
        new FactionJoinCommand();
        new FactionRenameCommand();
        new FactionPromoteCommand();
        new FactionDemoteCommand();
        new FactionLeaderCommand();
        new FactionUninviteCommand();
        new FactionChatCommand();
        new FactionSetHomeCommand();
        new FactionMessageCommand();
        new FactionAnnouncementCommand();
        new FactionLeaveCommand();
        new FactionShowCommand();
        new FactionKickCommand();
        new FactionInvitesCommand();
        new FactionAllyCommand();
        new FactionEnemyCommand();
        new FactionDepositCommand();
        new FactionWithdrawCommand();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ProfileListeners(), this);
        getServer().getPluginManager().registerEvents(new ScoreboardListeners(), this);
        getServer().getPluginManager().registerEvents(new ChatListeners(), this);
        getServer().getPluginManager().registerEvents(new ClaimListeners(), this);
    }

    public static Ekko getInstance() {
        return instance;
    }
}
