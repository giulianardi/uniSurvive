# 🎓 UniSurvive: Simulatore di Vita Universitaria

UniSurvive è un Gioco di Ruolo (RPG) gestionale a turni sviluppato in Java.
Il giocatore veste i panni di uno studente universitario e deve gestire strategicamente il proprio tempo,
le finanze, le energie e lo stress per affrontare gli esami e ottenere i CFU necessari alla laurea,
evitando il Game Over per "Burnout".

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle

### Istruzioni

```bash
git clone https://github.com/giulianardi/uniSurvive.git
cd uniSurvive
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

---

## 🤖 Uso di strumenti di AI

Nella realizzazione del progetto UniSurvive, gli strumenti di Intelligenza Artificiale sono stati impiegati in modo mirato
ed esclusivamente come supporto, revisione e bilanciamento, garantendo che ogni singola riga di codice e logica fosse pienamente padroneggiata.

Nello specifico, ho utilizzato Gemini per:

- Revisione e Supporto Architetturale: Validazione delle scelte di design e refactoring per garantire il disaccoppiamento dei componenti e il rigoroso rispetto dei principi SOLID.

- Game Design e Bilanciamento delle Statistiche: L'AI è stata impiegata come supporto per generare e bilanciare i valori numerici associati alle meccaniche di gioco. Ad esempio, è stata utilizzata per calcolare rapporti equilibrati tra le azioni (es. quanto denaro si ottiene con l'attività lavorativa, quanta energia si consuma studiando o di quanto si riduce lo stress durante un'uscita di svago), assicurando così un gameplay sfidante ma corretto.

- Troubleshooting: Risoluzione mirata di errori in fase di compilazione e supporto nella configurazione di Gradle.

📌 Per una descrizione più dettagliata delle scelte progettuali e dell'uso dell'AI, consultare la Wiki del repository.










