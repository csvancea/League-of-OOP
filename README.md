Proiect POO - Etapa 1 & 2
=========================
Student: Cosmin-Razvan Vancea - 323 CA


## Urmatoarele pachete au fost create in Etapa 1:

* Tema a fost modularizata pe pachete, fiecare pachet avand un rol bine definit:
  * **main**:
    - Main:
      - punctul de intrare in program
    - GameLogic:
      - responsabila de structura jocului:
        - impartirea pe runde
        - ordinea actiunilor (DoT, mutare, aplicare strategii, atacare, ingeri)
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
        - un vector de `IEntity`, insemnand entitatile care se afla in
        acea celula. 
        - o instanta `ISurface` reprezentand tipul de suprafata (`Woods`,
        `Volcanic` etc)
  * **abilities**:
    - toate abilitatile jocului, impartite pe pachete in functie de eroul care
    le detine
    - aceasta impartire nu denota nicio restrictie, in sensul ca un erou poate
    avea orice abilitate, nu doar pe cele din pachetul sau
    - abilitatile implementeaza interfata: `IAbility`
    - exista si interfata `IPassive` care permite implementarea de actiuni
    ce vor afecta un erou overtime. Trebuie suprascrisa metoda `apply` cu
    logica efectului overtime. (ex: stun, damage etc)
  * **entities**
    - in acest pachet se gasesc implementarile entitatilor ce pot fi plasate
    pe harta. Momentan, acestea sunt: *erou* si *inger*
    - entitatile ce pot fi plasate pe harta trebuie sa implementeze `IEntity`
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

## Urmatoarele pachete au fost adaugate in Etapa 2:

  * **entities**:
    *  **strategies**:
        - BasicStrategy:
          - clasa abstracta reprezentand tipul de baza al strategiei
          - implementeaza structura pe care trebuie sa o aiba o strategie:
            - intre ce limite ale HP-ului se aplica
            - ce modifica asupra eroului
        - extinderi ale clasei `BasicStrategy`:
          - `KnightStrategy`, `PyromancerStrategy`, `RogueStrategy`, `WizardStrategy`
        - StrategyFactory:
          - factory pentru tipurile de strategii
    * **angels**:
      - BasicAngel:
        - clasa abstracta reprezentand tipul de baza al ingerului
        - implementeaza elemente ce sunt comune tuturor tipurilor de ingeri:
          - pozitie, observatori etc
      - AngelFactory:
        - factory pentru tipurile de ingeri
      - implementarea propriu-zisa a ingerilor se face in pachetele:
        - **good**
        - **evil**
      - fiecare implementare este responsabila sa notifice observatorii cand
      aplica o actiune pe un erou 
  * **admin**:
    - aici este facuta implementarea *Marelui Magician*
    - observatorii au fost implementati folosind `PropertyChangeListener`
    - clasele observabile sunt:
      * BasicAngel
      * BasicHero
    - observatorii sunt:
      * AngelObserver:
        - acest observator este notificat cand:
          - un inger se spawneaza;
          - un inger actioneaza asupra unui erou (help/hit).
      * HeroObserver:
        - acest observator este notificat cand:
          - un erou este ucis (exceptie DoT);
          - un erou este inviat;
          - este schimbat nivelul unui erou.
    - observatorii notifica *Marele Magician* (GrandMagician.java), iar
    acesta are rolul de a scrie informatiile in fisierul de iesire

    
## Concepte OOP (folosite la implementarea ambelor etape):

### Visitor:
  * Folosit pentru: 
    1. interactiunea dintre abilitatea atacantului si eroul atacat
      * L-am folosit in acest caz datorita *hero modifier*-ului ce difera
      de la erou la erou pentru fiecare abilitate
      * Logica: eroul atacat *accepta* abilitatea atacantului
    2. interactiunea dintre un inger si eroul ajutat/atacat
      * L-am folosit in acest caz deoarece fiecare inger ajuta/ataca in mod
      diferit eroii in functie de tipul lor
      * Logica: eroul *accepta* ajutorul/atacul ingerului 

### Factory:
  * Folosit pentru:
    1. crearea eroilor in functie de tipul lor
    2. crearea ingerilor in functie de tipul lor
    3. crearea strategiilor in functie de eroul care le aplica
    4. crearea suprafetelor de pe harta
    5. crearea marelui magician
    6. crearea observatorilor

### Observer:
  * Folosit pentru implementarea *Marelui Magician* (denumit *admin* in cod)
  * clasele observabile sunt:
    * BasicAngel
    * BasicHero
  * observatorii sunt:
    * AngelObserver:
      - acest observator este notificat cand:
        - un inger se spawneaza;
        - un inger actioneaza asupra unui erou (help/hit).
    * HeroObserver:
      - acest observator este notificat cand:
        - un erou este ucis (exceptie DoT);
        - un erou este inviat;
        - este schimbat nivelul unui erou.

### Strategy:
  * Folosit pentru alegerea unei strategii de joc in functie de tipul de erou
  care o va aplica.

### Depencency injection:
  * Folosit pentru interactiunea intre clase.
  * Clase ce folosesc depencency injection:
    * BasicHero -> se injecteaza:
      - GameMap
    * BasicAngel -> se injecteaza:
      - GameMap      
    * GrandMagician -> se injecteaza:
      - FileWriter
    * FileGameLoader -> se injecteaza:
      - FileReader
    * GameLogic -> se injecteaza:
      - IGameLogic
      - FileWriter

### Singleton:
  * Folosit pentru:
    1. instantierea Hartii:
      * Initial, in etapa 1 nu am folosit singleton pentru harta deoarece am
      considerat ca in etapa viitoare ar putea exista putea avea mai multe
      harti, asa ca, in schimb, entitatile ce pot fi plasate pe harta vor tine
      o referinta la harta pe care se afla.
      * In etapa 2, eroii tin in continuare o referinta la harta, singura
      diferenta fiind ca aceasta este *instantiata* prin singleton, adica se
      apeleaza metoda statica `GameMap.getInstance()`, iar harta rezultata este
      injectata eroului (folosind conceptul depencency injection)
    2. instantierea *Marelui Magician* si a observatorilor asociati

### Mosteniri/interfete:
  1. pentru tipurile de eroi
  2. pentru tipurile de ingeri
  3. pentru tipurile de strategii
  4. pentru tipurile de suprafata
  5. pentru tipurile de incarcare de date
  6. pentru abilitati
  7. pentru tipurile de entitati ce pot fi plasate pe harta
