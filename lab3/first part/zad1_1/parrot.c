#include <stdlib.h>
#include <string.h>
typedef char const* (*PTRFUN)();

struct Animal{
  PTRFUN* vtable;
};

struct Parrot {
    PTRFUN *vtable;
    char const* name;
};

void construct_parrot(void* memory, char const* name) {
    struct Parrot* parrot = (struct Parrot*)memory;
    parrot->name = strdup(name);
}

char const* name(void* this) {
    struct Parrot* parrot = (struct Parrot*)this;
    return parrot->name;
}

char const* greet() {
    return "cvrk!";
}

char const* menu() {
    return "sjeme";
}

PTRFUN ParrotVTable[3] = {
        name, greet, menu
};

void* create(char const* name) {
    size_t size = sizeof(struct Parrot);
    void* memory = malloc(size);
    construct_parrot(memory, name);
    
    struct Animal* animal = (struct Animal*) memory;
    animal->vtable = ParrotVTable;

    return animal;
}