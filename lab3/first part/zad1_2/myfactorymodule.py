import importlib


def myfactory(module_name):
    module = importlib.import_module('plugins.' + module_name)
    pet_class = getattr(module, module_name.capitalize())
    return pet_class
