#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef char const* (*PTRFUN)();

struct Animal{
  PTRFUN* vtable;
};

struct Tiger {
    PTRFUN *vtable;
    char const* name;
};

void construct_tiger(void* memory, char const* name) {
    struct Tiger* tiger = (struct Tiger*)memory;
    tiger->name = strdup(name);
}

char const* name(void* this) {
    struct Tiger* tiger = (struct Tiger*)this;
    return tiger->name;
}

char const* greet() {
    return "rawr!";
}

char const* menu() {
    return "meso";
}

PTRFUN TigerVTable[3] = {
        name, greet, menu
};

void* create(char const* name) {
    size_t size = sizeof(struct Tiger);
    void* memory = malloc(size);
    construct_tiger(memory, name);
    
    struct Animal* animal = (struct Animal*) memory;

    animal->vtable = TigerVTable;

    return animal;
}
