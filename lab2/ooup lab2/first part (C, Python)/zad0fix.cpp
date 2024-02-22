#include <assert.h>
#include <iostream>
#include <list>
#include <stdlib.h>

using namespace std;

class Point {
public:
    int x;
    int y;
    Point(int x = 0, int y = 0) : x(x), y(y) {
    }
};

class Shape {
public:
    virtual void draw() = 0;
    virtual void move(int, int) = 0;

protected:
    Point center_;
};

class Circle : public Shape {

public:
    double radius_;

    Circle(double radius, int x, int y) : radius_(radius) {
        center_ = Point(x, y);
    }

    virtual void draw() {
        std::cerr << "in drawCircle\n";
    }
    virtual void move(int x_translate, int y_translate) {
        std::cerr << "in moveCircle\n";
        printf("(%d, %d) -> (%d, %d)\n", center_.x, center_.y, center_.x + x_translate, center_.y + y_translate);
        center_.x += x_translate;
        center_.y += y_translate;
    }
};
class Square : public Shape {
public:
    double side_;

    Square(double side, int x, int y) : side_(side) {
        center_ = Point(x, y);
    }

    virtual void draw() {
        std::cerr << "in drawSquare\n";
    }
    virtual void move(int x_translate, int y_translate) {
        std::cerr << "in moveSquare\n";
        printf("(%d, %d) -> (%d, %d)\n", center_.x, center_.y, center_.x + x_translate, center_.y + y_translate);
        center_.x += x_translate;
        center_.y += y_translate;
    }
};

class Rhomb : public Shape {
public:
    double e_diag_;
    double f_diag_;

    Rhomb(double e_diag, double f_diag, int x, int y) : e_diag_(e_diag), f_diag_(f_diag) {
        center_ = Point(x, y);
    }
    virtual void draw() {
        std::cerr << "in drawRhomb\n";
    }
    virtual void move(int x_translate, int y_translate) {
        std::cerr << "in moveRhomb\n";
        printf("(%d, %d) -> (%d, %d)\n", center_.x, center_.y, center_.x + x_translate, center_.y + y_translate);
        center_.x += x_translate;
        center_.y += y_translate;
    }
};

void drawShapes(const std::list<Shape *> &fig) {
    std::list<Shape *>::const_iterator it;
    for (it = fig.begin(); it != fig.end(); ++it) {
        (*it)->draw();
    }
}

void moveShapes(const std::list<Shape *> &fig, int x_translate, int y_translate) {
    std::list<Shape *>::const_iterator it;
    for (it = fig.begin(); it != fig.end(); ++it) {
        (*it)->move(x_translate, y_translate);
    }
}

int main(void) {
    std::list<Shape *> shapes;
    shapes.push_back(new Square(2.0, 1, 1));
    shapes.push_back(new Circle(1.0, 2, 1));
    shapes.push_back(new Circle(2.0, 1, 2));
    shapes.push_back(new Square(2.0, 3, 3));
    shapes.push_back(new Rhomb(2.0, 3.0, 0, 0));

    drawShapes(shapes);
    moveShapes(shapes, 1, 1);

    return 0;
}
