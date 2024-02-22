#include <stdio.h>
#include <stdlib.h>

typedef void *(*PTRFUN)();

typedef struct {
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
} Unary_Function;

// vtable: 0 - value_at, 1 - negative_value_at, 2 - tabulate
typedef double (*PTRFUN_UD)(Unary_Function *, double); // to shorten casting

double negative_value_at(Unary_Function *self, double x) {
    // return (*(PTRFUN_UD)obj->vtable[1])(obj, x);
    return -(*((PTRFUN_UD)self->vtable[0]))(self, x); // pointers store addresses, and then you can dereference address and call func
}

void tabulate(Unary_Function *self) {
    for (int x = self->lower_bound; x <= self->upper_bound; x++) {
        // printf("f(%d)=%lf\n", x, obj->value_at(x));
        printf("f(%d)=%lf\n", x, (*((PTRFUN_UD)self->vtable[0]))(self, x));
    }
}

int same_functions_for_ints(Unary_Function *f1, Unary_Function *f2, double tolerance) {
    if (f1->lower_bound != f2->lower_bound)
        return 0;
    if (f1->upper_bound != f2->upper_bound)
        return 0;
    for (int x = f1->lower_bound; x <= f1->upper_bound; x++) {
        double delta = (*((PTRFUN_UD)f1->vtable[0]))(f1, x) - (*((PTRFUN_UD)f2->vtable[0]))(f2, x);
        if (delta < 0)
            delta = -delta;
        if (delta > tolerance)
            return 0;
    }
    return 1;
}

PTRFUN UnaryVTable[] = {NULL, negative_value_at};

void constructUnaryFunction(Unary_Function *f, int lb, int ub) {
    f->lower_bound = lb;
    f->upper_bound = ub;
    f->vtable = UnaryVTable;
}

Unary_Function *createUnaryFunction(int lb, int ub) {
    Unary_Function *f = malloc(sizeof(Unary_Function));
    constructUnaryFunction(f, lb, ub);
    return f;
}

typedef struct {
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
} Square;

double square_value_at(Square *self, double x) {
    return x * x;
}

PTRFUN SquareVTable[] = {square_value_at, negative_value_at};

void constructSquare(Square *s, int lb, int ub) {
    s->vtable = SquareVTable;
    s->lower_bound = lb;
    s->upper_bound = ub;
}

Square *createSquare(int lb, int ub) {
    Square *square = malloc(sizeof(Square));
    constructSquare(square, lb, ub);
    return square;
}

typedef struct {
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
    double a;
    double b;
} Linear;

double linear_value_at(Linear *self, double x) {
    return (self->a) * x + (self->b);
}

PTRFUN LinearVTable[] = {linear_value_at, negative_value_at};

void constructLinear(Linear *l, int lb, int ub, double a, double b) {
    l->vtable = LinearVTable;
    l->lower_bound = lb;
    l->upper_bound = ub;
    l->a = a;
    l->b = b;
}

Linear *createLinear(int lb, int ub, double a, double b) {
    Linear *linear = malloc(sizeof(Linear));
    constructLinear(linear, lb, ub, a, b);
    return linear;
}

// TODO
int main() {
    // C++:
    //    Unary_Function *f1 = new Square(-2, 2);
    //    f1->tabulate();
    //    Unary_Function *f2 = new Linear(-2, 2, 5, -2);
    //    f2->tabulate();
    //    printf("f1==f2: %s\n", Unary_Function::same_functions_for_ints(f1, f2, 1E-6) ? "DA" : "NE");
    //    printf("neg_val f2(1) = %lf\n", f2->negative_value_at(1.0));
    //    delete f1;
    //    delete f2;

    Unary_Function *f1 = createSquare(-2, 2);
    tabulate(f1);

    Unary_Function *f2 = createLinear(-2, 2, 5, -2);
    tabulate(f2);

    printf("f1==f2: %s\n", same_functions_for_ints(f1, f2, 1E-6) ? "DA" : "NE");
    printf("neg_val f2(1) = %lf\n", (*((PTRFUN_UD)f2->vtable[1]))(f2, 1.0));

    free(f1);
    free(f2);

    return 0;
}
