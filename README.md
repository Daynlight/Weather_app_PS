Napisz program działający w terminalu (konsoli, inaczej proram tesktowy), który będzie analizował dane meteorologiczne z linka (odczyty sensorów z sensor community z rejonu Warszawy)

https://data.sensor.community/airrohr/v1/filter/box=52.36734243199027,20.819494415027485,52.09692843752652,21.319390572461643
Model danych (wygenerowane online klasy z formatu json) można znaleźć tutaj:

https://mw.home.amu.edu.pl/zajecia/PRA2025/model.zip
Program powinien wypisywać dowolne statystyki z odczytanych danych wymyślone przez użytkownika (np średnie temp, wilgotność, liczbę miejsc o dużej temp, dużym zanieczyszczeniu powietrza itp) i mieć możliwość zapisania tych statystyl w trzech formatach do wyboru: pliku PDF, json lub xml. Wykorzystaj dowolną bibliotekę Javy do tworzenia plików PDF. Postać pliku jest dowolna.

Napisz testy do wyrzystywanych metod, skorzystaj obowiązkowo z zamockowania danych dla danych pobieranych z data.sensor.

W programie wykorzystaj strumienie oraz co najmniej jedną strukturę danych (set, map, list, stack, etc.).
