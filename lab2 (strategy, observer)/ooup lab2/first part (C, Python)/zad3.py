def mymax(iterable, key=lambda x: x):
    max_x = max_key = None

    for x in iterable:
        if max_x is None or key(x) > max_key:
            max_x = x
            max_key = key(x)

    return max_x


maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0])
print("Max int:", maxint)

maxchar = mymax("Suncana strana ulice")
print("Max char:", maxchar)

maxstring = mymax([
    "Gle", "malu", "vocku", "poslije", "kise",
    "Puna", "je", "kapi", "pa", "ih", "njise"])
print("Max string:", maxstring)

D = {'burek': 8, 'buhtla': 5}
max_price = mymax(D, key=D.get)
print(max_price)

people = [("Bernard", "Bernardić"), ("Pero", "Perić"), ("Iva", "Ivić")]
last_person = mymax(people)
print(last_person) 