import os
from myfactorymodule import myfactory


def printGreeting(pet):
    print(pet.getname() + " pozdravlja: " + pet.greet())


def printMenu(pet):
    print(pet.getname() + " voli " + pet.menu())


def test():
    pets = []
    # Obiđi svaku datoteku kazala plugins
    for mymodule in os.listdir('zad1_2\plugins'):
        module_name, module_ext = os.path.splitext(mymodule)
        # Ako se radi o datoteci s Python kodom ...
        if module_ext == '.py':
            # Instanciraj ljubimca ...
            ljubimac = myfactory(module_name)('Ljubimac ' + str(len(pets)))
            # ... i dodaj ga u listu ljubimaca
            pets.append(ljubimac)

    # Ispiši ljubimce
    for pet in pets:
        printGreeting(pet)
        printMenu(pet)

test()
