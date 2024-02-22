#include "animal.hpp"
#include <string>
#include "myfactory.hpp"

class Tiger : public Animal {
public:
  Tiger(const std::string& name) : name_(name) {}

  char const* name() override {
    return name_.c_str();
  }

  char const* greet() override {
    return "Mijau!";
  }

  char const* menu() override {
    return "mlako mlijeko";
  }

  static void* myCreator(const std::string& arg) {
    return new Tiger(arg);
  }

  static int hreg;

private:
  std::string name_;
};

int Tiger::hreg = myfactory::instance().registerCreator("tiger", Tiger::myCreator);
