#include "myfactory.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

typedef char const* (*PTRFUN)();

struct Animal {
    PTRFUN* vtable;
};

void* myfactory(char const* libname, char const* ctorarg) {
    HMODULE module = LoadLibrary(libname);
    void* create = GetProcAddress(module, "create");
    struct Animal* animal = ((void* (*)(char const*))create)(ctorarg);

    return animal;
}
