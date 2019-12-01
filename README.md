Proiect POO - Etapa 1
=====================
Student: Cosmin-Razvan Vancea - 323 CA


# Pachete:

* Tema a fost modularizata pe pachete, fiecare pachet avand un rol bine definit:
  * **main**:
    - Main:
      - punctul de intrare in program
    - GameLogic:
      - responsabila de structura jocului:
        - impartirea pe runde
        - ordinea actiunilor (mutare, DoT, atacare etc)
      - datele despre joc sunt incarcate de o alta clasa `Loader`
      - clasa `Loader` trebuie sa implementeze interfata `IGameLoader`
      - folosind aceasta structura, datele pot fi incarcate din orice mediu
      (stdin, fisier, internet etc), atat timp cat se implementeaza `IGameLoader`
    - FileGameLoader:
      - implementeaza `IGameLoader`
      - responsabila cu incarcarea datele din fisier
  * **map**:
    - Map:
      - reprezinta harta jocului
      - fiecare celula `Cell` a hartii tine referinta la:
        - un vector de `IMapEntity`, insemnand entitatile care se afla in
        acea celula. Momentan doar `BasicHero` implementeaza aceasta clasa 
        - o instanta `ISurface` reprezentand tipul de suprafata (`Woods`,
        `Volcanic` etc)
  * **heroes**:
    - BasicHero:
      - clasa abstracta reprezentand tipul de baza al eroului
      - implementeaza elementele ce sunt comune tuturor tipurilor de eroi:
        - XP, Level, HP etc
        - logica de penalty passive (actiuni ce au efect overtime)
    - extinderi ale clasei `BasicHero`:
      - `Knight`, `Pyromancer`, `Rogue`, `Wizard`
    - HeroFactory:
      - factory pentru tipurile de eroi
  * **abilities**:
    - toate abilitatile jocului, impartite pe pachete in functie de eroul care
    le detine
    - aceasta impartire nu denota nicio restrictie, in sensul ca un erou poate
    avea orice abilitate, nu doar pe cele din pachetul sau
    - abilitatile implementeaza interfata: `IAbility`
    - exista si interfata `IPassive` care permite implementarea de actiuni
    ce vor afecta un erou overtime. Trebuie suprascrisa metoda `apply` cu
    logica efectului overtime. (ex: stun, damage etc)


# Concepte OOP: 

## Double-dispatch:
  * Folosit pentru interactiunea dintre abilitate atacantului si eroul atacat
  * Am folosit-o in acest caz datorita *hero modifier*-ului ce difera
  de la erou la erou pentru fiecare abilitate
  * Logica: eroul atacat *accepta* abilitatea atacantului

## Factory:
  * Folosit pentru crearea eroilor in functie de tipul lor
  * Folosit pentru crearea suprafetelor de pe harta

## Mosteniri/interfete:
  * pentru tipurile de eroi
  * pentru tipurile de suprafata
  * pentru tipurile de incarcare de date
  * pentru abilitati
  * pentru tipurile de entitati ce pot fi plasate pe harta

## Singleton:
  * Nu am folosit singleton pentru harta deoarece am considerat ca in etapa
  viitoare ar putea exista putea avea mai multe harti, asa ca, in schimb,
  entitatile ce pot fi plasate pe harta (momentan doar `BasicHero`) vor tine
  o referinta la harta pe care se afla.
