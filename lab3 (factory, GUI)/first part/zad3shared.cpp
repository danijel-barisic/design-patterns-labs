#include <iostream>
#include <memory>

int main() {
    std::shared_ptr<int> ptr(new int(42));

    std::cout << "Number of references: " << ptr.use_count() << std::endl;

    std::shared_ptr<int> ptr2 = ptr;

    std::cout << "Number of references: " << ptr.use_count() << std::endl;

    *ptr2 = 99;

    std::cout << "First value: " << *ptr << std::endl;

    return 0;
}