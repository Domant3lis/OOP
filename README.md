# OOP
University OOP course.

## Semester task
4. Personal note system: addresses, notes, tasks, calendar.

This project uses maven build system.

To build and run: `mvn compile exec:java`

### [Task I.2](https://github.com/Domant3lis/OOP/commit/8c739fac20d0ae6f54b1837485689685ea0bdb0b)
- [x] konstruktoriai, iš kurių vieną beargumentis, panaudoti this() konstrukciją
- [x] laukai, kuriems priega užtikrinama get/set metodais. Bent vienas laukas turi būti inicijuotas pradine reikšme.
- [x] (nestatinius) metodus. Bent vienas metodas turi būti perkrautas (overloaded)
- [x] Apibrėžti metodą println(), kuris išveda objekto turinį į išvedimo srautą
- [x] Įtraukti į klasės apibrėžimą ir prasmingai panaugoti static bei final elementus
- [x] Apibrėžti kitą (testinę klasę), kuri sukurtų pirmosios klasės objetus, jais pasinaudotų, kviesdama metodus, ir išvedinėtų laukų būsenas.

### [Task II.1](https://github.com/Domant3lis/OOP/commit/d2159281d74772ba2e6aba5ac209bc0246eba340)
Išvestinės klasės privalo:
- [X] Pasinaudoti _bazinės klasės_ konstruktoriumi super() 
	- [X] bei super-metodu.
- [X] Turėti papildomų metodų ir laukų
- [X] Užkloti Object metodą toString() ir dar bent vieną metodą
- [X] Kitos klasės privalo pasinaudoti sukurtų klasių polimorfiniu elgesiu
	(kviesti užklotus metodus bazinio tipo nuorodai)
- [X] Bazinė klasė privalo turėti metodų, kuriuos draudžiama užkloti
- [X] Visos projekto klasės privalo priklausyti bent 2 skirtingiems paketams

### [Task II.2](https://github.com/Domant3lis/OOP/commit/162cbbaca7d91ecf85fedbfc4a15a578bf786326)
- [x] Sukurti (netuščią) interfeisą (ar panaudoti jau egzistuojantį - standartinį), išreiškiantį kurios nors parašytos klasės funkcionavimo aspektą.
- [x] Interfeisą išplėsti (extends) kitu. 
- [x] Šį realizuoti abstrakčiąja klase, o pastarąją - konkrečiąja. 
Atkreipti dėmesį, kad konkrečiosios klasės funkcionalumo panaudojimas turi remti kiek įmanoma abstraktesne klase (interfeisu). Galite panaudoti jau egzistuojančią klasių hierarchiją.
0c517407792a559254541f6f6420853efe8ae92f
### [Task III.1](https://github.com/Domant3lis/OOP/commit/0c517407792a559254541f6f6420853efe8ae92f)
- [x] Apibrėžti bazinę išimties klasę savo projektui, išvestą iš Exception.
- [x] Apibrėžti išvestinę išimties klasę su patikslinančiąja informaciją.
- [x] Kitos klasės metodams deklaruoti (throws) metamas išimtis ir esant neteisingam kreipiniui jas iššaukti (throw).
- [x] Testinėje (main) programos klasėje gaudyti metamas išimtis, parūpinant vartotoją diagnostine informacija.

### Task III.2
- [x] Parinkti bent vieną klasę, kurios objektą būtų prasminga klonuoti ir paruošti (giliam) klonavimui.
- [x] Kitoje klasėje klonavimą prasmingai panaudoti.
- [ ] Identifikuoti tinkamą situaciją ir prasmingai panaudoti vieną iš aukščiau minėtųjų objektų kūrimo šablonų.