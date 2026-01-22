# week1-kotlinperusteet

# Compose-tilanhallinta
Jetpack Compose käyttää deklaratiivista käyttöliittymämallia, jossa UI kuvataan tilan funktiona. 
Kun tila muuttuu, Compose piirtää automaattisesti vain ne käyttöliittymän osat uudelleen, jotka riippuvat muuttuneesta tilasta.
Compose seuraa tilaa State<T>-olioiden avulla. Kun Composable lukee tilaa, Compose rekisteröi riippuvuuden kyseisen tilan ja Composablen välille. Kun tila päivittyy, Compose käynnistää recomposition eli käyttöliittymän uudelleenlaskennan.

# Miksi ViewModel on parempi kuin pelkkä remember.
ViewModel on parempi kuin pelkkä remember, koska se säilyttää tilan oikein elinkaarimuutoksissa ja erottaa tilalogiikan käyttöliittymästä.
Kun näyttöä kiertää konfiguraatio pysyy ennallaan ja säilyttää rotaation. Tällöin sovellusta ei ole sidottu UI:n uudelleenpiirtämiseen.

# miten ajetaan
Avaa projekti android studiossa,
valitse Open an existing project ja navigoi projektiin (week1),
käynnistä Android Emulator (klikkaa RUN (vihreä nuoli)),
Sovellus asentuu laitteelle ja käynnistyy automaattisesti.

Käynnistä release APK(valinnainen)
valitse Build -> build Bundles / APKs -> Build APKs
valitse release ja allekirjoita APK
Asenna APK laitteelle ja avaa sovellus normaalisti
