# Tri-Sport Lübeck App

Kleine Android-WebView-App für den internen Tri-Sport-Lübeck-Bereich.

Startseite:
https://intern.trisport-luebeck.de/

## APK ohne Android Studio bauen

Das Repository enthält einen GitHub-Actions-Workflow.

1. Projekt in ein GitHub-Repository hochladen.
2. Repository öffnen.
3. **Actions** → **Build APK**.
4. **Run workflow** auswählen.
5. Nach dem Build das Artefakt `TriSport-Luebeck-debug` herunterladen.
6. Darin liegt `app-debug.apk`.

Die App speichert WebView-Cookies und DOM-Speicher, sodass ein Login innerhalb der App erhalten bleiben kann.
