#include "myfactory.hpp"
#include <sstream>
#include "animal.hpp"
#include <iostream>


void printGreeting(Animal* animal){
  std::cout << animal->name() << " pozdravlja: " << animal->greet() << std::endl;
}

void printMenu(Animal* animal){
  std::cout << animal->name() << " voli " << animal->menu() << std::endl;
}

int main(void){
  myfactory& fact(myfactory::instance());
  std::vector<std::string> vecIds=fact.getIds();
  for (int i=0; i<vecIds.size(); ++i){
    std::ostringstream oss;
    oss <<"Ljubimac " <<i;
    Animal* pa=(Animal*) fact.create(vecIds[i], oss.str());
    printGreeting(pa);
    printMenu(pa);
    delete pa; 
  }
}