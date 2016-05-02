package com.alexandeh.nebula;

import com.alexandeh.nebula.factions.commands.*;
import com.alexandeh.nebula.files.ConfigFile;
import com.alexandeh.nebula.profiles.ProfileListeners;
import com.alexandeh.nebula.utils.command.CommandFramework;
import com.alexandeh.nebula.utils.player.SimpleOfflinePlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
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
public class Nebula extends JavaPlugin {

    private static Nebula instance;

    private CommandFramework framework;
    private ConfigFile mainConfig;
    private ConfigFile langConfig;

    public void onEnable() {
        instance = this;

        mainConfig = new ConfigFile(this, "config");
        langConfig = new ConfigFile(this, "lang");

        framework = new CommandFramework(this);
        SimpleOfflinePlayer.load(this);

        registerListeners();
        registerCommands();
    }

    public void onDisable() {
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
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ProfileListeners(), this);
    }

    public static Nebula getInstance() {
        return instance;
    }
}
