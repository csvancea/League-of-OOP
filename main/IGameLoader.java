package main;

import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import map.GameMap;

import java.util.List;

public interface IGameLoader {
    GameMap getGameMap();
    List<BasicHero> getHeroesList();
    List<List<Character>> getRoundMoves();
    List<List<BasicAngel>> getRoundAngels();
}
