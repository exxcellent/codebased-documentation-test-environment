# test-environment-for-evolutionary-architectures

Integrative Testumgebung für die Bachelorarbeit: 
- "Code-basierte Dokumentation evolutionärer Software-Architekturen"
- ...

Für die Testumgebung wird **Docker** und **Docker-Compose** verwendet. Zur Kontrolle, 
ob alle notwendigen Abhängigkeiten lokal installiert sind, 
kann `sh ./check.sh` aus den "Tools" verwendet werden.

#### Hinweis zur Nutzung:

Bitte die `env.conf.template` Datei kopieren und als `env.conf` speichern.
In der `env.conf` den absoluten Pfad zu diesem Repository eintragen, damit die folgenden Skripte funktionieren.

## Bogenliga Anwendung

Tags: _Maven, Spring Boot, Angular_

`sh ./run.sh bogenliga`


#### Weitere Infos:

- [Angular Application](http://localhost)
- [Swagger Doku](http://localhost:8080/swagger-ui.html)




## Fiktives Beispiel für eine BMW Microservice Anwendung

Tags: _Maven, JavaEE (Java 8), Glassfish Application Server_

`sh ./run.sh bmw`


Dieses Beispiel ist einer echten BMW-Anwendung nachempfunden. 
Die Anwendungsfälle sind allerdings frei empfunden und sollen nur Interaktion zwischen Microservices zeigen.
Es wurde lediglich der Technologie-Stack aus einem echten BMW-Projekt genutzt, 
um die codebasierten Dokumentation zu testen.

Drei Microservices kooperieren, um Dienstleistungen für Fahrzeuge zu erbringen.

- **Vehicle-Management (VEM)**
  bietet REST-Schnittstellen für eine Verwaltungsoberfläche an. 
  Anfragen werden an die beiden anderen Microservices weitergeleitet.
  
  - Anwendungsfall 1: Fahrzeuginformationen können per CRUD-Operationen abgefragt und verändert werden 
    (VEM => VEI).
  - Anwendungsfall 2: Zusätzlich wird eine Funktion angeboten, um einem Fahrzeug eine Nachricht zu schicken 
    (VEM => VES => VEI).
- **Vehicle-Services (VES)**
  bietet REST-Schnittstellen zur Kommunikation mit Fahrzeugen an.
  
  Fragt Fahrzeuginformationen vom VEI ab, um eine Nachricht an ein Fahrzeug schicken zu können (VES => VEI). 
- **Vehicle-Information (VEI)**
  verwaltet Informationen von Fahrzeugen.
  
  Der VEM und der VES nutzen die Informationen der Fahrzeuge für ihre Schnittstellen.
  
  
#### Weitere Infos:
- [Postman Collection](Codebasierte_Dokumentation.postman_collection.json) zum Aufruf der drei Service-Schnittstellen



## Tools

- `sh ./check.sh` Prüft, ob die notwendigen Kommandozeilentools installiert sind


- `sh ./docker-ps.sh all` Shortcut für docker-compose ps -f <Pfad zur docker-compose.yaml> für alle Testumgebungen
- `sh ./docker-ps.sh bogenliga` Shortcut für docker-compose ps -f <Pfad zur docker-compose.yaml der Bogenliga-Anwendung>
- `sh ./docker-ps.sh bmw` Shortcut für docker-compose ps -f <Pfad zur docker-compose.yaml des BMW-Anwendungsbeispiels>


- `sh ./docker-down.sh all` Shortcut für docker-compose down -f <Pfad zur docker-compose.yaml> für alle Testumgebungen
- `sh ./docker-down.sh bogenliga` Shortcut für docker-compose down -f <Pfad zur docker-compose.yaml der Bogenliga-Anwendung>
- `sh ./docker-down.sh bmw` Shortcut für docker-compose down -f <Pfad zur docker-compose.yaml des BMW-Anwendungsbeispiels>
