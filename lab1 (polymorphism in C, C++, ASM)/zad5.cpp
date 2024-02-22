#include <stdio.h>

class B {
public:
    virtual int __cdecl prva() = 0;
    virtual int __cdecl druga(int) = 0;
};

class D : public B {
public:
    virtual int __cdecl prva() { return 42; }
    virtual int __cdecl druga(int x) { return prva() + x; }
};

void call_virtual_funcs(B *pb) {
    void **vtable_ptr = *(void ***)pb;

    int (*prva_ptr)() = (int (*)())vtable_ptr[0];
    int (*druga_ptr)(B *, int) = (int (*)(B *, int))vtable_ptr[1];

    printf("Prva: %d\n", prva_ptr());
    printf("Druga: %d\n", druga_ptr(pb, 3));
}

int main() {
    D d;
    call_virtual_funcs(&d);
    return 0;
}
