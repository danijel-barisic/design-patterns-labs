#include <iostream>
#include <memory>

int main() {
    std::unique_ptr<int> ptr(new int(42));

    if (ptr) {
        std::cout << "Unique_ptr contains value: " << *ptr << std::endl;
    }

    std::unique_ptr<int> ptr2 = std::move(ptr);

    if (!ptr) {
        std::cout << "First unique_ptr is empty." << std::endl;
    }

    if (ptr2) {
        std::cout << "Value of second unique_ptr: " << *ptr2 << std::endl;
    }

    return 0;
}