#include "animal.hpp"
#include <string>
#include "myfactory.hpp"

class Parrot : public Animal {
public:
  Parrot(const std::string& name) : name_(name) {}

  char const* name() override {
    return name_.c_str();
  }

  char const* greet() override {
    return "Sto mu gromova!";
  }

  char const* menu() override {
    return "brazilske orahe";
  }

  static void* myCreator(const std::string& arg) {
    return new Parrot(arg);
  }

  static int hreg;

private:
  std::string name_;
};

int Parrot::hreg = myfactory::instance().registerCreator("parrot", Parrot::myCreator);