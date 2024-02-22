#include <assert.h>
#include <iostream>
#include <stdlib.h>

struct Point {
    int x;
    int y;
};
struct Shape {
    enum EType { circle,
                 square,
                 rhomb };
    EType type_;
};
struct Circle {
    Shape::EType type_;
    double radius_;
    Point center_;
};
struct Square {
    Shape::EType type_;
    double side_;
    Point center_;
};

struct Rhomb {
    Shape::EType type_;
    double e_diag_;
    double f_diag_;
    Point center_;
};

void drawSquare(struct Square *) {
    std::cerr << "in drawSquare\n";
}
void drawCircle(struct Circle *) {
    std::cerr << "in drawCircle\n";
}
void drawRhomb(struct Rhomb *) {
    std::cerr << "in drawRhomb\n";
}
void drawShapes(Shape **shapes, int n) {
    for (int i = 0; i < n; ++i) {
        struct Shape *s = shapes[i];
        switch (s->type_) {
        case Shape::square:
            drawSquare((struct Square *)s);
            break;
        case Shape::circle:
            drawCircle((struct Circle *)s);
            break;
        case Shape::rhomb:
            drawRhomb((struct Rhomb *)s);
            break;
        default:
            assert(0);
            exit(0);
        }
    }
}

void moveSquare(struct Square *s, int x_translate, int y_translate) {
    std::cerr << "in moveSquare\n";
    printf("(%d, %d) -> (%d, %d)\n", s->center_.x, s->center_.y, s->center_.x + x_translate, s->center_.y + y_translate);
    s->center_.x += x_translate;
    s->center_.y += y_translate;
}

void moveCircle(struct Circle *c, int x_translate, int y_translate) {
    std::cerr << "in moveCircle\n";
    printf("(%d, %d) -> (%d, %d)\n", c->center_.x, c->center_.y, c->center_.x + x_translate, c->center_.y + y_translate);
    c->center_.x += x_translate;
    c->center_.y += y_translate;
}

void moveRhomb(struct Rhomb *r, int x_translate, int y_translate) {
    std::cerr << "in moveRhomb\n";
    printf("(%d, %d) -> (%d, %d)\n", r->center_.x, r->center_.y, r->center_.x + x_translate, r->center_.y + y_translate);
    r->center_.x += x_translate;
    r->center_.y += y_translate;
}

void moveShapes(Shape **shapes, int n, int x_translate, int y_translate) {
    for (int i = 0; i < n; ++i) {
        struct Shape *s = shapes[i];
        switch (s->type_) {
        case Shape::square:
            moveSquare((struct Square *)s, x_translate, y_translate);
            break;
        case Shape::circle:
            moveCircle((struct Circle *)s, x_translate, y_translate);
            break;
      //   case Shape::rhomb:
      //       moveRhomb((struct Rhomb *)s, x_translate, y_translate);
      //       break;
        default:
            assert(0);
            exit(0);
        }
    }
}

int main() {
    Shape *shapes[4];
    shapes[0] = (Shape *)new Circle{Shape::circle, 1., Point{1, 1}};
    //  shapes[0]->type_ = Shape::circle;
    shapes[1] = (Shape *)new Square{Shape::square, 1., Point{0, 0}};
    //  shapes[1]->type_ = Shape::square;
    shapes[2] = (Shape *)new Square{Shape::square, 3., Point{2, 3}};
    //  shapes[2]->type_ = Shape::square;
    shapes[3] = (Shape *)new Circle{Shape::circle, 2.5, Point{2, 3}};
    //  sshapes[3]->type_ = Shape::circle;
    shapes[4] = (Shape *)new Rhomb{Shape::rhomb, 2.,2., Point{2, 3}};

    drawShapes(shapes, 5);

    moveShapes(shapes, 5, 1, 1);
}