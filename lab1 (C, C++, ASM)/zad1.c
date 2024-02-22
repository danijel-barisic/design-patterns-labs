#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef char const *(*PTRFUN)(); // ptr do fja tipa char const*

struct Animal {
    char *name;
    PTRFUN *vtable; //ptr do ptr do fja tipa char const*
};

void animalPrintGreeting(struct Animal *p) {
    printf("%s pozdravlja: %s\n", p->name, (*(p->vtable[0]))());
}

void animalPrintMenu(struct Animal *p) {
    printf("%s voli %s\n", p->name, (*(p->vtable[1]))());
}

char const *dogGreet(void) {
    return "vau!";
}
char const *dogMenu(void) {
    return "kuhanu govedinu";
}
char const *catGreet(void) {
    return "mijau!";
}
char const *catMenu(void) {
    return "konzerviranu tunjevinu";
}

PTRFUN dogVTable[] = {dogGreet, dogMenu};
PTRFUN catVTable[] = {catGreet, catMenu};

void constructDog(struct Animal *animal, char *name) {
    animal->name = name;
    animal->vtable = dogVTable;
}

void constructCat(struct Animal *animal, char *name) {
    animal->name = name;
    animal->vtable = catVTable;
}

struct Animal *createDog(char *name) {
    struct Animal *dog = malloc(sizeof(struct Animal));
    constructDog(dog, name);
    return dog;
}

struct Animal *createCat(char *name) {
    struct Animal *cat = malloc(sizeof(struct Animal));
    constructCat(cat, name);
    return cat;
}

char *dogNames[] = {"Buddy", "Max", "Charlie", "Rocky", "Cooper", "Daisy", "Lucy", "Molly", "Sadie", "Lola"};

struct Animal* createNDogs(int n) {
    if (n > 10) {
        printf("Number of dogs must be 10 max.\n");
        return NULL;
    }

    struct Animal *dogs = malloc(n * sizeof(struct Animal));

    for (int i = 0; i < n; i++) {
        struct Animal *dog = &dogs[i];
        constructDog(dog, dogNames[i]);
    }

    return dogs;
}

void testAnimals(void) {
    // 1) na stogu:

    // struct Animal p1;
    // constructDog(&p1, "Hamlet");
    // animalPrintGreeting(&p1);
    // animalPrintMenu(&p1);

    // 2) na heapu:

    struct Animal *p1 = createDog("Hamlet");
    struct Animal *p2 = createCat("Ofelija");
    struct Animal *p3 = createDog("Polonije");

    animalPrintGreeting(p1);
    animalPrintGreeting(p2);
    animalPrintGreeting(p3);

    animalPrintMenu(p1);
    animalPrintMenu(p2);
    animalPrintMenu(p3);

    free(p1);
    free(p2);
    free(p3);

    printf("\n\n");

    int n = 5;
    struct Animal *dogs = createNDogs(n);
    for (int i = 0; i < n; i++) {
        animalPrintGreeting(&dogs[i]);
        animalPrintMenu(&dogs[i]);
    }
    free(dogs);
}

int main(void) {
    testAnimals();

    return 0;
}