#include <iostream>
#include <memory>

int main() {
    std::shared_ptr<int> sharedPtr(new int(42));

    std::weak_ptr<int> weakPtr(sharedPtr);

    if (auto lockedPtr = weakPtr.lock()) {
        std::cout << "Value of weak_ptr: " << *lockedPtr << std::endl;
    } else {
        std::cout << "weak_ptr is not valid." << std::endl;
    }

    sharedPtr.reset();

    if (auto lockedPtr = weakPtr.lock()) {
        std::cout << "Value of weak_ptr: " << *lockedPtr << std::endl;
    } else {
        std::cout << "weak_ptr is not valid." << std::endl;
    }

    return 0;
}