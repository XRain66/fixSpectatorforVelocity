package com.example.spectatorfix;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.ServerPreConnectEvent;
import com.velocitypowered.api.event.player.GameProfileRequestEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "spectatorfix",
        name = "SpectatorFix",
        version = "1.0-SNAPSHOT",
        description = "Fixes spectator mode issues in Velocity",
        authors = {"XRain666"}
)
public class SpectatorFix {
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public SpectatorFix(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("SpectatorFix plugin has been enabled!");
    }

    @Subscribe
    public void onGameProfileRequest(GameProfileRequestEvent event) {
        // 确保玩家的游戏配置文件不会处于观察者模式
        event.setGameMode("survival");
    }

    @Subscribe
    public void onServerPreConnect(ServerPreConnectEvent event) {
        // 在服务器切换时重置游戏模式，防止卡在观察者模式
        event.getPlayer().setGameMode("survival");
    }
}
