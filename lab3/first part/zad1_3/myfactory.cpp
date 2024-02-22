#include "myfactory.hpp"

myfactory& myfactory::instance() {
  static myfactory theFactory;
  return theFactory;
}

int myfactory::registerCreator(const std::string& id, pFunCreator pfn) {
  creators_[id] = pfn;
  return 0;
}

void* myfactory::create(const std::string& id, const std::string& arg) {
  if (creators_.count(id) != 0) {
    pFunCreator creator = creators_[id];
    return creator(arg);
  }
  return nullptr;
}

std::vector<std::string> myfactory::getIds() {
  std::vector<std::string> ids;
  for (const auto& entry : creators_) {
    ids.push_back(entry.first);
  }
  return ids;
}

myfactory::myfactory() {}

myfactory::~myfactory() {}