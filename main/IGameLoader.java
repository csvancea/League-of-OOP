package main;

import angels.BasicAngel;
import heroes.BasicHero;
import map.GameMap;

import java.util.List;

public interface IGameLoader {
    GameMap getGameMap();
    List<BasicHero> getHeroesList();
    List<List<Character>> getRoundMoves();
    List<List<BasicAngel>> getRoundAngels();
}
